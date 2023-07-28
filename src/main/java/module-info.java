module com.codeforpizza.robcomgui {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.codeforpizza.robcomgui to javafx.fxml;
    exports com.codeforpizza.robcomgui;
}