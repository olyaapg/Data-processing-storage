import java.util.HashSet;
import java.util.Set;

public class PersonInfo {

  public String id;
  public String firstname;
  public String surname;
  public String gender;

  public String motherName;
  public String fatherName;
  public String wifeId;
  public String husbandId;
  public String spouseName;

  public Integer siblingsCount;
  public Integer childrenCount;

  public Set<String> sonsID = new HashSet<>();
  public Set<String> daughtersID = new HashSet<>();
  public Set<String> childrenNames = new HashSet<>();
  public Set<String> brothersID = new HashSet<>();
  public Set<String> sistersID = new HashSet<>();
  public Set<String> siblingsID = new HashSet<>();
  public Set<String> parentsID = new HashSet<>();
}
