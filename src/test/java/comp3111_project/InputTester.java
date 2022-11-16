package comp3111_project;

import org.junit.Before;
import org.junit.Test;

import javafx.scene.control.TextField;

public class InputTester {
	
	//Library test;

	
	@Before
	public void setup() throws Exception {
		//test = new Library();
		//JFXPanel fxPanel = new JFXPanel();

	}
	
	@Test
	public void test1() throws Exception {
		Library.main(null);
		TextField studentNo = new TextField();
		TextField avgK1 = new TextField();
		TextField avgK2 = new TextField();
		TextField probK3_1 = new TextField();
		TextField probK3_2 = new TextField();
		TextField probPref = new TextField();
		studentNo.setText("301");
		avgK1.setText("50");
		avgK2.setText("50");
		probK3_1.setText("50");;
		probK3_2.setText("50");
		probPref.setText("50");
		Library.submitAutogen(studentNo, avgK1, avgK2, probK3_1,
				probK3_2, probPref);
		Library.calculateStat();
	}
	
	@Test
	public void test2() throws Exception {
		Library.readCsv();
		Library.calculateStat();
	}
	
	@Test
	public void test3() throws Exception {
		TextField studentNo = new TextField();
		TextField avgK1 = new TextField();
		TextField avgK2 = new TextField();
		TextField probK3_1 = new TextField();
		TextField probK3_2 = new TextField();
		TextField probPref = new TextField();
		studentNo.setText("A");
		avgK1.setText("B");
		avgK2.setText("C");
		probK3_1.setText("D");;
		probK3_2.setText("E");
		probPref.setText("F");
		Library.submitAutogen(studentNo, avgK1, avgK2, probK3_1,
				probK3_2, probPref);
		Library.calculateStat();
	}
	
	@Test
	public void test4() throws Exception {
		TextField studentNo = new TextField();
		TextField avgK1 = new TextField();
		TextField avgK2 = new TextField();
		TextField probK3_1 = new TextField();
		TextField probK3_2 = new TextField();
		TextField probPref = new TextField();
		studentNo.setText("2000");
		avgK1.setText("2000");
		avgK2.setText("2000");
		probK3_1.setText("2000");;
		probK3_2.setText("2000");
		probPref.setText("2000");
		Library.submitAutogen(studentNo, avgK1, avgK2, probK3_1,
				probK3_2, probPref);
		Library.calculateStat();
	}
	
	@Test
	public void test5() throws Exception {
		TextField studentNo = new TextField();
		TextField avgK1 = new TextField();
		TextField avgK2 = new TextField();
		TextField probK3_1 = new TextField();
		TextField probK3_2 = new TextField();
		TextField probPref = new TextField();
		studentNo.setText("-1000");
		avgK1.setText("-1000");
		avgK2.setText("-1000");
		probK3_1.setText("-1000");;
		probK3_2.setText("-1000");
		probPref.setText("-1000");
		Library.submitAutogen(studentNo, avgK1, avgK2, probK3_1,
				probK3_2, probPref);
		Library.calculateStat();
	}
}
