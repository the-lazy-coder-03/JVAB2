module jva.java_project_block_2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens jva.java_project_block_2 to javafx.fxml;
    exports jva.java_project_block_2;
}