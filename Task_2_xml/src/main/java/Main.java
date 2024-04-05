import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.stream.XMLStreamException;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import org.xml.sax.SAXException;

public class Main {

  public static void main(String[] args) throws JAXBException, SAXException {
    People people = new People();
    try (InputStream stream = new FileInputStream("src/main/resources/people.xml")) {
      people.people = new MyParser().parse(stream);
    } catch (IOException | XMLStreamException e) {
      System.out.println(e.getMessage());
    }
    JAXBContext context = JAXBContext.newInstance(People.class.getName());

    SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
    Schema schema = schemaFactory.newSchema(new File("src/main/resources/schema.xsd"));

    Marshaller marshaller = context.createMarshaller();
    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
    marshaller.setSchema(schema);

    marshaller.marshal(people, new File("src/main/resources/output.xml"));
  }
}