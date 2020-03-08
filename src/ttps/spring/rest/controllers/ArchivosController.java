package ttps.spring.rest.controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.MultipartConfigElement;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import ttps.spring.exceptions.MascotaNotFoundException;
import ttps.spring.exceptions.StorageException;
import ttps.spring.model.Mascota;
import ttps.spring.responses.ImageResponse;
import ttps.spring.rest.services.MascotaService;
import ttps.spring.rest.services.StorageService;

@RestController
@RequestMapping(value="/api/archivos", produces={MediaType.APPLICATION_JSON_VALUE})
public class ArchivosController {

	@Autowired
	private MascotaService mascotaService;
	@Autowired
	private StorageService storageService;
	
	@Bean
    public MultipartConfigElement multipartConfigElement() {
        return new MultipartConfigElement("");
    }

    @Bean
    public MultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(1000000);
        return multipartResolver;
    }
    
    @GetMapping(value="/{slgMascota}/{imagen}/")
    public ResponseEntity<ImageResponse> guardarFotoMascota(
    		@PathVariable String slgMascota,
    		@PathVariable String imagen,
    		HttpServletRequest request,
    		HttpServletResponse response
		) throws IOException {
		Mascota m = mascotaService.encontrar(slgMascota);
		if (m == null) {
	         throw new MascotaNotFoundException("Mascota no encontrada." );
	    }
		try
		{
			Resource rec = this.storageService.cargarComoRecurso(imagen, slgMascota);
		    try
		    {
			    byte[] base64 = Base64.encode(IOUtils.toByteArray(rec.getInputStream()));
				String str = new String(base64);
				ImageResponse ir = new ImageResponse();
				ir.setB64str(str);
			    return new ResponseEntity<ImageResponse>(ir, HttpStatus.OK);
		    }
		    catch (IOException ioe)
		    {
		    	throw ioe;
		    }
		}
		catch(FileNotFoundException fnfe)
		{
			throw fnfe;
		}
    }

	@PostMapping(value="/guardar")
	public ResponseEntity<List<String>> guardarFotoMascota(
			@RequestParam(name="archivos[]") LinkedList<MultipartFile> archivos,
			@RequestParam String mascota
		) {
		Mascota m = mascotaService.encontrar(mascota);
		if (m == null) {
	         throw new MascotaNotFoundException("Mascota no encontrada." );
	    }
		try {
			this.storageService.borrarTodos(m.getSlug());
			List<String> paths = this.storageService.guardarTodos(archivos, m.getSlug());
            if (paths.isEmpty()) {
    			throw new StorageException("No se pudo guardar el archivo");
            }
            else {
            	mascotaService.actualizarMascota(m, paths.get(0));
            }
			return new ResponseEntity<List<String>>(paths, HttpStatus.OK);
		} catch (Exception e) {
			throw e;
		}
	}

}
