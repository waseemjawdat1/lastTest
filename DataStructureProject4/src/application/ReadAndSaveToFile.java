package application;

import java.io.File;
import java.util.Calendar;
import java.util.Scanner;

import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class ReadAndSaveToFile {
	private MenuItem read;
	
	public ReadAndSaveToFile() {
		read = new MenuItem("Read From File");
		read.setOnAction(e->{
			FileChooser f1 = new FileChooser();
			f1.setTitle("Choose File");
			f1.getExtensionFilters().add(new ExtensionFilter("Txt Files", "*.txt"));
			File f = f1.showOpenDialog(Main.primaryStage);
			if (f != null) {
				try (Scanner in = new Scanner (System.in)){
					while (in.hasNextLine()) {
						String []arr = new String[4];
						int i =0;
						while (i < 4) {
							if (in.hasNextLine())
							arr[i] = in.nextLine();
						}
						if (arr[3] == null)continue;
						String title = arr[0];
						String description = arr[1];
						int releaseYear = -1;
						try {
							releaseYear = Integer.parseInt(arr[2].trim());
						}
						catch (Exception ee) {
							continue;
						}
						if (releaseYear < 1 || releaseYear > Calendar.getInstance().get(Calendar.YEAR)) {
							continue;
						}
						double rate =-1;
						try {
							rate = Double.parseDouble(arr[3].trim());
						}catch (Exception ee) {
							continue;
						}
						if (rate < 0 || rate > 10) {
							continue;
						}
						Movie m = new Movie (title , description  , releaseYear , rate);
						Main.hash.insert(m);
						Main.tableMovie.add(m);
						
					}
				}
			}
		});
	}

	public MenuItem getRead() {
		return read;
	}

	public void setRead(MenuItem read) {
		this.read = read;
	}
	
}
