package com.bezkoder.spring.security.jwt.security.services.fileService;

import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface  FilesStorageService {
	  public void init();

	  public void save(MultipartFile file,String newname);

	  public Resource load(String filename);
	  
	  public boolean delete(String filename);
	  
	  public void deleteAll();

	  public Stream<Path> loadAll();
	  
	  public void save_cer(MultipartFile file,String newname);

	  public Resource load_cer(String filename);
	  
	  public boolean delete_cer(String filename);
	  
	  public void deleteAll_cer();

	  public Stream<Path> loadAll_cer();
	  
//	  public void save_model(MultipartFile file);
//
//	  public Resource load_model(String filename);
//	  
//	  public boolean delete_model(String filename);
//	  
//	  public void deleteAll_model();
//
//	  public Stream<Path> loadAll_model();
	  
	  public void save_diag(MultipartFile file,String newname);

	  public Resource load_diag(String filename);
	  
	  public boolean delete_diag(String filename);
	  
	  public void deleteAll_diag();

	  public Stream<Path> loadAll_diag();
	  
	  public void save_predict(MultipartFile file,String newname);

	  public Resource load_predict(String filename);
	  
	  public boolean delete_predict(String filename);
	  
	  public void deleteAll_predict();

	  public Stream<Path> loadAll_predict();
	  
	  public void save_proof(MultipartFile file,String newname);

	  public Resource load_proof(String filename);
	  
	  public boolean delete_proof(String filename);
	  
	  public void deleteAll_proof();

	  public Stream<Path> loadAll_proof();
}
