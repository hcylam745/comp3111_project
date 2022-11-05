package comp3111_project;

import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;

public class Person {
	private final SimpleStringProperty studentid;
	private final SimpleStringProperty studentname;
	private final SimpleStringProperty email;
	private int TeamNumber = -1; //-1 means not allocated a team
	private Integer k1energy; //these aren't final so that the try catch doesn't return an error.
	private Integer k2energy;
	private Boolean k3tick1;
	private Boolean k3tick2;
	private Boolean mypreference;
	private final SimpleStringProperty concerns;

	public Person(String student_id, String student_name, String email, String k1_energy, String k2_energy, String k3_tick1,
			String k3_tick2, String my_preference, String concerns) {
		this.studentid = new SimpleStringProperty(student_id);
		this.studentname = new SimpleStringProperty(student_name);
		this.email = new SimpleStringProperty(email);
		try {
			this.mypreference = (Integer.parseInt(my_preference) == 0) ? false : true;
		} catch (Exception e) {
			System.out.print("There was an error in parsing preference information for student " + student_name + ". Filling preference with false.\n");
			this.mypreference = false;
		}
		try {
			this.k1energy = Integer.parseInt(k1_energy);
			this.k2energy = Integer.parseInt(k2_energy);
			this.k3tick1 = (Integer.parseInt(k3_tick1) == 0) ? false : true;
			this.k3tick2 = (Integer.parseInt(k3_tick2) == 0) ? false : true;
		} catch (Exception e) {
			System.out.print("There was an error in parsing information for student " + student_name + ". Filling information with blanks.\n");
			System.out.println(e);
			this.k1energy = 0;
			this.k2energy = 0;
			this.k3tick1 = false;
			this.k3tick2 = false;
		}
		this.concerns = new SimpleStringProperty(concerns);
	}

	public String getStudentid() {
		return studentid.get();
	}

//	public void setStudentid(String val) {
//		studentid.set(val);
//	}

	public String getEmail() {
		return email.get();
	}
	
	public String getStudentname() {
		return studentname.get();
	}

//	public void setStudentname(String val) {
//		studentname.set(val);
//	}

	public Integer getK1energy() {
		return k1energy;
	}

//	public void setK1energy(String val) {
//		k1energy.set(val);
//	}

	public Integer getK2energy() {
		return k2energy;
	}

//	public void setK2energy(String val) {
//		k2energy.set(val);
//	}

	public Boolean getK3tick1() {
		return k3tick1;
	}

//	public void setK3trick1(String val) {
//		k3trick1.set(val);
//	}

	public Boolean getK3tick2() {
		return k3tick2;
	}

//	public void setK3trick2(String val) {
//		k3trick2.set(val);
//	}

	public Boolean getMypreference() {
		return mypreference;
	}

//	public void setMypreference(String val) {
//		mypreference.set(val);
//	}

	public String getConcerns() {
		return concerns.get();
	}

	public void setTeam(int x) {
		this.TeamNumber = x;
	}
	public int getTeam() {
		return this.TeamNumber;
	}
//	public void setConcerns(String val) {
//		concerns.set(val);
//	}
}
