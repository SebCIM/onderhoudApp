//package com.cimsolutions.DAO;
//
//import java.util.List;
//
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//
//import com.cimsolutions.entities.Deklagenlijst;
//import com.cimsolutions.utils.HibernateUtils;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//public class DeklagenDAOImpl implements DeklagenDAO {
//
//	private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
//
//	private SessionFactory sessionFactory;
//
//	public void setSessionFactory(SessionFactory sf) {
//		this.sessionFactory = sf;
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<Deklagenlijst> listDeklagen() {
//		// open session from class HibernateUtils
//		SessionFactory factory = HibernateUtils.getSessionFactory();
//		// get session to connect with database
//		Session session = factory.getCurrentSession();
//
//		try {
//			// open session to work with database
//			session.getTransaction().begin();
//			List<Deklagenlijst> deklagenList = session.createQuery("from Deklagenlijst").list();
//			if (deklagenList != null) {
//				for (Deklagenlijst b : deklagenList) {
//					// logger.info("Deklagenlijst List:" + b);
//				}
//				// close and commit transaction of current session
//				session.getTransaction().commit();
//				return deklagenList;
//			}
//
//		} catch (Exception e) {
//			// if something goes wrong, we will rollback
//			session.getTransaction().rollback();
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	@Override
//	public Deklagenlijst getDeklaagById(int id) {
//		// open session from class HibernateUtils
//		SessionFactory factory = HibernateUtils.getSessionFactory();
//		// get session to connect with database
//		Session session = factory.getCurrentSession();
//
//		try {
//			// open session to work with database
//			session.getTransaction().begin();
//			Deklagenlijst b = (Deklagenlijst) session.load(Deklagenlijst.class, new Integer(id));
//			if (b != null) {
//				// close and commit transaction of current session
//				session.getTransaction().commit();
//				return b;
//			}
//
//		} catch (Exception e) {
//			// if something goes wrong, we will rollback
//			session.getTransaction().rollback();
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//}
