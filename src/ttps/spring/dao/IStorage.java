package ttps.spring.dao;

import java.io.File;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface IStorage {

	void init();
	Stream<Path> cargarTodos();
	Path cargar(String originalFName, String slug);
	Resource cargarComoRecurso(String originalFName, String slug);
	Boolean borrarTodos();
	Boolean borrar(String originalFName, String slug);
	List<String> guardarTodos(List<MultipartFile> files, String dirName);
	String guardar(MultipartFile file, String dirName);
	void borrar(File file);
	void borrarTodos(String dirName);
	void createDirectory(String dir);

}
