package cropcert.certification.service;

import cropcert.certification.pojo.Advice;

public interface AdviceService {
	
	public Advice save(Advice advice);

	public Advice findById(Long id);
	
}
