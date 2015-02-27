package io.pivotal.rmqestimator.model.persistence;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name="RABBITMQ_CLUSTER")
public class Cluster implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7408951142309246977L;
	@Id
	@Column(name="CLUSTER_ID")
    @GeneratedValue
    private Long id;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="PROJECT_ID")
	private Project project;
	
	@Column(name="NAME",nullable=false, unique=true, length=100)
	private String name;
	
	@Column(name="DESC",nullable=true,unique=false,length=200)
	private String description;
	
	@OneToOne(optional=false)
	@JoinColumn(name="CLUSTER_TYPE_ID")
	private ClusterType clusterType;
	
	@Column(name="NET_TICK_TIME",nullable=true, unique=false)
	private Long netTickTime;
	
	@OneToMany(targetEntity=FederationUpstream.class)
	private List <FederationUpstream> federationUpstreams;
	
	@OneToMany(targetEntity=Policy.class)
	private List <Policy> policies;

	@ManyToOne(optional=false)
	private User owner;
	
	@ManyToOne(optional=false)
	private Vhost vHost;
	/**
	 * Virtual hosts for the cluster
	 */
	@OneToMany(targetEntity=Vhost.class)
	private List<Vhost> vhosts;
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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the clusterType
	 */
	public ClusterType getClusterType() {
		return clusterType;
	}
	/**
	 * @param clusterType the clusterType to set
	 */
	public void setClusterType(ClusterType clusterType) {
		this.clusterType = clusterType;
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
	/**
	 * @return the netTickTime
	 */
	public Long getNetTickTime() {
		return netTickTime;
	}
	/**
	 * @param netTickTime the netTickTime to set
	 */
	public void setNetTickTime(Long netTickTime) {
		this.netTickTime = netTickTime;
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
		Cluster other = (Cluster) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	/**
	 * @return the federationUpstreams
	 */
	public List <FederationUpstream> getFederationUpstreams() {
		return federationUpstreams;
	}
	/**
	 * @param federationUpstreams the federationUpstreams to set
	 */
	public void setFederationUpstreams(List <FederationUpstream> federationUpstreams) {
		this.federationUpstreams = federationUpstreams;
	}
	/**
	 * @return the policies
	 */
	public List<Policy> getPolicies() {
		return policies;
	}
	/**
	 * @param policies the policies to set
	 */
	public void setPolicies(List<Policy> policies) {
		this.policies = policies;
	}

}
