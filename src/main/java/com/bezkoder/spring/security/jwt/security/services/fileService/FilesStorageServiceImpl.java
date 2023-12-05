package com.bezkoder.spring.security.jwt.security.services.fileService;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.springframework.core.io.ByteArrayResource;

//import javax.annotation.Resource;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FilesStorageServiceImpl implements FilesStorageService {
	private final Path root = Paths.get("./uploads");
	private final Path root_certi = Paths.get("./certificates");
//	private final Path root_model = Paths.get("./models");
	private final Path root_diag = Paths.get("./diagnoses");
	private final Path root_predict = Paths.get("./predicts");
	private final Path root_proof = Paths.get("./appointments");

	  @Override
	  public void init() {
	    try {
	      Files.createDirectories(root);
	      Files.createDirectories(root_certi);
	      Files.createDirectories(root_predict);
	      Files.createDirectories(root_diag);
	      Files.createDirectories(root_proof);
	    } catch (IOException e) {
	      throw new RuntimeException("Could not initialize folder for upload!");
	    }
	  }

//	  @Override
	  public void save(MultipartFile file, String newname) {
	    try {
	      Files.copy(file.getInputStream(), this.root.resolve(newname));
	    } catch (Exception e) {
	      if (e instanceof FileAlreadyExistsException) {
	        throw new RuntimeException("A file of that name already exists.");
	      }

	      throw new RuntimeException(e.getMessage());
	    }
	  }
	  
	  @Override
	  public void save_cer(MultipartFile file, String newname) {
	    try {
	      Files.copy(file.getInputStream(), this.root_certi.resolve(newname));
	    } catch (Exception e) {
	      if (e instanceof FileAlreadyExistsException) {
	        throw new RuntimeException("A file of that name already exists.");
	      }

	      throw new RuntimeException(e.getMessage());
	    }
	  }
	  
	  public Resource load_cer(String filename) {
	    try {
	      Path file = root_certi.resolve(filename);
	      Resource resource = new UrlResource(file.toUri());

	      if (resource.exists() || resource.isReadable()) {
	        return resource;
	      } else {
	        throw new RuntimeException("Could not read the file!");
	      }
	    } catch (MalformedURLException e) {
	      throw new RuntimeException("Error: " + e.getMessage());
	    }
	  }

	  public boolean delete_cer(String filename) {
	    try {
	      Path file = root_certi.resolve(filename);
	      return Files.deleteIfExists(file);
	    } catch (IOException e) {
	      throw new RuntimeException("Error: " + e.getMessage());
	    }
	  }

	  public void deleteAll_cer() {
	    FileSystemUtils.deleteRecursively(root_certi.toFile());
	  }

	  public Stream<Path> loadAll_cer() {
	    try {
	      return Files.walk(this.root_certi, 1).filter(path -> !path.equals(this.root_certi)).map(this.root::relativize);
	    } catch (IOException e) {
	      throw new RuntimeException("Could not load the files!");
	    }
	  }
	  
	  @Override
	  public Resource load(String filename) {
	    try {
	      Path file = root.resolve(filename);
	      Resource resource = new UrlResource(file.toUri());

	      if (resource.exists() || resource.isReadable()) {
	        return resource;
	      } else {
	        throw new RuntimeException("Could not read the file!");
	      }
	    } catch (MalformedURLException e) {
	      throw new RuntimeException("Error: " + e.getMessage());
	    }
	  }

	  @Override
	  public boolean delete(String filename) {
	    try {
	      Path file = root.resolve(filename);
	      return Files.deleteIfExists(file);
	    } catch (IOException e) {
	      throw new RuntimeException("Error: " + e.getMessage());
	    }
	  }

	  @Override
	  public void deleteAll() {
	    FileSystemUtils.deleteRecursively(root.toFile());
	  }

	  @Override
	  public Stream<Path> loadAll() {
	    try {
	      return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
	    } catch (IOException e) {
	      throw new RuntimeException("Could not load the files!");
	    }
	  }
	///// model
	  
//	  public void save_model(MultipartFile file) {
//	    try {
//	      Files.copy(file.getInputStream(), this.root_model.resolve(file.getOriginalFilename()));
//	    } catch (Exception e) {
//	      if (e instanceof FileAlreadyExistsException) {
//	        throw new RuntimeException("A file of that name already exists.");
//	      }
//
//	      throw new RuntimeException(e.getMessage());
//	    }
//	  }
//	  
//	  public Resource load_model(String filename) {
//	    try {
//	      Path file = root_model.resolve(filename);
//	      Resource resource = new UrlResource(file.toUri());
//
//	      if (resource.exists() || resource.isReadable()) {
//	        return resource;
//	      } else {
//	        throw new RuntimeException("Could not read the file!");
//	      }
//	    } catch (MalformedURLException e) {
//	      throw new RuntimeException("Error: " + e.getMessage());
//	    }
//	  }
//	  
//	  public boolean delete_model(String filename) {
//	    try {
//	      Path file = root_model.resolve(filename);
//	      return Files.deleteIfExists(file);
//	    } catch (IOException e) {
//	      throw new RuntimeException("Error: " + e.getMessage());
//	    }
//	  }
//
//	  public void deleteAll_model() {
//	    FileSystemUtils.deleteRecursively(root_model.toFile());
//	  }
//
//	  public Stream<Path> loadAll_model() {
//	    try {
//	      return Files.walk(this.root_model, 1).filter(path -> !path.equals(this.root_model)).map(this.root_model::relativize);
//	    } catch (IOException e) {
//	      throw new RuntimeException("Could not load the files!");
//	    }
//	  }
	  
	  public void save_diag(MultipartFile file, String newname) {
	    try {
	      Files.copy(file.getInputStream(), this.root_diag.resolve(newname));
	    } catch (Exception e) {
	      if (e instanceof FileAlreadyExistsException) {
	        throw new RuntimeException("A file of that name already exists.");
	      }

	      throw new RuntimeException(e.getMessage());
	    }
	  }
	  
	  public Resource load_diag(String filename) {
	    try {
	      Path file = root_diag.resolve(filename);
	      Resource resource = new UrlResource(file.toUri());

	      if (resource.exists() || resource.isReadable()) {
	        return resource;
	      } else {
	        throw new RuntimeException("Could not read the file!");
	      }
	    } catch (MalformedURLException e) {
	      throw new RuntimeException("Error: " + e.getMessage());
	    }
	  }
	  
	  public boolean delete_diag(String filename) {
	    try {
	      Path file = root_diag.resolve(filename);
	      return Files.deleteIfExists(file);
	    } catch (IOException e) {
	      throw new RuntimeException("Error: " + e.getMessage());
	    }
	  }

	  public void deleteAll_diag() {
	    FileSystemUtils.deleteRecursively(root_diag.toFile());
	  }

	  public Stream<Path> loadAll_diag() {
	    try {
	      return Files.walk(this.root_diag, 1).filter(path -> !path.equals(this.root_diag)).map(this.root_diag::relativize);
	    } catch (IOException e) {
	      throw new RuntimeException("Could not load the files!");
	    }
	  }
	  
	  public void save_predict(MultipartFile file, String newname) {
	    try {
	      Files.copy(file.getInputStream(), this.root_predict.resolve(newname));
	    } catch (Exception e) {
	      if (e instanceof FileAlreadyExistsException) {
	        throw new RuntimeException("A file of that name already exists.");
	      }

	      throw new RuntimeException(e.getMessage());
	    }
	  }
	  
	  public Resource load_predict(String filename) {
	    try {
	      Path file = root_predict.resolve(filename);
	      Resource resource = new UrlResource(file.toUri());

	      if (resource.exists() || resource.isReadable()) {
	        return resource;
	      } else {
	        throw new RuntimeException("Could not read the file!");
	      }
	    } catch (MalformedURLException e) {
	      throw new RuntimeException("Error: " + e.getMessage());
	    }
	  }
	  
	  public boolean delete_predict(String filename) {
	    try {
	      Path file = root_predict.resolve(filename);
	      return Files.deleteIfExists(file);
	    } catch (IOException e) {
	      throw new RuntimeException("Error: " + e.getMessage());
	    }
	  }

	  public void deleteAll_predict() {
	    FileSystemUtils.deleteRecursively(root_predict.toFile());
	  }

	  public Stream<Path> loadAll_predict() {
	    try {
	      return Files.walk(this.root_predict, 1).filter(path -> !path.equals(this.root_predict)).map(this.root_predict::relativize);
	    } catch (IOException e) {
	      throw new RuntimeException("Could not load the files!");
	    }
	  }
	  
	  public void save_proof(MultipartFile file, String newname) {
		    try {
		      Files.copy(file.getInputStream(), this.root_proof.resolve(newname));
		    } catch (Exception e) {
		      if (e instanceof FileAlreadyExistsException) {
		        throw new RuntimeException("A file of that name already exists.");
		      }

		      throw new RuntimeException(e.getMessage());
		    }
		  }
		  
		  public Resource load_proof(String filename) {
		    try {
		      Path file = root_proof.resolve(filename);
		      Resource resource = new UrlResource(file.toUri());

		      if (resource.exists() || resource.isReadable()) {
		        return resource;
		      } else {
		        throw new RuntimeException("Could not read the file!");
		      }
		    } catch (MalformedURLException e) {
		      throw new RuntimeException("Error: " + e.getMessage());
		    }
		  }
		  
		  public boolean delete_proof(String filename) {
		    try {
		      Path file = root_proof.resolve(filename);
		      return Files.deleteIfExists(file);
		    } catch (IOException e) {
		      throw new RuntimeException("Error: " + e.getMessage());
		    }
		  }

		  public void deleteAll_proof() {
		    FileSystemUtils.deleteRecursively(root_proof.toFile());
		  }

		  public Stream<Path> loadAll_proof() {
		    try {
		      return Files.walk(this.root_proof, 1).filter(path -> !path.equals(this.root_proof)).map(this.root_proof::relativize);
		    } catch (IOException e) {
		      throw new RuntimeException("Could not load the files!");
		    }
		  }
}
