package comp3111_project;

public class Team {
	int NumberOfMembers;
	Person teamMembers[];
	public Team() {
		NumberOfMembers = 0;
		teamMembers = new Person[4];
	}
	public void addMember(Person p) {
		teamMembers[NumberOfMembers] = p;
		NumberOfMembers++;
	}
	public Float averageK1() {
		float sum=0;
		for(int i=0; i<NumberOfMembers; i++) {
			sum+= teamMembers[i].getK1energy();
		}
		return sum/NumberOfMembers;
	}
	
	public Float averageK2() {
		float sum=0;
		for(int i=0; i<NumberOfMembers; i++) {
			sum+= teamMembers[i].getK2energy();
		}
		return sum/NumberOfMembers;
	}
	
	public Float teamAvg() {
		return (this.averageK1()+this.averageK2())/2;
	}
	public int getNumberOfMembers() {
		return NumberOfMembers;
	}
	public Person getMember(int i) {
		return teamMembers[i];
	}
	public Person[] getTeamMembers() { return teamMembers; }
}
