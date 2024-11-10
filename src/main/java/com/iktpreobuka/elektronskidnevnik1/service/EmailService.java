package com.iktpreobuka.elektronskidnevnik1.service;

import com.iktpreobuka.elektronskidnevnik1.entity.EmailObject;

public interface EmailService {

	void sendSimpleMessage(EmailObject object);

	void sendTemplateMessage(EmailObject object) throws Exception;

	void sendMessageWithAttachment(EmailObject object, String pathToAttachment) throws Exception;

	void sendEmailToParent(String studentName, String parentEmail, String subject, String message);

}
