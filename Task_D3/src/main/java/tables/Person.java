package tables;

public class Person {
    private String person_id;
    private String gender;
    private String person_name;

    public Person(String person_id, String gender, String person_name) {
        this.person_id = person_id;
        this.gender = gender;
        this.person_name = person_name;
    }

    public String getPerson_id() {
        return person_id;
    }

    public void setPerson_id(String person_id) {
        this.person_id = person_id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPerson_name() {
        return person_name;
    }

    public void setPerson_name(String person_name) {
        this.person_name = person_name;
    }

    @Override
    public String toString() {
        return "Person [person_id=" + person_id + ", gender=" + gender + ", person_name=" + person_name + "]";
    }
}
