package uqtr.ecompagnon.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.List;
import java.util.Properties;

@Configuration
public class EmailConfig {
//Bonne configutation d'envoie de l'email
@Autowired
private Environment env;
    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", 587);
        properties.put("mail.smtp.auth", "true");
        properties.setProperty("mail.debug.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.user", env.getProperty("emailGmail"));
        properties.put("mail.password", env.getProperty("emailPasswordGmail"));
        properties.put("mail.debug", "true");

        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(env.getProperty("emailGmail"), env.getProperty("emailPasswordGmail"));
            }
        };

        Session session = Session.getInstance(properties, auth);
        mailSender.setSession(session);
        return mailSender;
    }


    @Bean
    public JavaMailSender getJavaMailSenderOutLook() {
      JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.office365.com");
        properties.put("mail.smtp.port", 25);
        properties.put("mail.smtp.auth", "true");
        properties.setProperty("mail.debug.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.user", env.getProperty("emailOutLook"));
        properties.put("mail.password", env.getProperty("emailPasswordOutLook"));
        properties.put("mail.debug", "true");

        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(env.getProperty("emailOutLook"), env.getProperty("emailPasswordOutLook"));
            }
        };
        Session session = Session.getInstance(properties, auth);
        mailSender.setSession(session);
        return mailSender;

    }


    @Bean
    public SimpleMailMessage emailTemplate() {
        SimpleMailMessage message = new SimpleMailMessage();
        // message.setTo("somebody@gmail.com");
        //message.setFrom("admin@gmail.com");
        message.setText("FATAL - Application crash. Save your job !!");
        return message;
    }
}
