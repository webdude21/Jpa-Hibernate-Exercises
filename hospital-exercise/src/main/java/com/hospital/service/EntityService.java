package com.hospital.service;

import com.hospital.model.BaseEntity;

public interface EntityService<T extends BaseEntity> {
	void create(T entity);

	public T retrieve(long id);
}
