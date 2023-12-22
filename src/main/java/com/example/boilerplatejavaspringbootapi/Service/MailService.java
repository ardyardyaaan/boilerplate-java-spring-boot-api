package com.example.boilerplatejavaspringbootapi.Service;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;

import com.example.boilerplatejavaspringbootapi.restapi.v1.user.entity.UserEntity;
import com.example.boilerplatejavaspringbootapi.restapi.v1.user.repository.UserRepository;

/**
 *
 * @author ardyardyaaan
 */

@Service
public class MailService {

    @Autowired
    private UserRepository userRepository;

    private Properties properties;
    private Session session;
    MimeMessage mimeMessage;
    private String host;
    private String port;
    private String usname;
    private String pwd;

    @Value("${spring.mail.host}")
    public void HOST(String value){
        this.host = value;
    }

    @Value("${spring.mail.port}")
    public void PORT(String value){ this.port = value;}

    @Value("${spring.mail.username}")
    public void USERNAME(String value){ this.usname = value;}

    @Value("${spring.mail.password}")
    public void PASSWORD(String value){ this.pwd = value;}

    boolean STARTTLS = true;
    boolean AUTH = true;
    
    public void sendEmail(UserEntity user, String subject, String message) throws MailException, MessagingException {
        try {
            System.out.println(String.format("[[ %s ]]-------------- sendEmail: Mail Send START --------------",user.getUserId()));
            properties = new Properties();
            properties.put("mail.smtp.host",host);
            // Setting STARTTLS_PORT
            properties.put("mail.smtp.port",port);
            // AUTH enabled
            properties.put("mail.smtp.auth",AUTH);
            // STARTTLS enabled
            properties.put("mail.smtp.starttls.enable", STARTTLS);

            // Authenticating
            Authenticator auth = new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(usname, pwd);
                }
            };

            // creating session
            session = Session.getInstance(properties, auth);

            // create mimemessage
            mimeMessage = new MimeMessage(session);

            //Set Message Header
            mimeMessage.addHeader("Content_type", "text/HTML; charset=UTF-8");
            mimeMessage.addHeader("format", "flowed");
            mimeMessage.addHeader("Content-Transfer-Encoding", "8bit");

            //from address should exist in the domain
            mimeMessage.setFrom(new InternetAddress(usname,"ADMIN TIA"));

            //setting to Recipient email
            mimeMessage.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(user.getEmail(),false));

            // Setting Subject message
            mimeMessage.setSubject(subject,"UTF-8");
            System.out.println(String.format("[[ %s ]]-------------- sendEmail: Subject [[ %s ]] --------------",user.getUserId(),subject));

            // setting text message body
            mimeMessage.setContent(message,"text/HTML; charset=UTF-8");

            // sending mail
            Transport.send(mimeMessage);
            System.out.println(String.format("[[ %s ]]-------------- sendEmail: Mail Send Successfully --------------",user.getUserId()));

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void sendEmailSetPassword(String email, String typeEmail) throws MailException, MessagingException {
        
        UserEntity user = userRepository.findByEmail(email);
        if (user != null) {
            user.setIsResetPassword(0);
            userRepository.save(user);

            String subject = typeEmail.equalsIgnoreCase("reset") ? "Reset Password" : "Set Password";
            String message = "<div style='width: 495px; margin-top: 20px'>"
                    + "<p style='margin-top: 20px'>Hi <b>" + user.getFullname() + ",</b></p>"
                    + "<p style='margin-top: 5px'>";
            
            if (typeEmail.equalsIgnoreCase("reset")) {
                message = message + "Someone recently requested a password change for your account. "
                        + "If this was you, you can set a new password here:";
            } else {
                message = message + "Please click on the following link to activate your membership "
                        + "automatically and set your password.";
            }
            
            message = message + "</p>"
                    + "<center><a href='https://google.com" + user.getUserId() + "' style='margin: 10px 0px; padding: 7px 30px; border-radius: 4px; background-color: rgb(38, 59, 128); text-decoration: none; color: #fff;'>Set Password</a></center>"
                    + "<br/>"
                    + "<p style='color: #d0d0d0'>find me everywhere : @ardyardyaaan</p>"
                    + "</div>";

            sendEmail(user, subject, message);
        } else {
            System.out.println("The Email Address Provided is Not Registered. Please Contact Your Administrator");
        }
    }
}
