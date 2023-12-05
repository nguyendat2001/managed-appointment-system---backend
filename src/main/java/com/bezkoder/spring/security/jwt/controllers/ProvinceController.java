package com.bezkoder.spring.security.jwt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.spring.security.jwt.models.Province;
import com.bezkoder.spring.security.jwt.payload.request.ProvinceRequest;
import com.bezkoder.spring.security.jwt.payload.response.MessageResponse;
import com.bezkoder.spring.security.jwt.security.services.ProvinceService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/provinces")
public class ProvinceController {
	@Autowired
	ProvinceService provinceService;
	
	@GetMapping
	public ResponseEntity<List<Province>> getAllProvince(){
		List<Province> Provinces = provinceService.getAllProvince();
		return new ResponseEntity<>(Provinces, HttpStatus.OK);
	}
	
	@GetMapping("/getAllActiveProvince")
	public ResponseEntity<List<Province>> getAllAvailableProvince(){
		List<Province> Provinces = provinceService.getAllIsactive();
		return new ResponseEntity<>(Provinces, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Province> getOneProvince(@PathVariable Long id){
		Province Province = provinceService.getProvinceById(id);
		if (Province != null) {
            return new ResponseEntity<>(Province, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	
	@GetMapping("/active/{id}")
	@PreAuthorize(" hasRole('ADMIN')")
	public ResponseEntity<Province> activeByRoomId(@PathVariable Long id){
		Province province = provinceService.activeProvince(id);
		if (province != null) {
			return new ResponseEntity<>(province, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	
	@GetMapping("/inactive/{id}")
	@PreAuthorize(" hasRole('ADMIN')")
	public ResponseEntity<Province> inactiveByRoomId(@PathVariable Long id){
		Province province = provinceService.inactiveProvince(id);
		if (province != null) {
			return new ResponseEntity<>(province, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> createProvince(@RequestBody ProvinceRequest ProvinceRequest){
		Province createProvince = provinceService.createProvince(ProvinceRequest);
		return ResponseEntity.ok(new MessageResponse("Add Province successfully!"));
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> deleteProvince(@PathVariable Long id){
		provinceService.deleteProvinceById(id);
		return ResponseEntity.ok(new MessageResponse("delete Province successfully!"));
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> updateProvince(@PathVariable Long id,@RequestBody ProvinceRequest ProvinceRequest){
		Province updateProvince = provinceService.updateProvince(id, ProvinceRequest);
		if (updateProvince != null) {
            return ResponseEntity.ok(new MessageResponse("Update Province successfully!"));
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
}
