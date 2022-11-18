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
public class ATU implements ATUInterface {
    private final ObservableList<Person> person;
    private final List<Person> k1;
    private final List<Person> k2;
    private final List<Person> remain;

    /**
     * Construct the Team
     * @param person All the people in the database
     */
    public ATU(ObservableList<Person> person) {
        this.person = person;
        List<Person> sorted = MergeSort.sort(person,1);
        k1 = sorted.subList(0,33);
        k2 = MergeSort.sort(sorted.subList(33,33 + 33),2);
        remain = sorted.subList(33 + 33,person.size());
    }

    /**
     * Add person into a team
     * @param i team number
     * @param k which sample to pick a person from
     * @param target Only for person k = 3 to calculate the dot product
     * @return person to be put into a team
     */
    @Override
    public Optional<Person> selectK(int i, int k, Integer...target) {
        Integer x = target.length>0 ? target[0]: 0;
        Integer y = target.length>0 ? target[1]: 0;
        if(k == 1){
            for(int j = 0; j < k1.size(); ++j){
                Person p = k1.get(j);
                if(p.getTeam() == -1) {
                    p.setTeam(i);
                    return Optional.of(p);
                }
            }
            return Optional.empty();
        } else if(k == 2) {
            for(int j = 0; j < k2.size(); ++j){
                Person p = k2.get(j);
                if(p.getTeam() == -1) {
                    p.setTeam(i);
                    return Optional.of(p);
                }
            }
            return Optional.empty();
        } else {
            int max = 0;
            int maxIndex = -1;
            for(int j = 0; j < remain.size(); ++j) {
                Person p = remain.get(j);
                if(p.getTeam() == -1) {
                    int newX = x * p.getK1energy();
                    int newY = y * p.getK2energy();
                    if(newX + newY > max) {
                        max = newX + newY;
                        maxIndex = j;
                    }
//
                }
            };
            remain.get(maxIndex).setTeam(i);
            return Optional.of(remain.get(maxIndex));
        }

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
    public List<Person> getK2() {
        return k2;
    }
}
