package org.unibl.etf.ip_user.beans;

import java.io.Serializable;

import org.unibl.etf.ip_user.dao.AccessDao;
import org.unibl.etf.ip_user.dto.Access;

public class AccessBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2962722900269828238L;

	public AccessBean() {
		// TODO Auto-generated constructor stub
	}
	
	public void addAccess(Access access) {
		AccessDao.insertAccess(access);
	}

}
