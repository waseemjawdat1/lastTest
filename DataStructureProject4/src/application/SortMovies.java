
package application;

import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SortMovies {
    private int index = -1;
    private TableView<Movie> movieTable;
    private TableColumn<Movie, String> titleCol, descriptionCol;
    private TableColumn<Movie, Integer> releaseYearCol;
    private TableColumn<Movie, Double> ratingCol;
    private Button next, prev;
    private ComboBox<String> sortOptions;
    private Label hashIndex, topLabel, leastLabel;
    private Scene sortScene;
    private Stage sortStage;
    private ObservableList<Movie> sorted = FXCollections.observableArrayList();

    public SortMovies() {
        try {
			index = Main.hash.getNextNonEmptyIndex(index);
		} catch (Exception e) {
		}

        hashIndex = new StandardLabel("Hash Index: " + index + " | AVL Height: " + Main.hash.getAVL(index).height(), 1).getL();
        topLabel = new Label();
        leastLabel = new Label();

        titleCol = new TableColumn<>("Title");
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionCol = new TableColumn<>("Description");
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        releaseYearCol = new TableColumn<>("Release Year");
        releaseYearCol.setCellValueFactory(new PropertyValueFactory<>("releaseYear"));
        ratingCol = new TableColumn<>("Rating");
        ratingCol.setCellValueFactory(new PropertyValueFactory<>("rate"));

        movieTable = new TableView<>();
        movieTable.getColumns().addAll(titleCol, descriptionCol, releaseYearCol, ratingCol);
        movieTable.setMinHeight(500);
        movieTable.setMaxWidth(1250);
        movieTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        String style = "-fx-background-color: #E5D9F2;-fx-border-color: #A294F9; -fx-text-fill: black;-fx-font-family: 'Montserrat'; ";
        for (TableColumn<Movie, ?> col : movieTable.getColumns()) {
            col.setStyle(style);
            col.setSortable(false);
        }

        sorted.setAll(Main.hash.getAVL(index).toList());
        movieTable.setItems(sorted);
        setTopAndLeastRanked();

        topLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: green;");
        leastLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: red;");

        next = new MyButton("Next", 2).getB1();
        prev = new MyButton("Previous", 2).getB1();

        sortOptions = new ComboBox<>();
        sortOptions.getItems().addAll("Ascending", "Descending");
        sortOptions.setValue("Ascending");

        sortOptions.setOnAction(e -> {
            if (sortOptions.getValue().equals("Descending")) {
                sorted.setAll(Main.hash.getAVL(index).toList());
                Collections.reverse(sorted);
            } else {
                sorted.setAll(Main.hash.getAVL(index).toList());
            }
            movieTable.setItems(sorted);
            setTopAndLeastRanked();
        });

        next.setOnAction(e -> {
            try {
                index = Main.hash.getNextNonEmptyIndex(index);
                updateView();
            } catch (Exception ex) {
                this.sortStage.close();
            }
        });

        prev.setOnAction(e -> {
            try {
                index = Main.hash.getPreviousNonEmptyIndex(index);
                updateView();
            } catch (Exception ex) {
                this.sortStage.close();
            }
        });

        VBox topSection = new VBox(10, hashIndex, topLabel, leastLabel, sortOptions);
        topSection.setAlignment(Pos.CENTER);
        topSection.setPadding(new Insets(10));

        HBox navButtons = new HBox(20, prev, next);
        navButtons.setAlignment(Pos.CENTER);
        navButtons.setPadding(new Insets(10));

        BorderPane root = new BorderPane();
        root.setTop(topSection);
        root.setCenter(movieTable);
        root.setBottom(navButtons);

        sortScene = new Scene(root, 1300, 700);
        sortStage = new Stage();
        sortStage.setScene(sortScene);
        sortStage.setTitle("Movie Sort");
        sortStage.show();
    }

    private void updateView() {
        hashIndex.setText("Hash Index: " + index + " | AVL Height: " + Main.hash.getAVL(index).height());
        sorted.setAll(Main.hash.getAVL(index).toList());
        if (sortOptions.getValue().equals("Descending")) {
            Collections.reverse(sorted);
        }
        movieTable.setItems(sorted);
        setTopAndLeastRanked();
    }

    public void setTopAndLeastRanked() {
        Movie top = null;
        Movie least = null;

        for (int i = 0; i < sorted.size(); i++) {
            Movie m = sorted.get(i);
            if (top == null || m.getRate() > top.getRate()) {
                top = m;
            }
            if (least == null || m.getRate() < least.getRate()) {
                least = m;
            }
        }

        topLabel.setText("🎬 Top Rated: " + (top != null ? top.getTitle() : "N/A"));
        leastLabel.setText("📉 Least Rated: " + (least != null ? least.getTitle() : "N/A"));
    }

	public Stage getSortStage() {
		return sortStage;
	}

	public void setSortStage(Stage sortStage) {
		this.sortStage = sortStage;
	}
    
}
