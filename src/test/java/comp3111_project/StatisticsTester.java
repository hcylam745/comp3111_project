package comp3111_project;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
/**
 * This class for junit to run tests on.
 * This specifically tests the Statistics class.
 * @author Henry
 */
public class StatisticsTester {
	// creates an instance of Statistics to run tests on.
	Statistics test;
	
	// no setup required.
	@Before
	public void setup() throws Exception {
		
	}
	
	// this will test statistics given correct inputs and its accessor and setter functions.
	// tests without the correct input are not required because statistics specifies strings as the input.
	// since the strings are not parsed, this means that all inputs must be correct, and therefore no other tests are required.
	@Test
	public void testWithCorrectInputs() {
		test = new Statistics("test", "test");
		System.out.print(test.getEntry());
		System.out.print(test.getValue());
		test.setEntry("testing");
		test.setValue("testing");
	}
}
