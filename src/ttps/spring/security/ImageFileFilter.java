package ttps.spring.security;

import java.io.File;
import java.io.FileFilter;

public class ImageFileFilter /*implements FileFilter*/
{
	private final String[] enabledExtensions = new String[] {".jpg", ".png", ".gif", "svg"};
	private final int maxSize = 2097152;//2*1024*1024 => 2MB

	public boolean accept(File file){
		/*if(!file.isFile() || file.length() > this.maxSize) {
			return false;
		}*/
		for (String extension : enabledExtensions)
		{
			if (file.getName().toLowerCase().endsWith(extension))
			{
				return true;
			}
		}
    	return false;
	}
}
