import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "parents-type")
class ParentsType {
  @XmlElement(name = "parent-id")
  ArrayList<IdType> parentId = new ArrayList<>();
}
