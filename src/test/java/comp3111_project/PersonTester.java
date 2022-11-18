package comp3111_project;

import org.junit.Before;
import org.junit.Test;


import org.junit.Before;
import org.junit.Test;


/**
 * Thie class is for junit to run tests on.
 * This specifically tests the person class.
 * @author Henry
 */
public class PersonTester {
	// this creates an instance of Person to test the constructor and its accessor functions on.
	Person test;
	
	// no setup required.
	@Before
	public void setup() throws Exception {
		
	}
	
	// this will test the person class' constructor and all of its accessor functions given correct inputs.
	@Test
	public void testWithCorrectInputs() {
		test = new Person("20723854", "Henry Chun Yin Lam", "hcylam@connect.ust.hk", "100", "100", "0", "1", "1", "test");
		System.out.print(test.getStudentid());
		System.out.print(test.getEmail());
		System.out.print(test.getStudentname());
		System.out.print(test.getK1energy());
		System.out.print(test.getK2energy());
		System.out.print(test.getK3tick1());
		System.out.print(test.getK3tick2());
		System.out.print(test.getMypreference());
		System.out.print(test.getConcerns());
	}
	
	// this will test the person class' constructor and all of its accessor functions given correct inputs, slightly different inputs from last time.
	@Test
	public void testWithCorrectInputs2() {
		test = new Person("20723854", "Henry Chun Yin Lam", "hcylam@connect.ust.hk", "100", "100", "1", "0", "0", "test");
		System.out.print(test.getStudentid());
		System.out.print(test.getEmail());
		System.out.print(test.getStudentname());
		System.out.print(test.getK1energy());
		System.out.print(test.getK2energy());
		System.out.print(test.getK3tick1());
		System.out.print(test.getK3tick2());
		System.out.print(test.getMypreference());
		System.out.print(test.getConcerns());
	}
	
	// this will test the person class' constructor if given incorrect inputs in the areas that require numberical input for it to be parsed.
	@Test
	public void testwithIncorrectInputs() {
		test = new Person("20723854", "Henry Chun Yin Lam", "hcylam@connect.ust.hk", "wrong", "incorrect", "words", "test", "one", "test");
		System.out.print(test.getStudentid());
		System.out.print(test.getEmail());
		System.out.print(test.getStudentname());
		System.out.print(test.getK1energy());
		System.out.print(test.getK2energy());
		System.out.print(test.getK3tick1());
		System.out.print(test.getK3tick2());
		System.out.print(test.getMypreference());
		System.out.print(test.getConcerns());
	}
}
