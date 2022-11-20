package comp3111_project;

import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;

/**
 * This is the association class used to connect the main function to the ATU engine
 */
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

	/**
	 * Constructor of a person
	 * @param student_id
	 * @param student_name
	 * @param email
	 * @param k1_energy
	 * @param k2_energy
	 * @param k3_tick1
	 * @param k3_tick2
	 * @param my_preference
	 * @param concerns
	 */
	public Person(String student_id, String student_name, String email, String k1_energy, String k2_energy, String k3_tick1,
			String k3_tick2, String my_preference, String concerns) {
		this.studentid = new SimpleStringProperty(student_id);
		this.studentname = new SimpleStringProperty(student_name);
		this.email = new SimpleStringProperty(email);
		try {
			this.k1energy = Integer.parseInt(k1_energy);
			this.k2energy = Integer.parseInt(k2_energy);
			this.k3tick1 = (Integer.parseInt(k3_tick1) == 0) ? false : true;
			this.k3tick2 = (Integer.parseInt(k3_tick2) == 0) ? false : true;
			this.mypreference = (Integer.parseInt(my_preference) == 0) ? false : true;
		} catch (Exception e) {
			System.out.print("There was an error in parsing information for student " + student_name + ". Filling information with blanks.\n");
			System.out.println(e);
			this.k1energy = 0;
			this.k2energy = 0;
			this.k3tick1 = false;
			this.k3tick2 = false;
			this.mypreference = false;
		}
		this.concerns = new SimpleStringProperty(concerns);
	}

	/**
	 * Get person's studentID
	 * @return
	 */
	public String getStudentid() {
		return studentid.get();
	}

	/**
	 * Get person's email
	 * @return
	 */
	public String getEmail() {
		return email.get();
	}

	/**
	 * get Person's name
	 * @return
	 */
	public String getStudentname() {
		return studentname.get();
	}

	/**
	 * get Person's K1 Energy
	 * @return
	 */
	public Integer getK1energy() {
		return k1energy;
	}

	/**
	 * get Person's K2 energy
	 * @return
	 */
	public Integer getK2energy() {
		return k2energy;
	}

	/**
	 * 	get Person's K3 tick1 preference
	 * @return
	 */
	public Boolean getK3tick1() {
		return k3tick1;
	}

	/**
	 * get Person's K3 tick 2 preference
	 * @return
	 */
	public Boolean getK3tick2() {
		return k3tick2;
	}

	/**
	 * get Person's preference on being a leader
	 * @return
	 */
	public Boolean getMypreference() {
		return mypreference;
	}

	/**
	 * get Person's concerns
	 * @return
	 */
	public String getConcerns() {
		return concerns.get();
	}

	/**
	 * Associate a person with a paritcular team
	 * @param x
	 */
	public void setTeam(int x) {
		this.TeamNumber = x;
	}

	/**
	 * get a Person's team id
 	 * @return
	 */
	public int getTeam() {
		return this.TeamNumber;
	}
}
