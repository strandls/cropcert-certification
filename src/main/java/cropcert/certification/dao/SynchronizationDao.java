package cropcert.certification.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.google.inject.Inject;

import cropcert.certification.pojo.Synchronization;

public class SynchronizationDao extends AbstractDao<Synchronization, Long>{

	@Inject
	protected SynchronizationDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public Synchronization findById(Long id) {
		Session session = sessionFactory.openSession();
		Synchronization entity = null;
		try {
			entity = session.get(Synchronization.class, id);
		} catch (Exception e) {
			throw e;
		} finally {
			session.close();
		}
		return entity;
	}

	public Synchronization getCurrentPartialReport(Long inspectorId, Long farmerId) {

		String queryStr = "from " + daoType.getSimpleName() + " t "
				+ " where farmerId = :farmerId and updatedBy = :inspectorId "
				+ "and isReportFinalized = false and isDeleted = false";

		Session session = sessionFactory.openSession();
		Query query = session.createQuery(queryStr);
		query.setParameter("inspectorId", inspectorId);
		query.setParameter("farmerId", farmerId);

		List<Synchronization> resultList = new ArrayList<Synchronization>();
		try {
				resultList = query.getResultList();
		} catch (NoResultException e) {
			throw e;
		}
		session.close();
		
		if(resultList == null || resultList.size() == 0) return null;
		
		return resultList.get(0);
	}
}
