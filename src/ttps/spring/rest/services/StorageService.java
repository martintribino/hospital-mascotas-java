package ttps.spring.rest.services;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import ttps.spring.dao.IStorage;
import ttps.spring.exceptions.StorageException;
import ttps.spring.exceptions.StorageFileNotFoundException;
import ttps.spring.helpers.StorageProperties;

@Service
public class StorageService implements IStorage {

	public static final String SANITIZE = "[^a-zA-Z0-9\\._]+";
	public static Path rootLocation;
	final long limit = 1024 * 256;    // 256 KB
	@Autowired
	ServletContext servletContext;

	@Autowired
	public void FileSystemStorageService(StorageProperties properties) {
		String absoluteFilePath = servletContext.getRealPath(properties.getLocation());
		StorageService.rootLocation = Paths.get(absoluteFilePath);
	}

	@Override
	public void init() {
		try { 
			Files.createDirectories(rootLocation);
		}
		catch (IOException e) {
			throw new StorageException("No se pudo inicializar", e);
		}
	}
	
	public static String sanitizeFileName(String filename) {
		return filename.replaceAll(StorageService.SANITIZE, "_");
	}

	@Override
	public Stream<Path> cargarTodos() {
		try {
			return Files.walk(StorageService.rootLocation, 1)
				.filter(path -> !path.equals(StorageService.rootLocation))
				.map(StorageService.rootLocation::relativize);
		}
		catch (IOException e) {
			throw new StorageException("No se pudieron leer los archivos guardados", e);
		}
	}

	@Override
	public List<String> guardarTodos(List<MultipartFile> files, String dirName) {
		List<String> guardados = new ArrayList<String>();
		for (MultipartFile file : files) {
            if (file.isEmpty()) {
                continue;
            }
    	    if (file.getSize() > limit) {
    	        throw new MaxUploadSizeExceededException(limit);
    	    }
            String path = this.guardar(file, dirName);
            guardados.add(path);
		}
		return guardados;
	}

	@Override
	public String guardar(MultipartFile file, String dirName) {
		if (file.isEmpty()) {
			throw new StorageException("Failed to store empty file " + file.getOriginalFilename());
		}
		String originalFName = StorageService.sanitizeFileName(file.getOriginalFilename());
		String filename = StringUtils.cleanPath(dirName + File.separator + originalFName);
		if (filename.contains("..")) {
			// This is a security check
			throw new StorageException(
					"No se puede guardar archivo con path relativo fuera del actual directorio "
							+ filename);
		}
		String dir = StringUtils.cleanPath(dirName);
		this.createDirectory(dir);
		try (InputStream inputStream = file.getInputStream()) {
			Path absPath = StorageService.rootLocation.resolve(filename);
			Files.copy(inputStream, absPath,
				StandardCopyOption.REPLACE_EXISTING);
			return originalFName;
		}
		catch (IOException e) {
			throw new StorageException("No se pudo guardar el archivo " + filename, e);
		}
	}

	@Override
	public Path cargar(String originalFName, String slug) {
		String filename = StringUtils.cleanPath(slug + File.separator + originalFName);
		return rootLocation.resolve(filename);
	}

	@Override
	public Resource cargarComoRecurso(String originalFName, String slug) {
		try {
			Path file = cargar(originalFName, slug);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			}
			else {
				throw new StorageFileNotFoundException(
						"No se pudo leer el archivo: " + originalFName);

			}
		}
		catch (MalformedURLException e) {
			throw new StorageFileNotFoundException("No se pudo leer el archivo: " + originalFName, e);
		}
	}

	@Override
	public Boolean borrarTodos() {
		return FileSystemUtils.deleteRecursively(rootLocation.toFile());
	}

	@Override
	public Boolean borrar(String originalFName, String slug) {
		Path path = cargar(originalFName, slug);
		return FileSystemUtils.deleteRecursively(path.toFile());
	}

	@Override
	public void borrar(File file) {
		try
		{
			file.delete();
		}
		catch (Exception e) {
        	System.out.println(e.getMessage());
		}
	}

	@Override
	public void borrarTodos(String dirName) {
		this.createDirectory(dirName);
		Path dir = StorageService.rootLocation.resolve(dirName);
		File dirs = dir.toFile();
		File[] files = dirs.listFiles();
		for (File file: files)
            this.borrar(file);
	}
	
	@Override
	public void createDirectory(String dir) {
		Path dirPath = StorageService.rootLocation.resolve(dir);
		try
		{
			Files.createDirectories(dirPath);
		}
		catch (Exception e)
		{
			throw new StorageException("No se puede crear el directorio " + dir);
		}
	}  

}
