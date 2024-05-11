import tables.Parent;
import tables.Person;
import tables.Spouse;

import java.util.ArrayList;
import java.util.List;

public class ToDBRunnable implements Runnable {
    private final List<PersonInfo> people;

    public ToDBRunnable(List<PersonInfo> people) {
        this.people = people;
    }

    @Override
    public void run() {
        List<Person> personList = new ArrayList<>(people.size());
        List<Parent> parentList = new ArrayList<>();
        List<Spouse> spouseList = new ArrayList<>();
        for (PersonInfo pi : people) {
            personList.add(new Person(pi.id, pi.gender, pi.name));
            if (!pi.parentsID.isEmpty()) {
                for (String parentID : pi.parentsID) {
                    parentList.add(new Parent(pi.id, parentID));
                }
            }
            if (!pi.sonsID.isEmpty()) {
                for (String sonsID : pi.sonsID) {
                    parentList.add(new Parent(sonsID, pi.id));
                }
            }
            if (!pi.daughtersID.isEmpty()) {
                for (String daughterID : pi.daughtersID) {
                    parentList.add(new Parent(daughterID, pi.id));
                }
            }
            if (pi.spouseId != null) {
                spouseList.add(new Spouse(pi.id, pi.spouseId));
            }
        }
        if (!personList.isEmpty()) {
            PersonDB.add(personList);
        }
        if (!parentList.isEmpty()) {
            ParentDB.add(parentList);
        }
        if (!spouseList.isEmpty()) {
            SpouseDB.add(spouseList);
        }
        String report = Thread.currentThread().getName() + " " +
                personList.size() + " people added;" + " " +
                parentList.size() + " parents added;" + " " +
                spouseList.size() + " spouses added";
        System.out.println(report);
    }
}
