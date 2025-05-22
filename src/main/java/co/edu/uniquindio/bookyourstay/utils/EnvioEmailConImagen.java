package co.edu.uniquindio.bookyourstay.utils;

import jakarta.activation.DataHandler;
import jakarta.activation.FileDataSource;
import jakarta.mail.*;
import jakarta.mail.internet.*;

import java.io.File;
import java.util.Properties;

public class EnvioEmailConImagen {

    private final String remitente = "santiago.olarteb@uqvirtual.edu.co";
    private final String clave = "tsnldhngzlqidqlg";

    public void enviarCorreo(String destinatario, String asunto, String cuerpoTexto, String rutaAdjunto) throws MessagingException {

        Properties propiedades = new Properties();
        propiedades.put("mail.smtp.auth", "true");
        propiedades.put("mail.smtp.starttls.enable", "true");
        propiedades.put("mail.smtp.host", "smtp.gmail.com");
        propiedades.put("mail.smtp.port", "587");

        Session sesion = Session.getInstance(propiedades, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(remitente, clave);
            }
        });

        // Crear mensaje base
        Message mensaje = new MimeMessage(sesion);
        mensaje.setFrom(new InternetAddress(remitente));
        mensaje.setRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
        mensaje.setSubject(asunto);

        // Parte del texto
        MimeBodyPart cuerpo = new MimeBodyPart();
        cuerpo.setText(cuerpoTexto);

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(cuerpo);

        // Si se pasa un archivo adjunto (como el QR)
        if (rutaAdjunto != null && !rutaAdjunto.isEmpty()) {
            MimeBodyPart adjunto = new MimeBodyPart();
            File archivo = new File(rutaAdjunto);
            adjunto.setDataHandler(new DataHandler(new FileDataSource(archivo)));
            adjunto.setFileName(archivo.getName());
            multipart.addBodyPart(adjunto);
        }

        mensaje.setContent(multipart);

        Transport.send(mensaje);
    }
}

