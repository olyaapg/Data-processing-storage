import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "siblings-type")
class SiblingsType {
  @XmlElement(name = "sibling-id")
  ArrayList<IdType> siblingId = new ArrayList<>();

  @XmlAttribute(name = "count", required = true)
  Integer count;
}