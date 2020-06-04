package cropcert.certification.pojo.enumtype;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "decision")
@XmlEnum
public enum Decision {

	@XmlEnumValue("YES")
	YES("YES"),
	@XmlEnumValue("NO")
	NO("NO"),
	@XmlEnumValue("PARTIAL")
	PARTIAL("PARTIAL");
	
	private String value;
	
	Decision(String value) {
		this.value = value;
	}
	
	public static Decision fromValue(String value) {
		for(Decision layerStatus : Decision.values()) {
			if(layerStatus.value.equals(value))
				return layerStatus;
		}
		throw new IllegalArgumentException(value);
	}
}
