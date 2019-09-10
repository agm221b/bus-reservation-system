package com.cg.busreservationsystem.service;

import java.time.LocalDate;
import java.util.List;

import com.cg.busreservationsystem.dto.Booking;
import com.cg.busreservationsystem.dto.Bus;
import com.cg.busreservationsystem.dto.Passenger;

public interface CustomerService {
	public List<Bus> getRunningBuses(LocalDate dateOfJourney, String src, String dest);
	public List<Booking> viewTicketsByDate(LocalDate date);
	public Integer cancelTicket(Booking b);
	public List<Booking> viewTicketList();
	public Passenger addPassenger(Passenger passenger);
	public List<Passenger> viewPassengers();
	public Double calculateCost();
	
}
