package comp3111_project;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


import org.junit.Before;
import org.junit.Test;

public class PersonTester {
	Person test;
	
	@Before
	public void setup() throws Exception {
		
	}
	
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