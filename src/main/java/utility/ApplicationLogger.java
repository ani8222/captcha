/*
 * Copyright (c) 2016-
 * Vodafone Teknoloji Hizmetleri A.S., Istanbul, Turkey
 *
 * All rights reserved. This Software or any portion of it can not be translated,
 * distributed, sold, adapted, arranged, used, copied, modified, de-compiled,
 * reverse assembled or otherwise reverse engineered, disassembled, replaced or made
 * additions to and to be reproduced in whole or in part, in any way, manner or form.
 */
package utility;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;



public class ApplicationLogger {

	private Logger logger;

	private static long traceIdSeq = 0;

	public long setTraceId() {
		long traceId = increaseTraceId();
		MDC.put("traceId", "[r:" + traceId + "]");
		return traceId;
	}

	public String getTraceId() {
		Object traceId = MDC.get("traceId");
		return (String) traceId;
	}

	private synchronized long increaseTraceId() {
		return ++traceIdSeq;
	}

	public ApplicationLogger() {
	}

	public ApplicationLogger(Class<? extends Object> clazz) {
		this();
		logger = Logger.getLogger(clazz);
	}

	public ApplicationLogger(String name) {
		this();
		logger = Logger.getLogger(name);
	}

	public void debug(Throwable throwable, String message, Object... parameters) {
		if (logger.isDebugEnabled()) {
			String formattedMessage = getFormattedMessage(message, parameters);
			logger.debug(formattedMessage, throwable);
		}
	}

	public void debug(String message, Object... parameters) {
		if (logger.isDebugEnabled()) {
			String formattedString = getFormattedMessage(message, parameters);
			logger.debug(formattedString);
		}
	}

	public void info(Throwable throwable, String message, Object... parameters) {
		if (logger.isInfoEnabled()) {
			String formattedMessage = getFormattedMessage(message, parameters);
			logger.info(formattedMessage, throwable);
		}
	}

	public void info(String message, Object... parameters) {
		if (logger.isInfoEnabled()) {
			String formattedMessage = getFormattedMessage(message, parameters);
			logger.info(formattedMessage);
		}
	}

	public void warn(Throwable throwable, String message, Object... parameters) {
		String formattedMessage = getFormattedMessage(message, parameters);
		logger.warn(formattedMessage, throwable);
	}

	public void warn(String message, Object... parameters) {
		String formattedMessage = getFormattedMessage(message, parameters);
		logger.warn(formattedMessage);
	}

	public void error(Throwable throwable, String message, Object... parameters) {
		String formattedMessage = getFormattedMessage(message, parameters);
		logger.error(formattedMessage, throwable);
	}

	public void error(String message, Object... parameters) {
		String formattedMessage = getFormattedMessage(message, parameters);
		logger.error(formattedMessage);
	}

	public void fatal(Throwable throwable, String message, Object... parameters) {
		String formattedMessage = getFormattedMessage(message, parameters);
		logger.fatal(formattedMessage, throwable);
	}

	public void fatal(String message, Object... parameters) {
		String formattedMessage = getFormattedMessage(message, parameters);
		logger.fatal(formattedMessage);
	}

	private void clearTraceId() {
		MDC.remove("traceId");
	}

	public void clearAll() {
		clearTraceId();
		MDC.clear();
	}

	private String getFormattedMessage(String message, Object... parameters) {
		String result = "";
		try {
			result = String.format(message, parameters);
		} catch (Exception e) {
			logger.error("Invalid log format!!! Message: " + message + " parameters: " + parameters);
		}
		return result;
	}
}
