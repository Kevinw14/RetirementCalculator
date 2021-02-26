package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Retirement Calculator application that takes input from the user
 * and calculates the return on investments at different interest rates
 * from their current age until they turn 72.
 *
 * @author Kevin Wood
 * @version 1.0
 */
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

        // Opens file system to choose a file to load.
        view.getLoadButton().setOnAction(event -> {
                try {
                    User user = handler.load(primaryStage);
                    controller.setUser(user);
                } catch (IOException e) {
                    view.getErrorLabel().setText("Error loading file " + e.toString());
                } catch (ClassNotFoundException e) {
                    view.getErrorLabel().setText("Error class was not found when loading user");
                } catch (NullPointerException ignored) {}
        });

        // Opens the file system to choose or create a file to save to.
        view.getSaveButton().setOnAction(event -> {
            try {
                handler.save(primaryStage, controller.getUser());
            } catch (IOException e) {
                view.getErrorLabel().setText("Error saving file " + e.toString());
            } catch (NullPointerException ignored) {
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
