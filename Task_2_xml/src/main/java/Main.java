import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
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

    public static void main(String[] args) {
        Map<String, PersonInfo> data = null;
        try (InputStream stream = new FileInputStream("src/main/resources/people.xml")) {
            data = new MyParser().parse(stream);
        } catch (IOException | XMLStreamException e) {
            System.out.println(e.getMessage());
        }

        People people = new People();
        var table = new HashMap<String, PersonType>();
        for (var p : Objects.requireNonNull(data).values()) {
            PersonType person = new PersonType();

            person.setId(p.id);
            person.setName(p.firstname + " " + p.surname);
            person.setGender(GenderType.valueOf(p.gender));
            table.put(p.id, person);
        }

        for (var k : data.keySet()) {
            if (!table.containsKey(k)) {
                PersonType person = new PersonType();
                PersonInfo p = data.get(k);

                person.setId(p.id);
                person.setName(p.firstname + " " + p.surname);
                person.setGender(GenderType.valueOf(p.gender));
                table.put(p.id, person);
                table.put(k, person);
            }
        }

        for (var p : table.values()) {
            var person = data.get(p.getId());

            if (person.spouseId != null) {
                IdType id = new IdType();
                id.setId(table.get(person.spouseId));
                p.setSpouse(id);
            }

            var children = new PersonType.Children();
            children.setCount(person.childrenCount);

            SonsType sons = new SonsType();
            for (var i : person.sonsID) {
                var id = new IdType();
                id.setId(table.get(i));
                sons.getSonId().add(id);
            }
            children.setSons(sons);

            var daughters = new DaughtersType();
            for (var i : person.daughtersID) {
                var id = new IdType();
                id.setId(table.get(i));
                daughters.getDaughterId().add(id);
            }
            children.setDaughters(daughters);

            p.setChildren(children);

            var siblings = new SiblingsType();
            siblings.setCount(person.siblingsCount);
            for (var i : person.siblingsID) {
                var id = new IdType();
                id.setId(table.get(i));
                siblings.getSiblingId().add(id);
            }
            p.setSiblings(siblings);

            var parents = new ParentsType();
            parents.getParentId();
            for (var i : person.parentsID) {
                var id = new IdType();
                id.setId(table.get(i));
                parents.getParentId().add(id);
            }
            p.setParents(parents);
        }
        people.getPerson().addAll(table.values());
        people.setCount(table.size());

        try {
            var classLoader = People.class.getClassLoader();
            JAXBContext context = JAXBContext.newInstance("classes", classLoader);

            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(new File("src/main/resources/schema.xsd"));

            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setSchema(schema);

            marshaller.marshal(people, new File("src/main/resources/output.xml"));
        } catch (JAXBException | SAXException e) {
            System.out.println(e.getMessage());
        }
    }
}