package com.yourcodelab.bean;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.yourcodelab.model.Customer;
import com.yourcodelab.service.CustomerService;

@ManagedBean(name = "customerManagedBean")
@SessionScoped
public class CustomerManagedBean implements Serializable{
	private static final long serialVersionUID = -9004785433894347006L;
	
	private Customer customer;
	private List<Customer> listCustomer;
	private CustomerService service;
	
	public CustomerManagedBean() throws ClassNotFoundException, IOException, SQLException{
		service = new CustomerService();
		customer = new Customer(0, "", "");
		listCustomer = service.listAll();
	}
	

	public void searchByNameAction() throws ClassNotFoundException, IOException, SQLException{
	
		System.out.println("Searching..." );
		this.listCustomer = service.findCustomerByName(customer.getName());
		
	}
	
	public String insertCustomerAction() throws ClassNotFoundException, IOException, SQLException{
		System.out.println("Saving..." + customer.getName());
		service.insertCustomer(customer);
		customer = new Customer(0, "", "");
		listCustomer = service.listAll();
		return "index";
	}
	
	public String updateCustomerAction() throws ClassNotFoundException, SQLException, IOException{
		System.out.println("Updating...");
		service.updateCustomer(customer);
		
		return "index";
	}
	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public String deleteCustomerAction() throws ClassNotFoundException, SQLException, IOException{
		service.deleteCustomer(customer);
		listCustomer = service.listAll();
		return "index";
	}
	public List<Customer> getListCustomer() {
		return listCustomer;
	}

	public void setListCustomer(List<Customer> listCustomer) {
		this.listCustomer = listCustomer;
	}
	
}
