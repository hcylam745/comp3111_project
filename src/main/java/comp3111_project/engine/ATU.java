package comp3111_project.engine;

import comp3111_project.Library;
import comp3111_project.Person;
import comp3111_project.utils.MergeSort;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.Optional;

/**
 * Logic for Teaming up Engine
 */
public class ATU {
    private final List<Person> k1;
    private final List<Person> remain;

    /**
     * Construct the Team
     * @param person All the people in the database
     */
    public ATU(ObservableList<Person> person) {
        List<Person> sorted = MergeSort.sort(person,1);
        k1 = sorted.subList(0,person.size() / 3);
        remain = sorted.subList(person.size() / 3,sorted.size());
    }

    /**
     * Get the list of people being put in K1
     * @return List of k1
     */
    public List<Person> getK1() {
        return k1;
    }

    /**
     * Get the list of people being put in K2
     * @return List of k2
     */
    public List<Person> getRemain() {
        return remain;
    }
}
