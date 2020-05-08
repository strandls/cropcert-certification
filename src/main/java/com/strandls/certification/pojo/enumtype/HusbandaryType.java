package com.strandls.certification.pojo.enumtype;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "quality")
@XmlEnum
public enum HusbandaryType {

	@XmlEnumValue("LOCKED")
	LOCKED("LOCKED"),
	@XmlEnumValue("FREE")
	FREE("FREE");
	
	private String value;
	
	HusbandaryType(String value) {
		this.value = value;
	}
	
	public static HusbandaryType fromValue(String value) {
		for(HusbandaryType layerStatus : HusbandaryType.values()) {
			if(layerStatus.value.equals(value))
				return layerStatus;
		}
		throw new IllegalArgumentException(value);
	}
}
