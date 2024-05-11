package tables;

public class Spouse {
    private String person_id;
    private String spouse_id;

    public Spouse(String person_id, String spouse_id) {
        this.person_id = person_id;
        this.spouse_id = spouse_id;
    }

    public String getPerson_id() {
        return person_id;
    }

    public void setPerson_id(String person_id) {
        this.person_id = person_id;
    }

    public String getSpouse_id() {
        return spouse_id;
    }

    public void setSpouse_id(String spouse_id) {
        this.spouse_id = spouse_id;
    }
}
