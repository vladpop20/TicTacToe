package ticTacToe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class TicTacToeApplication extends Application {

    private List<String> values = Arrays.asList("X", "O", "X", "O", "X", "O", "X", "O", "X");
    private List<Button> buttons = new ArrayList<>();
    private Label nextTurnText = new Label("Turn: X");
    private Label theWinnerLabel = new Label("Winner: ");
    private int turn = 0;
    private String winner;

    @Override
    public void start(Stage window) {
        // Create the main layout
        BorderPane layout = new BorderPane();

        // Create the components of main layout
        GridPane ticTacGrid = new GridPane();

        // Set the design of components        
        layout.setPrefSize(277, 200);
        ticTacGrid.setVgap(10);
        ticTacGrid.setHgap(10);
        ticTacGrid.setPadding(new Insets(10, 10, 10, 10));
        this.nextTurnText.setFont(Font.font("Monospaced", 40));
        this.theWinnerLabel.setFont(Font.font("Cambria", 25));

        // Create the list composed of 9 buttons  
        for (int i = 0; i < 9; i++) {
            buttons.add(this.getButton());
        }

        // Add the buttons for the grid layout
        int counter = 0;
        while (counter < 9) {
            for (int x = 0; x < 3; x++) {
                for (int y = 0; y < 3; y++) {
                    ticTacGrid.add(buttons.get(counter), x, y);
                    counter++;
                }
            }
        }

        // Set the position of components in the main layout
        layout.setTop(nextTurnText);
        layout.setCenter(ticTacGrid);
        layout.setBottom(theWinnerLabel);

        // Creates the main view and add the main layout
        Scene view = new Scene(layout);

        // Shows the application
        window.setScene(view);
        window.show();        

    }

    // Create the click event for each button, to assign the X or O character to each button
    // And also check for the winner
    public Button getButton() {
        Button button = new Button("");
        button.setFont(Font.font("Monospaced", 40));

        button.setOnMouseClicked((event) -> {                                                                                   // if there already exists "X" or "O"
            if (!button.getText().isEmpty()) {                                                                                  // it will not let to overwrite the letter
                return;
            }

            button.setText(this.values.get(turn));                                                                              // get's the right letter from the values list

            if (turn < 8) {                                                                                                     // it's checks if the turn counter is smaller than 8
                this.nextTurnText.setText("Turn: " + this.values.get(turn + 1));                                                // in order to avoid IndexOutOfBoundary using "turn +1" 
            } else {
                this.nextTurnText.setText("The end!");                                                                          // and when the turn is getting bigger that >= 8
                return;                                                                                                         // is printed the "The End" message on nextTurnText Label
            }

            this.turn++;

            if (checkTheRows() || checkTheColumns() || checkDiagonal()) {                                                       // if someone wins, the Winner Label is set with the winner Letter
                this.theWinnerLabel.setText("Winner: " + winner);

                if (this.theWinnerLabel.getText().equals("Winner: X") || this.theWinnerLabel.getText().equals("Winner: O")) {
                    this.nextTurnText.setText("The end!");
                }
            }
        });

        return button;
    }    

    public boolean checkTheRows() {
        for (int i = 0; i < 9; i += 3) {
            if (buttons.get(i).getText().equals(buttons.get(i + 1).getText()) && buttons.get(i + 1).getText().equals(buttons.get(i + 2).getText())) {
                winner = buttons.get(i).getText();
                return true;
            }
        }

        return false;
    }

    public boolean checkTheColumns() {
        for (int i = 0; i < 3; i++) {
            if (buttons.get(i).getText().equals(buttons.get(i + 3).getText()) && buttons.get(i + 3).getText().equals(buttons.get(i + 6).getText())) {
                winner = buttons.get(i).getText();
                return true;
            }
        }

        return false;
    }

    public boolean checkDiagonal() {
        if (buttons.get(0).getText().equals(buttons.get(4).getText()) && buttons.get(4).getText().equals(buttons.get(8).getText())) {
            winner = buttons.get(0).getText();
            return true;

        } else if (buttons.get(2).getText().equals(buttons.get(4).getText()) && buttons.get(4).getText().equals(buttons.get(6).getText())) {
            winner = buttons.get(0).getText();
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        launch(TicTacToeApplication.class);

    }

}
