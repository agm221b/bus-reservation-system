package com.cg.busreservationsystem.service;

import java.time.LocalDate;
import java.util.List;

import com.cg.busreservationsystem.dao.BookingDao;
import com.cg.busreservationsystem.dao.BookingDaoImpl;
import com.cg.busreservationsystem.dao.BusDao;
import com.cg.busreservationsystem.dao.BusDaoImpl;
import com.cg.busreservationsystem.dao.TransactionDao;
import com.cg.busreservationsystem.dao.TransactionDaoImpl;
import com.cg.busreservationsystem.dto.Bus;
import com.cg.busreservationsystem.dto.Transaction;

public class AdminServiceImpl implements AdminService{
	
	BusDao busDao=new BusDaoImpl();
	TransactionDao transactionDao=new TransactionDaoImpl();

	@Override
	public Bus addBusDetails(Bus bus) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer removeBusDetails(Integer busId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bus searchBus(Integer busId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Bus> viewBuses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transaction> getTransactionsByDate(LocalDate date) {
		// TODO Auto-generated method stub
		return null;
	}

}
