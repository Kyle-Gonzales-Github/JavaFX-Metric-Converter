package hellofx;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {
  private TextField conversion = new TextField();
  private TextField conversionTotal = new TextField();
  private Button btCalculate = new Button("Calculate");
  
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    // Create UI
    GridPane gridPane = new GridPane();
    gridPane.setHgap(5);
    gridPane.setVgap(5);
    gridPane.add(new Label("Enter your conversion:"), 0, 0);
    gridPane.add(conversion, 1, 0);
    gridPane.add(new Label("Conversion rate:"), 0, 1);
    gridPane.add(conversionTotal, 1, 1);
    gridPane.add(btCalculate, 1, 5);
    conversionTotal.setEditable(false);

    // Set properties for UI
    gridPane.setAlignment(Pos.CENTER);
    conversion.setAlignment(Pos.BOTTOM_RIGHT);
    conversionTotal.setAlignment(Pos.BOTTOM_RIGHT);
    GridPane.setHalignment(btCalculate, HPos.RIGHT);

    // Process events
    btCalculate.setOnAction(e -> caculateConversion());

    // Create a scene and place it in the stage
    Scene scene = new Scene(gridPane, 400, 250);
    primaryStage.setTitle("Metric Converter"); // Set title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
  }
  
  private void caculateConversion() {
    // Get values from text fields
    String input = conversion.getText();
    String[] strLength = input.split(" ");
    float sourceNum = 0;
    String fromMetric = " ";
    String toMetric = " ";
    try {
      if (strLength.length < 3 || strLength.length > 4) {
         System.out.printf("Your input is , which is not handled%n Please try again", input);
      } else if (strLength.length == 3) {
        sourceNum = Float.parseFloat(strLength[0]);
            fromMetric = strLength[1];
            toMetric = strLength[2];
      }  else {
            sourceNum = Float.parseFloat(strLength[0]);
            fromMetric = strLength[1];
            toMetric = strLength[3];
          }
          convert(sourceNum, fromMetric, toMetric);
    } catch (Exception e) {
      System.out.println("Something was inputted wrong, please try again.");
    }
  }
  
  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }

  public float convert(float sourceNum, String fromMetric, String toMetric) {
		float targetNum = 0;
		switch (fromMetric) {
		case "kg":
			switch (toMetric) {
			case "g": //kg to g
				targetNum = sourceNum * 1000;
			case "pnd":
				targetNum = (float) (sourceNum * 2.2046);
			}
			break;
		case "o": //ounces to Grams
			switch (toMetric) {
			case "g":
				targetNum = (float) (sourceNum /0.035274);
			}
			break;
		case "c": // celcius to farenheight
			switch (toMetric) {
			case "f":
				targetNum = (float) ((sourceNum * 1.8) + 32);
			}
			break;
		case "mi":
			switch (toMetric) {
			case "ft": // miles to feet
				targetNum = sourceNum * 5280;
			case "in": // miles to inches
				targetNum = sourceNum * 63360;
			}
			break;
		}
		conversionTotal.setText(sourceNum + fromMetric + " = " + targetNum + toMetric);
		return targetNum;
	}
}