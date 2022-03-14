package onlinestore_project.dao;


import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import onlinestore_project_ejbb.entities.Orderproduct;


@Stateless
public class OrderproductDAO {

	@PersistenceContext
	EntityManager em;
	
	public void create(Orderproduct orderproduct) {
		em.persist(orderproduct);
	}
	
	public Orderproduct merge(Orderproduct orderproduct) {
		return em.merge(orderproduct);
	}
	
	public void remove(Orderproduct orderproduct) {
		em.remove(em.merge(orderproduct));
	}
	
	public Orderproduct find(Object id) {
		Orderproduct o = em.find(Orderproduct.class, id);
		return o;
	}
	
	public Orderproduct findByIdOrderProduct(int idOrderProduct){
		Orderproduct o = null;
		Query query = em.createQuery("Select o from Orderproduct u where o.idOrderProduct = :idOrderProduct");
		query.setParameter("idOrderProduct", idOrderProduct);
		try {
			
			o = (Orderproduct) query.getSingleResult();
			
			}catch (Exception e) {
				e.printStackTrace();
			}
		return o;
	}
	
	public List<Orderproduct> getList(Map<String, Object> searchParams) {
		List<Orderproduct> list = null;

		// 1. Build query string with parameters
		String select = "select o ";
		String from = "from Orderproduct o ";
		String where = "";
		String orderby = "order by o.idOrder asc, o.price";

		// search for surname
		String idOrderProduct = (String) searchParams.get("idOrder");
		if (idOrderProduct != null) {
			if (where.isEmpty()) {
				where = "where ";
			} else {
				where += "and ";
			}
			where += "o.idOrderProduct like :idOrderProduct ";
		}
		
		// ... other parameters ... 

		// 2. Create query object
		Query query = em.createQuery(select + from + where + orderby);

		// 3. Set configured parameters
		if (idOrderProduct != null) {
			query.setParameter("idOrderProduct", idOrderProduct+"%");
		}

		// ... other parameters ... 

		// 4. Execute query and retrieve list of User objects
		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
	
	
	
}
