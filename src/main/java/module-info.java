module ru.rsreu.forcealghoritmapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    opens ru.rsreu.graphspreader to javafx.fxml;
    exports ru.rsreu.graphspreader;
    exports ru.rsreu.graphspreader.view;
    opens ru.rsreu.graphspreader.view to javafx.fxml;
    exports ru.rsreu.graphspreader.controller;
    opens ru.rsreu.graphspreader.controller to javafx.fxml;
}