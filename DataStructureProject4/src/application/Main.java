package application;
	
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	static Stage primaryStage = new Stage();
	static ObservableList<Movie> tableMovie = FXCollections.observableArrayList();
	static HashTableMovie hash = new HashTableMovie();
	@Override
	public void start(Stage primaryStage1) {
		try {
			MovieManagement manage = new MovieManagement();
			primaryStage.setScene(manage.getMovieScene());
			primaryStage.show();
			// now i want to test commit
			// another test
			//Last Test
			// hah another
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
