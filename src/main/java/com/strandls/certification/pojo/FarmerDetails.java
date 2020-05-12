package com.strandls.certification.pojo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;

@Entity
@Table(name = "farmer_details")
@XmlRootElement
@JsonIgnoreProperties
@ApiModel("FarmerDetails")
public class FarmerDetails implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6348557207086841389L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "farmer_details_id_generator")
	@SequenceGenerator(name = "farmer_details_id_generator", sequenceName = "farmer_details_id_seq", allocationSize = 1)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	
	@Column(name = "district")
	private String district;
	@Column(name = "field_coordinator_name")
	private String fieldCoordinatorName;
	@Column(name = "village")
	private String village;
	@Column(name = "collecting_center")
	private String colletingCenter;
	@Column(name = "farmer_name")
	private String farmerName;
	@Column(name = "farmer_code")
	private String farmerCode;
	@Column(name = "number_of_coffee_field")
	private Long numberOfCoffeeField;
	
	@OneToMany(mappedBy = "farmerInspection", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<FarmerDetails> farmerInspection = new HashSet<FarmerDetails>();
	
	public FarmerDetails() {
		super();
	}
	public FarmerDetails(Long id, String district, String fieldCoordinatorName, String village, String colletingCenter,
			String farmerName, String farmerCode, Long numberOfCoffeeField) {
		super();
		this.id = id;
		this.district = district;
		this.fieldCoordinatorName = fieldCoordinatorName;
		this.village = village;
		this.colletingCenter = colletingCenter;
		this.farmerName = farmerName;
		this.farmerCode = farmerCode;
		this.numberOfCoffeeField = numberOfCoffeeField;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getFieldCoordinatorName() {
		return fieldCoordinatorName;
	}
	public void setFieldCoordinatorName(String fieldCoordinatorName) {
		this.fieldCoordinatorName = fieldCoordinatorName;
	}
	public String getVillage() {
		return village;
	}
	public void setVillage(String village) {
		this.village = village;
	}
	public String getColletingCenter() {
		return colletingCenter;
	}
	public void setColletingCenter(String colletingCenter) {
		this.colletingCenter = colletingCenter;
	}
	public String getFarmerName() {
		return farmerName;
	}
	public void setFarmerName(String farmerName) {
		this.farmerName = farmerName;
	}
	public String getFarmerCode() {
		return farmerCode;
	}
	public void setFarmerCode(String farmerCode) {
		this.farmerCode = farmerCode;
	}
	public Long getNumberOfCoffeeField() {
		return numberOfCoffeeField;
	}
	public void setNumberOfCoffeeField(Long numberOfCoffeeField) {
		this.numberOfCoffeeField = numberOfCoffeeField;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
