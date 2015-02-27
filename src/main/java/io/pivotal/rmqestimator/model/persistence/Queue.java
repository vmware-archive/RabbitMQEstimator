package io.pivotal.rmqestimator.model.persistence;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name="RABBITMQ_QUEUE")
public class Queue implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3538646703065467382L;

	@Column(name="QUEUE_ID")
	@Id
    @GeneratedValue()
    private Long id;
	@Column(name="NAME", nullable=false,unique=false)
	private String name;
	@Column(name="IS_DURABLE",nullable=false)
	private Boolean isDurable;
	@Column(name="IS_PERSISTED",nullable=false)
	private Boolean isPersisted;
	@Column(name="IS_MIRRORED",nullable=false)
	private Boolean isMirrored;	
	
	@ManyToOne(optional=false)
	private User owner;
	
	@ManyToOne(optional=false)
	private Vhost vHost;
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
	 * @return the isDurable
	 */
	public Boolean getIsDurable() {
		return isDurable;
	}
	/**
	 * @param isDurable the isDurable to set
	 */
	public void setIsDurable(Boolean isDurable) {
		this.isDurable = isDurable;
	}
	/**
	 * @return the isPersisted
	 */
	public Boolean getIsPersisted() {
		return isPersisted;
	}
	/**
	 * @param isPersisted the isPersisted to set
	 */
	public void setIsPersisted(Boolean isPersisted) {
		this.isPersisted = isPersisted;
	}
	/**
	 * @return the isMirrored
	 */
	public Boolean getIsMirrored() {
		return isMirrored;
	}
	/**
	 * @param isMirrored the isMirrored to set
	 */
	public void setIsMirrored(Boolean isMirrored) {
		this.isMirrored = isMirrored;
	}
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
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Queue other = (Queue) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
