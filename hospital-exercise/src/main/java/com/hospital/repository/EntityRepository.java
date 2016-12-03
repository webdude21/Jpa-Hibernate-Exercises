package com.hospital.repository;

import com.hospital.model.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface EntityRepository<T extends BaseEntity> extends JpaRepository<T, Long> {
}
