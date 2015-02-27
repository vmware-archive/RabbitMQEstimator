/**
 * 
 */
package io.pivotal.rmqestimator.model.persistence;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * @author jdavis
 *
 */
@Entity
@Table(name="RABBITMQ_NODE")
public class Node implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4119364448466626948L;

	@Column(name="NODE_ID")
	@Id
    @GeneratedValue
    private Long id;
	@Column(nullable=false,unique=false,name="NAME")
	private String name;
	@Column(nullable=false,unique=false,name="RABBITMQ_VERSION")
	private String rabbitMQVersion;
	@Column(nullable=false,unique=false,name="RABBITMQ_NODE_TYPE")
	private String rabbitMQNodeType;//File or Memory
	@Column(nullable=false,unique=false,name="MEMORY_HIGH_WATERMARK")
	private float memoryHighWaterMark;
	@Column(nullable=false,unique=false,name="HIGH_WATERMARK_PAGING_RATIO")
	private float highWatermarkPagingRatio;
	@Column(nullable=false,unique=false,name="DISK_FREE_LIMIT")
	private Long diskFreeLimit;
	@Column(nullable=false,unique=false,name="MEM_RELATIVE")
	private float memRelative;
	@Column(nullable=false,unique=false,name="SYSTEM_MEMORY")
	private Long systemMemory;
	@Column(nullable=false,unique=false,name="IP_ADDRESS")
	private String ipAddress;
	@Column(nullable=false,unique=false,name="PORT")
	private Integer port;
	@Column(nullable=false,unique=false,name="TCP_LISTENERS")
	private String tcpListeners;
	@Column(nullable=false,unique=false,name="HANDSHAKE_TIMEOUT")
	private Long handshakeTimeout;
	@Column(nullable=false,unique=false,name="LOG_LEVELS")
	private Long logLevels;
	@Column(nullable=false,unique=false,name="FRAME_MAX")
	private Long frameMax;
	@Column(nullable=false,unique=false,name="CHANNEL_MAX")
	private Long channelMax;
	@Column(nullable=false,unique=false,name="HEARTBEAT")
	private Integer heartbeat;
	@Column(nullable=false,unique=false,name="DEFAULT_VHOST")
	private String defaultVhost;
	@Column(nullable=false,unique=false,name="DEFAULT_USER")
	private String defaultUser;
	@Column(nullable=false,unique=false,name="DEFAULT_PASS")
	private String defaultPass;
	@Column(nullable=false,unique=false,name="DEFAULT_USER_TAGS")
	private String defaultUserTags;
	@Column(nullable=false,unique=false,name="DEFAULT_PERMISSIONS")
	private String defaultPermissions;
	@Column(nullable=false,unique=false,name="COLLECT_STATISTICS")
	private String collectStatistics;
	@Column(nullable=false,unique=false,name="COLLECT_STATISTICS_INTERVAL")
	private Long collectStatisticsInterval;
	
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
	 * @return the rabbitMQVersion
	 */
	public String getRabbitMQVersion() {
		return rabbitMQVersion;
	}
	/**
	 * @param rabbitMQVersion the rabbitMQVersion to set
	 */
	public void setRabbitMQVersion(String rabbitMQVersion) {
		this.rabbitMQVersion = rabbitMQVersion;
	}
	/**
	 * @return the rabbitMQNodeType
	 */
	public String getRabbitMQNodeType() {
		return rabbitMQNodeType;
	}
	/**
	 * @param rabbitMQNodeType the rabbitMQNodeType to set
	 */
	public void setRabbitMQNodeType(String rabbitMQNodeType) {
		this.rabbitMQNodeType = rabbitMQNodeType;
	}
	/**
	 * @return the queues
	 */
	/**
	 * @return the memoryHighWaterMark
	 */
	public float getMemoryHighWaterMark() {
		return memoryHighWaterMark;
	}
	/**
	 * @param memoryHighWaterMark the memoryHighWaterMark to set
	 */
	public void setMemoryHighWaterMark(float memoryHighWaterMark) {
		this.memoryHighWaterMark = memoryHighWaterMark;
	}
	/**
	 * @return the highWatermarkPagingRatio
	 */
	public float getHighWatermarkPagingRatio() {
		return highWatermarkPagingRatio;
	}
	/**
	 * @param highWatermarkPagingRatio the highWatermarkPagingRatio to set
	 */
	public void setHighWatermarkPagingRatio(float highWatermarkPagingRatio) {
		this.highWatermarkPagingRatio = highWatermarkPagingRatio;
	}
	/**
	 * @return the diskFreeLimit
	 */
	public Long getDiskFreeLimit() {
		return diskFreeLimit;
	}
	/**
	 * @param diskFreeLimit the diskFreeLimit to set
	 */
	public void setDiskFreeLimit(Long diskFreeLimit) {
		this.diskFreeLimit = diskFreeLimit;
	}
	/**
	 * @return the memRelative
	 */
	public float getMemRelative() {
		return memRelative;
	}
	/**
	 * @param memRelative the memRelative to set
	 */
	public void setMemRelative(float memRelative) {
		this.memRelative = memRelative;
	}
	/**
	 * @return the systemMemory
	 */
	public Long getSystemMemory() {
		return systemMemory;
	}
	/**
	 * @param systemMemory the systemMemory to set
	 */
	public void setSystemMemory(Long systemMemory) {
		this.systemMemory = systemMemory;
	}
	/**
	 * @return the ipAddress
	 */
	public String getIpAddress() {
		return ipAddress;
	}
	/**
	 * @param ipAddress the ipAddress to set
	 */
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	/**
	 * @return the port
	 */
	public Integer getPort() {
		return port;
	}
	/**
	 * @param port the port to set
	 */
	public void setPort(Integer port) {
		this.port = port;
	}
	/**
	 * @return the tcpListeners
	 */
	public String getTcpListeners() {
		return tcpListeners;
	}
	/**
	 * @param tcpListeners the tcpListeners to set
	 */
	public void setTcpListeners(String tcpListeners) {
		this.tcpListeners = tcpListeners;
	}
	/**
	 * @return the handshakeTimeout
	 */
	public Long getHandshakeTimeout() {
		return handshakeTimeout;
	}
	/**
	 * @param handshakeTimeout the handshakeTimeout to set
	 */
	public void setHandshakeTimeout(Long handshakeTimeout) {
		this.handshakeTimeout = handshakeTimeout;
	}
	/**
	 * @return the logLevels
	 */
	public Long getLogLevels() {
		return logLevels;
	}
	/**
	 * @param logLevels the logLevels to set
	 */
	public void setLogLevels(Long logLevels) {
		this.logLevels = logLevels;
	}
	/**
	 * @return the frameMax
	 */
	public Long getFrameMax() {
		return frameMax;
	}
	/**
	 * @param frameMax the frameMax to set
	 */
	public void setFrameMax(Long frameMax) {
		this.frameMax = frameMax;
	}
	/**
	 * @return the channelMax
	 */
	public Long getChannelMax() {
		return channelMax;
	}
	/**
	 * @param channelMax the channelMax to set
	 */
	public void setChannelMax(Long channelMax) {
		this.channelMax = channelMax;
	}
	/**
	 * @return the heartbeat
	 */
	public Integer getHeartbeat() {
		return heartbeat;
	}
	/**
	 * @param heartbeat the heartbeat to set
	 */
	public void setHeartbeat(Integer heartbeat) {
		this.heartbeat = heartbeat;
	}
	/**
	 * @return the defaultVhost
	 */
	public String getDefaultVhost() {
		return defaultVhost;
	}
	/**
	 * @param defaultVhost the defaultVhost to set
	 */
	public void setDefaultVhost(String defaultVhost) {
		this.defaultVhost = defaultVhost;
	}
	/**
	 * @return the defaultUser
	 */
	public String getDefaultUser() {
		return defaultUser;
	}
	/**
	 * @param defaultUser the defaultUser to set
	 */
	public void setDefaultUser(String defaultUser) {
		this.defaultUser = defaultUser;
	}
	/**
	 * @return the defaultPass
	 */
	public String getDefaultPass() {
		return defaultPass;
	}
	/**
	 * @param defaultPass the defaultPass to set
	 */
	public void setDefaultPass(String defaultPass) {
		this.defaultPass = defaultPass;
	}
	/**
	 * @return the defaultUserTags
	 */
	public String getDefaultUserTags() {
		return defaultUserTags;
	}
	/**
	 * @param defaultUserTags the defaultUserTags to set
	 */
	public void setDefaultUserTags(String defaultUserTags) {
		this.defaultUserTags = defaultUserTags;
	}
	/**
	 * @return the defaultPermissions
	 */
	public String getDefaultPermissions() {
		return defaultPermissions;
	}
	/**
	 * @param defaultPermissions the defaultPermissions to set
	 */
	public void setDefaultPermissions(String defaultPermissions) {
		this.defaultPermissions = defaultPermissions;
	}
	/**
	 * @return the collectStatistics
	 */
	public String getCollectStatistics() {
		return collectStatistics;
	}
	/**
	 * @param collectStatistics the collectStatistics to set
	 */
	public void setCollectStatistics(String collectStatistics) {
		this.collectStatistics = collectStatistics;
	}
	/**
	 * @return the collectStatisticsInterval
	 */
	public Long getCollectStatisticsInterval() {
		return collectStatisticsInterval;
	}
	/**
	 * @param collectStatisticsInterval the collectStatisticsInterval to set
	 */
	public void setCollectStatisticsInterval(Long collectStatisticsInterval) {
		this.collectStatisticsInterval = collectStatisticsInterval;
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
		Node other = (Node) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
