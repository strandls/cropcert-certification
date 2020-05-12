package com.strandls.certification.service.imp;

import com.google.inject.Inject;
import com.strandls.certification.dao.AnimalDao;
import com.strandls.certification.pojo.Animal;
import com.strandls.certification.service.AbstractService;
import com.strandls.certification.service.AnimalService;

public class AnimalServiceImpl extends AbstractService<Animal> implements AnimalService {

	@Inject
	public AnimalServiceImpl(AnimalDao dao) {
		super(dao);
	}

}
