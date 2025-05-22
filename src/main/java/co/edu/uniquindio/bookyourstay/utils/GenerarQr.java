package co.edu.uniquindio.bookyourstay.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class GenerarQr {

    public static void generarCodigoQR(String texto, String nombreArchivo, int ancho, int alto) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(texto, BarcodeFormat.QR_CODE, ancho, alto);

        String rutaArchivo = "qrcodes/" + nombreArchivo + ".png";
        Path path = FileSystems.getDefault().getPath(rutaArchivo);

        // Crear carpeta si no existe
        File carpeta = new File("qrcodes");
        if (!carpeta.exists()) {
            carpeta.mkdirs();
        }

        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
    }
}
