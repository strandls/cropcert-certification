package cropcert.certification.pojo.response;

import cropcert.certification.pojo.Inspection;

import cropcert.user.model.Farmer;

public class FarmersLastReport {

	private Long id;
	private Farmer farmer;
	private Inspection inspection;

	public FarmersLastReport() {
		super();
	}

	public FarmersLastReport(Long id, Farmer farmer, Inspection inspection) {
		super();
		this.id = id;
		this.farmer = farmer;
		this.inspection = inspection;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Farmer getFarmer() {
		return farmer;
	}

	public void setFarmer(Farmer farmer) {
		this.farmer = farmer;
	}

	public Inspection getInspection() {
		return inspection;
	}

	public void setInspection(Inspection inspection) {
		this.inspection = inspection;
	}
}
