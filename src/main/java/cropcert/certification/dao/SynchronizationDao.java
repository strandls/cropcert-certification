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

	public List<Synchronization> getSynchronizationForCollectionCenter(Integer limit, Integer offset,
			List<Long> farmerIds) {
		String farmerIdsString = "(";
		for (Long farmerId : farmerIds) {
			farmerIdsString += farmerId + ",";
		}
		farmerIdsString += "-1)";

		String queryStr = "select * from " + daoType.getSimpleName() + " t "
				+ " where farmer_id in " + farmerIdsString + " and "
				+ " last_updated = (select max(last_updated) from synchronization s where s.farmer_id = t.farmer_id)";

		Session session = sessionFactory.openSession();
		org.hibernate.query.Query query = session.createNativeQuery(queryStr, Synchronization.class);

		List<Synchronization> resultList = new ArrayList<Synchronization>();
		try {
			if (limit > 0 && offset >= 0)
				query = query.setFirstResult(offset).setMaxResults(limit);
			resultList = query.getResultList();

		} catch (NoResultException e) {
			throw e;
		}
		session.close();
		return resultList;
	}
}
