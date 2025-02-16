package com.athira.demo.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "services")
public class Service {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ServiceId", nullable = false)
	private Integer serviceId;
	
	@Column(name = "ServiceName", nullable = false, length = 20)
    private String serviceName;
	
	@Column(name = "costPerNight", nullable = false, precision = 5, scale = 0)
    private double costPerNight;
    
	@JsonIgnore
	@OneToMany(mappedBy = "service", cascade = CascadeType.ALL) 
    private List<ServiceRequest> serviceRequests;

	public Service( String serviceName, double costPerNight) {

		this.serviceName = serviceName;
		this.costPerNight = costPerNight;
	}

	public Service(Integer serviceId, String serviceName, double costPerNight, List<ServiceRequest> serviceRequests) {
		super();
		this.serviceId = serviceId;
		this.serviceName = serviceName;
		this.costPerNight = costPerNight;
		this.serviceRequests = serviceRequests;
	}

	public Service() {
		// TODO Auto-generated constructor stub
	}

	public Integer getServiceId() {
		return serviceId;
	}

	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		if(serviceName == null || serviceName.trim().isEmpty()) {
			throw new IllegalArgumentException("Service name should not be null or empty.");
		}
		if(serviceName.length() > 20) {
			throw new IllegalArgumentException("Service name should not be greater than 20 letters.");
		}
		this.serviceName = serviceName;
	}

	public double getCostPerNight() {
		return costPerNight;
	}

	public void setCostPerNight(double costPerNight) {
		this.costPerNight = costPerNight;
	}

 }
