package model;

import java.io.Serializable;
import java.util.Date;


public class MessageBean implements Serializable {
	
	
	
	private int senderID;
	private int  messageID;
	private String messages;
	private String subject;
	private int recipientID;
	private String recipient;
	private int isRead;
	private Date date;
	
	
	public MessageBean () {
		
	}


	public int getSenderID() {
		return senderID;
	}


	public void setSenderID(int senderID) {
		this.senderID = senderID;
	}


	public int getMessageID() {
		return messageID;
	}


	public void setMessageID(int messageID) {
		this.messageID = messageID;
	}


	public String getMessages() {
		return messages;
	}


	public void setMessages(String messages) {
		this.messages = messages;
	}


	public String getSubject() {
		return subject;
	}


	public void setSubject(String subject) {
		this.subject = subject;
	}


	public int getRecipientID() {
		return recipientID;
	}


	public void setRecipientID(int recipientID) {
		this.recipientID = recipientID;
	}


	public int getIsRead() {
		return isRead;
	}


	public void setIsRead(int isRead) {
		this.isRead = isRead;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public String getRecipient() {
		return recipient;
	}


	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
	
}
