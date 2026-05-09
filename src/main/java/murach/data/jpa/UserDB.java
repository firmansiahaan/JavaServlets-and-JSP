package murach.data.jpa;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import murach.business.jpa.User;
import murach.sql.jpa.DBUtil;

public class UserDB {
	public static User getUserById(Long userId) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		try {
			User user = em.find(User.class, userId);
			return user;
		}
		finally {
			em.close();
		}
	}
	
	public static User selectUser(String email) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String qString = "SELECT u from User u " +
				"WHERE u.email = :email";
		TypedQuery<User> q = em.createQuery(qString, User.class);
		q.setParameter("email", email);
		
		User user = null;
		try {
			user = q.getSingleResult();
		} catch (NoResultException nre) {
			System.out.println(nre);
		}
		finally {
			em.close();
		}
		
		return user;
	}
	
	public static List<User> selectUsers() {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT u from User u";
        TypedQuery<User> q = em.createQuery(qString, User.class);

        List<User> users;
        try {
            users = q.getResultList();
            if (users == null || users.isEmpty())
                users = null;
        } finally {
            em.close();
        }
        return users;
    }
	
	public static void insert(User user) {
		EntityManagerFactory emf = DBUtil.getEmFactory();
		EntityManager em = emf.createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.persist(user);
			trans.commit();
		} catch(Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}
	
	public static void update(User user) {
		EntityManagerFactory emf = DBUtil.getEmFactory();
		EntityManager em = emf.createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.remove(user);
			trans.commit();
		} catch(Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}
		
	public static void delete(User user) {
		EntityManagerFactory emf = DBUtil.getEmFactory();
		EntityManager em = emf.createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.remove(user);
			trans.commit();
		} catch(Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}
	
	public static boolean emailExists(String email) {
		User u = selectUser(email);
		return u != null;
	}
}
