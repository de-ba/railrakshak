/**
 * Created by mun` on 16/03/2018.
 */

import com.google.gson.Gson;
import redis.clients.jedis.Jedis;

import java.util.Arrays;
import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Emailsend {
    public static void Email(String k, String username) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("railrakshak@gmail.com", "Delhi@123");
                    }
                });

        try {

            String s = OTP(6);
//            String urlConfimation = "http://localhost:4567/otp?code=" + s;
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("railrakshak@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(k));
            message.setSubject("Testing Subject");
            message.setText("your OTP is:  " + s);
            Transport.send(message);
            System.out.println("Done");
            Redis.storeOTP(username, s);

            //Main.listenFor(s);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    static String OTP(int len) {
        System.out.println("Generating OTP using random() : ");
        System.out.print("You OTP is : ");
        // Using numeric values
        String numbers = "0123456789";
        // Using random method
        Random rndm_method = new Random();
        char[] otp = new char[len];
        for (int i = 0; i < len; i++) {
            // Use of charAt() method : to get character value
            // Use of nextInt() as it is scanning the value as int
            otp[i] =
                    numbers.charAt(rndm_method.nextInt(numbers.length()));
        }
        String s = "";
        for (char c : otp) {
            s += c;
        }

        return s;
    }

    public static void Email1(String k, String firid) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("railrakshak@gmail.com", "Udaipur@123");
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("railrakshak@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(k));
            message.setSubject("Testing Subject");
            message.setText("your FIR Identity number is "+firid);
            Transport.send(message);
            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }}