module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;


    opens Jigsaw to javafx.fxml;
    exports Jigsaw;
    exports Jigsaw.Utils;
    opens Jigsaw.Utils to javafx.fxml;
}