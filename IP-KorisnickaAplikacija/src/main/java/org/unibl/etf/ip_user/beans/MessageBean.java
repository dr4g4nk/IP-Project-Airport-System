package org.unibl.etf.ip_user.beans;

import java.io.Serializable;

import org.unibl.etf.ip_user.dao.MessageDao;
import org.unibl.etf.ip_user.dto.Message;

public class MessageBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5091611583016219838L;

	public MessageBean() {
		// TODO Auto-generated constructor stub
	}

	public boolean addMessage(Message message) {
		return MessageDao.insertMessage(message);
	}
}
