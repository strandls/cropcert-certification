package cropcert.certification.pojo.enumtype;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "quality")
@XmlEnum
public enum Quality {

	@XmlEnumValue("GOOD")
	GOOD("GOOD"),
	@XmlEnumValue("FAIR")
	FAIR("FAIR"),
	@XmlEnumValue("POOR")
	POOR("POOR");
	
	private String value;
	
	Quality(String value) {
		this.value = value;
	}
	
	public static Quality fromValue(String value) {
		for(Quality layerStatus : Quality.values()) {
			if(layerStatus.value.equals(value))
				return layerStatus;
		}
		throw new IllegalArgumentException(value);
	}
}
