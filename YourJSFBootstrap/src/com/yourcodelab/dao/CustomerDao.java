package com.yourcodelab.dao;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yourcodelab.db.ConnectDB;
import com.yourcodelab.model.Customer;



public class CustomerDao extends GenericDAO{

	private PreparedStatement ps;
	private String SQL_INSERTACTION = "INSERT INTO TB_CUSTOMER(name,email) VALUES (?,?);";
	private String SQL_UPDATEACTION = "UPDATE TB_CUSTOMER SET name=?,email=? WHERE id=?;";
    private String SQL_LISTARACTION = "SELECT * FROM TB_CUSTOMER;";
	private String SQL_DELETEACTION = "DELETE FROM TB_CUSTOMER  WHERE id=?;";
	private String SQL_PESQUISARNOMEACTION = "SELECT * FROM TB_CUSTOMER WHERE name=?;";
	
	
	public void insertCustomer(Customer c){
		try {
			// Abrir conexão
			openConnection();
			
			// Preparar o comando SQL a ser enviado ao BD
			ps = connect.prepareStatement(SQL_INSERTACTION);
			ps.setString(1, c.getName());
			ps.setString(2, c.getEmail());
			// Executar o comando de INSERT, logo não se espera retorno
			ps.executeUpdate();
			// Fechar conexão
			closeConnection();
			
		} catch (ClassNotFoundException e) {
			System.out.println("Class not Found"+e);
		} catch (IOException e) {
			System.out.println("File not Found");
		} catch (SQLException e) {
			System.out.println("Error on Connecting");
		}
	}
   public List<Customer> listar() throws ClassNotFoundException,IOException,SQLException{
		
		List<Customer> lista = new ArrayList<Customer>();
		
		// Abrir conexão
		openConnection();
		
		ps = connect.prepareStatement(SQL_LISTARACTION);
		
		ResultSet rs = ps.executeQuery();
		
		if(rs != null){
			while(rs.next()){
				Customer c = new Customer
						(rs.getInt("id"), 
										rs.getString("name"),
										rs.getString("email"));
				lista.add(c);
			}
		}
		closeConnection();
		
		return lista;
}
	public Customer updateCustomer(Customer c) throws SQLException, ClassNotFoundException, IOException{
		openConnection();
		
		ps = connect.prepareStatement(SQL_UPDATEACTION);
		
		ps.setString(1, c.getName());
		ps.setString(2, c.getEmail());
		ps.setInt(3, c.getId());
		ps.execute();
		closeConnection();
		
		return null;
		
	}
	public Customer deleteCustomer(Customer c) throws SQLException, ClassNotFoundException, IOException{
		openConnection();
		
		ps = connect.prepareStatement(SQL_DELETEACTION);
		
		ps.setInt(1, c.getId());
		ps.execute();
		closeConnection();
		
		return null;
	}
	public List<Customer> obterCustomerPorname(String name) throws ClassNotFoundException, IOException, SQLException{
		List<Customer> lista = new ArrayList<Customer>();
		openConnection();
		ps = connect.prepareStatement(SQL_PESQUISARNOMEACTION);
		ps.setString(1,name);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			Customer c = new Customer(rs.getInt("id"),rs.getString("name"),rs.getString("email"));
			lista.add(c);
		}
		return lista;
	}
	
}
