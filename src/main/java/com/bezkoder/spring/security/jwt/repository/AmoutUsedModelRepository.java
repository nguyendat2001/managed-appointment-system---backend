package com.bezkoder.spring.security.jwt.repository;

import java.time.YearMonth;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bezkoder.spring.security.jwt.models.AmountUsedModel;
import com.bezkoder.spring.security.jwt.models.Model;

@Repository
public interface AmoutUsedModelRepository  extends JpaRepository<AmountUsedModel, Long> {
	List<AmountUsedModel> findByCreatedAt(YearMonth date);
	List<AmountUsedModel> findByModel(Model model);
	AmountUsedModel findByModelAndCreatedAt(Model model,YearMonth createdAt);
}
