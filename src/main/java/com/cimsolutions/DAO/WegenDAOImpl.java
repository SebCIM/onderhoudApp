package com.cimsolutions.DAO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.cimsolutions.entities.Baan;
import com.cimsolutions.entities.Userreparatie;
import com.cimsolutions.entities.Wegenlijst;
import com.cimsolutions.utils.HibernateUtils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WegenDAOImpl implements WegenDAO {

	private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Wegenlijst> listWegen() {
		// open session from class HibernateUtils
		SessionFactory factory = HibernateUtils.getSessionFactory();
		// get session to connect with database
		Session session = factory.getCurrentSession();

		try {
			// open session to work with database
			session.getTransaction().begin();
			List<Wegenlijst> wegList = session.createQuery("from Wegenlijst").list();
			if (wegList != null) {
				for (Wegenlijst w : wegList) {
					// logger.info("Weg List:" + w);
				}
				// close and commit transaction of current session
				session.getTransaction().commit();
				return wegList;
			}

		} catch (Exception e) {
			// if something goes wrong, we will rollback
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Wegenlijst getWegById(int id) {
		// open session from class HibernateUtils
		SessionFactory factory = HibernateUtils.getSessionFactory();
		// get session to connect with database
		Session session = factory.getCurrentSession();

		try {
			// open session to work with database
			session.getTransaction().begin();
			Wegenlijst w = (Wegenlijst) session.load(Wegenlijst.class, new Integer(id));
			if (w != null) {
				// logger.info("Baan loaded successfully, Baan details=" + w);
				// close and commit transaction of current session
				session.getTransaction().commit();
				return w;
			}

		} catch (Exception e) {
			// if something goes wrong, we will rollback
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return null;
	}

	// Get Filtered
	@Override
	public List<Wegenlijst> listFilteredWegen() {

		// Nieuwe wegenLijst
		List<Wegenlijst> wegenFilterList = new ArrayList<Wegenlijst>();
		// open session from class HibernateUtils
		SessionFactory factory = HibernateUtils.getSessionFactory();
		// get session to connect with database
		Session session = factory.getCurrentSession();

		try {
			// open session to work with database
			session.getTransaction().begin();
			List<Wegenlijst> wegList = session.createQuery("from Wegenlijst").list();
			String current = wegList.get(0).getDistrict();
			if (wegList != null) {
				for (int i = 0; i < wegList.size(); i++) {
					if (wegList.get(i).getDistrict() != "") {
						wegenFilterList.add(wegList.get(i));
					}
				}
				for (Wegenlijst w : wegList) {
					// logger.info("Weg List:" + w);
				}
				
				HashSet<Object> seen=new HashSet<>();
				wegList.removeIf(e->!seen.add(e.getDistrict()));

				// close and commit transaction of current session
				session.getTransaction().commit();
				return wegList;
			}

		} catch (Exception e) {
			// if something goes wrong, we will rollback
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return null;
	}

}
