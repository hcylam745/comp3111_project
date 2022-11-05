package comp3111_project.engine;

import comp3111_project.Person;
import comp3111_project.Team;
import comp3111_project.utils.MergeSort;
import javafx.collections.ObservableList;
import lombok.AllArgsConstructor;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class ATU implements ATUInterface {
    private final ObservableList<Person> person;
    private final List<Person> k1;
    private final List<Person> k2;
    private final List<Person> remain;
    private final double averageK1;

    public ATU(ObservableList<Person> person) {
        this.person = person;
        List<Person> sorted = MergeSort.sort(person,1);
        k1 = sorted.subList(0,33);
        k2 = MergeSort.sort(sorted.subList(33,33 + 33),2);
        remain = sorted.subList(33 + 33,person.size());
        averageK1 = getAverageK1();
    }

    @Override
    public Optional<Person> selectK(int i, int k, Integer...target) {
        Integer x = target.length>0 ? target[0]: 0;
        Integer y = target.length>0 ? target[1]: 0;
        if(k == 1){
            System.out.println("Right here");
            for(int j = 0; j < k1.size(); ++j){
                Person p = k1.get(j);
                if(p.getTeam() == -1) {
                    p.setTeam(i);
                    System.out.println("Found 1 at p1 = " + p.getK1energy());
                    return Optional.of(p);
                }
            }
        } else if(k == 2) {
            for(int j = 0; j < k2.size(); ++j){
                Person p = k2.get(j);
                if(p.getTeam() == -1) {
                    p.setTeam(i);
                    return Optional.of(p);
                }
            }
//            for(int j = i; j < k2.size(); ++j){
//                Person p = k2.get(j);
//                if(p.getTeam() == -1) {
//                    p.setTeam(i);
//                    System.out.println("Found 2 at p2 = " + p.getK2energy());
//                    return Optional.of(p);
//                }
//            }
        } else {
//            for(int j = k2.size() - 1; j >= 0; --j) {
//                Person p = k1.get(j);
//                if(p.getTeam() == -1) {
//                    p.setTeam(i);
//                    System.out.println("Found 3 at p1 = " + p.getK1energy() );
//                    return Optional.of(p);
//                }
//
//            };
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
        return Optional.empty();
    }

    public Optional<Person> selectFirst(){
        for(int i = 0; i < k1.size(); ++i){
            Person p = k1.get(i);
            if(p.getTeam() == -1 && p.getK1energy() > averageK1) {
                p.setTeam(i);
                return Optional.of(p);
            }
        }
        return Optional.empty();
    }

    public Optional<Person> selectSecond(){
        for(int i = k1.size() - 1; i >= 0 ; --i) {
            Person p = k1.get(i);
            if(p.getTeam() == -1) {
                p.setTeam(99 -i);
                return Optional.of(p);
            }
        }
        return Optional.empty();
    }

//    public Team assemble() {
//        Team[] team = new Team[33];
//        for(int i = 0; i < 33; ++i){
//            if(team[i] == null) {
//                team[i] = new Team();
//            }
//        }
//    }

    public List<Person> getK1() {
        return k1;
    }

    public List<Person> getK2() {
        return k2;
    }

    @Override
    public Double getAverageK1() {
        int sum = 0;
        for(Person p: k1) {
            sum += p.getK1energy();
        }
        return (double) sum / k1.size();
    }
}
