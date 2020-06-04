package cropcert.certification.pojo.enumtype;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "quantity")
@XmlEnum
public enum Quantity {

	@XmlEnumValue("MANY")
	MANY("MANY"),
	@XmlEnumValue("FEW")
	FEW("FEW"),
	@XmlEnumValue("NONE")
	NONE("NONE");
	
	private String value;
	
	Quantity(String value) {
		this.value = value;
	}
	
	public static Quantity fromValue(String value) {
		for(Quantity layerStatus : Quantity.values()) {
			if(layerStatus.value.equals(value))
				return layerStatus;
		}
		throw new IllegalArgumentException(value);
	}
}
