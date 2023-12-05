package com.bezkoder.spring.security.jwt.security.services.mailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.time.LocalDate;
import java.util.Properties;

@Service

public class EmailSenderService {
	private static final String CONTENT_TYPE_TEXT_HTML = "text/html;charset=\"utf-8\"";

    @Value("${config.mail.host}")
    private String host;
    @Value("${config.mail.port}")
    private String port;
    @Value("${config.mail.username}")
    private String email;
    @Value("${config.mail.password}")
    private String password;

    @Autowired
    ThymeleafService thymeleafService;

    public void sendMail( String patientname, String patientemail, String patientsdt, String doctorname, String doctoremail,
    		String doctorsdt, String banknumber,String bankname, String time,String date, String price) {
    	
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", port);

        Session session = Session.getInstance(props,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(email, password);
                    }
                });
        
        Message message = new MimeMessage(session);
        try {
            message.setRecipients(Message.RecipientType.TO, new InternetAddress[]{new InternetAddress(patientemail)});

            message.setFrom(new InternetAddress(email));
            message.setSubject("YEU CAU GUI XAC NHAN THONG TIN CHUYEN KHOAN!!!");
            
            message.setContent(thymeleafService.setContent( patientname, patientemail, patientsdt, doctorname, doctoremail,
            		 doctorsdt, banknumber, bankname, time, date, price), CONTENT_TYPE_TEXT_HTML);
            
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    
    public void sendMailText( String userName, String emailDoctor, String Password, String fullName) {
    	
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", port);

        Session session = Session.getInstance(props,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(email, password);
                    }
                });
        
        Message message = new MimeMessage(session);
        try {
            message.setRecipients(Message.RecipientType.TO, new InternetAddress[]{new InternetAddress(emailDoctor)});

            message.setFrom(new InternetAddress(email));
            message.setSubject("GỬI THÔNG TIN TÀI KHOẢN MẬT KHẨU ĐĂNG NHẬP HỆ THỐNG!!!");
            
            message.setText("Kính Gửi Ông/Bà " +fullName+"!\n\n"+
                    "Tài khoản mật khẩu của ông\bà "+fullName+" đã được thêm vào trong dữ liệu hệ thống.\n"+
            		"Thông tin tài khoản đăng nhập (User name): " +userName+"\n"+
                    "Thông tin mật khẩu đăng nhập (Password): "+Password+"\n"+
            		"Hiện tại tài khoản đã có thể đăng nhập vào hệ thống!\n\n\n"+
            		"Vui lòng không phản hồi lại mail này!\n"+
            		"Cảm ơn vì sự bất tiện này!");
            
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
