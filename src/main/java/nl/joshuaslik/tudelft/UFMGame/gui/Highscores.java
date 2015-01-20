package nl.joshuaslik.tudelft.UFMGame.gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.SortType;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import nl.joshuaslik.tudelft.UFMGame.backend.Human;
import nl.joshuaslik.tudelft.UFMGame.backend.Save;
import nl.joshuaslik.tudelft.UFMGame.backend.Team;
import nl.joshuaslik.tudelft.UFMGame.backend.User;

/**
 * Class to handle the highscore page
 * @author Sander Benoist
 *
 */
public class Highscores {
	
	@FXML
    private TableView<User> highscoretable;
    @FXML
    private TableColumn<User, String> usernames, goals;
	
	@FXML
	private void initialize(){
		LinkedHashMap<String, Integer> result = Save.getHighscore();
		ArrayList<User> arraylistusernames = new ArrayList<User>();
		for ( String key: result.keySet()){
			arraylistusernames.add(new Human(new Team("test", "test", "test"), key, 5));
		}
		ObservableList<User> observableUsernames = FXCollections.observableArrayList(arraylistusernames);

		highscoretable.setItems(observableUsernames);
		usernames.setCellValueFactory(new PropertyValueFactory<User, String>(
				"userName"));
		goals.setCellValueFactory(new PropertyValueFactory<User, String>(
				"userName"));
		goals.setCellFactory(new Callback<TableColumn<User, String>, TableCell<User, String>>(){
			@Override
			public TableCell<User, String> call(TableColumn<User, String> param){
				TableCell<User, String> cell = new TableCell<User, String>(){
					@Override
					public void updateItem(String item, boolean empty){
						if(item!= null){
							setText(Integer.toString(result.get(item)));
						}
					}
				};
				return cell;
			}
		});
		highscoretable.getSortOrder().add(goals);
		goals.setSortType(SortType.DESCENDING);
		goals.setSortable(true);
	}
	
	/**
	 * Loads the highscore page
	 * @throws IOException is thrown if the FXML file cannot be parsed. 
	 */
	public static void start() throws IOException {	
		AnchorPane scene = (AnchorPane) FXMLLoader.load(Class.class.getResource("/data/gui/pages-menu/Highscores.fxml"));
		Main.setCenter(scene);
		Topbar.start("HighScores");
	}
	
	/**
	 * handles clicking on the return button
	 * @throws IOException is thrown if the FXML file cannot be parsed.
	 */
	@FXML
	protected void handleReturn() throws IOException {
		MainMenu.start();
	}

}
