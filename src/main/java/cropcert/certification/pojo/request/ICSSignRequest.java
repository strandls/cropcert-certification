package cropcert.certification.pojo.request;

import cropcert.certification.pojo.Signature;

public class ICSSignRequest {

	private Long farmerId;
	private Integer version;
	private Integer subVersion;
	private Signature signature;

	public ICSSignRequest() {
		super();
	}

	public ICSSignRequest(Long farmerId, Integer version, Integer subVersion, Signature signature) {
		super();
		this.farmerId = farmerId;
		this.version = version;
		this.subVersion = subVersion;
		this.signature = signature;
	}

	public Long getFarmerId() {
		return farmerId;
	}

	public void setFarmerId(Long farmerId) {
		this.farmerId = farmerId;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getSubVersion() {
		return subVersion;
	}

	public void setSubVersion(Integer subVersion) {
		this.subVersion = subVersion;
	}

	public Signature getSignature() {
		return signature;
	}

	public void setSignature(Signature signature) {
		this.signature = signature;
	}
}
