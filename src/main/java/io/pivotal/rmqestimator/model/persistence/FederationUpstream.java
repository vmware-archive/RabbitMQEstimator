package io.pivotal.rmqestimator.model.persistence;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name="RABBITMQ_FEDERATION_UPSTREAM")
public class FederationUpstream implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -986624206114764771L;
	@Column(name="FEDERATION_UPSTREAM_ID")
	@Id
    @GeneratedValue
    private Long id;
	
	@Column(name="FEDERATION_UPSTREAM_NAME",length=100)
	private String federationUpStreamName;
	@Column(name="FEDERATION_URI",length=100)
	private String federationURI;
	@Column(name="FEDERATION_EXPIRATION")
	private Long federationExpiration;
	
	@ManyToOne(optional=false)
	private User owner;
	
	@ManyToOne(optional=false)
	private Vhost vHost;
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
	 * @return the federationUpStreamName
	 */
	public String getFederationUpStreamName() {
		return federationUpStreamName;
	}
	/**
	 * @param federationUpStreamName the federationUpStreamName to set
	 */
	public void setFederationUpStreamName(String federationUpStreamName) {
		this.federationUpStreamName = federationUpStreamName;
	}
	/**
	 * @return the federationURI
	 */
	public String getFederationURI() {
		return federationURI;
	}
	/**
	 * @param federationURI the federationURI to set
	 */
	public void setFederationURI(String federationURI) {
		this.federationURI = federationURI;
	}
	/**
	 * @return the federationExpiration
	 */
	public Long getFederationExpiration() {
		return federationExpiration;
	}
	/**
	 * @param federationExpiration the federationExpiration to set
	 */
	public void setFederationExpiration(Long federationExpiration) {
		this.federationExpiration = federationExpiration;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((federationUpStreamName == null) ? 0
						: federationUpStreamName.hashCode());
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
		FederationUpstream other = (FederationUpstream) obj;
		if (federationUpStreamName == null) {
			if (other.federationUpStreamName != null)
				return false;
		} else if (!federationUpStreamName.equals(other.federationUpStreamName))
			return false;
		return true;
	}
}
