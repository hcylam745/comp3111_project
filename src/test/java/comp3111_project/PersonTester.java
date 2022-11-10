package comp3111_project;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class PersonTester {
	Person testPerson;

	@Before
	public void setUp() throws Exception {
		testPerson = new Person("20724066","Sid","sjnatarajan","1","1","1","0","1","Nothing");
	}

	@Test
	public void testK2EnergyPositive() {
		assertTrue(testPerson.getK2energy()>=0 && testPerson.getK2energy()<=100 );
	}

	@Test
	public void testK1EnergyPositive() {
		assertTrue(testPerson.getK1energy()>=0 && testPerson.getK1energy()<=100);
	}

	@Test
	public void testEmail() {
		assertTrue(testPerson.getEmail().length()>=0);
	}

	@Test
	public void testName() {
		assertTrue(testPerson.getStudentname().length()>=0);
	}
}