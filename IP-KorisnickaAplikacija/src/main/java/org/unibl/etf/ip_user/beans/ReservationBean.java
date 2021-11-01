package org.unibl.etf.ip_user.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.unibl.etf.ip_user.dao.FligthDao;
import org.unibl.etf.ip_user.dao.ReservationDao;
import org.unibl.etf.ip_user.dao.ScheduleDao;
import org.unibl.etf.ip_user.dto.Fligth;
import org.unibl.etf.ip_user.dto.Location;
import org.unibl.etf.ip_user.dto.Reservation;
import org.unibl.etf.ip_user.dto.Schedule;
import org.unibl.etf.ip_user.dto.User;


public class ReservationBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1625187859540045381L;
	private User user;

	public ReservationBean(String account) {
		user = new User(null, null, null, null, null, null, account, null);
		get();
	}

	public ReservationBean(User user) {
		this.user = user;
		get();
	}

	public User getUser() {
		return user;
	}

	private void get() {
		fligths = FligthDao.getFligths(user.getAccount());
		fligths.stream().forEach(fligth -> {
			startLocations.add(fligth.getRoute().getStart());
			if (!startCountries.contains(fligth.getRoute().getStart().getCountry())) {
				startCountries.add(fligth.getRoute().getStart().getCountry());
			}
		});
		System.out.println(startCountries.size());
	}

	public List<Reservation> getReservations() {
		return ReservationDao.getReservationsForUser(user);
	}

	public boolean addReservation(Reservation reservation) {
		reservation.setUser(user);
		return ReservationDao.insertReservation(reservation);
	}

	public boolean changeStatus(long id) {
		return ReservationDao.changeStatus(id);
	}

	private List<Fligth> fligths;
	private List<Location> startLocations = new ArrayList<Location>();
	private List<Location> endLocations = new ArrayList<Location>();

	public List<String> startCountries = new ArrayList<String>();
	public List<String> startCities = new ArrayList<String>();
	public List<String> endCountries = new ArrayList<String>();
	public List<String> endCities = new ArrayList<String>();

	public void setStartCities(String country) {
		startCities.clear();
		startLocations.forEach(l -> {
			if (l.getCountry().equals(country) && !startCities.contains(l.getCity()))
				startCities.add(l.getCity());
		});
	}

	public void setEndCities(String country) {
		endCities.clear();
		endLocations.forEach(l -> {
			if (l.getCountry().equals(country) && !endCities.contains(l.getCity()))
				endCities.add(l.getCity());
		});
	}

	public void setEndCountries(String country, String city) {
		endCountries.clear();
		endLocations.clear();
		fligths.stream().filter(fligth -> fligth.getRoute().getStart().getCountry().equals(country)
				&& fligth.getRoute().getStart().getCity().equals(city)).forEach(fligth -> {
					if (!endCountries.contains(fligth.getRoute().getEnd().getCountry())) {
						endCountries.add(fligth.getRoute().getEnd().getCountry());
						endLocations.add(fligth.getRoute().getEnd());
					}
				});
	}
	
	public Fligth getFligth(String startCountry, String startCity, String endCountry, String endCity) {
		Optional<Fligth> optional = fligths.stream().filter(fligth-> fligth.getRoute().getStart().getCountry().equals(startCountry) 
				&& fligth.getRoute().getStart().getCity().equals(startCity) && fligth.getRoute().getEnd().getCountry().equals(endCountry) && fligth.getRoute().getEnd().getCity().equals(endCity)).findFirst();
		
		if(optional.isPresent()) {
			Fligth fligth = optional.get();
			return fligth;
		}
		return null;
	}

	public List<Schedule> getFligthSchedules(String startCountry, String startCity, String endCountry, String endCity){
		Optional<Fligth> optional = fligths.stream().filter(fligth-> fligth.getRoute().getStart().getCountry().equals(startCountry) 
				&& fligth.getRoute().getStart().getCity().equals(startCity) && fligth.getRoute().getEnd().getCountry().equals(endCountry) && fligth.getRoute().getEnd().getCity().equals(endCity)).findFirst();
		
		if(optional.isPresent()) {
			Fligth fligth = optional.get();
			return ScheduleDao.getSchedules(fligth);
		}
		return new ArrayList<Schedule>();
	}

}
