import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class MyParser {

  public Set<PersonInfo> people;
  private PersonInfo personInfo;

  public Set<String> attr = new HashSet<>();
  public Set<String> attrVal = new HashSet<>();
  public int allTimes = 0;
  public int countAttr = 0;

  private void getAttr(XMLStreamReader reader) {
    allTimes++;
    for (int i = 0; i < reader.getAttributeCount(); i++) {
      attr.add(reader.getAttributeLocalName(i));
      countAttr++;
    }
  }

  private void checkValues(XMLStreamReader reader) {
    for (int i = 0; i < reader.getAttributeCount(); i++) {
      attrVal.add(reader.getAttributeValue(i));
    }
  }

  public Set<PersonInfo> parse(InputStream stream) throws XMLStreamException {
    XMLInputFactory streamFactory = XMLInputFactory.newInstance();
    XMLStreamReader reader = streamFactory.createXMLStreamReader(stream);

    for (; reader.hasNext(); reader.next()) {
      int eventType = reader.getEventType();

      if (eventType == XMLStreamConstants.START_ELEMENT) {
        switch (reader.getLocalName()) {

          case ("people") -> {
            var strN = reader.getAttributeLocalName(0);
            if (strN.equals("count")) {
              var n = Integer.parseInt(reader.getAttributeValue(0));
              people = new HashSet<>(n);
            } else {
              System.out.println("There's no count");
            }
          }
          case ("person") -> {
            personInfo = new PersonInfo();
            for (int i = 0; i < reader.getAttributeCount(); i++) {
              switch (reader.getAttributeLocalName(i)) {
                case ("name") -> {
                  String[] name = reader.getAttributeValue(i).trim().split("\\s+");
                  personInfo.firstname = name[0];
                  personInfo.surname = name[1];
                }
                case ("id") -> personInfo.id = reader.getAttributeValue(i).trim();
                default -> System.out.println("Unknown attribute in person");
              }
            }
          }
          case ("id") -> {
            if (reader.getAttributeLocalName(0).equals("value")) {
              personInfo.id = reader.getAttributeValue(0).trim();
            } else {
              System.out.println("Unknown attribute in id");
            }
          }
          case ("firstname") -> {
            if (reader.getAttributeCount() > 0) {
              if (reader.getAttributeLocalName(0).equals("value")) {
                personInfo.firstname = reader.getAttributeValue(0).trim();
              } else {
                System.out.println("Unknown attribute in firstname");
              }
            } else {
              reader.next();
              personInfo.firstname = reader.getText().trim();
            }
          }
          case ("surname") -> {
            if (reader.getAttributeLocalName(0).equals("value")) {
              personInfo.surname = reader.getAttributeValue(0).trim();
            } else {
              System.out.println("Unknown attribute in surname");
            }
          }
          case ("fullname"), ("children") -> {
          }
          case ("first") -> {
            reader.next();
            personInfo.firstname = reader.getText().trim();
          }
          case ("family"), ("family-name") -> {
            reader.next();
            personInfo.surname = reader.getText().trim();
          }
          case ("gender") -> {
            if (reader.getAttributeCount() > 0) {
              if (reader.getAttributeLocalName(0).equals("value")) {
                personInfo.gender = reader.getAttributeValue(0).trim().toUpperCase()
                    .substring(0, 1);
              } else {
                System.out.println("Unknown attribute in gender");
              }
            } else {
              reader.next();
              personInfo.gender = reader.getText().trim().toUpperCase().substring(0, 1);
            }
          }
          case ("spouce") -> {
            if (reader.getAttributeCount() > 0) {
              if (reader.getAttributeLocalName(0).equals("value")) {
                if (!reader.getAttributeValue(0).trim().equals("NONE")) {
                  personInfo.spouseName = reader.getAttributeValue(0);
                }
              } else {
                System.out.println("Unknown attribute in spouce");
              }
            }
          }
          case ("husband") -> {
            if (reader.getAttributeLocalName(0).equals("value")) {
              personInfo.husbandId = reader.getAttributeValue(0).trim();
            } else {
              System.out.println("Unknown attribute in husband");
            }
          }
          case ("wife") -> {
            if (reader.getAttributeLocalName(0).equals("value")) {
              personInfo.wifeId = reader.getAttributeValue(0).trim();
            } else {
              System.out.println("Unknown attribute in wife");
            }
          }
          case ("siblings") -> {
            if (reader.getAttributeCount() > 0 && reader.getAttributeLocalName(0).equals("val")) {
              String[] siblings = reader.getAttributeValue(0).split("\\s+");
              personInfo.siblingsID.addAll(List.of(siblings));
            }
          }
          case ("brother") -> {
            reader.next();
            var brotherName = reader.getText().split("\\s+");
            personInfo.brothersID.add(brotherName[0] + " " + brotherName[1]);
          }
          case ("sister") -> {
            reader.next();
            var sisterName = reader.getText().split("\\s+");
            personInfo.sistersID.add(sisterName[0] + " " + sisterName[1]);
          }
          case ("siblings-number") -> {
            if (reader.getAttributeLocalName(0).equals("value")) {
              personInfo.siblingsCount = Integer.parseInt(reader.getAttributeValue(0).trim());
            } else {
              System.out.println("Unknown attribute in siblings-number");
            }
          }
          case ("child") -> {
            reader.next();
            var childName = reader.getText().split("\\s+");
            personInfo.childrenNames.add(childName[0] + " " + childName[1]);
          }
          case ("son") -> {
            if (reader.getAttributeLocalName(0).equals("id")) {
              personInfo.sonsID.add(reader.getAttributeValue(0).trim());
            } else {
              System.out.println("Unknown attribute in son");
            }
          }
          case ("daughter") -> {
            if (reader.getAttributeLocalName(0).equals("id")) {
              personInfo.daughtersID.add(reader.getAttributeValue(0).trim());
            } else {
              System.out.println("Unknown attribute in daughter");
            }
          }
          case ("parent") -> {
            if (reader.getAttributeCount() > 0 && reader.getAttributeLocalName(0).equals("value")) {
              if (!reader.getAttributeValue(0).trim().equals("UNKNOWN")) {
                personInfo.parentsID.add(reader.getAttributeValue(0).trim());
              }
            }
          }
          case ("father") -> {
            reader.next();
            var fatherName = reader.getText().split("\\s+");
            personInfo.fatherName = fatherName[0] + " " + fatherName[1];
          }
          case ("mother") -> {
            reader.next();
            var motherName = reader.getText().split("\\s+");
            personInfo.motherName = motherName[0] + " " + motherName[1];
          }
          case ("children-number") -> {
            if (reader.getAttributeLocalName(0).equals("value")) {
              personInfo.childrenCount = Integer.parseInt(reader.getAttributeValue(0));
            } else {
              System.out.println("Unknown attribute in children-number");
            }
          }
        }
      } else if (eventType == XMLStreamConstants.END_ELEMENT) {
        if (reader.getLocalName().equals("person")) {
          people.add(personInfo);
          personInfo = null;
        }
      }
    }
    System.out.println(attr);
    System.out.println(allTimes);
    System.out.println(countAttr);
    System.out.println(attrVal);
    return people;
  }
}