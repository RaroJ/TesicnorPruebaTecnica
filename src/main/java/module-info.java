module com.harrypotterapp.tesicnorpruebatecnica {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires java.sql;
    requires javafx.swing;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires mysql.connector.j;
    requires com.google.gson;
    requires java.desktop;


    opens com.harrypotterapp.tesicnorpruebatecnica to javafx.fxml;
    exports com.harrypotterapp.tesicnorpruebatecnica;
    exports com.harrypotterapp.tesicnorpruebatecnica.DB;
    opens com.harrypotterapp.tesicnorpruebatecnica.DB to javafx.fxml;
}