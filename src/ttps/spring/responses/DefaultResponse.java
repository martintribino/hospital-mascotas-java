package ttps.spring.responses;

import java.io.Serializable;
import org.json.JSONObject;

import org.springframework.http.HttpStatus;

public class DefaultResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private HttpStatus status;
	private int statusCode;
	private String statusText;

	public DefaultResponse() {
		this.setStatus(HttpStatus.SERVICE_UNAVAILABLE);
		this.setStatusCode(503);
		this.setStatusText("InternalError");
	}

	public DefaultResponse(HttpStatus status, int code, String text) {
		this.setStatus(status);
		this.setStatusCode(code);
		this.setStatusText(text);
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int status) {
		this.statusCode = status;
	}

	public String getStatusText() {
		return statusText;
	}

	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

    public JSONObject toJSON() {
        JSONObject jo = new JSONObject();
        jo.put("status", this.getStatus());
        jo.put("code", this.getStatusCode());
        jo.put("text", this.getStatusText());
        return jo;
    }

}
