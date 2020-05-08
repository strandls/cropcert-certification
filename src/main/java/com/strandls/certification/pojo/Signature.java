package com.strandls.certification.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "signature")
@XmlRootElement
@JsonIgnoreProperties
@Getter@Setter
@ApiModel("Signature")
public class Signature implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4809225755417237598L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "signature_id_generator")
	@SequenceGenerator(name = "signature_id_generator", sequenceName = "signature_id_seq", allocationSize = 1)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	
	@Column(name = "name")
	private String name;
	@Column(name = "date")
	private Timestamp date;
	@Column(name = "done")
	private Boolean done;
	@Column(name = "path")
	private String path;
}