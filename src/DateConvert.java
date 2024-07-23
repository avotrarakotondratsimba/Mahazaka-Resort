/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author AVOTRA
 */
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateConvert {
    // Méthode pour convertir java.util.Date en java.sql.Date
    public static java.sql.Date convertUtilDateToSqlDate(java.util.Date utilDate) {
        if (utilDate == null) {
            return null;
        }
        return new java.sql.Date(utilDate.getTime());
    }
    
    // Méthode pour convertir une chaîne de format "dd/MM/yyyy" en java.sql.Date
    public static java.sql.Date convertStringToSqlDate(String dateString) throws ParseException {
        if (dateString == null || dateString.isEmpty()) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd / MM / yyyy");
        java.util.Date utilDate = sdf.parse(dateString);
        return new java.sql.Date(utilDate.getTime());
    }
}
