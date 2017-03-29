package com.cimsolutions.DAO;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.cimsolutions.DAO.UserDaoImpl;
import com.cimsolutions.entities.Apuser;
import com.cimsolutions.entities.Reparatie;
import com.cimsolutions.entities.Userreparatie;
import com.cimsolutions.utils.HibernateUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserReparatieDAOImpl implements UserReparatieDAO {

	private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

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
				for (Userreparatie s : reparatieList) {
					// logger.info("Reparatie List:" + s);
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
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Userreparatie> listReparatiesById(int id) {
		// open session from class HibernateUtils
		SessionFactory factory = HibernateUtils.getSessionFactory();
		// get session to connect with database
		Session session = factory.getCurrentSession();

		try {
			// open session to work with database
			session.getTransaction().begin();
			List<Userreparatie> reparatieList = session.createQuery("from Userreparatie where apuserid = " + id).list();
			if (reparatieList != null) {
				for (Userreparatie s : reparatieList) {
					// logger.info("Reparatie List:" + s);
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

	@Override
	public Userreparatie getReparatieById(int id) {
		System.out.println("staren transactie");
		// open session from class HibernateUtils
		SessionFactory factory = HibernateUtils.getSessionFactory();
		// get session to connect with database
		Session session = factory.getCurrentSession();

		Userreparatie ur = new Userreparatie();

		try {
			System.out.println("ophalen transactie");
			session.getTransaction().begin();

			ur = (Userreparatie) session.load(Userreparatie.class, new Integer(id));

			System.out.println(ur.getApuser().getUsername());

			if (ur != null) {
				session.getTransaction().commit();
				logger.info("UserReparatie selected successfully, UserReparatie details=" + ur);
				return ur;
			}

		} catch (Exception e) {
			System.out.println("transactie failed");
			// if something goes wrong, we will rollback
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return ur;
	}

	@Override
	public void addReparatie(Reparatie r, int id) {
		Userreparatie ur = new Userreparatie();
		UserDaoImpl dao = new UserDaoImpl();

		ur.setApuser(dao.getUserById(id));

		r.setDatumtijd(this.getCurrentTimeUsingCalendar());

		System.out.println("add user");
		// open session from class HibernateUtils
		SessionFactory factory = HibernateUtils.getSessionFactory();
		// get session to connect with database
		Session session = factory.getCurrentSession();

		Transaction testTransaction = null;

		try {
			// open session to work with database
			testTransaction = session.beginTransaction();
			session.save(r);
			testTransaction.commit();

			logger.info("Reparatie saved successfully, Reparatie Details=" + r);

		} catch (Exception e) {
			// if something goes wrong, we will rollback
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			if (!testTransaction.wasCommitted()) {
				testTransaction.rollback();
			} else {
				System.out.println("Toevoegen van userreparatie");
				this.addUserReparatie(r, dao.getUserById(id));
			}
		}
	}

	public void addUserReparatie(Reparatie r, Apuser u) {
		Userreparatie ur = new Userreparatie();

		ur.setApuser(u);
		ur.setReparatie(r);

		// open session from class HibernateUtils
		SessionFactory factory = HibernateUtils.getSessionFactory();
		// get session to connect with database
		Session session = factory.getCurrentSession();

		Transaction testTransaction = null;

		try {
			// open session to work with database
			testTransaction = session.beginTransaction();
			session.save(ur);
			testTransaction.commit();

			logger.info("UserReparatie saved successfully, Reparatie Details=" + r);

		} catch (Exception e) {
			// if something goes wrong, we will rollback
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			if (!testTransaction.wasCommitted()) {
				testTransaction.rollback();
			}
		}
	}

	private Date getCurrentTimeUsingCalendar() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		System.out.println(dateFormat.format(date)); // 2016/11/16 12:08:43
		return date;
	}

	@Override
	public Reparatie getReparatieByUser(Apuser u) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateUser(Reparatie r) {
		// TODO Auto-generated method stub

	}

	public Userreparatie removeUserRepair(int id) {
		// TODO Auto-generated method stub
		System.out.println("removing Userrepair");
		// open session from class HibernateUtils
		SessionFactory factory = HibernateUtils.getSessionFactory();
		// get session to connect with database
		Session session = factory.getCurrentSession();

		Transaction testTransaction = null;
		Userreparatie ur = new Userreparatie();

		try {
			// open session to work with database
			testTransaction = session.beginTransaction();

			ur = (Userreparatie) session.load(Userreparatie.class, new Integer(id));

			if (ur != null) {
				session.delete(ur);
				testTransaction.commit();
				logger.info("UserReparatie deleted successfully, UserReparatie details=" + ur);
				return ur;
			}

		} catch (Exception e) {
			// if something goes wrong, we will rollback
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return ur;
	}

}
