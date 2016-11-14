/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author edixred
 */
public class SendEmail {
    
    static Properties mailServerProperties,mailServerPropertiesP;
    static Session getMailSession,getMailSessionP;
    static MimeMessage generateMailMessage,generateMailMessageP;
        
    public static void sendEmailRegister(String emailDestination, String Password) throws AddressException, MessagingException {
 
//Step1
        mailServerProperties = System.getProperties();
        mailServerProperties.put("mail.smtp.port", "587");
        mailServerProperties.put("mail.smtp.auth", "true");
        mailServerProperties.put("mail.smtp.starttls.enable", "true");
//Step2
        getMailSession = Session.getDefaultInstance(mailServerProperties, null);
        generateMailMessage = new MimeMessage(getMailSession);

        generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(emailDestination));
        generateMailMessage.setSubject("Análisis de Oportunidades  Energéticas con Fuentes Alternativas en el Departamento de Nariño");
        String emailBody = "Agradecemos su registro en nuestra Plataforma. Para acceder a su cuenta" 
                            + "<br><br> Su Usuario es: "+emailDestination
                            + "<br><br> Su Clave es: "+Password
                            +"<br>Puede acceder ingresando a <a href='geoalternar.udenar.edu.co'>geoalternar.udenar.edu.co</a>";
        generateMailMessage.setContent(emailBody, "text/html");
//Step3	
        Transport transport = getMailSession.getTransport("smtp");
        transport.connect("smtp.gmail.com", "giieefae@gmail.com", "giiee2015");
        transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
        transport.close();
             
	}
    
    public static void sendEmailPassword(String emailDestination, String Password) throws AddressException, MessagingException {
 
//Step1
        mailServerPropertiesP = System.getProperties();
        mailServerPropertiesP.put("mail.smtp.port", "587");
        mailServerPropertiesP.put("mail.smtp.auth", "true");
        mailServerPropertiesP.put("mail.smtp.starttls.enable", "true");
//Step2
        getMailSessionP = Session.getDefaultInstance(mailServerPropertiesP, null);
        generateMailMessageP = new MimeMessage(getMailSessionP);

        generateMailMessageP.addRecipient(Message.RecipientType.TO, new InternetAddress(emailDestination));
        generateMailMessageP.setSubject("Cambio Contraseña - Análisis de Oportunidades  Energéticas con Fuentes Alternativas en el Departamento de Nariño");
        String emailBody = "Ha realizado una actualizacion en su Contraseña. Recargue la página para acceder a su cuenta" 
                            + "<br><br> Su Usuario es: "+emailDestination
                            + "<br><br> Su Nueva Clave es: "+Password
                            +"<br><br><br>Gracias por usar Nuestros servicios"
                            + "<br><br>Puede acceder ingresando a <a href='geoalternar.udenar.edu.co'>GEOAlternar</a>";
        generateMailMessageP.setContent(emailBody, "text/html");
//Step3	
        Transport transport = getMailSessionP.getTransport("smtp");
        transport.connect("smtp.gmail.com", "giieefae@gmail.com", "giiee2015");
        transport.sendMessage(generateMailMessageP, generateMailMessageP.getAllRecipients());
        transport.close();
             
	}
    
    public static void sendComment(String name,String emailComment, String comment) throws AddressException, MessagingException {
 
//Step1
        mailServerPropertiesP = System.getProperties();
        mailServerPropertiesP.put("mail.smtp.port", "587");
        mailServerPropertiesP.put("mail.smtp.auth", "true");
        mailServerPropertiesP.put("mail.smtp.starttls.enable", "true");
//Step2
        getMailSessionP = Session.getDefaultInstance(mailServerPropertiesP, null);
        generateMailMessageP = new MimeMessage(getMailSessionP);

        generateMailMessageP.addRecipient(Message.RecipientType.TO, new InternetAddress("edixred@gmail.com"));
        generateMailMessageP.setSubject("Preguntas y Comentarios");
        String emailBody = name+" realizó el siguiiente comentario sobre la plataforma: <br><br>" 
                            + comment+"<br><br><br><br>"
                            + "att: "+ name + " / "+ emailComment;
        generateMailMessageP.setContent(emailBody, "text/html");
//Step3	
        Transport transport = getMailSessionP.getTransport("smtp");
        transport.connect("smtp.gmail.com", "giieefae@gmail.com", "giiee2015");
        transport.sendMessage(generateMailMessageP, generateMailMessageP.getAllRecipients());
        transport.close();
             
	}
    
}
