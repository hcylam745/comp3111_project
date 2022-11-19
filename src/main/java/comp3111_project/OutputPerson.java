package comp3111_project;

import javafx.beans.property.SimpleStringProperty;

/**
 * This Class is the Association Class used to help display a person's information to the GUI
 */
public class OutputPerson {
    //Store ID information of the Person for GUI
    public SimpleStringProperty studentid;
    //Store name information of the Person for GUI
    public SimpleStringProperty studentname;
    public Integer teamId;
    //Stores K1 energy of a peron
    public Float k1energy;
    //Stores K2 energy of a person
    public Float k2energy;
    //Stores the Team information of a particular output person
    public Team team;

    /**
     * Basic Constructor to generate an Output Person
     * @param studentid
     * @param studentname
     * @param teamId
     * @param team
     */
    public OutputPerson(String studentid, String studentname, int teamId, Team team) {
        this.studentid = new SimpleStringProperty(studentid);
        this.studentname = new SimpleStringProperty(studentname);
        this.teamId = (Integer) teamId;
        this.team = team;
        k1energy = 0.0f;
        k2energy = 0.0f;
    }

    /**
     * Getter for studentId
     * @return
     */
    public String getStudentid(){
        return studentid.get();
    }

    /**
     * Getter for studentName
     * @return
     */
    public String getStudentname(){
        return studentname.get();
    }

    /**
     * Getter for TeamID
     * @return
     */
    public Integer getTeamId(){
        return teamId;
    }

    /**
     * gets Team avg k1 of output person
     * @return
     */
    public Float getTeamAvgk1(){
        k1energy =  team.averageK1();
        return k1energy;
    }

    /**
     * gets Team avg k2 of output person
     * @return
     */
    public Float getTeamAvgk2(){
        k2energy = team.averageK2();
        return k2energy;
    }
}
