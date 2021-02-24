package com.capgemini.utils;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.http.HttpStatus;

public class ResponseService<T> {

	private T data;
	private ArrayList<ResponseServiceField> alerts = new ArrayList<ResponseServiceField>();
	@JsonIgnore
	private HttpStatus status = HttpStatus.OK;

	public ResponseService() {
	}

	public ResponseService(T data) {
		if (data != null) {
			this.data = data;
		}
	}

	public ResponseService(T data, HttpStatus status) {
		if (data != null) {
			this.data = data;
		}

		if (status != null) {
			this.status = status;
		}
	}

	public ResponseService(T data, HttpStatus status, ArrayList<ResponseServiceField> alerts) {
		if (data != null) {
			this.data = data;
		}

		if (status != null) {
			this.status = status;
		}

		if (alerts != null) {
			this.alerts = alerts;
		}
	}

	public static class ResponseServiceField {
		private String field;
		private ArrayList<String> messages = new ArrayList<>();

		public ResponseServiceField() {
			super();
		}

		public ResponseServiceField(String field) {
			super();
			this.field = field;
		}

		public ResponseServiceField(String field, String message) {
			super();
			this.field = field;

			if (message != null) {
				this.messages.add(message);
			}
		}

		public String getField() {
			return field;
		}

		public void setField(String field) {
			this.field = field;
		}

		public ArrayList<String> getMessages() {
			return messages;
		}

		public void addMensagem(String message) {
			if (message != null) {
				this.messages.add(message);
			}
		}

	}

	public void addAlert(ResponseServiceField responseServiceField) {
		Boolean existeField = false;

		for (ResponseServiceField rsf : this.alerts) {
			if (rsf.getField().equals(responseServiceField.getField())) {
				rsf.getMessages().add(responseServiceField.getMessages().get(0));
				existeField = true;
				break;
			}
		}

		if (!existeField) {
			this.alerts.add(responseServiceField);
		}
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		if (data != null) {
			this.data = data;
		}
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		if (status != null) {
			this.status = status;
		}
	}

	public ArrayList<ResponseServiceField> getAlerts() {
		return alerts;
	}

	public void setAlerts(ArrayList<ResponseServiceField> alerts) {
		if (alerts != null) {
			this.alerts = alerts;
		}
	}

}