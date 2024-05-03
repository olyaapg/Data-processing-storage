
package classes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Java class for parents-type complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="parents-type"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="parent-id" type="{http://fit.nsu.ru/people}id-type" maxOccurs="2" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "parents-type", namespace = "https://fit.nsu.ru/people", propOrder = {
        "parentId"
})
public class ParentsType {

    @XmlElement(name = "parent-id", namespace = "https://fit.nsu.ru/people")
    protected List<IdType> parentId;

    /**
     * Gets the value of the parentId property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the parentId property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getParentId().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IdType }
     */
    public List<IdType> getParentId() {
        if (parentId == null) {
            parentId = new ArrayList<>();
        }
        return this.parentId;
    }

}
