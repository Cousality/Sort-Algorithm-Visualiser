module sortalgorithm.sortalgorithmvisualiser {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;

    opens sortalgorithm.sortalgorithmvisualiser to javafx.fxml;
    exports sortalgorithm.sortalgorithmvisualiser;
}