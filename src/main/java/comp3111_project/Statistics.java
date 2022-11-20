package comp3111_project;

import javafx.beans.property.SimpleStringProperty;

/**
 * This is the class used for calculating and storing Statisitics information
 */
public class Statistics {
	//Storing first name
	private final SimpleStringProperty entry;
	//Storing lastname
	private final SimpleStringProperty value;

	/**
	 * Constructor of Statiscis of Person via First and Last Name
	 * @param fName
	 * @param lName
	 */
	public Statistics(String fName, String lName) {
		this.entry = new SimpleStringProperty(fName);
		this.value = new SimpleStringProperty(lName);
	}

	/**
	 * get First Name
	 * @return
	 */
	public String getEntry() {
		return entry.get();
	}

	/**
	 * sets First Name
	 * @param val
	 */
	public void setEntry(String val) {
		entry.set(val);
	}

	/**
	 * gets Last Name
	 * @return
	 */
	public String getValue() {
		return value.get();
	}

	/**
	 * sets Last Name
	 * @param val
	 */
	public void setValue(String val) {
		value.set(val);
	}
}
