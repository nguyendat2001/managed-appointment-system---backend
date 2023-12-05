package com.bezkoder.spring.security.jwt.security.services;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bezkoder.spring.security.jwt.models.AmountUsedModel;
import com.bezkoder.spring.security.jwt.models.Model;
import com.bezkoder.spring.security.jwt.payload.request.AmoutUsedModelRequest;
import com.bezkoder.spring.security.jwt.repository.AmoutUsedModelRepository;
import com.bezkoder.spring.security.jwt.repository.ModelRepository;

@Service
public class AmoutUsedModelService {
	@Autowired
	AmoutUsedModelRepository amoutUsedModelRepository;
	
	@Autowired
	ModelRepository modelRepository;
	
	public List<AmountUsedModel> getAll(){
		return amoutUsedModelRepository.findAll();
	}
	
	public AmountUsedModel getById(Long id) {
		return amoutUsedModelRepository.findById(id).orElse(null);
	}
	
	public List<AmountUsedModel> getByYearMonth(YearMonth yearMonth) {
		return amoutUsedModelRepository.findByCreatedAt(yearMonth);
	}
	
	public List<AmountUsedModel> getByModel(Long id) {
		Model model = modelRepository.findById(id).orElseThrow(() 
				-> new RuntimeException("Error: Model is not found."));
		return amoutUsedModelRepository.findByModel(model);
	}
	
//	public List<Array> getModelAmountByMonth(Long id) {
//		Model model = modelRepository.findById(id).orElseThrow(() 
//				-> new RuntimeException("Error: Model is not found."));
//		List<AmountUsedModel> result = amoutUsedModelRepository.findByModel(model);
//		
//		YearMonth date = YearMonth.now();
//		
//		for( int i = 0 ;i < 5 ;i ++) {
//			for (AmountUsedModel tmp : result) {
//				
//			}
//		
//		return result;
//	}
	
	public AmountUsedModel getByModelAndLocalDate(AmoutUsedModelRequest amoutUsedModelRequest) {
		Model model = modelRepository.findById(amoutUsedModelRequest.getModel()).orElseThrow(() 
				-> new RuntimeException("Error: Model is not found."));
		return amoutUsedModelRepository.findByModelAndCreatedAt(model,amoutUsedModelRequest.getCreatedAt());
	}
	
	@Transactional
	public AmountUsedModel createAmountUsedModel(AmoutUsedModelRequest amoutUsedModelRequest) {
		
		Model model = modelRepository.findById(amoutUsedModelRequest.getModel()).orElseThrow(() 
				-> new RuntimeException("Error: Model is not found."));
		YearMonth date = YearMonth.now();
		AmountUsedModel amountUsedModel = amoutUsedModelRepository.findByModelAndCreatedAt(model, date);
		if(amountUsedModel == null) {
			AmountUsedModel newAmount = new AmountUsedModel();
			newAmount.setModel(model);
			return amoutUsedModelRepository.save(newAmount);
		}else {
//			AmountUsedModel exsitAmountUsedModel = amountUsedModel.get(0);
//					exsitAmountUsedModel.setAmount(exsitAmountUsedModel.getAmount()+1);
//			return amoutUsedModelRepository.save(exsitAmountUsedModel);
			amountUsedModel.setAmount(amountUsedModel.getAmount()+1);
			return amoutUsedModelRepository.save(amountUsedModel);
		}
		
	}
	
	@Transactional
	public AmountUsedModel create2AmountUsedModel(AmoutUsedModelRequest amoutUsedModelRequest) {
		
		Model model = modelRepository.findById(amoutUsedModelRequest.getModel()).orElseThrow(() 
				-> new RuntimeException("Error: Model is not found."));
//		YearMonth date = YearMonth.now();
//		System.out.println(amoutUsedModelRequest.getCreatedAt());
		AmountUsedModel amountUsedModel = amoutUsedModelRepository.findByModelAndCreatedAt(model, amoutUsedModelRequest.getCreatedAt());
		if(amountUsedModel == null) {
			AmountUsedModel newAmount = new AmountUsedModel();
			newAmount.setModel(model);
			newAmount.setCreatedAt(amoutUsedModelRequest.getCreatedAt());
			return amoutUsedModelRepository.save(newAmount);
		}else {
//			AmountUsedModel exsitAmountUsedModel = amountUsedModel.get(0);
//					exsitAmountUsedModel.setAmount(exsitAmountUsedModel.getAmount()+1);
//			return amoutUsedModelRepository.save(exsitAmountUsedModel);
			amountUsedModel.setAmount(amountUsedModel.getAmount()+1);
			return amoutUsedModelRepository.save(amountUsedModel);
		}
		
	}
}
