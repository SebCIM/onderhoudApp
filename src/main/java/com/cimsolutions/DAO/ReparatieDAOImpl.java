package com.cimsolutions.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cimsolutions.entities.Apuser;
import com.cimsolutions.entities.Reparatie;
import com.cimsolutions.entities.Userreparatie;
import com.cimsolutions.utils.HibernateUtils;

public class ReparatieDAOImpl implements ReparatieDAO {
	private static final Logger logger = LoggerFactory.getLogger(ReparatieDAOImpl.class);

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	//Create
	public void addReparatie(Reparatie r) {
	}
	//Read
	public Reparatie getReparatieById(int id) {
		return null;
	}
	public Reparatie getReparatieByUser(Apuser u) {
		return null;
	}
	//Update
	public void updateUser(Reparatie r) {
	}
	//Delete
	public void removeUser(int id) {
	}
	//Get All
	@SuppressWarnings("unchecked")
	@Override
	public List<Userreparatie> listReparaties() {
		// open session from class HibernateUtils
				SessionFactory factory = HibernateUtils.getSessionFactory();
				// get session to connect with database
				Session session = factory.getCurrentSession();

				try {
					// open session to work with database
					session.getTransaction().begin();
					List<Userreparatie> reparatieList = session.createQuery("from Userreparatie").list();
					if (reparatieList != null) {
						for (Userreparatie r : reparatieList) {
							logger.info("Reparatie List::" + r);
						}
						// close and commit transaction of current session
						session.getTransaction().commit();
						return reparatieList;
					}

				} catch (Exception e) {
					// if something goes wrong, we will rollback
					session.getTransaction().rollback();
					e.printStackTrace();
				}
				return null;
	}
}