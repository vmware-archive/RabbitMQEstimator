/**
 * 
 */
package io.pivotal.rmqestimator.model.persistence;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author jdavis
 *
 */
@Entity
@Table(name="RABBITMQ_EXCHANGE")
public class Exchange implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7919296801558044464L;
	@Column(name="EXCHANGE_ID")
	@Id
    @GeneratedValue
    private Long id;
	
	@Column(name="EXCHANGE_NAME",nullable=false,length=100,unique=false)
    private String exchangeName;
	@Column(name="EXCHANGE_TYPE",nullable=false,length=100,unique=false)
    private String exchangeType;//direct, flow, topic
	@Column(name="IS_FEDERATED",nullable=false)
    private Boolean isFederated;
	@Column(name="IS_LOCAL",nullable=false)
	private Boolean isLocalExchange;
	@OneToMany(targetEntity=QueueBinding.class,mappedBy="id")
    private List <QueueBinding> bindings;
	
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
	 * @return the exchangeName
	 */
	public String getExchangeName() {
		return exchangeName;
	}
	/**
	 * @param exchangeName the exchangeName to set
	 */
	public void setExchangeName(String exchangeName) {
		this.exchangeName = exchangeName;
	}
	/**
	 * @return the exchangeType
	 */
	public String getExchangeType() {
		return exchangeType;
	}
	/**
	 * @param exchangeType the exchangeType to set
	 */
	public void setExchangeType(String exchangeType) {
		this.exchangeType = exchangeType;
	}
	/**
	 * @return the isFederated
	 */
	public Boolean getIsFederated() {
		return isFederated;
	}
	/**
	 * @param isFederated the isFederated to set
	 */
	public void setIsFederated(Boolean isFederated) {
		this.isFederated = isFederated;
	}
	/**
	 * @return the bindings
	 */
	public List<QueueBinding> getBindings() {
		return bindings;
	}
	/**
	 * @param bindings the bindings to set
	 */
	public void setBindings(List<QueueBinding> bindings) {
		this.bindings = bindings;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((exchangeName == null) ? 0 : exchangeName.hashCode());
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
		Exchange other = (Exchange) obj;
		if (exchangeName == null) {
			if (other.exchangeName != null)
				return false;
		} else if (!exchangeName.equals(other.exchangeName))
			return false;
		return true;
	}
	
}
