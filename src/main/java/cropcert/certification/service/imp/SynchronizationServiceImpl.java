package cropcert.certification.service.imp;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cropcert.certification.dao.SynchronizationDao;
import cropcert.certification.pojo.Synchronization;
import cropcert.certification.service.AbstractService;
import cropcert.certification.service.SynchronizationService;

public class SynchronizationServiceImpl extends AbstractService<Synchronization> implements SynchronizationService {

	@Inject
	private ObjectMapper objectMapper;
	
	@Inject
	private SynchronizationDao synchronizationDao;

	@Inject
	public SynchronizationServiceImpl(SynchronizationDao dao) {
		super(dao);
	}
	
	@Override
	public Synchronization save(HttpServletRequest request, String jsonString)
			throws JsonParseException, JsonMappingException, IOException {
		Synchronization synchronization = objectMapper.readValue(jsonString, Synchronization.class);
		synchronization = save(synchronization);
		return synchronization;
	}

	@Override
	public Synchronization getPartialReport(Long inspectorId, Long farmerId) {
		Synchronization sync = synchronizationDao.getCurrentPartialReport(inspectorId, farmerId);
		return sync;
	}
}
