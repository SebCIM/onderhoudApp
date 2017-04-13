package com.cimsolutions.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.cimsolutions.entities.Reparatiemethoden;
import com.cimsolutions.utils.HibernateUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class MethodeDAOImpl implements MethodeDAO {

	private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Reparatiemethoden> listMethode() {
		// open session from class HibernateUtils
		SessionFactory factory = HibernateUtils.getSessionFactory();
		// get session to connect with database
		Session session = factory.getCurrentSession();

		try {
			// open session to work with database
			session.getTransaction().begin();
			List<Reparatiemethoden> methodenList = session.createQuery("from Reparatiemethoden").list();
			if (methodenList != null) {
				for (Reparatiemethoden b : methodenList) {
					// logger.info("Baan List:" + b);
				}
				// close and commit transaction of current session
				session.getTransaction().commit();
				return methodenList;
			}

		} catch (Exception e) {
			// if something goes wrong, we will rollback
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Reparatiemethoden getMethodeById(int id) {
		// open session from class HibernateUtils
		SessionFactory factory = HibernateUtils.getSessionFactory();
		// get session to connect with database
		Session session = factory.getCurrentSession();

		try {
			// open session to work with database
			session.getTransaction().begin();
			Reparatiemethoden m = (Reparatiemethoden) session.load(Reparatiemethoden.class, new Integer(id));
			if (m != null) {
				// logger.info("Baan loaded successfully, Baan details=" + b);
				// close and commit transaction of current session
				session.getTransaction().commit();
				return m;
			}

		} catch (Exception e) {
			// if something goes wrong, we will rollback
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return null;
	}

}
