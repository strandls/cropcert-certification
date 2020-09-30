package cropcert.certification.pojo.request;

public class ICSSignRequest {

	private Long farmerId;
	private Integer version;
	private Integer subVersion;

	public ICSSignRequest() {
		super();
	}

	public ICSSignRequest(Long farmerId, Integer version, Integer subVersion) {
		super();
		this.farmerId = farmerId;
		this.version = version;
		this.subVersion = subVersion;
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
}
