package com.strandls.certification.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.strandls.certification.pojo.enumtype.Decision;
import com.strandls.certification.pojo.enumtype.Quantity;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "inspection")
@XmlRootElement
@JsonIgnoreProperties
@Getter@Setter
@ApiModel("Inspection")
public class Inspection implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5497916199514044930L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inspection_id_generator")
	@SequenceGenerator(name = "inspection_id_generator", sequenceName = "inspection_id_seq", allocationSize = 1)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	@Column(name = "date")
	private Timestamp date;
	@Column(name = "district")
	private String district;
	@Column(name = "field_coordinator_name")
	private String fieldCoordinatorName;
	@Column(name = "village")
	private String village;
	@Column(name = "colleting_center")
	private String colletingCenter;
	@Column(name = "farmer_name")
	private String farmerName;
	@Column(name = "farmer_code")
	private String farmerCode;
	@Column(name = "number_of_coffee_field")
	private Long numberOfCoffeeField;

	
	// Verification 
	@Column(name = "verification_date")
	private Timestamp verificationDate;
	@Column(name = "farmer_contract")
	private Timestamp farmerContract;
	
	// General information
	@Column(name = "last_used_chemicals")
	private Timestamp lastUsedChemicals;
	@Column(name = "chemicals_on_intercrop")
	private Boolean chemicalsOnIntercrop;
	@Column(name = "chemicals_on_non_coffee_field")
	private Boolean chemicalsOnNonCoffeeField;
	@Column(name = "manure_90_days_or_loss_before_harvest")
	private Boolean manure90DaysOrLossBeforeHarvest;
	@Column(name = "understanding_of_organic_ft_standards")
	private Boolean understandingOfOrganicFTStandards;
	@Column(name = "weed_control_adequate")
	private Boolean weedControlAdequate;
	@Column(name = "non_coffee_trees_planted")
	@Enumerated(EnumType.STRING)
	private Quantity nonCoffeeTreesPlanted;
	@Column(name = "signs_of_erosion")
	private Boolean signsOfErosion;
	@Column(name = "erosion_control_adequate")
	private Boolean erosionControlAdequate;
	@Column(name = "burning_of_crop_waste")
	private Boolean burningOfCropWaste;
	@Column(name = "farmer_hire_labour")
	private Boolean farmerHireLabour;
	@Column(name = "is_labour_fairly_treated")
	private Boolean isLabourFairlyTreated;
	@Column(name = "is_child_labour_imployed")
	private Boolean isChildLabourImployed;
	@Column(name = "plastic_disposal")
	private Quantity plasticDisposal;
	@Column(name = "is_other_waste_disposal_adequate")
	private Boolean isOtherWasteDisposalAdequate;
	@Column(name = "is_hh_making_joint_decision")
	private Boolean isHHMakingJointDecision;
	@Column(name = "is_hh_taking_farming_as_family_business")
	private Boolean isHHTakingFarmingAsFamilyBusiness;
	@Column(name = "comments")
	private String comments;
	
	// Farm list for the farmer;
	//private List<FarmPlot> farms;
	
	// Summary column
	@Column(name = "number_of_coffee_fields")
	private Integer numberOfCoffeeFields;
	@Column(name = "area_under_coffee")
	private Double areaUnderCoffee;
	@Column(name = "productive_trees")
	private Long productiveTrees;
	@Column(name = "total_area_of_farm")
	private Double totalAreaOfFarm;

	@Column(name = "known_to_harvest_ripe_cherries")
	private Boolean knownToHarvestRipeCherries;
	@Column(name = "practices_post_harvest_handlling")
	private Boolean practicesPostHarvestHandlling;
	
	
	// Animals
	@Column(name = "has_live_stock")
	private Boolean hasLiveStock;
	@Column(name = "chemical_treatment_on_livestock")
	private Boolean chemicalTreatmentOnLivestock;
	@Column(name = "livestock_treatment_conducted_5m_from_coffee")
	private Boolean livestockTreatmentConducted5mFromCoffee;
	//private List<Animal> animals;
	
	// Recommendation;
	@Column(name = "has_farmer_implemented_previous_advice")
	@Enumerated(EnumType.STRING)
	private Decision hasFarmerImplementedPreviousAdvice;
	
	//private List<Advice> advices;
	@Column(name = "made_serious_violation")
	private Boolean madeSeriousViolation;
	@Column(name = "violation_date")
	private Timestamp violationDate;
	@Column(name = "isRecommended_organic_certificatation")
	private Boolean isRecommendedOrganicCertificatation;
	
	@Column(name = "board_agm_minutes_kept")
	private Boolean boardAGMMinutesKept;
	@Column(name = "membership_lists_and_shares_updated")
	private Boolean membershipListsAndSharesUpdated;
	@Column(name = "is_annual_budget_and_audited_accounts")
	private Boolean isAnnualBudgetAndAuditedAccounts;
	@Column(name = "is_fair_trade_premium_budget_and_workplan")
	private Boolean isFairTradePremiumBudgetAndWorkplan;
	@Column(name = "is_envirnment_committe_and_its_workplan")
	private Boolean isEnvirnmentCommitteAndItsWorkplan;
	@Column(name = "is_ft_contract_person_appointed")
	private Boolean isFTContractPersonAppointed;

	@Column(name = "farmer")
	private Signature farmer;
	@Column(name = "field_coordinator")
	private Signature fieldCoordinator;
	@Column(name = "ics_manager")
	private Signature icsManager;
	@Column(name = "chair_person")
	private Signature chairPerson;
	
}
