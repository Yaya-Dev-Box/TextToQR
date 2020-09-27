module TextToQR.main {

    requires javafx.fxml;
    requires javafx.controls;
    requires core;
    requires java.desktop;
    requires org.controlsfx.controls;

    opens org.yayarh to javafx.fxml;

    exports org.yayarh;
}