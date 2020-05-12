package com.strandls.certification.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.google.inject.Inject;
import com.strandls.certification.pojo.FarmPlot;

public class FarmPlotDao extends AbstractDao<FarmPlot, Long>{

	@Inject
	protected FarmPlotDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public FarmPlot findById(Long id) {
		Session session = sessionFactory.openSession();
		FarmPlot entity = null;
		try {
			entity = session.get(FarmPlot.class, id);
		} catch (Exception e) {
			throw e;
		} finally {
			session.close();
		}
		return entity;
	}
}
