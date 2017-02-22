package com.cimsolutions.DAO;

import java.io.Serializable;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.cimsolutions.utils.HibernateUtils;

public class UserDao implements Serializable {

	public boolean checkLogin(String username, String pass) {
		
		//open session from class HibernateUtils
		SessionFactory factory = HibernateUtils.getSessionFactory();
		//get session to connect with database
		Session session = factory.getCurrentSession();
		
		try {
			//open session to work with database
			session.getTransaction().begin();
			String sql = "Select e from testdb e where e.username = :user and e.password = :pass";
			Query query = session.createQuery(sql);
			query.setParameter("user", username);
			query.setParameter("pass", pass);
			Object user = query.uniqueResult();
			if (user != null) {
				//close and commit trasaction of current session
				session.getTransaction().commit();
				return true;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			//if something go wrong, we will rollback
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return false;
	}
}
