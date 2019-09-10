package com.cg.busreservationsystem.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.cg.busreservationsystem.dao.BookingDao;
import com.cg.busreservationsystem.dao.BookingDaoImpl;
import com.cg.busreservationsystem.dao.BusDao;
import com.cg.busreservationsystem.dao.BusDaoImpl;
import com.cg.busreservationsystem.dao.TransactionDao;
import com.cg.busreservationsystem.dao.TransactionDaoImpl;
import com.cg.busreservationsystem.dto.Booking;
import com.cg.busreservationsystem.dto.Bus;
import com.cg.busreservationsystem.dto.Passenger;

public class CustomerServiceImpl implements CustomerService{

	BusDao busDao = new BusDaoImpl();
	BookingDao bookingDao = new BookingDaoImpl();
	TransactionDao transactionDao = new TransactionDaoImpl();
	@Override
	public List<Bus> getRunningBuses(LocalDate dateOfJourney, String src, String dest) {	//change method [parameters
		// TODO Auto-generated method stub
		/*Steps :
			1. Input Date, Src, Destination
			2. get dayOfJourney
			3. check in busList
			4. return buses
			5. select buses
			6. find transaction
			7. input number of passengers
			8. check seatsAvailable
			8. if yes, then proceed to add passengers, add to list<passenger>
			9. create booking object by adding remaining details.
			10. add to list<booking:>
		 */
		//DayOfWeek d = b.getDateOfJourney().getDayOfWeek();
		//getBuses(dateOfJourney, src, dest);

		List<Bus> busList = new ArrayList<Bus>();
		Set<DayOfWeek> days;
		DayOfWeek d = dateOfJourney.getDayOfWeek();
		for (Bus bus : (busDao.findAllBuses())) {
			days = bus.getDayOfJourney();
			if(days.contains(d)) {
				if((bus.getSource().equalsIgnoreCase(src)) && bus.getDestination().equalsIgnoreCase(dest))
					busList.add(bus);
			}
			
		}
		return busList;
	}



	@Override
	public List<Booking> viewTicketsByDate(LocalDate date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer cancelTicket(Booking b) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Booking> viewTicketList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Passenger addPassenger(Passenger passenger) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Passenger> viewPassengers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double calculateCost() {
		// TODO Auto-generated method stub
		return null;
	}

}
