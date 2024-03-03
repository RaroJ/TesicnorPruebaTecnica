module com.harrypotterapp.tesicnorpruebatecnica {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;

    opens com.harrypotterapp.tesicnorpruebatecnica to javafx.fxml;
    exports com.harrypotterapp.tesicnorpruebatecnica;
}