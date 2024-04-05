import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "children")
public class Children {
  @XmlElement(name = "sons")
  ArrayList<SonsType> sons = new ArrayList<>();
  @XmlElement(name = "daughters")
  ArrayList<DaughtersType> daughters = new ArrayList<>();

  @XmlAttribute(name = "count", required = true)
  Integer count;
}
