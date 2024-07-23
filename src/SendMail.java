
import java.util.ArrayList;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author AVOTRA
 */
public class SendMail {
    public static void sendMail(String email, String name, String roomsNb, String enterDate, String exitDate, int dayNb) {
        final String username = "youremail@service.com";
        final String password = "your password";
        String[] roomsTab = roomsNb.split(", ");
        ArrayList<String> tmpDesign = SelectDesignRoom.selectDesignRooms(roomsTab);
        String design = "";
        int j = 0;
        for (String d : tmpDesign) {
            if (!design.equals("")) design+=", " + d + " (N° " + roomsTab[j] + ")";
            else design = d + " (N° " + roomsTab[j] + ")";
            j++;
        }

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
          new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
          });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("youremail@service.com"));
            message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(email));
            message.setSubject("Confirmation de réservation de chambre à Mahazaka Resort");

            // Remplace le texte "Hello World" par le message amélioré
            String content = "Bonjour " + name + ",\n\n"
                    + "Nous sommes ravis de vous accueillir au Mahazaka Resort pour votre prochain séjour. Votre réservation a été confirmée avec succès.\n\n"
                    + "Voici les détails de votre réservation :\n\n"
                    + "- Désignation des chambres : " + design +"\n"
                    + "- Dates de séjour : du " + enterDate + " au " + exitDate + "\n"
                    + "- Nombre de jours : " + dayNb + "\n\n"
                    + "Nous vous garantissons un séjour agréable dans notre établissement. Si vous avez des demandes spéciales ou des questions, n'hésitez pas à nous contacter. Notre équipe est à votre entière disposition pour rendre votre séjour aussi confortable que possible.\n\n"
                    + "Nous avons hâte de vous accueillir et de vous offrir une expérience mémorable au Mahazaka Resort.\n\n"
                    + "Cordialement,\n"
                    + "L'équipe du Mahazaka Resort";

            message.setText(content);

            Transport.send(message);

            System.out.println("Message envoyé avec succès.");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
