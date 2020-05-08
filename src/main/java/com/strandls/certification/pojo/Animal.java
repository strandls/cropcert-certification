package com.strandls.certification.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.strandls.certification.pojo.enumtype.HusbandaryType;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "animal")
@XmlRootElement
@JsonIgnoreProperties
@Getter@Setter
@ApiModel("Animal")
public class Animal implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7761367629628125279L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "animal_id_generator")
	@SequenceGenerator(name = "animal_id_generator", sequenceName = "animal_id_seq", allocationSize = 1)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	
	@Column(name = "type")
	private String type;
	@Column(name = "number")
	private Long number;
	@Column(name = "husbandryType")
	private HusbandaryType husbandryType;
	@Column(name = "medication")
	private Boolean medication;
}
