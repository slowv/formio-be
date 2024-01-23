package com.cimb.demo.repository;

import com.cimb.demo.entity.FormEntity;
import com.cimb.demo.entity.enums.FormType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormRepository extends JpaRepository<FormEntity, String> {
    Page<FormEntity> findAllByType(FormType type, Pageable pageable);
}
