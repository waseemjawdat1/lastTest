package application;


import java.time.LocalDate;
import java.util.Calendar;

import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class MovieManagement {
private TableView <Movie> movieTable;
private TableColumn<Movie, String> titleCol , descriptionCol;
private TableColumn <Movie, Integer> releaseYearCol;
private TableColumn<Movie , Double> ratingCol;
private Button add , delete , update;
private TextField search;
private Label searchL;
private RadioButton byTitle, byYear;
private VBox all;
private MenuItem readFromFile =new ReadAndSaveToFile().getRead();
private MenuItem saveToFile = new ReadAndSaveToFile().getSave();
private MenuBar menu;
private Menu file , movie;
private MenuItem addMovie, updateMovie, deleteMovie , sortMovies;
private Scene movieScene;
Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();


public MovieManagement() {
	movieTable = new TableView<Movie>();
	titleCol = new TableColumn<Movie, String>("Title");
	titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
	descriptionCol = new TableColumn<Movie, String>("Description");
	descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
	releaseYearCol = new TableColumn<Movie, Integer>("Release Year");
	releaseYearCol.setCellValueFactory(new PropertyValueFactory<>("releaseYear"));
	ratingCol = new TableColumn<Movie, Double> ("Rating");
	ratingCol.setCellValueFactory(new PropertyValueFactory<>("rate"));
	
	movieTable.getColumns().addAll(titleCol , descriptionCol , releaseYearCol , ratingCol);
	movieTable.setItems(Main.tableMovie);
	movieTable.setMinHeight(500);
	movieTable.setMaxWidth(1250);
	movieTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	String style = "-fx-background-color: #E5D9F2;-fx-border-color: #A294F9; -fx-text-fill: black;-fx-font-family: 'Montserrat'; ";
	for (int i = 0 ; i < movieTable.getColumns().size(); i++) {
		movieTable.getColumns().get(i).setStyle(style);
		movieTable.getColumns().get(i).setSortable(false);
	}
	add = new MyButton ("Add Movie",2).getB1();
	add.setOnAction(e->{
		Label titleL = new StandardLabel("Title : ").getL();
		TextField title1 = new MyTextField().getT();
		Label descriptionL = new StandardLabel("Description : ").getL();
		TextField description1 = new MyTextField().getT();
		Label releaseYearL = new StandardLabel("Release Year").getL();
		DatePicker releaseYear1= new DatePicker();
		Label ratingL = new StandardLabel("Rating").getL();
		TextField rating1 = new MyTextField().getT();
		GridPane g= new GridPane ();
		g.addColumn(0, titleL , descriptionL , ratingL , releaseYearL);
		g.addColumn(1, title1 , description1 , rating1 , releaseYear1);
		g.setVgap(10);
		g.setHgap(10);
		g.setAlignment(Pos.CENTER);
		
		Button add = new MyButton ("Add",2).getB1();
		Button clear = new MyButton ("Clear",2).getB1();
		
		clear.setOnAction(ee->{
			title1.clear();
			description1.clear();
			releaseYear1.setValue(null);
			rating1.clear();
		});
		
		add.setOnAction(ee->{
			String title = title1.getText();
			if (title == null || title.isEmpty()) {
				notValid("Title is empty");
				return;
			}
			if (MovieCatalog.searchByTitle(title)) {
				notValid("There are a movie with this title");
				return;
			}
			
			String description = description1.getText();
			if (description == null || description.isEmpty()) {
				notValid("Description is empty");
				return;
			}
			String ratingS = rating1.getText();
			if (ratingS == null || ratingS.isEmpty()) {
				notValid("Rating is empty");
				return;
			}
			double rating = -1;
			try {
				rating = Double.parseDouble(ratingS);
			}catch (Exception eee) {
				notValid("Rating must be a double number");
				return;
			}
			if (rating < 0 || rating > 10) {
				notValid("Rating must be between 0-10");
				return;
			}
			LocalDate yearL = releaseYear1.getValue();
			if (yearL == null) {
				notValid("Select Year");
				return;
			}
			int year = yearL.getYear();
			if (year > Calendar.getInstance().get(Calendar.YEAR)) {
				notValid("Year must not be in future");
				return;
			}
			Movie m = new Movie (title , description , year , rating);
			
			Main.tableMovie.add(m);
			Main.hash.insert(m);
			Alert addedMovie = new Alert (AlertType.INFORMATION);
			addedMovie.setHeaderText(null);
			addedMovie.setTitle("Movie Added");
			addedMovie.setContentText(title + " Movie was added sucessfully");
			ImageView i = new ImageView (new Image ("truee1.png"));
			i.setFitHeight(50);
			i.setFitWidth(50);
			addedMovie.setGraphic(i);
			addedMovie.showAndWait();
		});
		VBox allAdd = new VBox (10);
		HBox h = new HBox (10);
		h.getChildren().addAll(add, clear);
		h.setAlignment(Pos.CENTER);
		
		allAdd.getChildren().addAll(g,h);
		allAdd.setAlignment(Pos.CENTER);
		
		Scene addScene = new Scene (allAdd , 600 , 600);
		Stage addStage = new Stage();
		addStage.setScene(addScene);
		addStage.setTitle("Add Movie Stage");
		addStage.show();
	});
	update = new MyButton ("Update",2).getB1();
	update.setOnAction(e->{
		if (!byTitle.isSelected()) {
			notValid("Select by Title and enter the title of movie you want to remove");
			return;
		}
		String title = search.getText();
		if (title == null || title.isEmpty()) {
			notValid("Search Field is empty , fill the title");
			return;
		}
		Movie m = MovieCatalog.getByTitle(title);
		if (m == null) {
			notValid("No movie with this title");
			return;
		}
		Label titleL = new StandardLabel("Title : ").getL();
		TextField title1 = new MyTextField().getT();
		title1.setText(title);
		Label descriptionL = new StandardLabel("Description : ").getL();
		TextField description1 = new MyTextField().getT();
		description1.setText(m.getDescription());
		Label releaseYearL = new StandardLabel("Release Year").getL();
		DatePicker releaseYear1= new DatePicker();
		releaseYear1.setValue(LocalDate.of(m.getReleaseYear(), 1, 1));
		Label ratingL = new StandardLabel("Rating").getL();
		TextField rating1 = new MyTextField().getT();
		rating1.setText(m.getRate()+"");
		GridPane g= new GridPane ();
		g.addColumn(0, titleL , descriptionL , ratingL , releaseYearL);
		g.addColumn(1, title1 , description1 , rating1 , releaseYear1);
		g.setVgap(10);
		g.setHgap(10);
		g.setAlignment(Pos.CENTER);
		
		Button update = new MyButton ("Update",2).getB1();
		Button clear = new MyButton ("Clear",2).getB1();
		
		clear.setOnAction(ee->{
			title1.clear();
			description1.clear();
			releaseYear1.setValue(null);
			rating1.clear();
		});
		
		Stage updateStage = new Stage();
		update.setOnAction(ee->{
			String title2 = title1.getText();
			if (title2 == null || title2.isEmpty()) {
				notValid("Title is empty");
				return;
			}
			boolean isRemoved = false;
			if (!title.toLowerCase().equals(title2.toLowerCase())) {
				MovieCatalog.earseByTitle(title);
				isRemoved = true;
			}
			if (MovieCatalog.searchByTitle(title2)) {
				notValid("There are a movie with this title");
				return;
			}
			
			String description = description1.getText();
			if (description == null || description.isEmpty()) {
				notValid("Description is empty");
				return;
			}
			
			String ratingS = rating1.getText();
			if (ratingS == null || ratingS.isEmpty()) {
				notValid("Rating is empty");
				return;
			}
			double rating = -1;
			try {
				rating = Double.parseDouble(ratingS);
			}catch (Exception eee) {
				notValid("Rating must be a double number");
				return;
			}
			if (rating < 0 || rating > 10) {
				notValid("Rating must be between 0-10");
				return;
			}
			LocalDate yearL = releaseYear1.getValue();
			if (yearL == null) {
				notValid("Select Year");
				return;
			}
			int year = yearL.getYear();
			if (year > Calendar.getInstance().get(Calendar.YEAR)) {
				notValid("Year must not be in future");
				return;
			}
			m.setTitle(title2);
			m.setDescription(description);
			m.setRate(rating);
			m.setReleaseYear(year);
			movieTable.refresh();
			if (isRemoved)
			Main.hash.insert(m);
			Alert updatedMovie = new Alert (AlertType.INFORMATION);
			updatedMovie.setHeaderText(null);
			updatedMovie.setTitle("Movie Updated");
			updatedMovie.setContentText(title2 + " Movie was updated sucessfully");
			ImageView i = new ImageView (new Image ("truee1.png"));
			i.setFitHeight(50);
			i.setFitWidth(50);
			updatedMovie.setGraphic(i);
			updatedMovie.showAndWait();
			updateStage.close();
		});
		VBox allUpdate = new VBox (10);
		HBox h = new HBox (10);
		h.getChildren().addAll(update, clear);
		h.setAlignment(Pos.CENTER);
		
		allUpdate.getChildren().addAll(g,h);
		allUpdate.setAlignment(Pos.CENTER);
		Scene updateScene = new Scene (allUpdate , 600 , 600);
	
		updateStage.setScene(updateScene);
		updateStage.setTitle("Update Movie Stage");
		updateStage.show();
		
	});
	delete = new MyButton ("Delete" ,2).getB1();
	delete.setOnAction(e->{
		if (!byTitle.isSelected()) {
			notValid("Select by title radio and enter title of movie you want to remove");
			return;
		}
		String title = search.getText();
		if (title == null || title.isEmpty()) {
			notValid("Title is empty , fill in search field");
			return;
		}
		Movie m = MovieCatalog.getByTitle(title);
		if (m == null) {
			notValid("No movie with this title");
			return;
		}
		MovieCatalog.earseByTitle(title);
		Main.tableMovie.remove(m);
		
		Alert movieDeleted = new Alert (AlertType.INFORMATION);
		movieDeleted.setHeaderText(null);
		movieDeleted.setTitle("Movie deleted");
		movieDeleted.setContentText(title + " Movie was removed sucessfully");
		ImageView i = new ImageView (new Image ("truee1.png"));
		i.setFitHeight(50);
		i.setFitWidth(50);
		movieDeleted.setGraphic(i);
		movieDeleted.showAndWait();
	});
	searchL = new StandardLabel ("Title / Release Year").getL();
	search = new MyTextField().getT();
	HBox searchBox =new HBox (10 , searchL , search);
	searchBox.setAlignment(Pos.CENTER);
	HBox buttons = new HBox (10 , add , update , delete);
	buttons.setAlignment(Pos.CENTER);
	byTitle = new RadioButton ("By Title");
	byYear = new RadioButton ("By Year");
	byTitle.setStyle("-fx-text-fill: #6C3483;-fx-font-size: 14px;-fx-font-weight: bold;-fx-padding: 4 10 4 10;");
	byYear.setStyle("-fx-text-fill: #6C3483;-fx-font-size: 14px;-fx-font-weight: bold;-fx-padding: 4 10 4 10;");

	ToggleGroup g =new ToggleGroup();
	
	byTitle.setToggleGroup(g);
	byYear.setToggleGroup(g);
	
	HBox radios = new HBox (5 , byTitle , byYear);
	HBox allSearch = new HBox (10 , searchBox , radios);
	allSearch.setAlignment(Pos.CENTER);
	
	menu = new MenuBar();
	file = new Menu ("File");
	file.getItems().addAll(readFromFile , saveToFile);
	movie = new Menu ("Movie");
	menu.getMenus().addAll(file , movie);
	addMovie = new MenuItem("Add Movie");
	updateMovie = new MenuItem ("Update Movie");
	deleteMovie = new MenuItem ("Delete Movie");
	sortMovies = new MenuItem("Sort Movie");
	movie.getItems().addAll(addMovie , updateMovie , deleteMovie ,sortMovies);
	addMovie.setOnAction(e->{
		add.fire();
	});
	updateMovie.setOnAction(e-> {
		update.fire();
	});
	deleteMovie.setOnAction(e->{
		delete.fire();
	});
	sortMovies.setOnAction(e->{
		if (Main.tableMovie.size() == 0 ) {
			MovieManagement.notValid("No Movies");
			return;
		}
		System.out.println(Main.tableMovie.size());
		System.out.println(Main.hash.getSize());
		new SortMovies().getSortStage().show();
	});
	all = new VBox (40);
	all.getChildren().addAll(menu, allSearch , buttons , movieTable);
	all.setAlignment(Pos.TOP_CENTER);
	
	movieScene = new Scene(all, screenBounds.getWidth(), screenBounds.getHeight() - 15);
	movieScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

	
}

public TableView<Movie> getMovieTable() {
	return movieTable;
}

public TableColumn<Movie, String> getTitleCol() {
	return titleCol;
}

public TableColumn<Movie, String> getDescriptionCol() {
	return descriptionCol;
}

public TableColumn<Movie, Integer> getReleaseYearCol() {
	return releaseYearCol;
}

public TableColumn<Movie, Double> getRatingCol() {
	return ratingCol;
}

public Button getAdd() {
	return add;
}

public Button getDelete() {
	return delete;
}

public Button getUpdate() {
	return update;
}

public TextField getSearch() {
	return search;
}

public Label getSearchL() {
	return searchL;
}

public RadioButton getByTitle() {
	return byTitle;
}

public RadioButton getByYear() {
	return byYear;
}

public VBox getAll() {
	return all;
}

public MenuItem getReadFromFile() {
	return readFromFile;
}

public MenuBar getMenu() {
	return menu;
}

public Menu getFile() {
	return file;
}

public Menu getMovie() {
	return movie;
}

public MenuItem getAddMovie() {
	return addMovie;
}

public MenuItem getUpdateMovie() {
	return updateMovie;
}

public MenuItem getDeleteMovie() {
	return deleteMovie;
}

public Scene getMovieScene() {
	return movieScene;
}

public Rectangle2D getScreenBounds() {
	return screenBounds;
}

public static void notValid(String s) {
	Alert notValid = new Alert(AlertType.ERROR);
	notValid.setHeaderText(null);
	notValid.setContentText(s);
	notValid.setTitle("Not Valid Input");
	ImageView i = new ImageView (new Image ("falsee1.png"));
	i.setFitHeight(50);
	i.setFitWidth(50);
	notValid.setGraphic(i);
	notValid.show();
}
}
