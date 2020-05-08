package com.strandls.certification.pojo.enumtype;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "quality")
@XmlEnum
public enum Quantity {

	@XmlEnumValue("GOOD")
	GOOD("GOOD"),
	@XmlEnumValue("FAIR")
	FAIR("FAIR"),
	@XmlEnumValue("POOR")
	POOR("POOR");
	
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
