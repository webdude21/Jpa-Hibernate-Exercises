package com.hospital.serviceImpl;

import com.hospital.model.BaseEntity;
import com.hospital.repository.EntityRepository;
import com.hospital.service.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EntityServiceImpl<T extends BaseEntity> implements EntityService<T> {

	private EntityRepository<T> entityRepository;

	@Autowired
	public EntityServiceImpl(EntityRepository<T> entityRepository) {
		this.entityRepository = entityRepository;
	}

	@Override
	public void create(T entity) {
		entityRepository.saveAndFlush(entity);
	}

	@Override
	public T retrieve(long id) {
		return entityRepository.findOne(id);
	}
}
