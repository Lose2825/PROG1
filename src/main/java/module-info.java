module br.feevale {
    requires javafx.controls;
    requires javafx.fxml;

    // Exports dos pacotes
    exports br.feevale;
    exports br.feevale.model;
    exports br.feevale.interfaces;
    exports br.feevale.service;
    exports br.feevale.controller;
    exports br.feevale.view;
    
    // Opens para JavaFX FXML (necessário para reflection)
    opens br.feevale to javafx.fxml;
    opens br.feevale.controller to javafx.fxml;
    opens br.feevale.model to javafx.base;
}
