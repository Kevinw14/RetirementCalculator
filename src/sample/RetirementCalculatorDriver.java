/**
 * Retirement Calculator application that takes input from the user
 * and calculates the return on investments at different interest rates
 * from their current age until they turn 72.
 *
 * @author Kevin Wood
 * @version 1.0
 */
package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.io.IOException;

public class RetirementCalculatorDriver extends Application {

    /**
     * Reads a user from the file system and sets up a view with the calculations
     * made and displays them in a tableview.
     *
     * @param primaryStage The top level container for JavaFX
     */
    @Override
    public void start(Stage primaryStage) {

        RetirementCalculatorView view = new RetirementCalculatorView();
        FileHandler<User> handler = new FileHandler<>();
        Controller controller;

        controller = new Controller(view);

        view.getLoadButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                try {
                    User user = handler.load(primaryStage);
                    controller.setUser(user);
                } catch (IOException e) {
                    System.out.println("IO Load Exception");
                } catch (ClassNotFoundException e) {
                    System.out.println("Class Not Found Exception");
                } catch (NullPointerException e) {
                    System.out.println("Null Load Exception");
                }
            }
        });

        view.getSaveButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                try {
                    handler.save(primaryStage, controller.getUser());
                } catch (IOException e) {
                    System.out.println("IO Save Exception");
                }
            }
        });

        primaryStage.setTitle("Retirement Calculator");
        primaryStage.setScene(new Scene(view, 1400, 600));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
