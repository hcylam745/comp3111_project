package comp3111_project;

import javafx.beans.property.SimpleStringProperty;

public class OutputPerson {
    public SimpleStringProperty studentid;
    public SimpleStringProperty studentname;
    public Integer teamId;
    public Float k1energy;
    public Float k2energy;
    public Team team;
    public OutputPerson(String studentid, String studentname, int teamId, Team team) {
        this.studentid = new SimpleStringProperty(studentid);
        this.studentname = new SimpleStringProperty(studentname);
        this.teamId = (Integer) teamId;
        this.team = team;
        k1energy = 0.0f;
        k2energy = 0.0f;
    }
    public String getStudentid(){
        return studentid.get();
    }
    public String getStudentname(){
        return studentname.get();
    }
    public Integer getTeamId(){
        return teamId;
    }
    public String getTeamMembers(){
        String temp= "1. ";
        int k =0;
        for(int i=0;i<team.getNumberOfMembers();i++){
            if(team.getMember(i).getStudentid().equals(studentid.get())){
                continue;
            }
            temp+=team.getMember(i).getStudentname();
            if(i < team.getNumberOfMembers()-1){
                temp+="\n"+(k+2)+". ";
                k++;
            }
        }
        return temp;
    }
    public Float getTeamAvgk1(){
        k1energy =  team.averageK1();
        return k1energy;
    }
    public Float getTeamAvgk2(){
        k2energy = team.averageK2();
        return k2energy;
    }
}
