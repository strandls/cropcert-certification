package cropcert.certification.service;

import cropcert.certification.pojo.Animal;

public interface AnimalService {
	
	public Animal save(Animal animal);

	public Animal findById(Long id);
	
}
