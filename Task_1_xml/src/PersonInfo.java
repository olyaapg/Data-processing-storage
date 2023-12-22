import java.util.HashSet;
import java.util.Set;

public class PersonInfo {

  public String id;
  public String firstname;
  public String surname;
  public String gender;

  public String motherName;
  public String fatherName;
  public Set<String> parentsID = new HashSet<>();

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

  public void merge(PersonInfo somePerson) {
    if (id == null) {
      id = somePerson.id;
    }
    if (firstname == null) {
      firstname = somePerson.firstname;
    }
    if (surname == null) {
      surname = somePerson.surname;
    }
    if (gender == null) {
      gender = somePerson.gender;
    }

    if (motherName == null) {
      motherName = somePerson.motherName;
    }
    if (fatherName == null) {
      fatherName = somePerson.fatherName;
    }
    parentsID.addAll(somePerson.parentsID);

    if (wifeId == null) {
      wifeId = somePerson.wifeId;
    } else if (husbandId == null) {
      husbandId = somePerson.husbandId;
    }
    if (spouseName == null) {
      spouseName = somePerson.spouseName;
    }

    if (siblingsCount == null) {
      siblingsCount = somePerson.siblingsCount;
    }
    if (childrenCount == null) {
      childrenCount = somePerson.childrenCount;
    }

    sonsID.addAll(somePerson.sonsID);
    daughtersID.addAll(somePerson.daughtersID);
    childrenNames.addAll(somePerson.childrenNames);

    brothersID.addAll(somePerson.brothersID);
    sistersID.addAll(somePerson.sistersID);
    siblingsID.addAll(somePerson.siblingsID);
  }
}
