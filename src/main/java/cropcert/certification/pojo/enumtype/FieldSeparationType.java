package cropcert.certification.pojo.enumtype;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "field_separation_type")
@XmlEnum
public enum FieldSeparationType {

	@XmlEnumValue("NONE")
	NONE("NONE"),
	@XmlEnumValue("TREES")
	TREES("TREES"),
	@XmlEnumValue("FENCE")
	FENCE("FENCE"),
	@XmlEnumValue("DRENCH")
	DRENCH("DRENCH"),
	@XmlEnumValue("OTHER")
	OTHER("OTHER");
	
	private String value;
	
	FieldSeparationType(String value) {
		this.value = value;
	}
	
	public static FieldSeparationType fromValue(String value) {
		for(FieldSeparationType layerStatus : FieldSeparationType.values()) {
			if(layerStatus.value.equals(value))
				return layerStatus;
		}
		throw new IllegalArgumentException(value);
	}
}
