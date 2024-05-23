package tables;

public class Parent {
    private String person_id;
    private String parent_id;

    public Parent(String person_id, String parent_id) {
        this.person_id = person_id;
        this.parent_id = parent_id;
    }

    public String getPerson_id() {
        return person_id;
    }

    public void setPerson_id(String person_id) {
        this.person_id = person_id;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }
}
