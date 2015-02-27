package io.pivotal.rmqestimator.model.persistence;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="RABBITMQ_QUEUE_BINDING")
public class QueueBinding implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 734629199816492629L;

	@Column(name="QUEUE_BINDING_ID")
	@Id
    @GeneratedValue
    private Long id;
	
	@OneToOne(optional=false)
	@JoinColumn(name="QUEUE_ID",unique=false,nullable=false)
	private Queue queue;
	
	@OneToOne(optional=false)
	@JoinColumn(name="EXCHANGE_ID",unique=false,nullable=false)
	private Exchange exchange;
	
	@Column(name="BINDING_EXPRESSION",nullable=false)
	private String bindingExpression;
	@Column(name="BINDING_KEY",nullable=true)
	private String bindingKey;
	
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
	 * @return the queue
	 */
	public Queue getQueue() {
		return queue;
	}
	/**
	 * @param queue the queue to set
	 */
	public void setQueue(Queue queue) {
		this.queue = queue;
	}
	/**
	 * @return the exchange
	 */
	public Exchange getExchange() {
		return exchange;
	}
	/**
	 * @param exchange the exchange to set
	 */
	public void setExchange(Exchange exchange) {
		this.exchange = exchange;
	}
	/**
	 * @return the bindingExpression
	 */
	public String getBindingExpression() {
		return bindingExpression;
	}
	/**
	 * @param bindingExpression the bindingExpression to set
	 */
	public void setBindingExpression(String bindingExpression) {
		this.bindingExpression = bindingExpression;
	}
	/**
	 * @return the bindingKey
	 */
	public String getBindingKey() {
		return bindingKey;
	}
	/**
	 * @param bindingKey the bindingKey to set
	 */
	public void setBindingKey(String bindingKey) {
		this.bindingKey = bindingKey;
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
				+ ((bindingExpression == null) ? 0 : bindingExpression
						.hashCode());
		result = prime * result
				+ ((bindingKey == null) ? 0 : bindingKey.hashCode());
		result = prime * result
				+ ((exchange == null) ? 0 : exchange.hashCode());
		result = prime * result + ((queue == null) ? 0 : queue.hashCode());
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
		QueueBinding other = (QueueBinding) obj;
		if (bindingExpression == null) {
			if (other.bindingExpression != null)
				return false;
		} else if (!bindingExpression.equals(other.bindingExpression))
			return false;
		if (bindingKey == null) {
			if (other.bindingKey != null)
				return false;
		} else if (!bindingKey.equals(other.bindingKey))
			return false;
		if (exchange == null) {
			if (other.exchange != null)
				return false;
		} else if (!exchange.equals(other.exchange))
			return false;
		if (queue == null) {
			if (other.queue != null)
				return false;
		} else if (!queue.equals(other.queue))
			return false;
		return true;
	}
}
