import javax.xml.stream.XMLStreamException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


public class Main {
    private static void writePeopleToDB(int nThreads, String xmlFilePath) {
        try (InputStream stream = new FileInputStream(xmlFilePath)) {
            new MyParser(nThreads).parse(stream);
        } catch (IOException | XMLStreamException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        String xmlFilePath = "src/main/resources/output.xml";
        if (args.length == 0) {
            return;
        }
        int nThreads = Integer.parseInt(args[0]);
        writePeopleToDB(nThreads, xmlFilePath);
    }
}