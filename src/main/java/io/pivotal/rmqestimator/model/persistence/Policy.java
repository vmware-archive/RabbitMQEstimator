package io.pivotal.rmqestimator.model.persistence;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name="RABBIT_MQ_POLICY")
public class Policy implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6083185594565625891L;

	@Column(name="POLICY_ID")
	@Id
    @GeneratedValue()
    private Long id;
	@Column(name="NAME", nullable=false,unique=false)
	private String name;
	@Column(name="PRIORITY")
	private int priority;
	@Column(name="SELECTION")
	private String selection;
	@Column(name="FEDERATION_UPSTREAM_SET")
	private String federationUpsteamSet;
	@Column(name="HA_MODE")
	private String haMode;
	@Column(name="APPLY_TO")
	private String applyTo;//can be queues exchange or all
	
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
	 * @return the priority
	 */
	public int getPriority() {
		return priority;
	}
	/**
	 * @param priority the priority to set
	 */
	public void setPriority(int priority) {
		this.priority = priority;
	}
	/**
	 * @return the selection
	 */
	public String getSelection() {
		return selection;
	}
	/**
	 * @param selection the selection to set
	 */
	public void setSelection(String selection) {
		this.selection = selection;
	}
	/**
	 * @return the federationUpsteamSet
	 */
	public String getFederationUpsteamSet() {
		return federationUpsteamSet;
	}
	/**
	 * @param federationUpsteamSet the federationUpsteamSet to set
	 */
	public void setFederationUpsteamSet(String federationUpsteamSet) {
		this.federationUpsteamSet = federationUpsteamSet;
	}
	/**
	 * @return the haMode
	 */
	public String getHaMode() {
		return haMode;
	}
	/**
	 * @param haMode the haMode to set
	 */
	public void setHaMode(String haMode) {
		this.haMode = haMode;
	}
	/**
	 * @return the applyTo
	 */
	public String getApplyTo() {
		return applyTo;
	}
	/**
	 * @param applyTo the applyTo to set
	 */
	public void setApplyTo(String applyTo) {
		this.applyTo = applyTo;
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
		Policy other = (Policy) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
