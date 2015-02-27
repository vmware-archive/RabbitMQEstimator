package io.pivotal.rmqestimator.model.persistence;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="PROJECT")
public class Project implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7047032196521384883L;
	
	@Column(name="PROJECT_ID")
	@Id
    @GeneratedValue()
    private Long id;
	/**
	 * Name of the project
	 */
	@Column(name="NAME", nullable=false,unique=true,length=100)
	private String name;
	/**
	 * Description of the project
	 */
	@Column(name="DESC",nullable=true,unique=false,length=200)
	private String description;
	/**
	 * Name of the creator of the project
	 */
	@Column(name="CREATOR_NAME",nullable=false,unique=false,length=200)
	private String creatorName;
	
	@Column(name="ANALYSIS_TYPE",nullable=false,unique=false,length=20)
	private String analysisType;
	
	@OneToMany(targetEntity=Cluster.class)
	@JoinColumn(name="CLUSTER_ID")
	private List<Cluster> rabbitMQClusters;
	
	@Column(name="DEPLOY_TO_CLOUD_FOUNDRY_SERVICE", nullable=false)
	private Boolean deployToCloudFoundryService;
	  
	@Column(name="DEPLOY_TO_CLOUD_FOUNDRY_BOSH",nullable=false)
	private Boolean deployToCloudFoundryBosh;
	
	@Column(name="DEPLOY_TO_DOCKER",nullable=false)
	private Boolean deployToDocker;

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
	 * @return the creatorName
	 */
	public String getCreatorName() {
		return creatorName;
	}
	/**
	 * @param creatorName the creatorName to set
	 */
	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}
	/**
	 * @return the analysisType
	 */
	public String getAnalysisType() {
		return analysisType;
	}
	/**
	 * @param analysisType the analysisType to set
	 */
	public void setAnalysisType(String analysisType) {
		this.analysisType = analysisType;
	}
	/**
	 * @return the rabbitMQClusterNames
	 */
	public List<Cluster> getRabbitMQClusters() {
		return rabbitMQClusters;
	}
	/**
	 * @param rabbitMQClusterNames the rabbitMQClusterNames to set
	 */
	public void setRabbitMQClusters(
			List<Cluster> rabbitMQClusterNames) {
		this.rabbitMQClusters = rabbitMQClusterNames;
	}
	/**
	 * @return the deployToCloudFoundryService
	 */
	public Boolean getDeployToCloudFoundryService() {
		return deployToCloudFoundryService;
	}
	/**
	 * @param deployToCloudFoundryService the deployToCloudFoundryService to set
	 */
	public void setDeployToCloudFoundryService(Boolean deployToCloudFoundryService) {
		this.deployToCloudFoundryService = deployToCloudFoundryService;
	}
	/**
	 * @return the deployToCloudFoundryBosh
	 */
	public Boolean getDeployToCloudFoundryBosh() {
		return deployToCloudFoundryBosh;
	}
	/**
	 * @param deployToCloudFoundryBosh the deployToCloudFoundryBosh to set
	 */
	public void setDeployToCloudFoundryBosh(Boolean deployToCloudFoundryBosh) {
		this.deployToCloudFoundryBosh = deployToCloudFoundryBosh;
	}
	/**
	 * @return the deployToDocker
	 */
	public Boolean getDeployToDocker() {
		return deployToDocker;
	}
	/**
	 * @param deployToDocker the deployToDocker to set
	 */
	public void setDeployToDocker(Boolean deployToDocker) {
		this.deployToDocker = deployToDocker;
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
		Project other = (Project) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
