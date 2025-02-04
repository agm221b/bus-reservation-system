/**
 * 
 */
package com.cg.busreservationsystem.test;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.cg.busreservationsystem.dao.UserDao;
import com.cg.busreservationsystem.dao.UserDaoImpl;
import com.cg.busreservationsystem.dto.Booking;
import com.cg.busreservationsystem.dto.Bus;
import com.cg.busreservationsystem.dto.BusTransaction;
import com.cg.busreservationsystem.dto.Passenger;
import com.cg.busreservationsystem.dto.User;
import com.cg.busreservationsystem.exception.BookingException;
import com.cg.busreservationsystem.service.UserService;
import com.cg.busreservationsystem.service.UserServiceImpl;
import com.cg.busreservationsystem.service.Validation;
import com.cg.busreservationsystem.util.DBUtil;

/**
 * @author admin
 *
 */
public class TestBusBooking {

	static UserService userService;
	static int counter = 0;
	static Validation validation;
    static final String EXCEPTIONMSG="Exception occured: ";
	public static void main(String[] args) {
		userService = new UserServiceImpl();
		showUserMenu();
	}
	
	static void showUserMenu() {
		validation = new Validation();
		Scanner scanner = new Scanner(System.in);
		int runLoop = 1;
		int goBackChoice=1;
		String input;
		while (runLoop != 0) {
			int choice;
			while (true) {
				System.out.println("-------------------------------------");
				System.out.println("Select 1 for Admin");
				System.out.println("Select 2 for Customer");
				System.out.println("--------------------------------------");
				System.out.println("Enter your choice:");
				input = scanner.next();
		
				try {
					choice = validation.validateChoice(input);
					if (choice == 1) {
						while(goBackChoice!=0) {
							System.out.println("Press 1 to continue to the same menu, 0 to go back");
							goBackChoice=scanner.nextInt();
							if(goBackChoice==1) {
								adminMenu();
							}
							else {
								break;
							}
							
						}
						
					} else if (choice == 2) {
						while(goBackChoice!=0) {
							System.out.println("Press 1 to continue to the same menu, 0 to go back");
							goBackChoice=scanner.nextInt();
							if(goBackChoice==1) {
								customerMenu();
							}
							else {
								break;
							}
						}
						
					}
					break;
				} catch (RuntimeException e) { 
				System.out.println(EXCEPTIONMSG + e.getMessage());
					continue;
				}
			}

			System.out.println("Press 1 to continue, 0 to exit to the application");
			runLoop = scanner.nextInt();

		}
	}

	static void adminMenu() {
		validation = new Validation();
		Scanner scanner = new Scanner(System.in);
		
		int runLoop = 1;
		String input;
		while (runLoop != 0) {
			int choice = 0;
			while (true) {
				System.out.println("-------------------------------------------------");
				System.out.println("Press 1 for Adding Bus Details");
				System.out.println("Press 2 for Removing Bus Details");
				System.out.println("Press 3 for Modifying Bus Details");
				System.out.println("Press 4 for Viewing All Buses");
				System.out.println("Press 5 for Viewing BusTransaction Details");
				System.out.println("Press 6 for Editing Personal Details");
				System.out.println("---------------------------------------------------");
				System.out.println("Enter your choice:");
				
				input = scanner.next(); 
				try {
					choice = validation.validateAdminChoice(input);
					break;
				} catch (RuntimeException e) {
					
					System.out.println(EXCEPTIONMSG + e.getMessage());
					continue;
				}
			}

			switch (choice) {
			case 1:
				// fetch details here
				System.out.println("Enter the bus name");
				String busName = scanner.next();
				String busType;
				while (true) {

					System.out.println("Enter the bus type(sleeper/semi_sleeper)");
					busType = scanner.next();

					try {
						validation.validateBusType(busType);
						break;
					} catch (RuntimeException e) {
						
						System.out.println(EXCEPTIONMSG + e.getMessage());
						continue;
					}
				}

				String busClass;
				while (true) {

					System.out.println("Enter the bus class(ac/non_ac)");
					busClass = scanner.next();
					try {
						validation.validateBusClass(busClass);
						break;
					} catch (Exception e) {
						
						System.out.println(EXCEPTIONMSG + e.getMessage());
						continue;
					}
				}

				int busSeats;
				while (true) {
					System.out.println("Enter the no of bus seats");
					try {
						busSeats = UserServiceImpl.checkNumberInput();
						break;
					} catch (Exception e) {
						
						System.out.println("Exception :" + e.getMessage());
						continue;
					}
				}
				int noOfDays = 0;
				while (true) {
					System.out.println("Enter the no of days of the week on which day the bus will run");
					input = scanner.next();
					try {
						noOfDays = validation.validateDayChoice(input);
						break;
					} catch (RuntimeException e) {
						
						System.out.println(EXCEPTIONMSG + e.getMessage());
						continue;
					}
				}
				List<DayOfWeek> days = new ArrayList<DayOfWeek>();
				for (int i = 0; i < noOfDays; i++) {
					int day = 0;
					while (true) {
						System.out.println("Enter the day number starting from 1(Monday) to 7(Sunday): ");
						input = scanner.next();

						try {
							day = validation.validateChoice(input);
							break;
						} catch (RuntimeException e) {
							
							System.out.println(EXCEPTIONMSG + e.getMessage());
							continue;
						}
					}
					days.add(DayOfWeek.of(day));
				}

				String source;
				String destination;
				while (true) {
					System.out.println("Enter the bus source: ");
					source = scanner.next();

					System.out.println("Enter the bus destination");

					destination = scanner.next();

					try {
						validation.validateTravel(source, destination);
						break;
					} catch (Exception e) {
						
						System.out.println(EXCEPTIONMSG + e.getMessage());
						continue;
					}
				}

				double costPerSeat;
				while (true) {

					System.out.println("Enter the bus cost per seat");

					try {
						costPerSeat = Validation.validateCost();
						break;
					} catch (Exception e) {
						
						System.out.println(EXCEPTIONMSG + e.getMessage());
						continue;
					}
				}
				Bus bus = new Bus();
				bus.setBusName(busName);
				bus.setBusType(busType);
				bus.setBusClass(busClass);
				bus.setNoOfSeats(busSeats);
				bus.setDayOfJourney(days);
				bus.setSource(source);
				bus.setDestination(destination);
				bus.setCost(costPerSeat);

				bus = userService.addBusDetails(bus);
				System.out.println("Bus added: ");
				System.out.println("Bus Id: " + bus.getBusId());
				System.out.println("Bus name: " + bus.getBusName());
				System.out.println("Bus Type: " + bus.getBusType());
				System.out.println("Bus Class: " + bus.getBusClass());
				System.out.println("Number of Seats: " + bus.getNoOfSeats());
				System.out.println("Days Of Journey: " + bus.getDayOfJourney());
				System.out.println("Source: " + bus.getSource());
				System.out.println("Destination: " + bus.getDestination());
				System.out.println("Cost per seat: " + bus.getCost());
				// System.out.println(userService.addBusDetails(bus));

				// System.out.println(userServ.viewBuses());

				break;

			case 2:
				BigInteger busId;
				while (true) {
					System.out.println("Enter the bus id to remove");
					input = scanner.next(); // INputMismatchExcp
					try {
						busId = validation.validateBigIntegerChoice(input);
						int removeStatus = userService.removeBusDetails(busId);
						if (removeStatus == 1) {
							System.out.println("Bus removed");
						} else {
							System.out.println("Id not found");
						}
						break;
					} catch (RuntimeException e) {

						System.out.println(EXCEPTIONMSG + e.getMessage());
						continue;
					}
				}

				break;
			case 3:
				// BigInteger busId ;
				while (true) {
					System.out.println("Enter the bus id to update details: ");
					input = scanner.next();
					try {
						busId = validation.validateBigIntegerChoice(input);

						for (Bus busObj : userService.viewBuses()) {
							if (busId.equals(busObj.getBusId())) {
								double cost;
								while (true) {
									System.out.println("Update the cost per seat of the bus: ");
									try {
										cost = Validation.validateCost();

										busObj.setCost(cost);
										System.out.println("cost per seat of the bus updated");
										break;
									} catch (Exception e) {
										
										System.out.println(EXCEPTIONMSG + e.getMessage());
										continue;
									}
								}

							} else {
								System.out.println("Id not found");
							}
						}
						break;
					} catch (RuntimeException e) {

						System.out.println(EXCEPTIONMSG + e.getMessage());
						continue;
					}
				}
				break;
			case 4:
				for(Bus busOb:userService.viewBuses()) {
					System.out.println(busOb.getBusId()+" "+busOb.getBusName()+" "+busOb.getNoOfSeats()+" "+busOb.getBusType()+" "+busOb.getBusClass()+" "+busOb.getSource()+" "+busOb.getDestination()+" "+busOb.getCost());
				}
				break;
			case 5:
				LocalDate date;
				while (true) {
					System.out.println("Enter the date(DD-MM-YYYY): ");
					String dateStr = scanner.next();
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
					date = LocalDate.parse(dateStr, formatter);
					try {
						validation.validateDate(date);
						break;
					} catch (Exception e) {
						System.out.println("Exception occurred: " + e.getMessage());
						continue;

					}
				}
				System.out.println("List of transactions");
				for (BusTransaction busTransaction : userService.getTransactionsByDate(date)) {
					System.out.println(
							busTransaction.getDate() + " " + busTransaction.getBus() + busTransaction.getBookings());
				}
				break;
			case 6:
				System.out.println("You cannot edit your personal details. System is under maintenance");
				break;
			default:
				System.out.println("Wrong choice : Enter a valid Integer input");
				break;
			}
			System.out.println("----------------------------------------------------");
			System.out.println("Press 1 to continue, 0 to exit to the main menu");
			runLoop = scanner.nextInt();
			System.out.println("----------------------------------------------------");
		}

	}

	static void customerMenu() {
		validation = new Validation();
		Scanner scanner = new Scanner(System.in);
		int runLoop = 1;
		String input;
		while (runLoop != 0) {
			int choice = 0;
			while (true) {
				System.out.println("-----------------------------------------------------");
				System.out.println("Press 1 to Booking a Ticket");
				System.out.println("Press 2 for Viewing a Booking");
				System.out.println("Press 3 for Viewing Bookings List");
				System.out.println("Press 4 for Cancelling a Ticket");
				System.out.println("Press 5 for Editing Personal Details");
				System.out.println("------------------------------------------------------");
				System.out.println("Enter your choice: ");
				input = scanner.next(); // INputMismatchExcp
				try {
					choice = validation.validateIntegerChoice(input);
					break;
				} catch (RuntimeException e) {
					
					System.out.println(EXCEPTIONMSG + e.getMessage());
					continue;
				}
			}

			switch (choice) {
			case 1:
				LocalDate date;
				while (true) {
					System.out.println("Enter your date of journey(DD-MM-YYYY):");
					String dateStr = scanner.next();
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
					date = LocalDate.parse(dateStr, formatter);
					try {
						validation.validateDate(date);
						break;
					} catch (Exception e) {
						System.out.println("Exception occurred: " + e.getMessage());
						continue;

					}
				}

				String source;
				String destination;
				while (true) {
					System.out.println("Enter the bus source: ");
					source = scanner.next();

					System.out.println("Enter the bus destination");

					destination = scanner.next();

					try {
						validation.validateTravel(source, destination);
						break;
					} catch (Exception e) {
						
						System.out.println(EXCEPTIONMSG + e.getMessage());
						continue;
					}
				}

				List<Bus> busList = userService.getRunningBuses(date, source, destination);
				System.out.println("Running buses on your day of journey: ");
				int i = 0;
				for (Bus b : busList) {
					System.out.println((i + 1) + " " + b.getBusId() + " " + b.getBusName() + " " + b.getBusType() + " "
							+ b.getBusClass() + " " + b.getCost());
				}
				BigInteger busId;
				while (true) {
					System.out.println("Enter the bus Id of the bus you will be travelling: ");
					input = scanner.next();
					try {
						busId = validation.validateBigIntegerChoice(input);
						// BigInteger busId=scanner.nextBigInteger();
						break;
					} catch (Exception e) {
						
						System.out.println(EXCEPTIONMSG + e.getMessage());
						continue;
					}
				}

				for (Bus busObj : busList) {
					if (busId.equals(busObj.getBusId())) {
						int passengersCount;
						while (true) {
							System.out.println("Enter the number of passengers: ");
							passengersCount = scanner.nextInt();
							try {
								validation.validatePassengersCount(passengersCount);
								break;
							} catch (Exception e) {
								
								System.out.println(EXCEPTIONMSG + e.getMessage());
								continue;
							}
						}
						// System.out.println("Enter the number of passengers: ");
						// passengersCount=scanner.nextInt();
						boolean bookingStatus = userService.checkBusTransaction(date, busObj, passengersCount);
						if (bookingStatus) {
							List<Passenger> passengersList = new ArrayList<Passenger>();
							for (int j = 0; j < passengersCount; j++) {
								System.out.println("Enter Passenger " + j + 1 + " details: ");
								Passenger passenger = new Passenger();
								System.out.println("Name: ");
								String passengerName = scanner.next();
								passenger.setPassengerName(passengerName);
								int passengerAge = scanner.nextInt();
								passenger.setPassengerAge(passengerAge);
								System.out.println("Gender(M/F)");
								char passengerGender = scanner.next().charAt(0);
								passenger.setPassengerGender(passengerGender);
								passengersList.add(passenger);
							}
							String paymentMode;
							while (true) {
								System.out.println("Enter the mode of payment(UPI/DC/CC/NB): ");
								paymentMode = scanner.next();
								try {
									validation.validatePaymentMode(paymentMode);
									break;
								} catch (Exception e) {
									
									System.out.println(EXCEPTIONMSG + e.getMessage());
									continue;
								}
							}

							Booking booking = userService.createBooking(passengersList, date, busObj, paymentMode);
							System.out.println("Booking details: ");
							System.out.println(booking.getBookingId() + " " + booking.getDateOfJourney() + " "
									+ booking.getModeOfPayment());
							System.out.println("List of passengers");
							for (Passenger p : booking.getPassengers()) {
								System.out.println(p.getPassengerName() + " " + p.getPassengerAge() + " "
										+ p.getPassengerGender());
							}
						} else {
							System.out.println("Bus is full. Can't proceed with booking");
							continue;
						}
					}
				}
				break;
			case 2:
				break;
			case 3:
				System.out.println("List of your bookings: ");
				for (Booking booking : userService.viewTicketList()) {
					System.out.println(booking.getBookingId() + " " + booking.getDateOfJourney() + " "
							+ booking.getModeOfPayment() + " " + booking.getPassengers());
				}
				break;
			case 4:
				BigInteger bookingId;
				while (true) {
					System.out.println("Enter the booking id you want to cancel the booking for: ");
					input = scanner.next(); // INputMismatchExcp
					try {
						bookingId = validation.validateBigIntegerChoice(input);

						for (Booking booking : userService.viewTicketList()) {
							if (booking.getBookingId().equals(bookingId)) {
								int cancelStatus = userService.cancelTicket(booking);
								if (cancelStatus == 1) {
									System.out.println("Booking cancelled");
								} else {
									System.out.println("Error in cancelling the booking");
								}
							}
						}
						break;
					} catch (RuntimeException e) {

						System.out.println(EXCEPTIONMSG + e.getMessage());
						continue;
					}
				}
				break;

			case 5:
				System.out.println("You cannot edit your personal details. System is under maintenance");
				break;
			default:
				System.out.println("Wrong choice : Enter a valid Integer input");
				break;
			}
			System.out.println("------------------------------------------------------------");
			System.out.println("Press 1 to continue, 0 to exit to the main menu");
			runLoop = scanner.nextInt();
			

		}
		scanner.close();

	}

}
