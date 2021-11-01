package org.unibl.etf.ip_user.dto;

import java.io.Serializable;

public class Message implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4050941776434000636L;
	private long id;
	private String status, email, date, subject, content;
	
	public Message() {
		// TODO Auto-generated constructor stub
	}

	public Message(String email, String subject, String content) {
		super();
		this.email = email;
		this.subject = subject;
		this.content = content;
	}

	public Message(long id, String status, String email, String date, String subject, String content) {
		super();
		this.id = id;
		this.status = status;
		this.email = email;
		this.date = date;
		this.subject = subject;
		this.content = content;
	}

	public long getId() {
		return id;
	}

	public String getStatus() {
		return status;
	}

	public String getEmail() {
		return email;
	}

	public String getDate() {
		return date;
	}

	public String getSubject() {
		return subject;
	}

	public String getContent() {
		return content;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
