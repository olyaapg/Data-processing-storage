import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.stream.XMLStreamException;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import classes.*;
import org.xml.sax.SAXException;

public class Main {

    public static void main(String[] args) throws JAXBException, SAXException {
        List<PersonInfo> personInfos = null;
        try (InputStream stream = new FileInputStream("src/main/resources/people.xml")) {
            personInfos = new MyParser().parse(stream);
        } catch (IOException | XMLStreamException e) {
            System.out.println(e.getMessage());
        }

        People people = new People();
        for (PersonInfo personInfo : Objects.requireNonNull(personInfos)) {
            PersonType person = new PersonType();

            person.setId(personInfo.id);
            person.setName(personInfo.firstname + " " + personInfo.surname);
            person.setGender(GenderType.valueOf(personInfo.gender));
            if (personInfo.spouseId != null) {
                person.getSpouse().setId(personInfo.spouseId);
            }

            if (personInfo.childrenCount != 0) {
                PersonType.Children children = getChildren(personInfo);
                person.setChildren(children);
            }

            if (personInfo.parentsID != null) {
                for (String s : personInfo.parentsID) {
                    var id = new IdType();
                    id.setId(s);
                    person.getParents().getParentId().add(id);
                }
            }

            if (personInfo.siblingsCount != 0) {
                for (String s : personInfo.siblingsID) {
                    var id = new IdType();
                    id.setId(s);
                    person.getSiblings().getSiblingId().add(id);
                }
                person.getSiblings().setCount(personInfo.siblingsCount);
            }

            people.getPerson().add(person);
        }

        JAXBContext context = JAXBContext.newInstance(People.class.getPackage().getName());

        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = schemaFactory.newSchema(new File("src/main/resources/schema.xsd"));

        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setSchema(schema);

        marshaller.marshal(people, new File("src/main/resources/output.xml"));
    }

    private static PersonType.Children getChildren(PersonInfo personInfo) {
        PersonType.Children children = new PersonType.Children();

        SonsType sons = new SonsType();
        for (String s : personInfo.sonsID) {
            var id = new IdType();
            id.setId(s);
            sons.getSonId().add(id);
        }
        children.setSons(sons);

        DaughtersType daughters = new DaughtersType();
        for (String s : personInfo.daughtersID) {
            var id = new IdType();
            id.setId(s);
            daughters.getDaughterId().add(id);
        }
        children.setDaughters(daughters);
        children.setCount(personInfo.childrenCount);
        return children;
    }
}