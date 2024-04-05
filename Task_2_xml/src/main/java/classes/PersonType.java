
package classes;

import javax.annotation.processing.Generated;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.ArrayList;

/**
 * <p>Java class for person-type complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="person-type"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="spouse" type="{http://fit.nsu.ru/people}id-type" minOccurs="0"/&gt;
 *         &lt;element name="parents" type="{http://fit.nsu.ru/people}parents-type" minOccurs="0"/&gt;
 *         &lt;element name="siblings" type="{http://fit.nsu.ru/people}siblings-type" minOccurs="0"/&gt;
 *         &lt;element name="children"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="sons" type="{http://fit.nsu.ru/people}sons-type" minOccurs="0"/&gt;
 *                   &lt;element name="daughters" type="{http://fit.nsu.ru/people}daughters-type" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *                 &lt;attribute name="count" use="required" type="{http://www.w3.org/2001/XMLSchema}int" /&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}ID" /&gt;
 *       &lt;attribute name="gender" use="required" type="{http://fit.nsu.ru/people}gender-type" /&gt;
 *       &lt;attribute name="name" use="required" type="{http://fit.nsu.ru/people}person-name-type" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "person-type", namespace = "http://fit.nsu.ru/people", propOrder = {
        "spouse",
        "parents",
        "siblings",
        "children"
})
@Generated(value = "com.sun.tools.xjc.Driver", comments = "JAXB RI v3.0.2", date = "2024-04-05T22:26:24+07:00")
public class PersonType {

    @XmlElement(namespace = "http://fit.nsu.ru/people")
    @Generated(value = "com.sun.tools.xjc.Driver", comments = "JAXB RI v3.0.2", date = "2024-04-05T22:26:24+07:00")
    protected IdType spouse;
    @XmlElement(namespace = "http://fit.nsu.ru/people")
    @Generated(value = "com.sun.tools.xjc.Driver", comments = "JAXB RI v3.0.2", date = "2024-04-05T22:26:24+07:00")
    protected ParentsType parents;
    @XmlElement(namespace = "http://fit.nsu.ru/people")
    @Generated(value = "com.sun.tools.xjc.Driver", comments = "JAXB RI v3.0.2", date = "2024-04-05T22:26:24+07:00")
    protected SiblingsType siblings;
    @XmlElement(namespace = "http://fit.nsu.ru/people", required = true)
    @Generated(value = "com.sun.tools.xjc.Driver", comments = "JAXB RI v3.0.2", date = "2024-04-05T22:26:24+07:00")
    protected PersonType.Children children;
    @XmlAttribute(name = "id", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    @Generated(value = "com.sun.tools.xjc.Driver", comments = "JAXB RI v3.0.2", date = "2024-04-05T22:26:24+07:00")
    protected String id;
    @XmlAttribute(name = "gender", required = true)
    @Generated(value = "com.sun.tools.xjc.Driver", comments = "JAXB RI v3.0.2", date = "2024-04-05T22:26:24+07:00")
    protected GenderType gender;
    @XmlAttribute(name = "name", required = true)
    @Generated(value = "com.sun.tools.xjc.Driver", comments = "JAXB RI v3.0.2", date = "2024-04-05T22:26:24+07:00")
    protected String name;

    public PersonType() {
        setParents(new ParentsType());
        parents.parentId = new ArrayList<>();
        siblings = new SiblingsType();
        siblings.siblingId = new ArrayList<>();
        children = new PersonType.Children();
        children.sons = new SonsType();
        children.sons.sonId = new ArrayList<>();
        children.daughters = new DaughtersType();
        children.daughters.daughterId = new ArrayList<>();
    }

    /**
     * Gets the value of the spouse property.
     *
     * @return possible object is
     * {@link IdType }
     */
    @Generated(value = "com.sun.tools.xjc.Driver", comments = "JAXB RI v3.0.2", date = "2024-04-05T22:26:24+07:00")
    public IdType getSpouse() {
        if (spouse == null) {
            spouse = new IdType();
        }
        return spouse;
    }

    /**
     * Sets the value of the spouse property.
     *
     * @param value allowed object is
     *              {@link IdType }
     */
    @Generated(value = "com.sun.tools.xjc.Driver", comments = "JAXB RI v3.0.2", date = "2024-04-05T22:26:24+07:00")
    public void setSpouse(IdType value) {
        this.spouse = value;
    }

    /**
     * Gets the value of the parents property.
     *
     * @return possible object is
     * {@link ParentsType }
     */
    @Generated(value = "com.sun.tools.xjc.Driver", comments = "JAXB RI v3.0.2", date = "2024-04-05T22:26:24+07:00")
    public ParentsType getParents() {
        return parents;
    }

    /**
     * Sets the value of the parents property.
     *
     * @param value allowed object is
     *              {@link ParentsType }
     */
    @Generated(value = "com.sun.tools.xjc.Driver", comments = "JAXB RI v3.0.2", date = "2024-04-05T22:26:24+07:00")
    public void setParents(ParentsType value) {
        this.parents = value;
    }

    /**
     * Gets the value of the siblings property.
     *
     * @return possible object is
     * {@link SiblingsType }
     */
    @Generated(value = "com.sun.tools.xjc.Driver", comments = "JAXB RI v3.0.2", date = "2024-04-05T22:26:24+07:00")
    public SiblingsType getSiblings() {
        return siblings;
    }

    /**
     * Sets the value of the siblings property.
     *
     * @param value allowed object is
     *              {@link SiblingsType }
     */
    @Generated(value = "com.sun.tools.xjc.Driver", comments = "JAXB RI v3.0.2", date = "2024-04-05T22:26:24+07:00")
    public void setSiblings(SiblingsType value) {
        this.siblings = value;
    }

    /**
     * Gets the value of the children property.
     *
     * @return possible object is
     * {@link PersonType.Children }
     */
    @Generated(value = "com.sun.tools.xjc.Driver", comments = "JAXB RI v3.0.2", date = "2024-04-05T22:26:24+07:00")
    public PersonType.Children getChildren() {
        return children;
    }

    /**
     * Sets the value of the children property.
     *
     * @param value allowed object is
     *              {@link PersonType.Children }
     */
    @Generated(value = "com.sun.tools.xjc.Driver", comments = "JAXB RI v3.0.2", date = "2024-04-05T22:26:24+07:00")
    public void setChildren(PersonType.Children value) {
        this.children = value;
    }

    /**
     * Gets the value of the id property.
     *
     * @return possible object is
     * {@link String }
     */
    @Generated(value = "com.sun.tools.xjc.Driver", comments = "JAXB RI v3.0.2", date = "2024-04-05T22:26:24+07:00")
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    @Generated(value = "com.sun.tools.xjc.Driver", comments = "JAXB RI v3.0.2", date = "2024-04-05T22:26:24+07:00")
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the gender property.
     *
     * @return possible object is
     * {@link GenderType }
     */
    @Generated(value = "com.sun.tools.xjc.Driver", comments = "JAXB RI v3.0.2", date = "2024-04-05T22:26:24+07:00")
    public GenderType getGender() {
        return gender;
    }

    /**
     * Sets the value of the gender property.
     *
     * @param value allowed object is
     *              {@link GenderType }
     */
    @Generated(value = "com.sun.tools.xjc.Driver", comments = "JAXB RI v3.0.2", date = "2024-04-05T22:26:24+07:00")
    public void setGender(GenderType value) {
        this.gender = value;
    }

    /**
     * Gets the value of the name property.
     *
     * @return possible object is
     * {@link String }
     */
    @Generated(value = "com.sun.tools.xjc.Driver", comments = "JAXB RI v3.0.2", date = "2024-04-05T22:26:24+07:00")
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    @Generated(value = "com.sun.tools.xjc.Driver", comments = "JAXB RI v3.0.2", date = "2024-04-05T22:26:24+07:00")
    public void setName(String value) {
        this.name = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     *
     * <p>The following schema fragment specifies the expected content contained within this class.
     *
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="sons" type="{http://fit.nsu.ru/people}sons-type" minOccurs="0"/&gt;
     *         &lt;element name="daughters" type="{http://fit.nsu.ru/people}daughters-type" minOccurs="0"/&gt;
     *       &lt;/sequence&gt;
     *       &lt;attribute name="count" use="required" type="{http://www.w3.org/2001/XMLSchema}int" /&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "sons",
            "daughters"
    })
    @Generated(value = "com.sun.tools.xjc.Driver", comments = "JAXB RI v3.0.2", date = "2024-04-05T22:26:24+07:00")
    public static class Children {

        @XmlElement(namespace = "http://fit.nsu.ru/people")
        @Generated(value = "com.sun.tools.xjc.Driver", comments = "JAXB RI v3.0.2", date = "2024-04-05T22:26:24+07:00")
        protected SonsType sons;
        @XmlElement(namespace = "http://fit.nsu.ru/people")
        @Generated(value = "com.sun.tools.xjc.Driver", comments = "JAXB RI v3.0.2", date = "2024-04-05T22:26:24+07:00")
        protected DaughtersType daughters;
        @XmlAttribute(name = "count", required = true)
        @Generated(value = "com.sun.tools.xjc.Driver", comments = "JAXB RI v3.0.2", date = "2024-04-05T22:26:24+07:00")
        protected int count;

        /**
         * Gets the value of the sons property.
         *
         * @return possible object is
         * {@link SonsType }
         */
        @Generated(value = "com.sun.tools.xjc.Driver", comments = "JAXB RI v3.0.2", date = "2024-04-05T22:26:24+07:00")
        public SonsType getSons() {
            return sons;
        }

        /**
         * Sets the value of the sons property.
         *
         * @param value allowed object is
         *              {@link SonsType }
         */
        @Generated(value = "com.sun.tools.xjc.Driver", comments = "JAXB RI v3.0.2", date = "2024-04-05T22:26:24+07:00")
        public void setSons(SonsType value) {
            this.sons = value;
        }

        /**
         * Gets the value of the daughters property.
         *
         * @return possible object is
         * {@link DaughtersType }
         */
        @Generated(value = "com.sun.tools.xjc.Driver", comments = "JAXB RI v3.0.2", date = "2024-04-05T22:26:24+07:00")
        public DaughtersType getDaughters() {
            return daughters;
        }

        /**
         * Sets the value of the daughters property.
         *
         * @param value allowed object is
         *              {@link DaughtersType }
         */
        @Generated(value = "com.sun.tools.xjc.Driver", comments = "JAXB RI v3.0.2", date = "2024-04-05T22:26:24+07:00")
        public void setDaughters(DaughtersType value) {
            this.daughters = value;
        }

        /**
         * Gets the value of the count property.
         */
        @Generated(value = "com.sun.tools.xjc.Driver", comments = "JAXB RI v3.0.2", date = "2024-04-05T22:26:24+07:00")
        public int getCount() {
            return count;
        }

        /**
         * Sets the value of the count property.
         */
        @Generated(value = "com.sun.tools.xjc.Driver", comments = "JAXB RI v3.0.2", date = "2024-04-05T22:26:24+07:00")
        public void setCount(int value) {
            this.count = value;
        }

    }

}
