import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MyParser {
    private final int nThreads;

    public MyParser(int nThreads) {
        this.nThreads = nThreads;
    }

    public void parse(InputStream stream) throws XMLStreamException {
        XMLInputFactory streamFactory = XMLInputFactory.newInstance();
        XMLStreamReader reader = streamFactory.createXMLStreamReader(stream);

        PersonInfo personInfo = null;
        List<PersonInfo> people = null;
        ExecutorService executorService = null;
        int segmentLength = 0;

        for (; reader.hasNext(); reader.next()) {
            int eventType = reader.getEventType();

            if (eventType == XMLStreamConstants.START_ELEMENT) {
                switch (reader.getLocalName()) {

                    case ("people") -> {
                        var strN = reader.getAttributeLocalName(0);
                        if (strN.equals("count")) {
                            int nPeople = Integer.parseInt(reader.getAttributeValue(0));
                            segmentLength = nPeople / nThreads;
                            if (nPeople % nThreads != 0) {
                                segmentLength++;
                            }
                            executorService = Executors.newFixedThreadPool(nThreads);
                            people = new ArrayList<>();
                        } else {
                            System.out.println("There's no count");
                        }
                    }
                    case ("person") -> {
                        personInfo = new PersonInfo();
                        for (int i = 0; i < reader.getAttributeCount(); i++) {
                            switch (reader.getAttributeLocalName(i)) {
                                case ("id") -> personInfo.id = reader.getAttributeValue(i);
                                case ("gender") -> personInfo.gender = reader.getAttributeValue(i);
                                case ("name") -> personInfo.name = reader.getAttributeValue(i);
                                default -> System.out.println("Unknown attribute in person");
                            }
                        }
                    }
                    case ("spouse") -> {
                        if (reader.getAttributeCount() > 0 && reader.getAttributeLocalName(0).equals("id")) {
                            assert personInfo != null;
                            personInfo.spouseId = reader.getAttributeValue(0);
                        } else {
                            System.out.println("Unknown attribute in spouse");
                        }
                    }
                    case ("parent-id") -> {
                        assert personInfo != null;
                        if (reader.getAttributeCount() > 0 && reader.getAttributeLocalName(0).equals("id")) {
                            personInfo.parentsID.add(reader.getAttributeValue(0));
                        } else {
                            System.out.println("Unknown attribute in parent-id");
                        }
                    }
                    case ("sibling-id") -> {
                        if (reader.getAttributeCount() > 0 && reader.getAttributeLocalName(0).equals("id")) {
                            assert personInfo != null;
                            personInfo.siblingsID.add(reader.getAttributeValue(0));
                        } else {
                            System.out.println("Unknown attribute in sibling-id");
                        }
                    }
                    case ("son-id") -> {
                        if (reader.getAttributeCount() > 0 && reader.getAttributeLocalName(0).equals("id")) {
                            assert personInfo != null;
                            personInfo.sonsID.add(reader.getAttributeValue(0));
                        } else {
                            System.out.println("Unknown attribute in son-id");
                        }
                    }
                    case ("daughter-id") -> {
                        if (reader.getAttributeCount() > 0 && reader.getAttributeLocalName(0).equals("id")) {
                            assert personInfo != null;
                            personInfo.daughtersID.add(reader.getAttributeValue(0));
                        } else {
                            System.out.println("Unknown attribute in daughter-id");
                        }
                    }
                }
            } else if (eventType == XMLStreamConstants.END_ELEMENT) {
                switch (reader.getLocalName()) {
                    case ("person") -> {
                        assert people != null;
                        people.add(personInfo);
                        personInfo = null;
                        if (people.size() == segmentLength) {
                            executorService.submit(new ToDBRunnable(people));
                            people = new ArrayList<>();
                        }
                    }
                    case ("people") -> {
                        assert people != null;
                        if (!people.isEmpty()) {
                            executorService.submit(new ToDBRunnable(people));
                        }
                        executorService.shutdown();
                    }
                }
            }
        }
        reader.close();
        try {
            if (!Objects.requireNonNull(executorService).awaitTermination(10, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
            NormalizerParent.normalize();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
