package com.akash.springboot.project.carServiceApp.packg.customer;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class CustomerDAOImplementation implements CustomerDAO {

	private EntityManager entityManager;
	@Override
	@Transactional
	public List<Customer> getAllInfo() {
		// TODO Auto-generated method stub
		Session session=entityManager.unwrap(Session.class);
		
		//create a Query
		Query<Customer> query=session.createQuery("from Customer",Customer.class);
		
		List<Customer> c=query.getResultList();
		return c;
	}
	
	public CustomerDAOImplementation(EntityManager theEM) {
		entityManager=theEM;
	}

	@Override
	@Transactional
	public int deleteCustomerAppointment(String email) {
		// TODO Auto-generated method stub
		Session session=entityManager.unwrap(Session.class);
		
		String hql = "DELETE FROM Customer c WHERE c.email=:email";
		Query query = session.createQuery(hql);
		query.setParameter("email",email);
		int rowCount = query.executeUpdate();
		
		
		return query.executeUpdate();
		
	}

	@Override
	@Transactional
	public void addCustomerAppointment(Customer cc) {
		Session session=entityManager.unwrap(Session.class);
		
		session.saveOrUpdate(cc);
		
		System.out.println("Appointment Booked");
		
		return;
		
	}

	@Override
	@Transactional
	public void updateCustomerAppointment(Customer cc) {
		//use addCustomerAppointment code.
		//...coz Session locking is not multi-thread support
		return;
		
	}

	@Override
	@Transactional
	public Customer getAppointmentDetails(String email) {
		Session session=entityManager.unwrap(Session.class);
		
		Query query = session.createQuery("from Customer c where c.email = :email");
	      query.setParameter("email", email);
	      return (Customer) query.uniqueResult();
		
		
	}

	

}
