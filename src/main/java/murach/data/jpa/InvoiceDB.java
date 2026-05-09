package murach.data.jpa;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import murach.business.jpa.Invoice;
import murach.sql.jpa.DBUtil;

public class InvoiceDB {
	
	public static List<Invoice> selectUnprocessedInvoice() {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String qString = "SELECT i FROM Invoice i  " +
				"WHERE i.isProcessed = 'n'";
		TypedQuery<Invoice> q = em.createQuery(qString, Invoice.class);
		
		List<Invoice> invoices;
		try {
			invoices = q.getResultList();
			if (invoices == null || invoices.isEmpty()) {
				invoices = null;
			}
		}
		finally {
			em.close();
		}
		
		return invoices;
	}
	
	public static void updateMultipleEntities() {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		String qString = "UPDATE Invoice i set i.isProcessed='Y' " +
				"WHERE i.id < :id";
		Query q = em.createQuery(qString);
		q.setParameter("id", 200);
		try {
			trans.begin();
			q.executeUpdate();
			trans.commit();
		} catch(Exception e) {
			trans.rollback();
			System.out.println(e);
		}
		finally {
			em.close();
		}
	}
	
	public static void deleteMultipleEntities() {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		String qString = "DELETE FROM Invoice i set i.isProcessed='Y' " +
				"WHERE i.id < :id";
		Query q = em.createQuery(qString);
		q.setParameter("id", 200);
		try {
			trans.begin();
			q.executeUpdate();
			trans.commit();
		} catch(Exception e) {
			System.out.println(e);
			trans.rollback();
		}
		finally {
			em.close();
		}
	}	
}
