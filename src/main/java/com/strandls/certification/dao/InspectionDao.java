package com.strandls.certification.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.google.inject.Inject;
import com.strandls.certification.pojo.Inspection;

public class InspectionDao extends AbstractDao<Inspection, Long> {

	@Inject
	protected InspectionDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public Inspection findById(Long id) {
		Session session = sessionFactory.openSession();
		Inspection entity = null;
		try {
			entity = session.get(Inspection.class, id);
		} catch (Exception e) {
			throw e;
		} finally {
			session.close();
		}
		return entity;
	}

	public List<Inspection> getReportsForInspector(Integer limit, Integer offset, Long inspectorId, Long farmerId) {

		String queryStr = "from " + daoType.getSimpleName() + " t "
				+ "where t.inspectorId =  :inspectorId and t.farmerDetails.id = :farmerId order by id";
		/*
		 * String queryStr = "" + "from " + daoType.getSimpleName() + " t " + "where t."
		 * + property + " " + condition + " :value" + " order by id";
		 */

		Session session = sessionFactory.openSession();
		org.hibernate.query.Query query = session.createQuery(queryStr);
		query.setParameter("inspectorId", inspectorId);
		query.setParameter("farmerId", farmerId);

		List<Inspection> resultList = new ArrayList<Inspection>();
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
