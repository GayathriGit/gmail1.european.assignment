package com.gmail.utilities;

public class CustomisedException extends Exception {
	
	private static final long serialVersionUID = -638334519508390458L;

	private static String fieldValue = null;
	private static String errorMessage = null;
	 

	/**
	 * @param fieldValue
	 * @param errorMessage
	 */
	public CustomisedException(String fieldValue, String errorMessage) {

		this.fieldValue = fieldValue;
		this.errorMessage = errorMessage;
	}

	public static String getFieldValue() {
		return fieldValue;
	}

	public static void setFieldValue(String fieldValue) {
		CustomisedException.fieldValue = fieldValue;
	}

	public static String getErrorMessage() {
		return errorMessage;
	}

	public static void setErrorMessage(String errorMessage) {
		CustomisedException.errorMessage = errorMessage;
	}

}
