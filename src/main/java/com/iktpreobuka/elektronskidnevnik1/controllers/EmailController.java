package com.iktpreobuka.elektronskidnevnik1.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.elektronskidnevnik1.entity.EmailObject;
import com.iktpreobuka.elektronskidnevnik1.service.EmailService;


@RestController
@RequestMapping(path = "/")
public class EmailController {

	@Autowired
	private EmailService emailService;
	
	private static final Logger logger = LoggerFactory.getLogger(Korisnik1Controller.class);

	
	private static String PATH_TO_ATTACHMENT = "E://proba//slika.jpg";
	

	
	@PostMapping("/simpleEmail")
	public String sendSimpleMessage(@RequestBody EmailObject object) {
		LocalDateTime currentTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedTime = currentTime.format(formatter);
		logger.info("[{}] E poruka je uspesno poslata roditelju (ime,prezime, email) : vremeSlanja = {} ", object,  formattedTime );
		if (object == null || object.getTo() == null || object.getText() == null) {
			return "Email not sent. Check TO and TEXT fields.";
		}
		emailService.sendSimpleMessage(object);
		return "Your mail has been sent!";
	}

	@PostMapping("/templateEmail")
	public String sendTemplateMessage(@RequestBody EmailObject object) throws Exception {
		if (object == null || object.getTo() == null || object.getText() == null) {
			return null;
		}
		emailService.sendTemplateMessage(object);
		return "Your mail has been sent!";
	}

	@PostMapping("/emailWithAttachment")
	public String sendMessageWithAttachment(@RequestBody EmailObject object) throws Exception {
		if (object == null || object.getTo() == null || object.getText() == null) {
			return null;
		}
		emailService.sendMessageWithAttachment(object, PATH_TO_ATTACHMENT);
		return "Your mail has been sent!";
	}

}
