/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
/**
 *
 * @author fyrak
 */
public class RecuPDF {
    static void genererRecuSejour(String type, String nomClient, String chambre, int nombreJours, String dateEntree, String dateSortie) throws SQLException {
        try {
            int numeroSejour = SelectVisitNumberForPDF.selectVisitNumberForPDF(type);
            String[] chambresTab = chambre.split(", ");
            ArrayList<String> tmpDesign = SelectDesignRoom.selectDesignRooms(chambresTab);
            String design = "";
            int j = 0;
            for (String d : tmpDesign) {
                if (!design.equals("")) design+=", " + d + " (N° " + chambresTab[j] + ")";
                else design = d + " (N° " + chambresTab[j] + ")";
                j++;
            }
            String[] tmpEDate = dateEntree.split(" / ");
            String[] tmpExDate = dateSortie.split(" / ");
            String eDate = "", exDate = "";
            for (int i = 0; i < 3; i++) {
                if (i == 1) {
                    eDate+="/"+tmpEDate[i]+"/";
                    exDate+="/"+tmpExDate[i]+"/";
                } else {
                    eDate+=tmpEDate[i];
                    exDate+=tmpExDate[i];
                }
            }
            // Création d'un nouveau document PDF
            PDDocument document = new PDDocument();

            // Ajout d'une nouvelle page
            PDPage page = new PDPage();
            document.addPage(page);

            // Initialisation du contenu du PDF
            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            
            File fontFile = new File("src/training/Font/Poppins-Regular.ttf");
            PDType0Font font = PDType0Font.load(document, fontFile);

            // Utilisation de la police chargée
            contentStream.setFont(font, 12);
            contentStream.beginText();

            // Position du texte et ajout des informations
            contentStream.newLineAtOffset(50, 700);
            contentStream.showText("Sejour N° " + numeroSejour);
            contentStream.newLineAtOffset(0, -20);
            contentStream.showText("Nom du Client : " + nomClient);
            contentStream.newLineAtOffset(0, -20);
            contentStream.showText("Désignation chambre : " + design);
            contentStream.newLineAtOffset(0, -20);
            contentStream.showText("Nombre de jours : " + nombreJours);
            contentStream.newLineAtOffset(0, -20);
            contentStream.showText("Date d’entrée : " + eDate);
            contentStream.newLineAtOffset(0, -20);
            contentStream.showText("Date de sortie : " + exDate);
            

            // Fin du texte et fermeture du contenu
            contentStream.endText();
            contentStream.close();

            // Sauvegarde du document PDF
            document.save(new File("E:\\JAVA project\\PDF_Generated\\Reçu_" + numeroSejour + ".pdf"));
            document.close();

            System.out.println("Le reçu du séjour a été généré avec succès.");
        } catch (IOException e) {
            System.err.println("Erreur lors de la création du reçu du séjour : " + e.getMessage());
        }
    }
}
