module com.example.modele_vuejavafx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.example.modele_vuejavafx to javafx.fxml;
    exports com.example.modele_vuejavafx;
    exports modele;
    exports vue;
}