package application;




import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class MyButton {
	
	Button b1;
	public MyButton(String text, int i) {
	    b1 = new Button(text);
	    if (i ==1) {
	    b1.setMinWidth(250);
	    b1.setMinHeight(120);
	    b1.setStyle(
	        "-fx-font-size: 30px;" +
	        "-fx-font-family: 'Georgia';" +
	        "-fx-font-weight: bold;" +
	        "-fx-text-fill: #3E125F;" +
	        "-fx-background-color: linear-gradient(to bottom, #A66CFF, #E4C1F9);" +
	        "-fx-background-radius: 12px;" +
	        "-fx-effect: dropshadow(gaussian, rgba(62, 18, 95, 0.3), 12, 0, 0, 6);" +
	        "-fx-cursor: hand;"
	    );

	    b1.setOnMouseEntered(e -> {
	        b1.setStyle(
	            "-fx-font-size: 30px;" +
	            "-fx-font-family: 'Georgia';" +
	            "-fx-font-weight: bold;" +
	            "-fx-text-fill: #3E125F;" +
	            "-fx-background-color: linear-gradient(to bottom, #933FFF, #D9A7EB);" +
	            "-fx-background-radius: 12px;" +
	            "-fx-effect: dropshadow(gaussian, rgba(62, 18, 95, 0.5), 14, 0, 0, 8);" +
	            "-fx-cursor: hand;"
	        );
	    });

	    b1.setOnMouseExited(e -> {
	        b1.setStyle(
	            "-fx-font-size: 30px;" +
	            "-fx-font-family: 'Georgia';" +
	            "-fx-font-weight: bold;" +
	            "-fx-text-fill: #3E125F;" +
	            "-fx-background-color: linear-gradient(to bottom, #A66CFF, #E4C1F9);" +
	            "-fx-background-radius: 12px;" +
	            "-fx-effect: dropshadow(gaussian, rgba(62, 18, 95, 0.3), 12, 0, 0, 6);" +
	            "-fx-cursor: hand;"
	        );
	    });
	    }
	    else if (i == 3) {
	        b1.setMinWidth(350);
	        b1.setMinHeight(350);
	        b1.setStyle(
	            "-fx-font-size: 24px;" +
	            "-fx-font-family: 'Georgia';" +
	            "-fx-font-weight: bold;" +
	            "-fx-text-fill: #3E125F;" + // deep violet text (matches your theme)
	            "-fx-background-color: linear-gradient(to bottom, #E0BBE4, #D291BC);" + // soft lavender to orchid
	            "-fx-background-radius: 12px;" +
	            "-fx-border-radius: 12px;" +
	            "-fx-effect: dropshadow(gaussian, rgba(62, 18, 95, 0.25), 16, 0, 0, 6);" +
	            "-fx-cursor: hand;"
	        );

	        b1.setOnMouseEntered(e -> {
	            b1.setStyle(
	                "-fx-font-size: 24px;" +
	                "-fx-font-family: 'Georgia';" +
	                "-fx-font-weight: bold;" +
	                "-fx-text-fill: #3E125F;" +
	                "-fx-background-color: linear-gradient(to bottom, #D291BC, #E0BBE4);" + // reverse gradient
	                "-fx-background-radius: 12px;" +
	                "-fx-border-radius: 12px;" +
	                "-fx-effect: dropshadow(gaussian, rgba(62, 18, 95, 0.4), 18, 0, 0, 8);" +
	                "-fx-cursor: hand;"
	            );
	        });

	        b1.setOnMouseExited(e -> {
	            b1.setStyle(
	                "-fx-font-size: 24px;" +
	                "-fx-font-family: 'Georgia';" +
	                "-fx-font-weight: bold;" +
	                "-fx-text-fill: #3E125F;" +
	                "-fx-background-color: linear-gradient(to bottom, #E0BBE4, #D291BC);" +
	                "-fx-background-radius: 12px;" +
	                "-fx-border-radius: 12px;" +
	                "-fx-effect: dropshadow(gaussian, rgba(62, 18, 95, 0.25), 16, 0, 0, 6);" +
	                "-fx-cursor: hand;"
	            );
	        });
	    }


	    else {
	    	 b1.setMinWidth(100);
	 	    b1.setMinHeight(50);
	 	    b1.setStyle(
	 	        "-fx-font-size: 20px;" +
	 	        "-fx-font-family: 'Georgia';" +
	 	        "-fx-font-weight: bold;" +
	 	        "-fx-text-fill: #3E125F;" +
	 	        "-fx-background-color: linear-gradient(to bottom, #A66CFF, #E4C1F9);" +
	 	        "-fx-background-radius: 12px;" +
	 	        "-fx-effect: dropshadow(gaussian, rgba(62, 18, 95, 0.3), 12, 0, 0, 6);" +
	 	        "-fx-cursor: hand;"
	 	    );

	 	    b1.setOnMouseEntered(e -> {
	 	        b1.setStyle(
	 	            "-fx-font-size: 20px;" +
	 	            "-fx-font-family: 'Georgia';" +
	 	            "-fx-font-weight: bold;" +
	 	            "-fx-text-fill: #3E125F;" +
	 	            "-fx-background-color: linear-gradient(to bottom, #933FFF, #D9A7EB);" +
	 	            "-fx-background-radius: 12px;" +
	 	            "-fx-effect: dropshadow(gaussian, rgba(62, 18, 95, 0.5), 14, 0, 0, 8);" +
	 	            "-fx-cursor: hand;"
	 	        );
	 	    });

	 	    b1.setOnMouseExited(e -> {
	 	        b1.setStyle(
	 	            "-fx-font-size: 20px;" +
	 	            "-fx-font-family: 'Georgia';" +
	 	            "-fx-font-weight: bold;" +
	 	            "-fx-text-fill: #3E125F;" +
	 	            "-fx-background-color: linear-gradient(to bottom, #A66CFF, #E4C1F9);" +
	 	            "-fx-background-radius: 12px;" +
	 	            "-fx-effect: dropshadow(gaussian, rgba(62, 18, 95, 0.3), 12, 0, 0, 6);" +
	 	            "-fx-cursor: hand;"
	 	        );
	 	    });
	    }
	}




	public MyButton (String text) {
		b1 = new Button(text);
		b1.setMinWidth(300);
		b1.setMinHeight(100);
		b1.setStyle(
			    "-fx-font-size: 20px;" +
			    "-fx-font-family: 'Times New Roman';" +
			    "-fx-font-weight: bold;" +
			    "-fx-text-base-color: #5A00B5;"+
			    "-fx-text-fill: #5A00B5;" +
			    "-fx-background-color: linear-gradient(to bottom, #B97DFF, #FFB28C);" +
			    "-fx-background-radius: 5px;" +
			    "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.2), 10, 0, 0, 5);" +
			    "-fx-cursor: hand;"
			);

			b1.setOnMouseEntered(e -> {
			    b1.setStyle(
			        "-fx-font-size: 20px;" +
			        "-fx-font-family: 'Times New Roman';" +
			        "-fx-font-weight: bold;" +
			        "" +
			        "-fx-text-fill: #5A00B5;" +
			        "-fx-background-color: linear-gradient(to bottom, #9A64FF, #FF9F6A);" +
			        "-fx-background-radius: 5px;" +
			        "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.3), 12, 0, 0, 6);" +
			        "-fx-cursor: hand;"
			    );
			});


		b1.setOnMouseExited(e -> {
		    b1.setStyle(
		        "-fx-font-size: 20px;" +
		        "-fx-font-family: 'Times New Roman';" +
		        "-fx-font-weight: bold;" +
		        "-fx-text-base-color: #5A00B5;"+
		        "-fx-text-fill: #5A00B5;" +
		        "-fx-background-color: linear-gradient(to bottom, #B97DFF, #FFB28C);" +
		        "-fx-background-radius: 5px;" +
		        "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.2), 10, 0, 0, 5);" +
		        "-fx-cursor: hand;"
		    );
		});
	}
	public MyButton (Image i) {
		ImageView icon  = new ImageView(i);
		icon.setFitHeight(50);
		icon.setFitWidth(50);
		b1 = new Button(null , icon);
		b1.setMinWidth(300);
		b1.setMinHeight(100);
		
		b1.setStyle("-fx-background-color: linear-gradient(to bottom, #B97DFF, #FFB28C); -fx-background-radius: 5px;-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.2), 10, 0, 0, 5);-fx-cursor: hand;");
		b1.setOnMouseEntered(e-> {
			b1.setStyle("-fx-background-color: linear-gradient(to bottom, #9A64FF, #FF9F6A);-fx-font-size: 14px;-fx-background-radius: 5px;-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.3), 12, 0, 0, 6);-fx-cursor: hand;");
		});
		b1.setOnMouseExited(e->{
			b1.setStyle("-fx-background-color: linear-gradient(to bottom, #B97DFF, #FFB28C);-fx-font-size: 14px;-fx-background-radius: 5px;-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.2), 10, 0, 0, 5);-fx-cursor: hand;");
		});
	}
	public MyButton (Image i , int a) {
		ImageView icon  = new ImageView(i);
		icon.setFitHeight(50);
		icon.setFitWidth(50);
		b1 = new Button(null , icon);
		b1.setMinWidth(70);
		b1.setMinHeight(50);
	b1.setStyle("-fx-background-color: linear-gradient(to bottom, #B97DFF, #FFB28C); -fx-background-radius: 5px;-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.2), 10, 0, 0, 5);-fx-cursor: hand;");
		b1.setOnMouseEntered(e-> {
			b1.setStyle("-fx-background-color: linear-gradient(to bottom, #9A64FF, #FF9F6A); -fx-font-size: 14px;-fx-background-radius: 5px;-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.3), 12, 0, 0, 6);-fx-cursor: hand;");
		});
   	b1.setOnMouseExited(e->{
		b1.setStyle("-fx-background-color: linear-gradient(to bottom, #B97DFF, #FFB28C); -fx-font-size: 14px;-fx-background-radius: 5px;-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.2), 10, 0, 0, 5);-fx-cursor: hand;");
		});
	}
	public MyButton (Image i , String s) {
		ImageView icon  = new ImageView(i);
		icon.setFitHeight(30);
		icon.setFitWidth(30);
		b1 = new Button(null , icon);
		b1.setMinWidth(50);
		b1.setMinHeight(30);
	b1.setStyle("-fx-background-color: linear-gradient(to bottom, #B97DFF, #FFB28C); -fx-background-radius: 5px;-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.2), 10, 0, 0, 5);-fx-cursor: hand;");
		b1.setOnMouseEntered(e-> {
			b1.setStyle("-fx-background-color: linear-gradient(to bottom, #9A64FF, #FF9F6A); -fx-font-size: 14px;-fx-background-radius: 5px;-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.3), 12, 0, 0, 6);-fx-cursor: hand;");
		});
   	b1.setOnMouseExited(e->{
		b1.setStyle("-fx-background-color: linear-gradient(to bottom, #B97DFF, #FFB28C); -fx-font-size: 14px;-fx-background-radius: 5px;-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.2), 10, 0, 0, 5);-fx-cursor: hand;");
		});
	}
	public Button getB1() {
		return b1;
	}
	
}
