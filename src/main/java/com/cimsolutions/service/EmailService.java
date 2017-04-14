package com.cimsolutions.service;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public class EmailService
{
    private JavaMailSender mailSender;


    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendMail(String from, String to, String subject, String msg) {
        try {

            MimeMessage message = mailSender.createMimeMessage();

            message.setSubject(subject);
            MimeMessageHelper helper;
            helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setText(msg, true);
            mailSender.send(message);
        } catch (MessagingException ex) {
            Logger.getLogger(EmailService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}

//package com.cimsolutions.service;
// 
//import javax.mail.MessagingException;
//import javax.mail.internet.MimeMessage;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.MailSender;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.stereotype.Component;
// 
//@Component
//public class EmailService
//{
////    @Autowired
////    private JavaMailSender javaMailSender;
//// 
////    public void send(String to, String subject, String body) throws MessagingException
////    {
////        MimeMessage message = javaMailSender.createMimeMessage();
////        MimeMessageHelper helper = new MimeMessageHelper(message, true);
//// 
////        helper.setSubject(subject);
////        helper.setTo(to);
////        helper.setText(body);
//// 
////        javaMailSender.send(message);
////    }
//    
//	private MailSender mailSender;
//
//	public void setMailSender(MailSender mailSender) {
//		this.mailSender = mailSender;
//	}
//
//	public void sendMail(String from, String to, String subject, String msg) {
//
//		SimpleMailMessage message = new SimpleMailMessage();
//
//		message.setFrom(from);
//		message.setTo(to);
//		message.setSubject(subject);
//		message.setText(msg);
//		mailSender.send(message);
//	}
//    
//    
//}