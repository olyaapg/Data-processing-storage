import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import javax.xml.stream.XMLStreamException;

public class Main {

  public static void main(String[] args) {
    try (InputStream stream = new FileInputStream("src/resources/people.xml")) {
      ArrayList<PersonInfo> data = new MyParser().parse(stream);

      for (int i = 0; i < 15; i++) {
        System.out.println(data.get(i).toString());
      }
    } catch (IOException | XMLStreamException e) {
      System.out.println(e.getMessage());
    }
  }
}