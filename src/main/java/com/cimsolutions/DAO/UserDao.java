package com.cimsolutions.DAO;

import java.io.Serializable;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.cimsolutions.entities.Apuser;
import com.cimsolutions.utils.HibernateUtils;

public class UserDao implements Serializable {

	public boolean checkLogin(String token) {

		// open session from class HibernateUtils
		SessionFactory factory = HibernateUtils.getSessionFactory();
		// get session to connect with database
		Session session = factory.getCurrentSession();

		try {
			// open session to work with database
			session.getTransaction().begin();
			String sql = "Select e from Apuser e where e.token = :token";
			Query query = session.createQuery(sql);
			query.setParameter("token", token);
			Object user = query.uniqueResult();
			if (user != null) {
				// close and commit transaction of current session
				session.getTransaction().commit();
				return true;
			}

		} catch (Exception e) {
			// TODO: handle exception
			// if something go wrong, we will rollback
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return false;
	}
}
