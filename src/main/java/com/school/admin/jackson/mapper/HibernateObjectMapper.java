package com.school.admin.jackson.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.school.admin.exception.OQSystemException;

public interface HibernateObjectMapper {

	public ObjectMapper fetchEagerly(boolean forceLazyLoading);
	
	public String prepareJSON(ObjectMapper objectMapper, Object data) throws OQSystemException;
}
