package onlinestore_project_ejbb.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the product database table.
 * 
 */
@Entity
@NamedQuery(name="Product.findAll", query="SELECT p FROM Product p")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idProduct;

	private byte availability;

	@Lob
	private String category;

	@Lob
	private String description;

	@Lob
	private String name;

	private double price;

	@Temporal(TemporalType.DATE)
	private Date releaseDate;

	@Lob
	private String size;

	//bi-directional many-to-one association to Orderproduct
	@OneToMany(mappedBy="product")
	private List<Orderproduct> orderproducts;

	public Product() {
	}

	public int getIdProduct() {
		return this.idProduct;
	}

	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}

	public byte getAvailability() {
		return this.availability;
	}

	public void setAvailability(byte availability) {
		this.availability = availability;
	}

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Date getReleaseDate() {
		return this.releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getSize() {
		return this.size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public List<Orderproduct> getOrderproducts() {
		return this.orderproducts;
	}

	public void setOrderproducts(List<Orderproduct> orderproducts) {
		this.orderproducts = orderproducts;
	}

	public Orderproduct addOrderproduct(Orderproduct orderproduct) {
		getOrderproducts().add(orderproduct);
		orderproduct.setProduct(this);

		return orderproduct;
	}

	public Orderproduct removeOrderproduct(Orderproduct orderproduct) {
		getOrderproducts().remove(orderproduct);
		orderproduct.setProduct(null);

		return orderproduct;
	}

}