package com.cimsolutions.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.cimsolutions.entities.District;
import com.cimsolutions.utils.HibernateUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class DistrictDAOImpl implements DistrictDAO {

	private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<District> listDistrict() {
		// open session from class HibernateUtils
		SessionFactory factory = HibernateUtils.getSessionFactory();
		// get session to connect with database
		Session session = factory.getCurrentSession();

		try {
			// open session to work with database
			session.getTransaction().begin();
			List<District> districtList = session.createQuery("from District").list();
			if (districtList != null) {
				for (District u : districtList) {
					// logger.info("District List:" + u);
				}
				// close and commit transaction of current session
				session.getTransaction().commit();
				return districtList;
			}

		} catch (Exception e) {
			// if something goes wrong, we will rollback
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public District getDistrictById(int id) {
		// open session from class HibernateUtils
		SessionFactory factory = HibernateUtils.getSessionFactory();
		// get session to connect with database
		Session session = factory.getCurrentSession();

		try {
			// open session to work with database
			session.getTransaction().begin();
			District d = (District) session.load(District.class, new Integer(id));
			if (d != null) {
				// logger.info("District loaded successfully, District details=" + d);
				// close and commit transaction of current session
				session.getTransaction().commit();
				return d;
			}

		} catch (Exception e) {
			// if something goes wrong, we will rollback
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return null;
	}

}
