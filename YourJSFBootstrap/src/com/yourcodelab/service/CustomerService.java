package com.yourcodelab.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yourcodelab.dao.CustomerDao;
import com.yourcodelab.model.Customer;

public class CustomerService {

	CustomerDao dao = new CustomerDao();

	public List<Customer> listAll() throws ClassNotFoundException, IOException, SQLException{
		
		List<Customer> list = new ArrayList<Customer>();
		list = dao.listar();
		
		return list;
	}

	public void insertCustomer(Customer c){
		CustomerDao dao = new CustomerDao();
			if(c != null){
				System.out.println("Service..." + c.getName());
				dao.insertCustomer(c);
			}
	}
	
	public List<Customer> findCustomerByName(String name) throws ClassNotFoundException, IOException, SQLException{
		List<Customer> list = new ArrayList<Customer>();
		Customer c = new Customer(0,name,"");
			list = dao.obterCustomerPorname(name);
			return list;
	}
	public void updateCustomer(Customer c) throws ClassNotFoundException, SQLException, IOException{
		CustomerDao dao = new CustomerDao();
			if(c != null){
				System.out.println("Service..." + c.getName());
				dao.updateCustomer(c);
			}
	}
	public void deleteCustomer(Customer c) throws ClassNotFoundException, SQLException, IOException{
		CustomerDao dao = new CustomerDao();
			if(c != null){
				dao.deleteCustomer(c);
			}
	}
}
