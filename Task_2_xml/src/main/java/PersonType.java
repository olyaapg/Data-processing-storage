import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "person-type")
class PersonType {
  IdType spouse;
  ParentsType parents;
  SiblingsType siblings;
  Children children;

  @XmlID
  @XmlSchemaType(name = "ID")
  IdType id;

  @XmlAttribute(name = "name", required = true)
  String name;

  @XmlAttribute(name = "gender", required = true)
  var gender: GenderType? = null
}