package comp3111_project;

import comp3111_project.engine.ATU;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

/**
 * Test for process
 */
public class ProcessTest {

    private final ObservableList<Person> list = FXCollections.observableArrayList();

    /**
     * Initialize 100 Students
     */
    @Before
    public void init(){
        for(int i = 0 ; i < 100 ; ++i) {
            String index = String.valueOf(i);
            String id = "id" + index;
            String name = "name" + index;
            String email = "email" + index;
            list.add(new Person(id,name,email,String.valueOf(i),String.valueOf(i),"0","0","",""));
        }
    }

    /**
     * Make sure the ATU engine split the dataset correctly
     */
    @Test
    public void TestConstructor() {
        ATU engine = new ATU(list);

        assertEquals(engine.getK1().size(),33);
        assertEquals(engine.getK2().size(), 33);
    }

    /**
     * Make sure we select the highest k1 for the team 0
     */
    @Test
    public void TestSelectK1() {
        ATU engine = new ATU(list);

        assertEquals(engine.selectK(0,1).orElse(null),engine.getK1().get(0));
    }

    /**
     * Make sure we select lowest k2 for the team 0
     */
    @Test
    public void TestSelectK2() {
        ATU engine = new ATU(list);

        assertEquals(engine.selectK(0,2).orElse(null),engine.getK2().get(0));
    }

    /**
     * Make sure there's at least one person with k1 higher than the average k1
     */
    @Test
    public void TestSelectK3() {
        double averagek1 = list.stream().mapToInt(Person::getK1energy).average().orElse(-1);
        ATU atu = new ATU(list);
        Team[] teams = new Team[33];
        for(int i = 0 ; i < 33 ; ++i) {
            if(teams[i] == null) {
                teams[i] = new Team();
            }
            Optional<Person> p1 = atu.selectK(i,1);
            Optional<Person> p2 = atu.selectK(i,2);
            int x = 150 - p1.get().getK1energy() + p2.get().getK1energy();
            int y = 150  - p2.get().getK2energy() + p1.get().getK2energy();
            Optional<Person> p3 = atu.selectK(i,3,x,y);

            List<Person> k1Filtered = atu.getK1().stream().filter(p -> p.getTeam() == -1).collect(Collectors.toList());
            teams[i].addMember(p1.get());
            teams[i].addMember(p2.get());
            teams[i].addMember(p3.get());

            List<Person> team = Arrays.asList(teams[i].getTeamMembers());
            int max = 0;
            for(Person p: team) {
                if(p == null)
                    break;
                if(p.getK1energy() > max) {
                    max = p.getK1energy();
                }
            }

            assert max > averagek1;
        }
    }
}
