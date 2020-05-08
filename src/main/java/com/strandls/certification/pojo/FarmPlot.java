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
import com.strandls.certification.pojo.enumtype.FieldSeparationType;
import com.strandls.certification.pojo.enumtype.Quality;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "farm_plot")
@XmlRootElement
@JsonIgnoreProperties
@Getter@Setter
@ApiModel("FarmPlot")
public class FarmPlot implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4636368184012471812L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "farm_plot_id_generator")
	@SequenceGenerator(name = "farm_plot_id_generator", sequenceName = "farm_plot_id_seq", allocationSize = 1)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	
	@Column(name = "fieldName")
	private String fieldName;
	@Column(name = "acres")
	private Double acres;
	@Column(name = "main_crop")
	private String mainCrop;
	@Column(name = "intercrops")
	private String intercrops;
	@Column(name = "number_of_coffe_trees")
	private Long numberOfCoffeTrees;
	@Column(name = "yeild_estimate")
	private Double yeildEstimate;
	@Column(name = "area_under_coffee")
	private Double areaUnderCoffee;
	@Column(name = "is_coffee_tree_well_maintained")
	private Boolean isCoffeeTreeWellMaintained;
	@Column(name = "pruining")
	private Quality pruining;
	@Column(name = "number_of_pruined_coffee_trees")
	private Long numberOfPruinedCoffeeTrees;
	@Column(name = "stumping")
	private Quality stumping;
	@Column(name = "number_of_stumped_tree")
	private Long numberOfStumpedTree;
	@Column(name = "planting_new_coffee_seedings")
	private Boolean plantingNewCoffeeSeedings;
	@Column(name = "last_use_of_non_allowed_chemicals")
	private Timestamp lastUseOfNonAllowedChemicals;
	@Column(name = "inter_plot_buffer_zones")
	private Boolean interPlotBufferZones;
	@Column(name = "field_separation")
	private FieldSeparationType fieldSeparation;
	@Column(name = "multiple_owner_with_organic")
	private Boolean multipleOwnerWithOrganic;
}
