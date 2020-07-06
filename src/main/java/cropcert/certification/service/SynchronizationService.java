package cropcert.certification.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import cropcert.certification.pojo.Synchronization;
import cropcert.user.ApiException;

public interface SynchronizationService {

	public Synchronization save(HttpServletRequest request, String jsonString)
			throws JsonParseException, JsonMappingException, IOException;

	public Synchronization save(Synchronization synchronization);

	public Synchronization findByPropertyWithCondtion(String property, String value, String condition);

	public List<Synchronization> getByPropertyWithCondtion(String property, Object value, String condition, int limit,
			int offset);

	public List<Synchronization> getSynchronizationForCollectionCenter(HttpServletRequest request, Integer limit,
			Integer offset, Long ccCode) throws ApiException;
}