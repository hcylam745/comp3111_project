package comp3111_project;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;




public class OutputTester {

    Team x;
    Person pOne;
    Person pTwo;
    /**
     * Initalize the necessary variables for the test
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        x = new Team();
        pOne = new Person("12345678","John","p1.com","80", "100" ,"1" ,"0","0","");
        pTwo = new Person("87654321","Mary","p1.com","60", "90" ,"1" ,"0","0","");
        x.addMember(pOne);
        x.addMember(pTwo);
    }

    /**
     * Test the OutputPerson class
     *
     * @throws Exception
     */
    @Test
    public void testOutputPerson() throws Exception {
        OutputPerson p1 = new OutputPerson(pOne.getStudentid(), pOne.getStudentname(),  1,x);
        OutputPerson p2 = new OutputPerson(pTwo.getStudentid(), pTwo.getStudentname(), 1,x);
        assertEquals("12345678",p1.getStudentid());
        assertEquals("John",p1.getStudentname());
        assertEquals("87654321",p2.getStudentid());
        assertEquals("Mary",p2.getStudentname());
        assertEquals(1,(int) p1.getTeamId());
        assertEquals( 1,(int)p2.getTeamId());
        assertEquals(70.0f, x.averageK1(), 0.01);
        assertEquals(70.0f,p1.getTeamAvgk1(), 0.01);
        assertEquals(95.0f, p2.getTeamAvgk2(),  0.01);
    }
}
