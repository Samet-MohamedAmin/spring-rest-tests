package com.assignment.recipe.services.springdatajpa;

import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.assignment.recipe.models.Potato;
import com.assignment.recipe.repositories.PotatoRepository;
import com.assignment.recipe.services.PotatoService;

@Service
@Profile("springdatajpa")
public class PotatoJPAService implements PotatoService {

	private final PotatoRepository potatoRepository;

	@Autowired
	public PotatoJPAService(PotatoRepository potatoRepository) {

		this.potatoRepository = potatoRepository;
	}

	@Override
	public Set<Potato> findAll() {

		return Set.copyOf(potatoRepository.findAll());
	}

	@Override
	public Potato findById(Long aLong) {
		
		return potatoRepository.findById(aLong).orElse(null);
	}

	@Override
	public Potato save(Potato object) {
		
		return potatoRepository.save(object);
	}

	@Override
	@Transactional
	public Set<Potato> saveAll(Set<Potato> objectList) {
		
    	return Set.copyOf(potatoRepository.saveAll(objectList));
	}

	@Override
	public void delete(Potato object) {

		potatoRepository.delete(object);
	}

	@Override
	public void deleteById(Long aLong) {

		potatoRepository.deleteById(aLong);
	}
}
