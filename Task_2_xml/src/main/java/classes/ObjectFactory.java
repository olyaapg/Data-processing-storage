
package classes;

import javax.annotation.processing.Generated;
import javax.xml.bind.annotation.XmlRegistry;

/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the classes package.
 * <p>An ObjectFactory allows you to programatically
 * construct new instances of the Java representation
 * for XML content. The Java representation of XML
 * content can consist of schema derived interfaces
 * and classes representing the binding of schema
 * type definitions, element declarations and model
 * groups.  Factory methods for each of these are
 * provided in this class.
 */
@XmlRegistry
@Generated(value = "com.sun.tools.xjc.Driver", comments = "JAXB RI v3.0.2", date = "2024-04-05T22:26:24+07:00")
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: classes
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link PersonType }
     */
    public PersonType createPersonType() {
        return new PersonType();
    }

    /**
     * Create an instance of {@link People }
     */
    public People createPeople() {
        return new People();
    }

    /**
     * Create an instance of {@link IdType }
     */
    public IdType createIdType() {
        return new IdType();
    }

    /**
     * Create an instance of {@link ParentsType }
     */
    public ParentsType createParentsType() {
        return new ParentsType();
    }

    /**
     * Create an instance of {@link SiblingsType }
     */
    public SiblingsType createSiblingsType() {
        return new SiblingsType();
    }

    /**
     * Create an instance of {@link SonsType }
     */
    public SonsType createSonsType() {
        return new SonsType();
    }

    /**
     * Create an instance of {@link DaughtersType }
     */
    public DaughtersType createDaughtersType() {
        return new DaughtersType();
    }

    /**
     * Create an instance of {@link PersonType.Children }
     */
    public PersonType.Children createPersonTypeChildren() {
        return new PersonType.Children();
    }

}
