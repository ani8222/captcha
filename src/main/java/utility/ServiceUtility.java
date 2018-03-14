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

import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class ServiceUtility {
	private static ApplicationLogger logger = new ApplicationLogger(ServiceUtility.class);

	private static ObjectMapper mapper = new ObjectMapper();

	public static final String SID_REGEX = "[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}";
	public static final String DCE_SID_REGEX = "DCE-" + SID_REGEX;
	public static final String MSISDN_REGEX = "5[0-9]{9}";
	public static final String PWD_REGEX = "[0-9]{8}";
	public static final String OTP_CODE_REGEX = "[1-9][0-9]{5}";
	public static final String TCKN_REGEX = "[1-9][0-9]{10}";
	public static final String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	

	public static String toJson(Object object) {
		try {
			return mapper.writeValueAsString(object);

		} catch (JsonProcessingException e) {
			logger.error(e.getMessage());
		}
		return "";
	}

	public static String notNull(String str) {
		return StringUtils.isBlank(str) ? "" : str.trim();
	}

}
