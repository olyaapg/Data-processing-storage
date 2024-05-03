
package classes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for person-type complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="person-type"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}anyType"&gt;
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
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "person-type", namespace = "https://fit.nsu.ru/people", propOrder = {
    "spouse",
    "parents",
    "siblings",
    "children"
})
public class PersonType {

    @XmlElement(namespace = "https://fit.nsu.ru/people")
    protected IdType spouse;
    @XmlElement(namespace = "https://fit.nsu.ru/people")
    protected ParentsType parents;
    @XmlElement(namespace = "https://fit.nsu.ru/people")
    protected SiblingsType siblings;
    @XmlElement(namespace = "https://fit.nsu.ru/people", required = true)
    protected PersonType.Children children;
    @XmlAttribute(name = "id", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;
    @XmlAttribute(name = "gender", required = true)
    protected GenderType gender;
    @XmlAttribute(name = "name", required = true)
    protected String name;

    /**
     * Gets the value of the spouse property.
     * 
     * @return
     *     possible object is
     *     {@link IdType }
     *     
     */
    public IdType getSpouse() {
        return spouse;
    }

    /**
     * Sets the value of the spouse property.
     * 
     * @param value
     *     allowed object is
     *     {@link IdType }
     *     
     */
    public void setSpouse(IdType value) {
        this.spouse = value;
    }

    /**
     * Gets the value of the parents' property.
     * 
     * @return
     *     possible object is
     *     {@link ParentsType }
     *     
     */
    public ParentsType getParents() {
        return parents;
    }

    /**
     * Sets the value of the parents' property.
     * 
     * @param value
     *     allowed object is
     *     {@link ParentsType }
     *     
     */
    public void setParents(ParentsType value) {
        this.parents = value;
    }

    /**
     * Gets the value of the siblings' property.
     * 
     * @return
     *     possible object is
     *     {@link SiblingsType }
     *     
     */
    public SiblingsType getSiblings() {
        return siblings;
    }

    /**
     * Sets the value of the siblings' property.
     * 
     * @param value
     *     allowed object is
     *     {@link SiblingsType }
     *     
     */
    public void setSiblings(SiblingsType value) {
        this.siblings = value;
    }

    /**
     * Gets the value of the children property.
     * 
     * @return
     *     possible object is
     *     {@link PersonType.Children }
     *     
     */
    public PersonType.Children getChildren() {
        return children;
    }

    /**
     * Sets the value of the children property.
     * 
     * @param value
     *     allowed object is
     *     {@link PersonType.Children }
     *     
     */
    public void setChildren(PersonType.Children value) {
        this.children = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the gender property.
     * 
     * @return
     *     possible object is
     *     {@link GenderType }
     *     
     */
    public GenderType getGender() {
        return gender;
    }

    /**
     * Sets the value of the gender property.
     * 
     * @param value
     *     allowed object is
     *     {@link GenderType }
     *     
     */
    public void setGender(GenderType value) {
        this.gender = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
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
     *     &lt;restriction base="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="sons" type="{http://fit.nsu.ru/people}sons-type" minOccurs="0"/&gt;
     *         &lt;element name="daughters" type="{http://fit.nsu.ru/people}daughters-type" minOccurs="0"/&gt;
     *       &lt;/sequence&gt;
     *       &lt;attribute name="count" use="required" type="{http://www.w3.org/2001/XMLSchema}int" /&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "sons",
        "daughters"
    })
    public static class Children {

        @XmlElement(namespace = "https://fit.nsu.ru/people")
        protected SonsType sons;
        @XmlElement(namespace = "https://fit.nsu.ru/people")
        protected DaughtersType daughters;
        @XmlAttribute(name = "count", required = true)
        protected int count;

        /**
         * Gets the value of the sons' property.
         * 
         * @return
         *     possible object is
         *     {@link SonsType }
         *     
         */
        public SonsType getSons() {
            return sons;
        }

        /**
         * Sets the value of the sons' property.
         * 
         * @param value
         *     allowed object is
         *     {@link SonsType }
         *     
         */
        public void setSons(SonsType value) {
            this.sons = value;
        }

        /**
         * Gets the value of the daughters' property.
         * 
         * @return
         *     possible object is
         *     {@link DaughtersType }
         *     
         */
        public DaughtersType getDaughters() {
            return daughters;
        }

        /**
         * Sets the value of the daughters' property.
         * 
         * @param value
         *     allowed object is
         *     {@link DaughtersType }
         *     
         */
        public void setDaughters(DaughtersType value) {
            this.daughters = value;
        }

        /**
         * Gets the value of the count property.
         * 
         */
        public int getCount() {
            return count;
        }

        /**
         * Sets the value of the count property.
         * 
         */
        public void setCount(int value) {
            this.count = value;
        }

    }

}
