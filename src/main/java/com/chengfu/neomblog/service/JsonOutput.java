package com.chengfu.neomblog.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chengfu.neomblog.model.Publish;
import com.chengfu.neomblog.util.Utils;

@Component
public class JsonOutput {
	@Autowired
	ObjectMapper objectMapper;
	
	@Autowired
	Utils utils;
	
	public String toJson(Publish publish) {
		Map<String, Object> result = toObject(publish);
		return writeJson(result);
	}
	
	public String toJson(Collection<? extends Publish> publishes) {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		for (Publish publish : publishes) {
			result.add(toObject(publish));
		}
		return writeJson(result);
	}
	
	private Map<String, Object> toObject(Publish publish) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("publishedAt", publish.getPublishedAt().getTime());
		result.put("userDisplayName", publish.getUser().getDisplayName());
		result.put("userLoginName", publish.getUser().getLoginName());
		result.put("userProfileImageUrl", utils.generateProfileImageUrl(publish.getUser().getEmail()));
		result.put("message", publish.getMessage().getContent());
		return result;
	}
	
	private String writeJson(Object obj) {
		try {
			return objectMapper.writeValueAsString(obj);
		} catch (Exception e) {
			return "{}";
		}
	}
}
