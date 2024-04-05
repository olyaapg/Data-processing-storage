import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class MyParser {

  public ArrayList<PersonInfo> parse(InputStream stream) throws XMLStreamException {
    XMLInputFactory streamFactory = XMLInputFactory.newInstance();
    XMLStreamReader reader = streamFactory.createXMLStreamReader(stream);

    PersonInfo personInfo = null;
    ArrayList<PersonInfo> people = null;
    int nPeople = 0;

    for (; reader.hasNext(); reader.next()) {
      int eventType = reader.getEventType();

      if (eventType == XMLStreamConstants.START_ELEMENT) {
        switch (reader.getLocalName()) {

          case ("people") -> {
            var strN = reader.getAttributeLocalName(0);
            if (strN.equals("count")) {
              nPeople = Integer.parseInt(reader.getAttributeValue(0));
              people = new ArrayList<>();
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
              assert personInfo != null;
              personInfo.id = reader.getAttributeValue(0).trim();
            } else {
              System.out.println("Unknown attribute in id");
            }
          }
          case ("firstname") -> {
            if (reader.getAttributeCount() > 0) {
              if (reader.getAttributeLocalName(0).equals("value")) {
                assert personInfo != null;
                personInfo.firstname = reader.getAttributeValue(0).trim();
              } else {
                System.out.println("Unknown attribute in firstname");
              }
            } else {
              reader.next();
              assert personInfo != null;
              personInfo.firstname = reader.getText().trim();
            }
          }
          case ("surname") -> {
            if (reader.getAttributeLocalName(0).equals("value")) {
              assert personInfo != null;
              personInfo.surname = reader.getAttributeValue(0).trim();
            } else {
              System.out.println("Unknown attribute in surname");
            }
          }
          case ("fullname"), ("children") -> {
          }
          case ("first") -> {
            reader.next();
            assert personInfo != null;
            personInfo.firstname = reader.getText().trim();
          }
          case ("family"), ("family-name") -> {
            reader.next();
            assert personInfo != null;
            personInfo.surname = reader.getText().trim();
          }
          case ("gender") -> {
            if (reader.getAttributeCount() > 0) {
              if (reader.getAttributeLocalName(0).equals("value")) {
                assert personInfo != null;
                personInfo.gender = reader.getAttributeValue(0).trim().toUpperCase()
                    .substring(0, 1);
              } else {
                System.out.println("Unknown attribute in gender");
              }
            } else {
              reader.next();
              assert personInfo != null;
              personInfo.gender = reader.getText().trim().toUpperCase().substring(0, 1);
            }
          }
          case ("spouce") -> {
            if (reader.getAttributeCount() > 0) {
              if (reader.getAttributeLocalName(0).equals("value")) {
                if (!reader.getAttributeValue(0).trim().equals("NONE")) {
                  assert personInfo != null;
                  personInfo.spouseName = reader.getAttributeValue(0);
                }
              } else {
                System.out.println("Unknown attribute in spouce");
              }
            }
          }
          case ("husband") -> {
            if (reader.getAttributeLocalName(0).equals("value")) {
              assert personInfo != null;
              personInfo.husbandId = reader.getAttributeValue(0).trim();
            } else {
              System.out.println("Unknown attribute in husband");
            }
          }
          case ("wife") -> {
            if (reader.getAttributeLocalName(0).equals("value")) {
              assert personInfo != null;
              personInfo.wifeId = reader.getAttributeValue(0).trim();
            } else {
              System.out.println("Unknown attribute in wife");
            }
          }
          case ("siblings") -> {
            if (reader.getAttributeCount() > 0 && reader.getAttributeLocalName(0).equals("val")) {
              String[] siblings = reader.getAttributeValue(0).split("\\s+");
              assert personInfo != null;
              personInfo.siblingsID.addAll(List.of(siblings));
            }
          }
          case ("brother") -> {
            reader.next();
            var brotherName = reader.getText().split("\\s+");
            assert personInfo != null;
            personInfo.brothersNames.add(brotherName[0] + " " + brotherName[1]);
          }
          case ("sister") -> {
            reader.next();
            var sisterName = reader.getText().split("\\s+");
            assert personInfo != null;
            personInfo.sistersNames.add(sisterName[0] + " " + sisterName[1]);
          }
          case ("siblings-number") -> {
            if (reader.getAttributeLocalName(0).equals("value")) {
              assert personInfo != null;
              personInfo.siblingsCount = Integer.parseInt(reader.getAttributeValue(0).trim());
            } else {
              System.out.println("Unknown attribute in siblings-number");
            }
          }
          case ("child") -> {
            reader.next();
            var childName = reader.getText().split("\\s+");
            assert personInfo != null;
            personInfo.childrenNames.add(childName[0] + " " + childName[1]);
          }
          case ("son") -> {
            if (reader.getAttributeLocalName(0).equals("id")) {
              assert personInfo != null;
              personInfo.sonsID.add(reader.getAttributeValue(0).trim());
            } else {
              System.out.println("Unknown attribute in son");
            }
          }
          case ("daughter") -> {
            if (reader.getAttributeLocalName(0).equals("id")) {
              assert personInfo != null;
              personInfo.daughtersID.add(reader.getAttributeValue(0).trim());
            } else {
              System.out.println("Unknown attribute in daughter");
            }
          }
          case ("parent") -> {
            assert personInfo != null;
            if (reader.getAttributeCount() > 0 && reader.getAttributeLocalName(0).equals("value")) {
              if (!reader.getAttributeValue(0).trim().equals("UNKNOWN")) {
                personInfo.parentsID.add(reader.getAttributeValue(0).trim());
              }
            }
          }
          case ("father") -> {
            reader.next();
            var fatherName = reader.getText().split("\\s+");
            assert personInfo != null;
            personInfo.fatherName = fatherName[0] + " " + fatherName[1];
          }
          case ("mother") -> {
            reader.next();
            var motherName = reader.getText().split("\\s+");
            assert personInfo != null;
            personInfo.motherName = motherName[0] + " " + motherName[1];
          }
          case ("children-number") -> {
            if (reader.getAttributeLocalName(0).equals("value")) {
              assert personInfo != null;
              personInfo.childrenCount = Integer.parseInt(reader.getAttributeValue(0));
            } else {
              System.out.println("Unknown attribute in children-number");
            }
          }
        }
      } else if (eventType == XMLStreamConstants.END_ELEMENT) {
        if (reader.getLocalName().equals("person")) {
          assert people != null;
          people.add(personInfo);
          personInfo = null;
        }
      }
    }
    reader.close();
    assert people != null;
    return putInOrder(people, nPeople);
  }

  private ArrayList<PersonInfo> putInOrder(ArrayList<PersonInfo> people, int nPeople) {
    System.out.println("Putting in order");

    Map<String, PersonInfo> peopleWithID = new HashMap<>();
    ArrayList<PersonInfo> rest = new ArrayList<>();

    for (PersonInfo person : people) {
      if (person.id != null) {
        if (peopleWithID.containsKey(person.id)) {
          peopleWithID.get(person.id).merge(person);
        } else {
          peopleWithID.put(person.id, person);
        }
      } else {
        rest.add(person);
      }
    }

    assert peopleWithID.size() == nPeople;
    Predicate<PersonInfo> nonNullName = person -> person.firstname != null
        && person.surname != null;
    assert peopleWithID.values().parallelStream().allMatch(nonNullName);
    assert rest.parallelStream().allMatch(nonNullName);

    for (PersonInfo person : rest) {
      Predicate<PersonInfo> nameEquals = person2 -> person2.firstname.equals(person.firstname)
          && person2.surname.equals(person.surname);
      Map<String, PersonInfo> similarPeople = findSimilar(nameEquals, peopleWithID);

      if (!similarPeople.isEmpty()) {
        for (PersonInfo somePerson : similarPeople.values()) {
          person.merge(somePerson);
        }
        for (String key : similarPeople.keySet()) {
          peopleWithID.put(key, person);
        }
      } else {
        System.out.println(
            "couldn't find the id for the person" + person.firstname + " " + person.surname);
      }
    }

    Validating validator = new Validating(peopleWithID);
    validator.check(this);

    System.out.println(peopleWithID.get("P403398"));
    return new ArrayList<>(peopleWithID.values());
  }

  public Map<String, PersonInfo> findSimilar(Predicate<PersonInfo> predicate,
      Map<String, PersonInfo> people) {
    return people.entrySet().
        parallelStream().
        filter(entry -> predicate.test(entry.getValue())).
        collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
  }
}