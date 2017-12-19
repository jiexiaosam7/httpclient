package cn.itcast.httpclient;

public class HttpResult {

	private Integer status;
	
	private String content;

	public HttpResult(Integer status) {
		super();
		this.status = status;
	}

	public HttpResult(Integer status, String content) {
		super();
		this.status = status;
		this.content = content;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
}
