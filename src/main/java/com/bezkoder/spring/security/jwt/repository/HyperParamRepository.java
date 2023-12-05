package com.bezkoder.spring.security.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bezkoder.spring.security.jwt.models.HyperParam;

@Repository
public interface HyperParamRepository extends JpaRepository<HyperParam, Long>{

}
