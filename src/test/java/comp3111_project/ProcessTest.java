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
        int size = list.size();

        assertEquals(engine.getK1().size(),size / 3);
        assertEquals(engine.getRemain().size(), size - (size / 3));
    }
}
