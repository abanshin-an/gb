package hw_02_04;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

public class Controller {
    public TextArea textArea;
    public Button sendBtn;
    public TextField messageString;
    public Button exitBtn;
    @FXML
    public void sendIt() {
        Platform.runLater(() -> {
            textArea.appendText(messageString.getText() + "\n");
            messageString.clear();
            messageString.requestFocus();
        });
    }
    @FXML
    public void moveFocus(){
        Platform.runLater(() -> messageString.requestFocus());
    }
    @FXML
    public void exit(ActionEvent event) {
        Platform.runLater(() -> {
            Window window = ((Node) (event.getSource())).getScene().getWindow();
            if (window instanceof Stage) {
                ((Stage) window).close();
            }
        });
    }
}
