package vn.hcmute.services;

import java.io.IOException;
import java.nio.file.Path;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


public interface IStorageService {

	void init();

	void delete(String fileName) throws IOException;

	Path load(String fileName);

	Resource loadAsResource(String fileName);

	void store(MultipartFile file, String storeFileName);

	String getStorageFileName(MultipartFile file, String id);

}
