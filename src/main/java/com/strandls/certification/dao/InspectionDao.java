package com.strandls.certification.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.google.inject.Inject;
import com.strandls.certification.pojo.Inspection;

public class InspectionDao extends AbstractDao<Inspection, Long>{

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
}
