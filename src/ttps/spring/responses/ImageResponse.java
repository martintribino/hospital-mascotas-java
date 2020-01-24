package ttps.spring.responses;

import java.io.Serializable;

public class ImageResponse implements Serializable {

	/**
	 * ImageResponse
	 */
	private static final long serialVersionUID = -8454704625156096061L;

	private String qrcode;
	private String extension;
	private int width;
	private int height;

	public ImageResponse() {
		this.qrcode = "";
		this.setExtension("");
		this.setWidth(150);
		this.setHeight(150);
	}

	public String getQrcode() {
		return qrcode;
	}

	public void setQrcode(String qrcode) {
		this.qrcode = qrcode;
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
