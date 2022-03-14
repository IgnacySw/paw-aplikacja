package onlinestore_project_ejbb.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idUser;

	@Temporal(TemporalType.DATE)
	private Date birthdate;

	@Lob
	private String email;

	@Lob
	private String login;

	@Lob
	private String name;

	@Lob
	private String pass;

	@Lob
	private String surname;

	//bi-directional many-to-one association to Myorder
	@OneToMany(mappedBy="user")
	private List<Myorder> myorders;

	//bi-directional many-to-one association to Role
	@ManyToOne
	private Role role;

	public User() {
	}

	public int getIdUser() {
		return this.idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public Date getBirthdate() {
		return this.birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPass() {
		return this.pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public List<Myorder> getMyorders() {
		return this.myorders;
	}

	public void setMyorders(List<Myorder> myorders) {
		this.myorders = myorders;
	}

	public Myorder addMyorder(Myorder myorder) {
		getMyorders().add(myorder);
		myorder.setUser(this);

		return myorder;
	}

	public Myorder removeMyorder(Myorder myorder) {
		getMyorders().remove(myorder);
		myorder.setUser(null);

		return myorder;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}