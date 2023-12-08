package vn.hcmute.services.impl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import vn.hcmute.config.StorageProperties;
import vn.hcmute.exception.StorageException;
import vn.hcmute.services.IStorageService;

@Service
public class StorageServiceImpl implements IStorageService{
	private final Path rootLocation;
	
	
	
	public StorageServiceImpl(StorageProperties properties) {
		this.rootLocation = Paths.get(properties.getLocation());
	}

	@Override
	public String getStorageFileName(MultipartFile file, String id) {
		String ext = FilenameUtils.getExtension(file.getOriginalFilename());
		return "img" + id + "." + ext;
	}
	
	@Override
	public void store(MultipartFile file, String storeFileName) {
		try {
			if (file.isEmpty()) {
				throw new StorageException("Failed to store empty file!");
			}
			Path destinationFile = this.rootLocation.resolve(Paths.get(storeFileName)).normalize().toAbsolutePath();
			if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
				throw new StorageException("Cannot store file outside upload directory!");
			}
			try (InputStream inputStream  = file.getInputStream()){
				Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
			}
		} catch (Exception e) {
			throw new StorageException("Failed to store file " , e);
		}
	}
	
	@Override
	public Resource loadAsResource(String fileName) {
		try {
			Path file = load(fileName);
			Resource res = new UrlResource(file.toUri());
			if (res.exists() || res.isReadable()) {
				return res;
			}
			throw new StorageException("Cannot read file: " + fileName);
		} catch (Exception e) {
			throw new StorageException("Cannot read file: " + fileName);
		}
	}

	@Override
	public Path load(String fileName) {
		return rootLocation.resolve(fileName);
	}
	
	@Override
	public void delete(String fileName) throws IOException {
		Path destinationFile = rootLocation.resolve(Paths.get(fileName)).normalize().toAbsolutePath();
		Files.delete(destinationFile);
	}
	
	@Override
	public void init() {
		try {
			Files.createDirectories(rootLocation);
			System.out.println(rootLocation.toString());
		} catch (Exception e) {
			throw new StorageException("Cannot create upload folder!");
		}
	}
}
