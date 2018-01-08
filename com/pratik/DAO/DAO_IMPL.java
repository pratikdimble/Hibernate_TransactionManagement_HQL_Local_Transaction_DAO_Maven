package com.pratik.DAO;

import java.util.List;

import org.hibernate.CacheMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.pratik.domain.BankAccount;
import com.pratik.utility.HibernateUtil;

public class DAO_IMPL implements DAO_Interface {
	private static final String HQL_QUERY_WITHDRAW="UPDATE BankAccount SET balance=balance-:amount WHERE id=:source";
	private static final String HQL_QUERY_CREDIT="UPDATE BankAccount SET balance=balance+:amount WHERE id=:dest";
	
	@Override
	public void saveCustomer() {
		Session ses=null;
		//get the session
		ses=HibernateUtil.getSession();
		
		BankAccount acc=new BankAccount();
		
		acc.setId(102);
		acc.setName("Rohit");
		acc.setBalance(30000);
		//save objs (parent to child)
				Transaction tx=null;
				try{
				 tx=ses.beginTransaction();
				   ses.save(acc);
				 tx.commit();
				 System.out.println("Objects are saved....");
				  }//try
				  catch(Exception e){
				    tx.rollback();
				    }
				//close the session
				HibernateUtil.closeSession(ses);
	}
	
	@Override
	public boolean transferMoney(int source,int destination,float amount) {
		Session ses=null;
		boolean flag=false;
		//get the session
		ses=HibernateUtil.getSession();
		System.out.println("Source:: "+source+"\tDestination:: "+destination);
		BankAccount acc=new BankAccount();
		//create hql query
		Query query1=ses.createQuery(HQL_QUERY_WITHDRAW);
			//set the attributes
			query1.setInteger("source", source);
			query1.setFloat("amount", amount);
		//create hql query
		Query query2=ses.createQuery(HQL_QUERY_CREDIT);
			//set the attributes
			query2.setInteger("dest", destination);
			query2.setFloat("amount", amount);
		 
		
		//save objs (parent to child)
				Transaction tx=null;
				try{
				 tx=ses.beginTransaction();
				 int result1=query1.executeUpdate();
				  int result2=query2.executeUpdate();
				  if(result1==0||result2==0)
				  {
					  tx.rollback();
					  System.out.println("***\n\t MONEY TRANSEFERED FAILED---> TRANSACTION ROLLBACKED ....");
				  flag=false;
				  }
				  else
				  {
				 tx.commit();
				  System.out.println("***\n\t MONEY TRANSEFERED SUCCESSFULLY---> TRANSACTION COMMITED ....");
				 flag=true;
				  }
					  }//try
				  catch(Exception e){
				    tx.rollback();
				    flag=false;
				    }
				//close the session
				HibernateUtil.closeSession(ses);
				return flag;

}

	
}//class close
