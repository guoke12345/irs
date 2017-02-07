package com.framework.core.exception;

@SuppressWarnings("serial")
public class InfoTipException extends RuntimeException {

		private String code;
		private String result;
		private String message;
		
		public InfoTipException(String message) {
			super(message);
			this.message = message;
		}
		
		
		public InfoTipException(String result, String message) {
			super(message);
			this.result = result;
			this.message = message;
		}


		public InfoTipException(String code, String result, String message) {
			super(message);
			this.code = code;
			this.result = result;
			this.message = message;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getResult() {
			return result;
		}

		public void setResult(String result) {
			this.result = result;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}
		
		@Override
		public String toString() {
			return "{code:'" + code + "',result:'" + result + "',message:'" + message +"'}";
		}
}
