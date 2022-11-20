package comp3111_project;

import comp3111_project.engine.ATUEngine;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

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
        List<List<Person>> engine = ATUEngine.run(list);

        for(int i = 0 ; i < engine.size() - 1 ; ++i) {
            double before = engine.get(i).stream().mapToInt(Person::getK1energy).average().orElse(-1);
            double after = engine.get(i + 1).stream().mapToInt(Person::getK1energy).average().orElse(-1);
            assert before >= after;
        }
    }
}
