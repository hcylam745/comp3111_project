package comp3111_project;

import static org.junit.Assert.*;

import javafx.scene.control.TableView;
import org.junit.Before;
import org.junit.Test;

import javafx.scene.control.TextField;



public class OutputTester {
    @Before
    public void setUp() throws Exception {
        Team x;
        Person y;
        OutputPerson z;
    }
    
    @Test
    public void testOutputPerson() throws Exception {
        //test OutputPerson class function
        OutputPerson p1 = new OutputPerson("12345678", "John", 1, new Team());
        OutputPerson p2 = new OutputPerson("87654321", "Mary", 2, new Team());
        p1.getStudentid();
        p1.getStudentname();
        p1.getTeamId();
        p1.getTeamMembers();
        p1.getTeamAvgk1();
        p1.getTeamAvgk2();
    }

    @Test
    public void TestLibrary() throws Exception{
        Library.main(null);
        
    }
}
