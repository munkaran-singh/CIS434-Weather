/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package weatherapplication;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.text.Text;
/**
 *
 * @author munkaransingh
 */

public class WeatherApplication extends Application{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        launch(args);
    }
    
    public void start(Stage primaryStage) {
	try {
		Parent root = FXMLLoader.load(getClass().getResource("WeatherApplication.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("JavaFx Task Weather 1");
		primaryStage.setScene(scene);
		primaryStage.show();
	} 
        catch(Exception e) {
		e.printStackTrace();
	}
                
    }
    
}