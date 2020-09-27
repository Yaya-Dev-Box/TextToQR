package org.yayarh;

import com.google.zxing.WriterException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.controlsfx.control.Notifications;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    TextField text;
    @FXML
    ImageView image;

    private final QRCodeGenerator qr = new QRCodeGenerator();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            image.setImage(qr.createQRCode("A Ninja Was here"));
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void generateQRCode() {
        if (text.getText().isEmpty()) return;
        try {
            Image codeImage = qr.createQRCode(text.getText());
            image.setImage(codeImage);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void saveQRCode() {
        if (qr.text == null || qr.text.isBlank() || qr.bufferedImg == null) return;

        String fileName = new Date().toString();
        fileName = fileName.replace(":", "-");

        File outputFile = new File(System.getProperty("user.home") + "\\Documents\\TextToQR\\" + fileName + ".jpg");
        System.out.println(outputFile.mkdirs());

        try {
            ImageIO.write(qr.bufferedImg, "jpg", outputFile);
            Notifications.create().title("Code QR enregistré").show();
        } catch (IOException e) {
            e.printStackTrace();
            Notifications.create().title("Erreur").showWarning();
        }
    }

}