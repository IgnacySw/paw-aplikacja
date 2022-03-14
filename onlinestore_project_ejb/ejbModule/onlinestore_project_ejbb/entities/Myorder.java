package onlinestore_project_ejbb.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the myorder database table.
 * 
 */
@Entity
@NamedQuery(name="Myorder.findAll", query="SELECT m FROM Myorder m")
public class Myorder implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idOrder;

	@Temporal(TemporalType.DATE)
	private Date dateOfOrder;

	@Temporal(TemporalType.DATE)
	private Date dateOfReceive;

	//bi-directional many-to-one association to User
	@ManyToOne
	private User user;

	//bi-directional many-to-one association to Orderproduct
	@OneToMany(mappedBy="myorder")
	private List<Orderproduct> orderproducts;

	public Myorder() {
	}

	public int getIdOrder() {
		return this.idOrder;
	}

	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}

	public Date getDateOfOrder() {
		return this.dateOfOrder;
	}

	public void setDateOfOrder(Date dateOfOrder) {
		this.dateOfOrder = dateOfOrder;
	}

	public Date getDateOfReceive() {
		return this.dateOfReceive;
	}

	public void setDateOfReceive(Date dateOfReceive) {
		this.dateOfReceive = dateOfReceive;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Orderproduct> getOrderproducts() {
		return this.orderproducts;
	}

	public void setOrderproducts(List<Orderproduct> orderproducts) {
		this.orderproducts = orderproducts;
	}

	public Orderproduct addOrderproduct(Orderproduct orderproduct) {
		getOrderproducts().add(orderproduct);
		orderproduct.setMyorder(this);

		return orderproduct;
	}

	public Orderproduct removeOrderproduct(Orderproduct orderproduct) {
		getOrderproducts().remove(orderproduct);
		orderproduct.setMyorder(null);

		return orderproduct;
	}

}