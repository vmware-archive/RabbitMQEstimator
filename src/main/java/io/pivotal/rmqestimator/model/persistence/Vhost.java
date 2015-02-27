package io.pivotal.rmqestimator.model.persistence;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="RABBITMQ_VHOST")
public class Vhost implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6651784173613390046L;

	@Column(name="VHOST_ID",nullable=false)
	@Id
    @GeneratedValue
    private Long id;
	
	@Column(name="VHOST_NAME",nullable=false)
	private String name;

	@OneToMany(targetEntity=User.class)
	private List<User> users;

	@OneToMany(targetEntity=Queue.class)
	private List <Queue> queues;

	@OneToMany(targetEntity=Exchange.class)
	private List <Exchange> exchanges;
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the users
	 */
	public List<User> getUsers() {
		return users;
	}

	/**
	 * @param users the users to set
	 */
	public void setUsers(List<User> users) {
		this.users = users;
	}

	/**
	 * @return the queues
	 */
	public List<Queue> getQueues() {
		return queues;
	}

	/**
	 * @param queues the queues to set
	 */
	public void setQueues(List<Queue> queues) {
		this.queues = queues;
	}

	/**
	 * @return the exchanges
	 */
	public List<Exchange> getExchanges() {
		return exchanges;
	}

	/**
	 * @param exchanges the exchanges to set
	 */
	public void setExchanges(List<Exchange> exchanges) {
		this.exchanges = exchanges;
	}
	
	
}
