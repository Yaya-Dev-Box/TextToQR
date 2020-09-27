package org.yayarh;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

import java.awt.image.BufferedImage;

public class QRCodeGenerator {

    public BufferedImage bufferedImg;
    public String text;

    public Image createQRCode(String txt) throws WriterException {
        text = txt;

        //Code from: https://www.dynamsoft.com/codepool/zxing-write-read-qrcode.html
        QRCodeWriter writer = new QRCodeWriter();
        int width = 256, height = 256;
        bufferedImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB); // create an empty image
        int white = 255 << 16 | 255 << 8 | 255;
        int black = 0;

        BitMatrix bitMatrix = writer.encode(txt, BarcodeFormat.QR_CODE, width, height);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                bufferedImg.setRGB(i, j, bitMatrix.get(i, j) ? black : white); // set pixel one by one
            }
        }

        //Code from: https://stackoverflow.com/questions/30970005/bufferedimage-to-javafx-image (Second answer)
        WritableImage wr;
        wr = new WritableImage(bufferedImg.getWidth(), bufferedImg.getHeight());
        PixelWriter pw = wr.getPixelWriter();
        for (int x = 0; x < bufferedImg.getWidth(); x++) {
            for (int y = 0; y < bufferedImg.getHeight(); y++) {
                pw.setArgb(x, y, bufferedImg.getRGB(x, y));
            }
        }

        return new ImageView(wr).getImage();
    }
}
