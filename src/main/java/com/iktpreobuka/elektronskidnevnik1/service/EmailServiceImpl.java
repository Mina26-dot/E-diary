package com.iktpreobuka.elektronskidnevnik1.service;

import java.io.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.iktpreobuka.elektronskidnevnik1.entity.EmailObject;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	public JavaMailSender emailSender;

	public void sendEmailToParent(String studentName, String parentEmail, String subject, String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(parentEmail);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);
        emailSender.send(mailMessage);
    }
	
	
	@Override
	public void sendSimpleMessage(EmailObject object) {

		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(object.getTo());
		message.setSubject(object.getSubject());
		message.setText(object.getText());
		emailSender.send(message);

	}

	@Override
	public void sendTemplateMessage(EmailObject object) throws Exception {

		MimeMessage mail = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mail, true);
		helper.setTo(object.getTo());
		helper.setSubject(object.getSubject());
		String text = "<html><body><table style='border:2px solid black'>" + 
                "<tr><td>" + 
                "<h2>Poruka od nastavnika:</h2>" +
                "<p>" + object.getText() + "</p>" + 
                "</td></tr>" + 
                "</table></body></html>";
		helper.setText(text, true);
		emailSender.send(mail);
	}

	@Override
	public void sendMessageWithAttachment(EmailObject object, String pathToAttachment) throws Exception {

		MimeMessage mail = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mail, true);
		
		helper.setTo(object.getTo());
		helper.setSubject(object.getSubject());
		helper.setText(object.getText(), false);
		
		FileSystemResource file = new FileSystemResource(new File(pathToAttachment));
		helper.addAttachment(file.getFilename(), file);
		emailSender.send(mail);

	}


}
