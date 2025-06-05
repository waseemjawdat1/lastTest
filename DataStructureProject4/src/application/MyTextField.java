package application;




import javafx.scene.control.TextField;

public class MyTextField {
	TextField t;
	public MyTextField () {
		t=  new TextField();
		t.setStyle(
			    "-fx-background-color: white;" +
			    "-fx-background-radius: 3px;" +
			    "-fx-border-radius: 6px;" +
			    "-fx-border-color: #e5b8ff;" +
			    "-fx-border-width: 1px;" +
			    "-fx-padding: 8 12;" +
			    "-fx-pref-height: 10px;" + 
			    "-fx-font-size: 13px;" +
			    "-fx-text-fill: #4a4a4a;" +
			    "-fx-min-height: 30px;"
			);
	}
	
	public static TextField getTForRmove() {
		TextField tt = new TextField();
		tt=  new TextField();
		tt.setStyle(
			    "-fx-background-color: white;" +
			    "-fx-background-radius: 3px;" +
			    "-fx-border-radius: 6px;" +
			    "-fx-border-color: #e5b8ff;" +
			    "-fx-border-width: 1px;" +
			    "-fx-padding: 8 12;" +
			    "-fx-pref-height: 10px;" + 
			    "-fx-font-size: 13px;" +
			    "-fx-text-fill: #4a4a4a;" +
			    "-fx-min-height: 30px;"
			);
		tt.setEditable(false);
		return tt;
	}
	public TextField getT() {
		return t;
	}
	public void setT(TextField t) {
		this.t = t;
	}

}
