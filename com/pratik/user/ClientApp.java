package com.pratik.user;

import com.pratik.DAO.DAO_Factory;
import com.pratik.DAO.DAO_Interface;
import com.pratik.utility.HibernateUtil;

public class ClientApp {

	public static void main(String[] args) {

			//use DAO
		DAO_Interface dao=DAO_Factory.getInstance();
		//get the DAO
			//dao.saveCustomer();
		
		boolean status=dao.transferMoney(101,102,5000);
	if(status==true)
		System.out.println("***\n\t TRANSACTION Successfull ....");
	else
		System.out.println("***\n\t TRANSACTION Failed ....");
			//close the session factory
			HibernateUtil.closeFactory();
	}
	

}
