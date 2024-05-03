
package classes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for daughters-type complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="daughters-type"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="daughter-id" type="{http://fit.nsu.ru/people}id-type" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "daughters-type", namespace = "https://fit.nsu.ru/people", propOrder = {
        "daughterId"
})
public class DaughtersType {

    @XmlElement(name = "daughter-id", namespace = "https://fit.nsu.ru/people")
    protected List<IdType> daughterId;

    /**
     * Gets the value of the daughterId property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the daughterId property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDaughterId().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IdType }
     */
    public List<IdType> getDaughterId() {
        if (daughterId == null) {
            daughterId = new ArrayList<>();
        }
        return this.daughterId;
    }

}
