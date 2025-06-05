package application;




import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class StandardLabel  {
	Label  l;
	public StandardLabel(String text) {
		l = new Label(text);
		l.setFont(Font.font("Times New Roman", FontWeight.BOLD, 16));
		l.setStyle(
			    "-fx-font-size: 18px;" +
			    "-fx-text-fill: #9b59b6;" + 
			    "-fx-font-weight: bold;" +
			    "-fx-font-family: 'Georgia';"
			);

	}
	public StandardLabel(String text , int i) {
		l = new Label(text);
		l.setFont(Font.font("Times New Roman", FontWeight.BOLD, 16));
		l.setStyle(
			    "-fx-font-size: 32px;" + 
			    "-fx-font-family: 'Georgia';" +
			    "-fx-font-weight: bold;" + 
			    "-fx-text-base-color: #4A4A4A;"+
			    "-fx-text-fill: #4A4A4A;" +
			    "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.25), 5, 0.3, 2, 2);" 
			);
		l.setAlignment(Pos.CENTER);
	}
	public Label getL() {
		return l;
	}
	
}
