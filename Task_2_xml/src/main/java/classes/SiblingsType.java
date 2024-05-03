
package classes;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for siblings-type complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="siblings-type"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="sibling-id" type="{http://fit.nsu.ru/people}id-type" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="count" use="required" type="{http://www.w3.org/2001/XMLSchema}int" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "siblings-type", namespace = "https://fit.nsu.ru/people", propOrder = {
        "siblingId"
})
public class SiblingsType {

    @XmlElement(name = "sibling-id", namespace = "https://fit.nsu.ru/people")
    protected List<IdType> siblingId;
    @XmlAttribute(name = "count", required = true)
    protected int count;

    /**
     * Gets the value of the siblingId property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the javax XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the siblingId property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSiblingId().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IdType }
     */
    public List<IdType> getSiblingId() {
        if (siblingId == null) {
            siblingId = new ArrayList<>();
        }
        return this.siblingId;
    }

    /**
     * Sets the value of the count property.
     */
    public void setCount(int value) {
        this.count = value;
    }

}
