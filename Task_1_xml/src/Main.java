import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;
import javax.xml.stream.XMLStreamException;

public class Main {

  public static void main(String[] args) {
    try (InputStream stream = new FileInputStream("src/resources/people.xml")) {
      Set<PersonInfo> data = new MyParser().parse(stream);

      //data.subList(1, 100).forEach(System.out::println);
    } catch (IOException | XMLStreamException e) {
      System.out.println(e.getMessage());
    }
  }
}