import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public class Validating {

    Map<String, PersonInfo> people;

    Validating(Map<String, PersonInfo> people) {
        this.people = people;
    }

    private void addSpouse(PersonInfo person, String spouseId) {
        if (person.spouseName != null) {
            return;
        }
        PersonInfo spouse = people.get(spouseId);
        person.spouseName = spouse.firstname + " " + spouse.surname;
    }

    private void checkFullnessGenderAndSpouse(MyParser parser) {
        System.out.println("Checking fullness gender");

        for (PersonInfo person : people.values()) {
            if (person.gender != null) {
                continue;
            }

            if (person.wifeId != null) {
                person.gender = "M";
                addSpouse(person, person.wifeId);
                people.get(person.wifeId).gender = "F";
            } else if (person.husbandId != null) {
                person.gender = "F";
                addSpouse(person, person.husbandId);
                people.get(person.husbandId).gender = "M";
            } else if (person.spouseName != null) {
                var spouseName = person.spouseName.split("\\s+");
                Map<String, PersonInfo> foundList = findNamesEquals(parser, spouseName);
                if (!foundList.isEmpty()) {
                    PersonInfo spouse = foundList.values().stream().toList().get(0);
                    if (spouse.gender.equals("F")) {
                        person.gender = "M";
                        person.wifeId = spouse.id;
                    } else if (spouse.gender.equals("M")) {
                        person.gender = "F";
                        person.husbandId = spouse.id;
                    }
                    person.spouseName = spouse.firstname + " " + spouse.surname;
                }
            }

            assert person.gender != null;
            assert person.spouseName != null;
            assert person.wifeId != null || person.husbandId != null;
            person.spouseId = Objects.requireNonNullElseGet(person.wifeId, () -> person.husbandId);
        }
    }

    private Map<String, PersonInfo> findNamesEquals(MyParser parser, String[] spouseName) {
        Predicate<PersonInfo> nameEquals = person2 -> spouseName[0].equals(person2.firstname)
                && spouseName[1].equals(person2.surname);
        return parser.findSimilar(nameEquals, people);
    }

    private void fillAndCheckParents() {
        System.out.println("Filling and checking parents");

        for (PersonInfo person : people.values()) {

            if (!person.parentsID.isEmpty() && (person.motherName == null || person.fatherName == null)) {
                for (String id : person.parentsID) {

                    PersonInfo parent = people.get(id);
                    if (parent.gender.equals("F")) {
                        var motherName = parent.firstname + " " + parent.surname;
                        if (person.motherName == null) {
                            person.motherName = motherName;
                        } else {
                            assert person.motherName.equals(motherName);
                        }

                    } else {
                        var fatherName = parent.firstname + " " + parent.surname;
                        if (person.fatherName == null) {
                            person.fatherName = fatherName;
                        } else {
                            assert person.fatherName.equals(fatherName);
                        }
                    }
                }
            }
        }
    }

    private void fillAndCheckChildren(MyParser parser) {
        System.out.println("Filling and checking children");

        for (PersonInfo person : people.values()) {
            for (String childName : person.childrenNames) {
                var name = childName.split("\\s+");
                var foundList = findNamesEquals(parser, name);
                if (!foundList.isEmpty()) {
                    for (PersonInfo child : foundList.values()) {
                        if (child.gender.equals("M")) {
                            person.sonsID.add(child.id);
                        } else {
                            person.daughtersID.add(child.id);
                        }
                    }
                }
            }

            for (String sonId : person.sonsID) {
                var son = people.get(sonId);
                var sonName = son.firstname + " " + son.surname;
                person.childrenNames.add(sonName);
            }

            for (String daughterId : person.daughtersID) {
                var daughter = people.get(daughterId);
                var daughterName = daughter.firstname + " " + daughter.surname;
                person.childrenNames.add(daughterName);
            }

            assert person.childrenCount == null || person.childrenCount == person.childrenNames.size();
        }
    }

    private void fillAndCheckSiblings() {
        System.out.println("Filling and checking siblings");

        for (PersonInfo person : people.values()) {

            for (String siblingId : person.siblingsID) {
                var sibling = people.get(siblingId);
                var siblingName = sibling.firstname + " " + sibling.surname;
                if (sibling.gender.equals("F")) {
                    person.sistersNames.add(siblingName);
                } else {
                    person.brothersNames.add(siblingName);
                }
            }
        }
    }

    private void finalCheck() {
        for (PersonInfo person : people.values()) {
            assert person.id != null;
            assert person.firstname != null && person.surname != null;
            assert person.gender != null;
            assert person.childrenCount == person.childrenNames.size();
          //  if (person.childrenCount != person.daughtersID.size() + person.sonsID.size())
               // throw new AssertionError();
        }
    }

    public void check(MyParser parser) {
        checkFullnessGenderAndSpouse(parser);
        fillAndCheckParents();
        fillAndCheckSiblings();
        fillAndCheckChildren(parser);
        finalCheck();
    }
}
