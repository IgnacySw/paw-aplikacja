package onlinestore_project.dao;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import onlinestore_project_ejbb.entities.Product;

@Stateless
public class ProductDAO {
	private Query query;
	
	@PersistenceContext
	EntityManager em;
	public void create(Product product) {
		em.persist(product);
	}
	public Product merge(Product product) {
		return em.merge(product);
	}
	public void remove(Product product) {
		em.remove(em.merge(product));
	}
	public Product find(Object id) {
		return em.find(Product.class, id);
	}
	public List<Product> getFullList(){
		List<Product> list = null;
		Query query = em.createQuery("Select p from product p");
		try {
			list = query.getResultList();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public List<Product> getList(Map<String, Object> searchParams) {
		List<Product> list = null;

		// 1. Build query string with parameters
		String select = "select p ";
		String from = "from Product p ";
		String where = "";
		String orderby = "order by p.name asc, p.category";

		// search for surname
		String name = (String) searchParams.get("name");
		if (name != null) {
			if (where.isEmpty()) {
				where = "where ";
			} else {
				where += "and ";
			}
			where += "p.name like :name ";
		}
		
		// ... other parameters ... 

		// 2. Create query object
		Query query = em.createQuery(select + from + where + orderby);

		// 3. Set configured parameters
		if (name != null) {
			query.setParameter("name", name+"%");
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
	public int getProductID(String isbn) {
		int id = 0;
		String where = "";

		where = createWhere("isbn", isbn, where);
		query = em.createQuery("SELECT p.idProduct FROM product p " + where);
		query.setParameter("isbn", isbn);

		try {
			id = (int) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return id;
	}
	
	private String createWhere(String paramName, String param, String currentWhere) {
		String where = currentWhere;

		if (param != null) {
			if (where.isEmpty()) {
				where = "where ";
			} else {
				where += "and ";
			}
			if (paramName.equals("isbn") || paramName.equals("status")) {
				where += "p." + paramName + " like :" + paramName + " ";
			}
			if (paramName.equals("name")) {
				where += "bi." + paramName + " like :" + paramName + " ";
			}
		}

		return where;
	}
}
