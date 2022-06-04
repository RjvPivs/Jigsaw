package Jigsaw;

import java.net.URL;
import java.util.ResourceBundle;

import Jigsaw.Utils.Cell;
import Jigsaw.Utils.Timer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class HelloController {
    /**
     * A Timer class example.
     */
    public Timer time;
    /**
     * 3*3 field for generated figures.
     */
    boolean[][] generatedField;
    /**
     * 9*9 field for placed figures.
     */
    boolean[][] playingField;
    /**
     * A Color class example.
     */
    Color color;
    int placedAmount = 0, generatedAmount = 0;
    int genColumn = 0, genRow = 0, playColumn = 0, playRow = 0;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private GridPane gameField;

    @FXML
    private Button generateNewFigure;

    @FXML
    private TextField generated;

    @FXML
    private GridPane generator;

    @FXML
    private VBox mainPanel;

    @FXML
    private TextField placed;

    @FXML
    private TextField timer;

    @FXML
    private Button stopping;

    @FXML
    private Button start;

    @FXML
    void startClick(ActionEvent event) {
        resetField();
        resetGenerator();
        timer.setText("");
        placed.setText("");
        generated.setText("");
        placedAmount = 0;
        generatedAmount = 0;
        time = new Timer(timer);
        time.start();
        stopping.setVisible(true);
        generateNewFigure.setVisible(true);
        start.setVisible(false);
    }

    @FXML
    void stopTheGame(ActionEvent event) {
        Timer.Stopping();
        start.setVisible(true);
        stopping.setVisible(false);
        generateNewFigure.setVisible(false);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game finished!");
        alert.setHeaderText("You`ve finished the game!");
        alert.setContentText("You can learn about your score on the certain fields.");
        alert.show();
    }

    @FXML
    void generateClick(ActionEvent event) {
        generator.getChildren().clear();
        Cell cell = new Cell();
        generatedField = cell.getCell();
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                Rectangle rect = new Rectangle(40, 40);
                if (generatedField[i][j]) {
                    rect.setFill(cell.getColor());
                    color = cell.getColor();
                    int finalI = i;
                    int finalJ = j;
                    rect.setOnMouseEntered(mouseEvent -> {
                        genColumn = finalI;
                        genRow = finalJ;
                    });
                } else {
                    rect.setFill(Color.WHITE);
                    int finalI = i;
                    int finalJ = j;
                    rect.setOnMouseEntered(mouseEvent -> {
                        genColumn = finalI;
                        genRow = finalJ;
                    });
                }
                generator.add(rect, i, j);
            }
        }
        generated.setText("Generation attempts: " + ++generatedAmount);
    }

    @FXML
    void moveTheFigure(MouseEvent event) {
        if (generatedField[genColumn][genRow]) {
            Dragboard db = generator.startDragAndDrop(TransferMode.ANY);
            ClipboardContent cb = new ClipboardContent();
            cb.putString("1");
            db.setContent(cb);
            event.consume();
        }
    }

    /**
     * Checks if a figure can be placed.
     *
     * @return A result of a verification.
     */
    boolean checker() {
        int startCol = playColumn - genColumn, startRow = playRow - genRow;
        boolean[][] tempor = new boolean[3][3];
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (startCol + i < 0 || startCol + i > 8 || startRow + j < 0 || startRow + j > 8) {
                    tempor[i][j] = true;
                } else {
                    tempor[i][j] = playingField[startCol + i][startRow + j];
                }
            }
        }
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (tempor[i][j] && generatedField[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    @FXML
    void dropTheFigure(DragEvent event) {
        boolean checker = checker();
        if (event.getDragboard().hasString() && checker) {
            event.acceptTransferModes(TransferMode.ANY);
        }
    }

    /**
     * Resets the generated field.
     */
    void resetGenerator() {
        generator.getChildren().clear();
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                Rectangle rect = new Rectangle(40, 40);
                rect.setFill(Color.WHITE);
                generator.add(rect, i, j);
            }
        }
    }

    /**
     * Resets the main field.
     */
    void resetField() {
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                ((Rectangle) gameField.getChildren().get(9 * (i) + j + 1)).setFill(Color.WHITE);
                playingField[i][j] = false;
            }
        }
    }

    @FXML
    void droppedFigure(DragEvent event) {
        int startCol = playColumn - genColumn, startRow = playRow - genRow;
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (generatedField[i][j]) {
                    playingField[startCol + i][startRow + j] = true;
                    ((Rectangle) gameField.getChildren().get(9 * (startCol + i) + startRow + j + 1)).setFill(color);
                }
            }
        }
        resetGenerator();
        placed.setText("Placed figures: " + ++placedAmount);
    }

    @FXML
    void initialize() {
        assert gameField != null : "fx:id=\"gameField\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert generateNewFigure != null : "fx:id=\"generateNewFigure\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert generated != null : "fx:id=\"generated\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert generator != null : "fx:id=\"generator\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert mainPanel != null : "fx:id=\"mainPanel\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert placed != null : "fx:id=\"placed\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert timer != null : "fx:id=\"timer\" was not injected: check your FXML file 'hello-view.fxml'.";
        resetGenerator();
        playingField = new boolean[9][9];
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                Rectangle rect = new Rectangle(35, 35);
                rect.setFill(Color.WHITE);
                int finalI = i;
                int finalJ = j;
                rect.setOnMouseEntered(mouseEvent -> {
                    playColumn = finalI;
                    playRow = finalJ;
                });
                rect.setOnDragOver(mouseEvent -> {
                    playColumn = finalI;
                    playRow = finalJ;
                });
                gameField.add(rect, i, j);
                playingField[i][j] = false;
            }
        }
    }
}
