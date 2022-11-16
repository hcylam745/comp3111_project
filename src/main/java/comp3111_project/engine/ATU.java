package comp3111_project.engine;

import comp3111_project.Person;
import comp3111_project.utils.MergeSort;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.Optional;

public class ATU implements ATUInterface {
    private final ObservableList<Person> person;
    private final List<Person> k1;
    private final List<Person> k2;
    private final List<Person> remain;

    public ATU(ObservableList<Person> person) {
        this.person = person;
        List<Person> sorted = MergeSort.sort(person,1);
        k1 = sorted.subList(0,33);
        k2 = MergeSort.sort(sorted.subList(33,33 + 33),2);
        remain = sorted.subList(33 + 33,person.size());
    }

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

    public List<Person> getK1() {
        return k1;
    }

    public List<Person> getK2() {
        return k2;
    }
}
