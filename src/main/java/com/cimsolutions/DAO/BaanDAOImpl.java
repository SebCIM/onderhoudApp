package com.cimsolutions.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.cimsolutions.entities.Baan;
import com.cimsolutions.utils.HibernateUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class BaanDAOImpl implements BaanDAO {

	private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Baan> listBaan() {
		// open session from class HibernateUtils
		SessionFactory factory = HibernateUtils.getSessionFactory();
		// get session to connect with database
		Session session = factory.getCurrentSession();

		try {
			// open session to work with database
			session.getTransaction().begin();
			List<Baan> baanList = session.createQuery("from Baan").list();
			if (baanList != null) {
				for (Baan b : baanList) {
					// logger.info("Baan List:" + b);
				}
				// close and commit transaction of current session
				session.getTransaction().commit();
				return baanList;
			}

		} catch (Exception e) {
			// if something goes wrong, we will rollback
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Baan getBaanById(int id) {
		// open session from class HibernateUtils
		SessionFactory factory = HibernateUtils.getSessionFactory();
		// get session to connect with database
		Session session = factory.getCurrentSession();

		try {
			// open session to work with database
			session.getTransaction().begin();
			Baan b = (Baan) session.load(Baan.class, new Integer(id));
			if (b != null) {
				// logger.info("Baan loaded successfully, Baan details=" + b);
				// close and commit transaction of current session
				session.getTransaction().commit();
				return b;
			}

		} catch (Exception e) {
			// if something goes wrong, we will rollback
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return null;
	}

}
