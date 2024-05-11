import java.util.HashSet;
import java.util.Set;

public class PersonInfo {

    public String id;
    public String name;
    public String gender;

    public Set<String> parentsID = new HashSet<>();

    public String spouseId;

    public Set<String> siblingsID = new HashSet<>();

    public Set<String> sonsID = new HashSet<>();
    public Set<String> daughtersID = new HashSet<>();

    @Override
    public String toString() {
        return "PersonInfo [id=" + id + ", name=" + name + ", gender=" + gender +
                ", nParentsID=" + parentsID.size() + ", spouseID=" + spouseId +
                ", nSiblingsID=" + siblingsID.size() + ", nSonsID=" + sonsID.size() +
                ", nDaughtersID=" + daughtersID.size() + "]";
    }
}
