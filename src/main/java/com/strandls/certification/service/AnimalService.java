package com.strandls.certification.service;

import com.strandls.certification.pojo.Animal;

public interface AnimalService {
	
	public Animal save(Animal animal);

	public Animal findById(Long id);
	
}
