package com.cimsolutions.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.cimsolutions.DAO.StrookDAO;
import com.cimsolutions.DAO.UserDaoImpl;
import com.cimsolutions.entities.Baan;
import com.cimsolutions.entities.Strook;
import com.cimsolutions.utils.HibernateUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class StrookDAOImpl implements StrookDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Strook> listStrook() {
		// open session from class HibernateUtils
		SessionFactory factory = HibernateUtils.getSessionFactory();
		// get session to connect with database
		Session session = factory.getCurrentSession();

		try {
			// open session to work with database
			session.getTransaction().begin();
			List<Strook> strookList = session.createQuery("from Strook").list();
			if (strookList != null) {
				for (Strook s : strookList) {
					// logger.info("Strook List:" + s);
				}
				// close and commit transaction of current session
				session.getTransaction().commit();
				return strookList;
			}

		} catch (Exception e) {
			// if something goes wrong, we will rollback
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Strook getStrookById(int id) {
		// open session from class HibernateUtils
		SessionFactory factory = HibernateUtils.getSessionFactory();
		// get session to connect with database
		Session session = factory.getCurrentSession();

		try {
			// open session to work with database
			session.getTransaction().begin();
			Strook s = (Strook) session.load(Strook.class, new Integer(id));
			if (s != null) {
				// logger.info("Strook loaded successfully, Strook details=" + s);
				// close and commit transaction of current session
				session.getTransaction().commit();
				return s;
			}

		} catch (Exception e) {
			// if something goes wrong, we will rollback
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return null;
	}

}
