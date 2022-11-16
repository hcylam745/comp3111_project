package comp3111_project;

import org.junit.Before;
import org.junit.Test;

public class StatisticsTester {
	Statistics test;
	
	@Before
	public void setup() throws Exception {
		
	}
	
	@Test
	public void testWithCorrectInputs() {
		test = new Statistics("test", "test");
		System.out.print(test.getEntry());
		System.out.print(test.getValue());
		test.setEntry("testing");
		test.setValue("testing");
	}
}
