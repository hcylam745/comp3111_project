package comp3111_project;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.io.*;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.nio.charset.StandardCharsets;
import com.opencsv.CSVReader;
import javafx.scene.control.Button;
import com.opencsv.exceptions.CsvValidationException;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class Library extends Application {

	private TableView<Statistics> stat_table = new TableView<Statistics>(); //is this class needed? i'll leave the decision to sid (output)
	private TableView<Person> person_table = new TableView<Person>();
	private static TableView<Team> team_table = new TableView<Team>();
	private static Team teams[];
	private final ObservableList<Statistics> stat_data = FXCollections.observableArrayList(
			new Statistics("Total Number of Students", "100"),
			new Statistics("K1_Energy(Average, Min, Max)", "(59.8, 10, 80)"),
			new Statistics("K2_Energy(Average, Min, Max)", "(62.3, 40, 85)"), new Statistics("K3_Tick1 = 1", "12"),
			new Statistics("K3_Tick2 = 1", "3"), new Statistics("My_Preference = 1", "19"));

	private final static ObservableList<Person> person_data = FXCollections.observableArrayList();

	public static void read(String csvFile) throws IOException, CsvValidationException {
		int counter = 0;
		System.out.print("\n");
		try (var fr = new FileReader(csvFile, StandardCharsets.UTF_8); var reader = new CSVReader(fr)) {
			String [] tempArr;
			while((tempArr = reader.readNext()) != null) {
				if (counter == 0) {
					counter++;
					continue; 
				}
				person_data.add(new Person(tempArr[0],tempArr[1],tempArr[2],tempArr[3],
						tempArr[4],tempArr[5],tempArr[6],tempArr[7],tempArr[8]));
			}
		}
	}
	//for process
	public static void AllocateTeams(){
		teams = new Team[34];
		//put every person into a team
		for (int i=0; i<100; i++) {
			if(teams[i/3] == null) {
				teams[i/3] = new Team();
			}
			teams[i/3].addMember(person_data.get(i));
			person_data.get(i).setTeam(i/3+1);	
		}
		// add team to team table
		for (int i=0; i<34; i++) {
			team_table.getItems().add(teams[i]);
		}
	}
	public static void main(String[] args) throws Exception {

		String csvFile = "src/main/resources/data.CSV";
		Library.read(csvFile);
		Library.AllocateTeams();
		System.out.println("Hello");
		launch(args);

	}

	@Override
	public void start(Stage stage_stat) {
		Scene scene_stat = new Scene(new Group());
		stage_stat.setTitle("Table of students' personal data");
		stage_stat.setWidth(450);
		stage_stat.setHeight(500);

		final Label label_stat = new Label("Statistics");
		label_stat.setFont(new Font("Arial", 20));

		stat_table.setEditable(true);

		TableColumn entry_column = new TableColumn("Entry");
		entry_column.setMinWidth(100);
		entry_column.setCellValueFactory(new PropertyValueFactory<Statistics, String>("entry"));

		TableColumn value_column = new TableColumn("Value");
		value_column.setMinWidth(100);
		value_column.setCellValueFactory(new PropertyValueFactory<Statistics, String>("value"));

		stat_table.setItems(stat_data);
		stat_table.getColumns().addAll(entry_column, value_column);
		stat_table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		final VBox vbox_stat = new VBox();
		vbox_stat.setSpacing(5);
		vbox_stat.setPadding(new Insets(10, 0, 0, 10));
		vbox_stat.getChildren().addAll(label_stat, stat_table);

		((Group) scene_stat.getRoot()).getChildren().addAll(vbox_stat);
		stage_stat.setScene(scene_stat);


		Stage stage_person = new Stage();
		Scene scene_person = new Scene(new Group());
		stage_person.setTitle("Table of statistics data");
		stage_person.setWidth(450);
		stage_person.setHeight(500);

		final Label label_person = new Label("Person");
		label_person.setFont(new Font("Arial", 20));

		person_table.setEditable(true);

		TableColumn studentid_column = new TableColumn("Student_ID");
		studentid_column.setMinWidth(100);
		studentid_column.setCellValueFactory(new PropertyValueFactory<Statistics, String>("studentid"));

		TableColumn studentname_column = new TableColumn("Student_Name");
		studentname_column.setMinWidth(200);
		studentname_column.setCellValueFactory(new PropertyValueFactory<Statistics, String>("studentname"));

		TableColumn email_column = new TableColumn("email");
		email_column.setMinWidth(100);
		email_column.setCellValueFactory(new PropertyValueFactory<Statistics, String>("email"));
		
		TableColumn k1energy_column = new TableColumn("K1_Energy");
		k1energy_column.setMinWidth(100);
		k1energy_column.setCellValueFactory(new PropertyValueFactory<Statistics, String>("k1energy"));

		TableColumn k2energy_column = new TableColumn("k2_Energy");
		k2energy_column.setMinWidth(100);
		k2energy_column.setCellValueFactory(new PropertyValueFactory<Statistics, String>("k2energy"));

		TableColumn k3trick1_column = new TableColumn("K3_Tick1");
		k3trick1_column.setMinWidth(100);
		k3trick1_column.setCellValueFactory(new PropertyValueFactory<Statistics, String>("k3tick1"));

		TableColumn k3trick2_column = new TableColumn("K3_Tick2");
		k3trick2_column.setMinWidth(100);
		k3trick2_column.setCellValueFactory(new PropertyValueFactory<Statistics, String>("k3tick2"));

		TableColumn mypreference_column = new TableColumn("My_Preference");
		mypreference_column.setMinWidth(100);
		mypreference_column.setCellValueFactory(new PropertyValueFactory<Statistics, String>("mypreference"));

		TableColumn concerns_column = new TableColumn("Concerns");
		concerns_column.setMinWidth(100);
		concerns_column.setCellValueFactory(new PropertyValueFactory<Statistics, String>("concerns"));

		person_table.setItems(person_data);
		person_table.getColumns().addAll(studentid_column, studentname_column, email_column, k1energy_column, k2energy_column,
				k3trick1_column, k3trick2_column, mypreference_column, concerns_column);
		person_table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		final VBox vbox_person = new VBox();
		vbox_person.setSpacing(5);
		vbox_person.setPadding(new Insets(10, 0, 0, 10));
		vbox_person.getChildren().addAll(label_person, person_table);

		((Group) scene_person.getRoot()).getChildren().addAll(vbox_person);
		Button searchButton = new Button("Search");
		Button showStatisticsButton = new Button("Show Statistics");
		//add button to the scene
		// make a search box 
		TextField searchBox = new TextField();
		searchBox.setPromptText("Student_ID");
		TextField searchBox2 = new TextField();
		searchBox2.setPromptText("Student_Name");
		searchBox.setPrefColumnCount(10);
		searchBox2.setPrefColumnCount(10);
		// add search box to the scene
		vbox_person.getChildren().addAll(searchBox, searchBox2, searchButton, showStatisticsButton);
		searchButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event){
				String targetStudentId = searchBox.getText();
				String targetStudentName = searchBox2.getText();
				Person targetPerson;
				// search for the student id in the table and show it
				Person targetPerson2 = person_table.getItems().stream().filter(person -> person.getStudentname().equals(targetStudentName)).findFirst().orElse(null);
				Person targetPerson1 = person_table.getItems().stream().filter(person -> person.getStudentid().equals(targetStudentId)).findFirst().orElse(null);	
				if(targetPerson1 != null && targetPerson2 != null && !targetPerson1.getStudentid().equals(targetPerson2.getStudentid())){
					targetPerson = null;
					System.out.println("Contradictory information");
				}
				else if(targetPerson1 != null && targetPerson2 == null){
					targetPerson = targetPerson1;
				}
				else if(targetPerson2 != null && targetPerson1 == null){
					targetPerson = targetPerson2;
				}
				else if(targetPerson1.getStudentid().equals(targetPerson2.getStudentid())){
					targetPerson = targetPerson1; // they are both the same person information
				}
				else{
					targetPerson = null;
					System.out.println("Invalid Search Information");
				}
				if (targetPerson != null) {
					// show the person in the table
					person_table.getSelectionModel().select(targetPerson);
					person_table.scrollTo(targetPerson);
					System.out.println(targetPerson.getTeam());
					OutputPerson target = new OutputPerson(targetPerson.getStudentid(), targetPerson.getStudentname(),targetPerson.getTeam(),teams[targetPerson.getTeam()-1]);
					// make a new table to show the person
					TableView<OutputPerson> person_table2 = new TableView<OutputPerson>();
					person_table2.setEditable(true);
					TableColumn studentid_column2 = new TableColumn("Student_ID");
					studentid_column2.setMinWidth(100);
					studentid_column2.setCellValueFactory(new PropertyValueFactory<OutputPerson, String>("studentid"));
					TableColumn studentname_column2 = new TableColumn("Student_Name");
					studentname_column2.setMinWidth(200);
					studentname_column2.setCellValueFactory(new PropertyValueFactory<OutputPerson, String>("studentname"));
					TableColumn team_column2 = new TableColumn("Team");
					team_column2.setMinWidth(100);
					team_column2.setCellValueFactory(new PropertyValueFactory<OutputPerson, Integer>("TeamId"));
					person_table2.getItems().add(target);
					// add column of team members of person
					TableColumn teamMembers_column = new TableColumn("Team Members");
					teamMembers_column.setMinWidth(200);
					teamMembers_column.setCellValueFactory(new PropertyValueFactory<OutputPerson, String>("TeamMembers"));
					// add column of average k1 energy
					TableColumn k1energy_column2 = new TableColumn("K1_Energy");
					k1energy_column2.setMinWidth(100);
					k1energy_column2.setCellValueFactory(new PropertyValueFactory<OutputPerson, Float>("TeamAvgk1"));
					// add column of average k2 energy
					TableColumn k2energy_column2 = new TableColumn("k2_Energy");
					k2energy_column2.setMinWidth(100);
					k2energy_column2.setCellValueFactory(new PropertyValueFactory<OutputPerson, Float>("TeamAvgk2"));
					person_table2.getColumns().addAll(studentid_column2, studentname_column2, team_column2, teamMembers_column, k1energy_column2, k2energy_column2);
					person_table2.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
					Stage query = new Stage();
					Scene queryPerson = new Scene(new Group());
					final VBox vbox_person2 = new VBox();
					vbox_person2.setSpacing(5);
					vbox_person2.setPadding(new Insets(10, 0, 0, 10));
					vbox_person2.getChildren().addAll(label_person, person_table2);
					((Group) queryPerson.getRoot()).getChildren().addAll(vbox_person2);
					query.setScene(queryPerson);
					query.show();
				}
			}
		});
		// show stage_stat when showStatisticsButton is clicked
		showStatisticsButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				stage_stat.show();
			}
		});
		//make new stage for chart
		Stage stage_chart = new Stage();
		//make button called show graphs
		Button showGraphsButton = new Button("Show Graphs");
		//add button to the scene
		vbox_person.getChildren().addAll(showGraphsButton);
		// make line chart of K1 energy of all students
		//defining the axes
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
		final LineChart<Number,Number> lineChart = 
                new LineChart<Number,Number>(xAxis,yAxis);
		lineChart.setTitle("K1 and K2 energy of students");
		XYChart.Series series = new XYChart.Series();
		series.setName("K1 energy");
		// sort persons in person_table by K1 energy, descending
		ObservableList<Person> sortedPersons = person_table.getItems().sorted((p1, p2) -> p2.getK1energy().compareTo(p1.getK1energy()));
		// add sorted persons to the line chart
		int i = 0;
		for (Person person : sortedPersons) {
			series.getData().add(new XYChart.Data(i++, person.getK1energy()));
		}
		// make line chart of K2 energy of all students
		XYChart.Series series2 = new XYChart.Series();
		series2.setName("K2 energy");
		// add data to the line chart
		i =0;
		for (Person person : sortedPersons) {
			series2.getData().add(new XYChart.Data(i++, person.getK2energy()));
		}
		// add the line chart to the scene
		Scene scene  = new Scene(lineChart,800,600);
		lineChart.getData().add(series);
		lineChart.getData().add(series2);
		stage_chart.setScene(scene);
		// show the stage when showGraphsButton is clicked
		showGraphsButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				stage_chart.show();
			}
		});
		// new button to show team average energy
		Button showTeamAvgButton = new Button("Show Team Average Energy");
		// add button to the scene
		vbox_person.getChildren().addAll(showTeamAvgButton);
		// make new stage for team average energy
		Stage stage_teamavg = new Stage();
		// make new table for team average energy
		stage_person.setScene(scene_person);
		final NumberAxis teamXAxis = new NumberAxis();
        final NumberAxis teamyAxis = new NumberAxis();
		final LineChart<Number,Number> teamK1Chart = 
				new LineChart<Number,Number>(teamXAxis,teamyAxis);
		teamK1Chart.setTitle("Team Average K1 Energy");
		XYChart.Series teamK1Series = new XYChart.Series();
		teamK1Series.setName("Team Average K1 Energy");
		// sort teams in team_table by average K1 energy, descending
		ObservableList<Team> sortedTeams = team_table.getItems().sorted((t1, t2) -> t2.averageK1().compareTo(t1.averageK2()));
		// add sorted teams to the line chart
		i = 0;
		for (Team team : sortedTeams) {
			teamK1Series.getData().add(new XYChart.Data(i++, team.averageK1()));
		}
		// make new line chart for team average K2 energy
		XYChart.Series teamK2Series = new XYChart.Series();
		teamK2Series.setName("Team Average K2 Energy");
		// add data to the line chart
		i =0;
		for (Team team : sortedTeams) {
			teamK2Series.getData().add(new XYChart.Data(i++, team.averageK2()));
		}
		// make new line chart for team average K1+K2 energy
		XYChart.Series teamK3Series = new XYChart.Series();
		teamK3Series.setName("Team Average K1+K2 Energy");
		// add data to the line chart
		i =0;
		for (Team team : sortedTeams) {
			teamK3Series.getData().add(new XYChart.Data(i++, team.teamAvg()));
		}
		// add the line chart to the scene
		Scene teamScene  = new Scene(teamK1Chart,800,600);
		teamK1Chart.getData().add(teamK1Series);
		teamK1Chart.getData().add(teamK2Series);
		teamK1Chart.getData().add(teamK3Series);
		stage_teamavg.setScene(teamScene);
		// show the stage when showTeamAvgButton is clicked
		showTeamAvgButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				stage_teamavg.show();
			}
		});

		stage_person.show();
	}
}
