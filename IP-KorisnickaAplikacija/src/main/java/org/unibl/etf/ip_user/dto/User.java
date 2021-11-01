package org.unibl.etf.ip_user.dto;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6680318614418206822L;
	private String username, password, email, name, surname, address, account, country;
	
	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(String email, String password) {
		super();
		this.password = password;
		this.email = email;
	}

	public User(String email, String username, String password, String name, String surname, String address,
			 String account, String country) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.name = name;
		this.surname = surname;
		this.address = address;
		this.account = account;
		this.country = country;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public String getAddress() {
		return address;
	}

	public String getAccount() {
		return account;
	}

	public String getCountry() {
		return country;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String generateHash() {
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
			String encoded = Base64.getEncoder().encodeToString(hash);
			return encoded;
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			System.err.println(e);
		}
		return null;
	}
}
