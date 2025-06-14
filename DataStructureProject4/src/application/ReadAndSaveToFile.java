package application;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class ReadAndSaveToFile {
	private MenuItem read ,save;
	
	public ReadAndSaveToFile() {
		read = new MenuItem("Read From File");
		save = new MenuItem("Save To File");
		read.setOnAction(e -> {
		    FileChooser f1 = new FileChooser();
		    f1.setTitle("Choose File");
		    f1.getExtensionFilters().add(new ExtensionFilter("Txt Files", "*.txt"));
		    File f = f1.showOpenDialog(Main.primaryStage);
		    if (f != null) {
		    	try (Scanner in = new Scanner(f)) {
		    	    while (in.hasNextLine()) {
		    	        try {
		    	            String titleLine = in.nextLine().trim();
		    	            String descLine = in.hasNextLine() ? in.nextLine().trim() : "";
		    	            String yearLine = in.hasNextLine() ? in.nextLine().trim() : "";
		    	            String rateLine = in.hasNextLine() ? in.nextLine().trim() : "";
		    	            if (in.hasNextLine()) in.nextLine();
		    	            if (!titleLine.startsWith("Title: ") || !descLine.startsWith("Description: ")
		    	                    || !yearLine.startsWith("Release Year: ") || !rateLine.startsWith("Rating: ")) {
		    	               System.out.println("skipped");
		    	                continue;
		    	            }

		    	            String title = titleLine.substring("Title: ".length()).trim();
		    	            String description = descLine.substring("Description: ".length()).trim();
		    	            int year = Integer.parseInt(yearLine.substring("Release Year: ".length()).trim());
		    	            double rating = Double.parseDouble(rateLine.substring("Rating: ".length()).trim());

		    	            if (year < 1900 || year > Calendar.getInstance().get(Calendar.YEAR) || rating < 0 || rating > 10)
		    	                continue;

		    	            Movie m = new Movie(title, description, year, rating);

		    	            if (!Main.hash.search(m)) {
		    	                Main.hash.insert(m);
		    	                Main.tableMovie.add(m);
		    	            } else {
		    	                System.out.println("Duplicate " + m.getTitle());
		    	            }
		    	        } catch (Exception err) {
		    	        }
		    	    }
		    	    
		    	    
		    	    Alert doneRead = new Alert (AlertType.INFORMATION);
		    	    doneRead.setTitle("File Read");
		    	    doneRead.setHeaderText(null);
		    	    doneRead.setContentText("Data imported sucessfully");
		    	    ImageView trueI = new ImageView (new Image ("truee1.png"));
		    	    trueI.setFitHeight(50);
		    	    trueI.setFitWidth(50);
		    	    doneRead.setGraphic(trueI);
		    	    doneRead.showAndWait();
		    	    
		    	    System.out.println("list size: " + Main.tableMovie.size());
		    	    System.out.println("hash size: " + Main.hash.getSize());
		    	    System.out.println("all trees size: " + Main.hash.getSz());

		    	} catch (Exception ex) {
		    	    ex.printStackTrace();
		    	}

		    }
		});
		save.setOnAction(e->{
			 FileChooser f1 = new FileChooser();
			    f1.setTitle("Choose File");
			    f1.getExtensionFilters().add(new ExtensionFilter("Txt Files", "*.txt"));
			    File f = f1.showOpenDialog(Main.primaryStage);
			    if (f != null) {
			    	try (PrintWriter p = new PrintWriter (f)){
			    		for (int i = 0 ; i < Main.hash.getCapacity(); i++) {
			    			ArrayList <Movie> toSave = Main.hash.getAVL(i).toList();
			    			for (int j= 0 ; j < toSave.size(); j++) {
			    				p.println("Title: " + toSave.get(j).getTitle());
			    				p.println("Description: " + toSave.get(j).getDescription());
			    				p.println("Release Year: " + toSave.get(j).getReleaseYear());
			    				p.println("Rating: " + toSave.get(j).getRate());
			    				p.println();
			    			}
			    		}
			    		
			    	}catch (Exception exc) {
			    		
			    	}
			    	 Alert doneSave = new Alert (AlertType.INFORMATION);
			    	    doneSave.setTitle("File Saved");
			    	    doneSave.setHeaderText(null);
			    	    doneSave.setContentText("Data exported sucessfully");
			    	    ImageView trueI = new ImageView (new Image ("truee1.png"));
			    	    trueI.setFitHeight(50);
			    	    trueI.setFitWidth(50);
			    	    doneSave.setGraphic(trueI);
			    	    doneSave.showAndWait();
			    }
		});


	}

	public MenuItem getRead() {
		return read;
	}
	
	public MenuItem getSave() {
		return save;
	}

	public void setRead(MenuItem read) {
		this.read = read;
	}
	
}
