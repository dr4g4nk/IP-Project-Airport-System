package org.unibl.etf.ip_user.beans;

import java.io.Serializable;

import org.unibl.etf.ip_user.dao.UserDao;
import org.unibl.etf.ip_user.dto.User;

public class UserBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3176426881850647939L;
	private boolean loggedIn;
	private User user = new User();

	public UserBean() {
		// TODO Auto-generated constructor stub
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public User getUser() {
		return user;
	}

	public boolean login(String email, String password) {
		if (email == null || password == null)
			return false;
		User tmp = new User(email, password);
		User userDb = UserDao.getUserByEmail(email);

		if (userDb != null && userDb.getPassword().equals(tmp.generateHash())) {
			this.user = userDb;
			this.user.setPassword("");
			loggedIn = true;
			return true;
		}

		return false;
	}

	public void logout() {
		user = new User();
		loggedIn = false;

	}

	public boolean isEmailAllowed(String email) {
		return UserDao.getUserByEmail(email) == null ? true : false;
	}

	public boolean isUsernameAllowed(String username) {
		return UserDao.getUserByUsername(username) == null ? true : false;
	}

	public boolean addUser(User user) {
		return UserDao.insertUser(user);
	}
}
