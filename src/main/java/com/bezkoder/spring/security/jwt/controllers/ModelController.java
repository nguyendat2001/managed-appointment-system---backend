package com.bezkoder.spring.security.jwt.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bezkoder.spring.security.jwt.models.Model;
import com.bezkoder.spring.security.jwt.models.Model;
import com.bezkoder.spring.security.jwt.payload.request.ModelRequest;
import com.bezkoder.spring.security.jwt.payload.response.MessageResponse;
import com.bezkoder.spring.security.jwt.security.services.ModelService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/models")
public class ModelController {
	@Autowired
	ModelService modelService;

	@GetMapping
	public ResponseEntity<List<Model>> getAllModel(){
		List<Model> Models = modelService.getAllModel();
		return new ResponseEntity<>(Models, HttpStatus.OK);
	}
	
	@GetMapping("/getAllActive")
	@PreAuthorize("hasRole('USER') or hasRole('DOCTOR') or hasRole('ADMIN')")
	public ResponseEntity<List<Model>> getAllActiveModel(){
		List<Model> Models = modelService.getAllActive();
		return new ResponseEntity<>(Models, HttpStatus.OK);
	}
	
	@GetMapping("/active/{id}")
	@PreAuthorize(" hasRole('ADMIN')")
	public ResponseEntity<Model> activeByRoomId(@PathVariable Long id){
		Model model = modelService.active(id);
		if (model != null) {
			return new ResponseEntity<>(model, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	
	@GetMapping("/inactive/{id}")
	@PreAuthorize(" hasRole('ADMIN')")
	public ResponseEntity<Model> inactiveByRoomId(@PathVariable Long id){
		Model model = modelService.inactive(id);
		if (model != null) {
			return new ResponseEntity<>(model, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	
	@GetMapping("/segmentation")
	public ResponseEntity<List<Model>> getAllSegModel(){
		List<Model> Models = modelService.getSegmentationModel();
		return new ResponseEntity<>(Models, HttpStatus.OK);
	}
	
	@GetMapping("/classification")
	public ResponseEntity<List<Model>> getAllClassifiModel(){
		List<Model> Models = modelService.getClassificationModel();
		return new ResponseEntity<>(Models, HttpStatus.OK);
	}
	
	@GetMapping("/findbydataset/{id}")
	public ResponseEntity<List<Model>> getModelbydataset(@PathVariable Long id){
		List<Model> Models = modelService.getModelByDataset(id);
		return new ResponseEntity<>(Models, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Model> getOneModel(@PathVariable Long id){
		Model model = modelService.getModelById(id);
		if (model != null) {
            return new ResponseEntity<>(model, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}

	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> createModel(@RequestBody ModelRequest modelRequest){
		modelService.createModel(modelRequest);
		return ResponseEntity.ok(new MessageResponse("Add Model successfully!"));
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> deleteModel(@PathVariable Long id){
		modelService.deleteModelById(id);
		return ResponseEntity.ok(new MessageResponse("delete Model successfully!"));
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> updateModel(@PathVariable Long id,@RequestBody ModelRequest ModelRequest){
		Model updateModel = modelService.updateModel(id, ModelRequest);
		if (updateModel != null) {
            return ResponseEntity.ok(new MessageResponse("Update Model successfully!"));
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	
//	@PostMapping("/upload-file/{id}")
//	public ResponseEntity<MessageResponse> uploadModel(@PathVariable Long id,@RequestParam("file") MultipartFile file){
////		file
////		String uniqueID = UUID.randomUUID().toString();
//		Model updateModel = modelService.uploadFile(id, file);
//		if (updateModel != null) {
//            return ResponseEntity.ok(new MessageResponse("Upload Model Model successfully!"));
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//	}
//	
//	@PostMapping("/update-file/{id}")
//	public ResponseEntity<MessageResponse> updateModel(@PathVariable Long id,@RequestParam("file") MultipartFile file){
//		Model updateModel = modelService.updateFile(id, file);
//		if (updateModel != null) {
//            return ResponseEntity.ok(new MessageResponse("Update Model Model successfully!"));
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//	}
}
