package io.pivotal.rmqestimator.model.persistence;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="RABBITMQ_QUEUE_BINDING")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5158715125686787923L;
	@Column(name="USER_ID")
	@Id
    @GeneratedValue
    private Long id;
	
	@Column(name="USER_NAME")
	private String name;
	
	@Column(name="PASSWORD")
	private String password;
	
}
