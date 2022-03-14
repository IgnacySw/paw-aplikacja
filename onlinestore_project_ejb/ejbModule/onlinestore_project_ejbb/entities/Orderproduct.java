package onlinestore_project_ejbb.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the orderproduct database table.
 * 
 */
@Entity
@NamedQuery(name="Orderproduct.findAll", query="SELECT o FROM Orderproduct o")
public class Orderproduct implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idOrderProduct;

	private double price;

	//bi-directional many-to-one association to Product
	@ManyToOne
	private Product product;

	//bi-directional many-to-one association to Myorder
	@ManyToOne
	@JoinColumn(name="Order_idOrder")
	private Myorder myorder;

	public Orderproduct() {
	}

	public int getIdOrderProduct() {
		return this.idOrderProduct;
	}

	public void setIdOrderProduct(int idOrderProduct) {
		this.idOrderProduct = idOrderProduct;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Myorder getMyorder() {
		return this.myorder;
	}

	public void setMyorder(Myorder myorder) {
		this.myorder = myorder;
	}

}