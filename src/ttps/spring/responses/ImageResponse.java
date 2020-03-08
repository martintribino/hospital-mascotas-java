package ttps.spring.responses;

import java.io.Serializable;

public class ImageResponse implements Serializable {

	/**
	 * ImageResponse
	 */
	private static final long serialVersionUID = -8454704625156096061L;

	private String b64str;
	private String extension;
	private int width;
	private int height;

	public ImageResponse() {
		this.setB64str("");
		this.setExtension("");
		this.setWidth(150);
		this.setHeight(150);
	}

	public String getB64str() {
		return b64str;
	}

	public void setB64str(String b64str) {
		this.b64str = b64str;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

}
