package nl.joshuaslik.tudelft.UFMGame.gui;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import nl.joshuaslik.tudelft.UFMGame.backend.Game;
import nl.joshuaslik.tudelft.UFMGame.backend.Save;
import nl.joshuaslik.tudelft.UFMGame.backend.Team;
import nl.joshuaslik.tudelft.UFMGame.backend.formation.Form343;
import nl.joshuaslik.tudelft.UFMGame.gui.game.MainGame;

public class NewGameController {
	
		private Team choosenteam;
		private static String username;
	
	 	@FXML
	    private TableView<Team> teamtable;
	    @FXML
	    private TableColumn<Team, String> teamColumn;
	    @FXML
	    private TableColumn<Team, String> coachColumn;
	    @FXML
	    private TableColumn<Team, String> averagedefence;
	    @FXML
	    private TableColumn<Team, String> averagestamina;
	    @FXML
	    private TableColumn<Team, String> averageattack;
	    @FXML
	    private TableColumn<Team, String> totalplayers;
	    @FXML
	    private TableColumn<Team, String> teamvalue;
	    
	    /**
	     * Initializes the controller class. This method is automatically called
	     * after the fxml file has been loaded.
	     */
	    @FXML
	    private void initialize() {
	    	
	    	ObservableList<Team> teams = FXCollections.observableArrayList(Save.loadTeams());
	    	teamtable.setItems(teams);
	    	
	    	teamColumn.setCellValueFactory(new PropertyValueFactory<Team, String>(
					"teamName"));
			coachColumn.setCellValueFactory(new PropertyValueFactory<Team, String>(
					"coachName"));
			averagedefence.setCellValueFactory(new PropertyValueFactory<Team, String>(
					"averageDefencePower"));
			averagestamina.setCellValueFactory(new PropertyValueFactory<Team, String>(
					"averageStamina"));
			averageattack.setCellValueFactory(new PropertyValueFactory<Team, String>(
					"averageAttackPower"));
			totalplayers.setCellValueFactory(new PropertyValueFactory<Team, String>(
					"totalPlayers"));
			teamvalue.setCellValueFactory(new PropertyValueFactory<Team, String>(
					"teamValue"));
	    
	        // Listen for selection changes 
			teamtable.getSelectionModel().selectedItemProperty().addListener(
					(observable, oldValue, newValue) -> selectedTeam(newValue));
	    }
	    
	    
	    @FXML
		protected void handleChooseTeam() {
	    	if (choosenteam != null) {
				Team chosenTeam = teamtable.getSelectionModel().getSelectedItem();
				Game Game1 = Save.newGame(chosenTeam, username);
				MainGame.setGame(Game1);
				Form343 form = new Form343(Game1.getUser().getTeam());
				Game1.getUser().getTeam().changeFormationType(form);
				MainGame.initialize();
				try {
					MainGame.start();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	    
	    public void selectedTeam(Team team){
	    	choosenteam = team;
	    }
	    
	    public static void start(String user) throws IOException {
	    	username = user;
	 
			AnchorPane scene = FXMLLoader.load(Class.class.getResource("/data/gui/pages-menu/NewGame.fxml"));
			Main.setCenter(scene);
		}

}