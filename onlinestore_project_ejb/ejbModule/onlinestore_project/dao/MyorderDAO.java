package onlinestore_project.dao;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import onlinestore_project_ejbb.entities.Myorder;
import onlinestore_project_ejbb.entities.Role;
import onlinestore_project_ejbb.entities.User;


@Stateless
public class MyorderDAO {
		private Query query;
	@PersistenceContext
	EntityManager em;
	public void create(Myorder order) {
		em.persist(order);
	}
	public Myorder merge(Myorder order) {
		return em.merge(order);
	}
	public void remove(Myorder order) {
		em.remove(em.merge(order));
	}
	public Myorder find(Object id) {
		return em.find(Myorder.class, id);
	}
	
	
	
	public List<Myorder> getFullList(){
		List<Myorder> list = null;
		Query query = em.createQuery("Select o from order o");
		try {
			list = query.getResultList();
			}catch (Exception e) {
				e.printStackTrace();
			}
		return list;
	}
	public List<Myorder> getList(Map<String, Object> searchParams) {
		List<Myorder> list = null;

		// 1. Build query string with parameters
		String select = "select o ";
		String from = "from Order o ";
		String where = "";
		String orderby = "order by o.idOrder asc";

		// search for surname
		String idOrder = (String) searchParams.get("idOrder");
		if (idOrder != null) {
			if (where.isEmpty()) {
				where = "where ";
			} else {
				where += "and ";
			}
			where += "u.idOrder like :idOrder ";
		}
		
		// ... other parameters ... 

		// 2. Create query object
		Query query = em.createQuery(select + from + where + orderby);

		// 3. Set configured parameters
		if (idOrder != null) {
			query.setParameter("idOrder", idOrder+"%");
		}

		// ... other parameters ... 

		// 4. Execute query and retrieve list of Person objects
		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	/*
	 * public int getOrderID(int idUser) { int id = 0; String where = "";
	 * 
	 * where = createWhere("idUser", idUser, where); query =
	 * em.createQuery("SELECT o.idUser FROM Order o " + where);
	 * query.setParameter("idUser", idUser);
	 * 
	 * try { id = (int) query.getSingleResult(); } catch (Exception e) {
	 * e.printStackTrace(); }
	 * 
	 * return id; }
	 */
	
	private String createWhere(String paramName, String param, String currentWhere) {
		String where = currentWhere;

		if (param != null) {
			if (where.isEmpty()) {
				where = "WHERE ";
			} else {
				where += "AND ";
			}
			if (paramName.equals("idUser")) {
				where += "u." + paramName + " like :" + paramName + " ";
			} else if (paramName.equals("name")) {
				where += "r." + paramName + " like:" + paramName + " ";
			}
		}

		return where;
	}

	public Myorder findById(int idOrder){
		Myorder o = null;
		Query query = em.createQuery("Select o from Order o where o.idOrder = :idOrder");
		query.setParameter("idOrder", idOrder);
		try {
			
			o = (Myorder) query.getSingleResult();
			
			}catch (Exception e) {
				e.printStackTrace();
			}
		return o;
	}
	
}
