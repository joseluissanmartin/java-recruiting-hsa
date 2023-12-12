package com.example.demoCupones.models;

public class Wrapper {

	private Long timestamp;
	private Integer status;
	private String mensaje;
	private Object data;

	public Wrapper() {
		super();
	}

	public Wrapper(Object data,String mensaje, Integer status, Long timestamp) {
		super();
		this.data = data;
		this.mensaje = mensaje;
		this.status = status;
		this.timestamp = timestamp;

	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}


	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
}
