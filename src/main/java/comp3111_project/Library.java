package comp3111_project;

import java.io.*;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import comp3111_project.Person;
import javafx.scene.control.Button;

public class Library extends Application {

	private TableView<Statistics> stat_table = new TableView<Statistics>();
	private TableView<Person> person_table = new TableView<Person>();

	private final static ObservableList<Statistics> stat_data = FXCollections.observableArrayList();


	private final static ObservableList<Person> person_data = FXCollections.observableArrayList();

	public static void calculateStat() {
		Integer totalCount = 0;
		Double k1Avg = 0.0;
		Integer k1Min = 9999999;
		Integer k1Max = -1;
		Double k2Avg = 0.0;
		Integer k2Min = 9999999;
		Integer k2Max = -1;
		Integer k3Tick1 = 0;
		Integer k3Tick2 = 0;
		Integer Preference = 0;
		for (int i = 0; i < person_data.size(); i++) {
			totalCount++;
			Integer k1_temp = person_data.get(i).getK1energy();
			if (k1_temp < k1Min) {
				k1Min = k1_temp;
			}
			if (k1_temp > k1Max) {
				k1Max = k1_temp;
			}
			k1Avg = (k1Avg * (totalCount - 1) + k1_temp) / totalCount;
			Integer k2_temp = person_data.get(i).getK2energy();
			if (k2_temp < k2Min) {
				k2Min = k2_temp;
			}
			if (k2_temp > k2Max) {
				k2Max = k2_temp;
			}
			k2Avg = (k2Avg * (totalCount - 1) + k2_temp) / totalCount;
			if (person_data.get(i).getK3tick1() == true) {
				k3Tick1++;
			}
			if (person_data.get(i).getK3tick2() == true) {
				k3Tick2++;
			}
			if (person_data.get(i).getMypreference() == true) {
				Preference++;
			}
		}
		stat_data.add(new Statistics("Total Number of Students", String.valueOf(totalCount)));
		stat_data.add(new Statistics("K1_Energy(Average, Min, Max)", "(" + String.format("%.2f",k1Avg) + ", " + 
		String.valueOf(k1Min) + ", " + String.valueOf(k1Max) + ")"));
		stat_data.add(new Statistics("K2_Energy(Average, Min, Max)", "(" + String.format("%.2f",k2Avg) + ", " + 
		String.valueOf(k2Min) + ", " + String.valueOf(k2Max) + ")"));
		stat_data.add(new Statistics("K3_Tick1 = 1", String.valueOf(k3Tick1)));
		stat_data.add(new Statistics("K3_Tick2 = 1", String.valueOf(k3Tick2)));
		stat_data.add(new Statistics("My_Preference = 1", String.valueOf(Preference)));
	}
	
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
				for (int i = 0; i < tempArr.length; i++) {
					// i inbetween 3 and 7 inclusive are the inputs that are parsed into integers and used as variables for the person class, so if null input, then default to 0.
					if (tempArr[i] == "" && i >= 3 && i <= 7) {
						tempArr[i] = "0";
					}
				}
				person_data.add(new Person(tempArr[0],tempArr[1],tempArr[2],tempArr[3],
						tempArr[4],tempArr[5],tempArr[6],tempArr[7],tempArr[8]));
				counter++;
			}
		}
	}

	public static void readCsv() throws Exception {
		String csvFile = "src/main/resources/data.CSV";
		Library.read(csvFile);
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println("Hello");
		launch(args);

	}

	@Override
	public void start(Stage stage_stat) {
		
		Stage stage_select_input = new Stage();
		Scene scene_select_input = new Scene(new Group());
		stage_select_input.setTitle("Select Input Style");
		stage_select_input.setWidth(200);
		stage_select_input.setHeight(200);
		
		Button csvReadButton = new Button("Read CSV");
		Button autoGenerationButton = new Button("Auto-Generate");
		
		final VBox vbox_input = new VBox();
		vbox_input.getChildren().addAll(csvReadButton, autoGenerationButton);
		
		((Group) scene_select_input.getRoot()).getChildren().addAll(vbox_input);
		
		stage_select_input.setScene(scene_select_input);
		stage_select_input.show();
	
		Scene scene_stat = new Scene(new Group());
		stage_stat.setTitle("Table of students' personal data");
		stage_stat.setWidth(450);
		stage_stat.setHeight(500);

		final Label label_stat = new Label("Statistics");
		label_stat.setFont(new Font("Arial", 20));

		stat_table.setEditable(true);

		TableColumn rowindex_column = new TableColumn("Row_Index");
		rowindex_column.setMinWidth(100);
		// this adds a counter for row_index
		rowindex_column.setCellFactory(col -> new TableCell<Statistics, Void>(){
			@Override
			public void updateIndex(int index) {
				super.updateIndex(index);
				if(isEmpty() || index < 0) {
					setText(null);
				} else {
					setText(Integer.toString(index));
				}
			}
		});
		
		TableColumn entry_column = new TableColumn("Entry");
		entry_column.setMinWidth(100);
		entry_column.setCellValueFactory(new PropertyValueFactory<Statistics, String>("entry"));

		TableColumn value_column = new TableColumn("Value");
		value_column.setMinWidth(100);
		value_column.setCellValueFactory(new PropertyValueFactory<Statistics, String>("value"));

		stat_table.setItems(stat_data);
		stat_table.getColumns().addAll(rowindex_column, entry_column, value_column);
		stat_table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		final VBox vbox_stat = new VBox();
		vbox_stat.setSpacing(5);
		vbox_stat.setPadding(new Insets(10, 0, 0, 10));
		vbox_stat.getChildren().addAll(label_stat, stat_table);

		((Group) scene_stat.getRoot()).getChildren().addAll(vbox_stat);

		stage_stat.setScene(scene_stat);
		//stage_stat.show();

		Stage stage_person = new Stage();
		Scene scene_person = new Scene(new Group());
		stage_person.setTitle("Table of statistics data");
		stage_person.setWidth(450);
		stage_person.setHeight(500);

		final Label label_person = new Label("Person");
		label_person.setFont(new Font("Arial", 20));

		person_table.setEditable(true);

		TableColumn rowindex_column2 = new TableColumn("Row_Index");
		rowindex_column2.setMinWidth(100);
		// this adds a counter for row_index
		rowindex_column2.setCellFactory(col -> new TableCell<Statistics, Void>(){
			@Override
			public void updateIndex(int index) {
				super.updateIndex(index);
				if(isEmpty() || index < 0) {
					setText(null);
				} else {
					setText(Integer.toString(index));
				}
			}
		});
		
		TableColumn studentid_column = new TableColumn("Student_ID");
		studentid_column.setMinWidth(100);
		studentid_column.setCellValueFactory(new PropertyValueFactory<Statistics, String>("studentid"));

		TableColumn studentname_column = new TableColumn("Student_Name");
		studentname_column.setMinWidth(100);
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
		person_table.getColumns().addAll(rowindex_column2, studentid_column, studentname_column, email_column, k1energy_column, k2energy_column,
				k3trick1_column, k3trick2_column, mypreference_column, concerns_column);
		person_table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		final VBox vbox_person = new VBox();
		vbox_person.setSpacing(5);
		vbox_person.setPadding(new Insets(10, 0, 0, 10));
		vbox_person.getChildren().addAll(label_person, person_table);

		((Group) scene_person.getRoot()).getChildren().addAll(vbox_person);

		stage_person.setScene(scene_person);
		//stage_person.show();
		
		csvReadButton.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					Library.readCsv();
				} catch (Exception e) {
					System.out.print("Invalid CSV Input");
					return;
				}
				Library.calculateStat();
				stage_select_input.hide();
				stage_person.show();
				stage_stat.show();
			}
		});
		
		Stage stage_auto_generate = new Stage();
		
		//gridpane and hbox used to line up text and textboxes horizontally, as well as to put them in rows.
		GridPane root = new GridPane();
		root.setAlignment(Pos.CENTER);
		
		HBox box1 = new HBox();
		Text studentNoPrompt = new Text();
		studentNoPrompt.setText("Input Number of Students (Min 200, Max 500)");
		TextField studentNo = new TextField();
		
		box1.getChildren().addAll(studentNoPrompt,studentNo);
		box1.setPrefWidth(600);
		box1.setSpacing(50);
		HBox.setHgrow(studentNo, Priority.ALWAYS);
		
		HBox box2 = new HBox();
		Text avgK1Prompt = new Text();
		avgK1Prompt.setText("Input Average K1 Energy per Class (Please input a percentage without the % sign)");
		TextField avgK1 = new TextField();
		
		box2.getChildren().addAll(avgK1Prompt,avgK1);
		box2.setPrefWidth(600);
		box2.setSpacing(50);
		HBox.setHgrow(avgK1, Priority.ALWAYS);
		
		HBox box3 = new HBox();
		Text avgK2Prompt = new Text();
		avgK2Prompt.setText("Input Average K2 Energy per Class (Please input a percentage without the % sign)");
		TextField avgK2 = new TextField();
		
		box3.getChildren().addAll(avgK2Prompt, avgK2);
		box3.setPrefWidth(600);
		box3.setSpacing(50);
		HBox.setHgrow(avgK2, Priority.ALWAYS);
		
		HBox box4 = new HBox();
		Text probK3_1Prompt = new Text();
		probK3_1Prompt.setText("Input Probablility that K3_Tick1 is True (Please input a percentage without the % sign)");
		TextField probK3_1 = new TextField();
		
		box4.getChildren().addAll(probK3_1Prompt,probK3_1);
		box4.setPrefWidth(600);
		box4.setSpacing(50);
		HBox.setHgrow(probK3_1, Priority.ALWAYS);
		
		HBox box5 = new HBox();
		Text probK3_2Prompt = new Text();
		probK3_2Prompt.setText("Input Probablility that K3_Tick2 is True (Please input a percentage without the % sign)");
		TextField probK3_2 = new TextField();
		
		box5.getChildren().addAll(probK3_2Prompt,probK3_2);
		box5.setPrefWidth(600);
		box5.setSpacing(50);
		HBox.setHgrow(probK3_2, Priority.ALWAYS);
		
		HBox box6 = new HBox();
		Text probPrefPrompt = new Text();
		probPrefPrompt.setText("Input Probablility that My_Preference is True (Please input a percentage without the % sign)");
		TextField probPref = new TextField();
		
		box6.getChildren().addAll(probPrefPrompt, probPref);
		box6.setPrefWidth(600);
		box6.setSpacing(50);
		HBox.setHgrow(probPref, Priority.ALWAYS);
		
		HBox box7 = new HBox();
		Button submitButton = new Button("Submit");
		
		box7.getChildren().addAll(submitButton);
		box7.setPrefWidth(600);
		HBox.setHgrow(submitButton, Priority.ALWAYS);
		
		root.add(box1, 0, 0);
		root.add(box2, 0, 1);
		root.add(box3, 0, 2);
		root.add(box4, 0, 3);
		root.add(box5, 0, 4);
		root.add(box6, 0, 5);
		root.add(box7, 0, 6);
		
		Scene scene_auto_generate = new Scene(root, 800, 800);
		
		stage_auto_generate.setHeight(300);
		stage_auto_generate.setWidth(700);
		stage_auto_generate.setScene(scene_auto_generate);
		
		autoGenerationButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				stage_auto_generate.show();
			}
		});
		
		submitButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// get text from the textfields & attempt to parse, pass error if cannot parse.
				Integer studentNumberNo;
				Integer averageK1No;
				Integer averageK2No;
				Integer probabilityK3_1No;
				Integer probabilityK3_2No;
				Integer probabilityPrefNo;
				try {
					studentNumberNo = Integer.parseInt(studentNo.getText());
					averageK1No = Integer.parseInt(avgK1.getText());
					averageK2No = Integer.parseInt(avgK2.getText());
					probabilityK3_1No = Integer.parseInt(probK3_1.getText());
					probabilityK3_2No = Integer.parseInt(probK3_2.getText());
					probabilityPrefNo = Integer.parseInt(probPref.getText());
					
				} catch (Exception e) {
					HBox box8 = new HBox();
					Text errorPrompt = new Text();
					box8.getChildren().addAll(errorPrompt);
					errorPrompt.setText("There was an error in parsing some of the data fields inputted.\nPlease input all integers");
					root.add(box8, 0, 7);
					return;
				}
				// checks to make sure the percentages inputted are in range of 0 to 100.
				if (averageK1No > 100 || averageK1No < 0 || averageK2No > 100 || averageK2No < 2 || probabilityK3_1No > 100 ||
						probabilityK3_1No < 0 || probabilityK3_2No > 100 || probabilityK3_2No < 0 || probabilityPrefNo > 100 || 
						probabilityPrefNo < 0) {
					HBox box8 = new HBox();
					Text errorPrompt = new Text();
					box8.getChildren().addAll(errorPrompt);
					errorPrompt.setText("There was an error in parsing some of the data fields inputted.\nPlease input all percentages in range of 0 to 100");
					root.add(box8, 0, 7);
					return;
				}
				// if studentnumberno is larger than 500, set to 500, if smaller than 200, set to 200.
				if (studentNumberNo > 500) {
					studentNumberNo = 500;
				} else if (studentNumberNo < 200) {
					studentNumberNo = 200;
				}
				
				//generate a list of sample names from sample_names csv and make the size according to studentNumberNo
				ArrayList<String> firstNames = new ArrayList<String>();
				ArrayList<String> lastNames = new ArrayList<String>();
				String sampleNamesFile = "src/main/resources/sample_names.CSV";
				try {
					File file = new File(sampleNamesFile);
					InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "UTF-8");
					BufferedReader br = new BufferedReader(isr);
					String line = " ";
					while ((line = br.readLine()) != null) {
						firstNames.add(line.split(",")[0]);
						lastNames.add(line.split(",")[1]);
					}
					br.close();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
				ArrayList<String> sampleNames = new ArrayList<String>();
				Integer counter = 0;
				for (Integer i = 0; i < firstNames.size(); i++) {
					if (counter >= studentNumberNo) {
						break;
					}
					for (Integer j = 0; j < lastNames.size(); j++) {
						if (counter >= studentNumberNo) {
							break;
						}
						sampleNames.add(firstNames.get(i) + "," + lastNames.get(j));
						counter++;
					}
				}
				
				//generate arraylist of size studentnumberno that has unique student ids.
				ArrayList<String> sampleIDs = new ArrayList<String>();
				Integer id = Integer.parseInt(String.valueOf((int)(Math.random() * 10)) + String.valueOf((int)(Math.random() * 10)) + 
						String.valueOf((int)(Math.random() * 10)) + String.valueOf((int)(Math.random() * 10)) + 
						String.valueOf((int)(Math.random() * 10)) + String.valueOf((int)(Math.random() * 10)) + 
						String.valueOf((int)(Math.random() * 10)) + String.valueOf((int)(Math.random() * 10)));
				// to make sure that the ranomly generated ID will never go over 99999999
				if (id >= 90000000) {
					id -= 10000000;
				}
				for (Integer i = 0; i < studentNumberNo; i++) {
					sampleIDs.add(String.valueOf(id));
					id += (int)Math.round(Math.random() * 1000) + 1;
				}
				
				//generate array of k1 and k2 energy per student.
				//
				// k1temp and k2temp randomly generate a number from 0 - 100, then it takes average k1/k2 inputted by the user, and
				// adds another entry into the k1/k2 array such that the average of the two added numbers always equals k1/k2 average
				// inputted by the user.
				//
				averageK1No += (int)((Math.random() * 10) - 5);
				averageK2No += (int)((Math.random() * 10) - 5);
				ArrayList<String> k1Energies = new ArrayList<String>();
				ArrayList<String> k2Energies = new ArrayList<String>();
				for (Integer i = 0; i < Math.floor(studentNumberNo/2); i++) {
					Long k1temp = Math.round((Math.random() * 100));
					Long k2temp = Math.round((Math.random() * 100));
					Integer k1temp_2 = Math.round((averageK1No * 2) - k1temp);
					Integer k2temp_2 = Math.round((averageK2No * 2) - k2temp);
					if (k1temp_2 > 100) {
						k1temp += k1temp_2 - 100;
						k1temp_2 = 100;
					}
					if (k1temp_2 < 0) {
						k1temp += k1temp_2;
						k1temp_2 = 0;
					}
					if (k2temp_2 > 100) {
						k2temp += k2temp_2 - 100;
						k2temp_2 = 100;
					}
					if (k2temp_2 < 0) {
						k2temp += k2temp_2;
						k2temp_2 = 0;
					}
					k1Energies.add(String.valueOf(k1temp));
					k2Energies.add(String.valueOf(k2temp));
					k1Energies.add(String.valueOf(k1temp_2));
					k2Energies.add(String.valueOf(k2temp_2));
				}
				// if the number of students is odd, then the method of adding pairs above will not work, so just add a new value
				// exactly on the averages such that the average will also come out to the same.
				if (studentNumberNo % 2 != 0) {
					k1Energies.add(String.valueOf(averageK1No));
					k2Energies.add(String.valueOf(averageK2No));
				}
				
				//all inputs have been parsed, now generate dataset.
				Integer k3_1No = (int)Math.round(probabilityK3_1No / 100.0 * studentNumberNo);
				Integer k3_2No = (int)Math.round(probabilityK3_2No / 100.0 * studentNumberNo);
				Integer prefNo = (int)Math.round(probabilityPrefNo / 100.0 * studentNumberNo);
				for (Integer i = 0; i < studentNumberNo; i++) {
					String k3_1 = "0";
					if (((Math.random() > 0.5) || (k3_1No == studentNumberNo - i)) && k3_1No > 0) {
						k3_1 = "1";
						k3_1No--;
					}
					String k3_2 = "0";
					if (((Math.random() > 0.5) || (k3_2No == studentNumberNo - i)) && k3_2No > 0) {
						k3_2 = "1";
						k3_2No--;
					}
					String pref = "0";
					if (((Math.random() > 0.5) || (prefNo == studentNumberNo - i)) && prefNo > 0) {
						pref = "1";
						prefNo--;
					}
					String email = Character.toLowerCase(sampleNames.get(i).split(",")[0].charAt(0)) + 
							sampleNames.get(i).split(",")[1].toLowerCase() + "@connect.ust.hk";
					person_data.add(new Person(sampleIDs.get(i), sampleNames.get(i), email, k1Energies.get(i), k2Energies.get(i), 
							k3_1, k3_2, pref, ""));
				}
				Library.calculateStat();
				stage_select_input.hide();
				stage_auto_generate.hide();
				stage_person.show();
				stage_stat.show();
			}
		});
	}
}
