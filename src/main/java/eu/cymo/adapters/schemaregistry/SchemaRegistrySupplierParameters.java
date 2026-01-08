package eu.cymo.adapters.schemaregistry;

import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import io.confluent.kafka.schemaregistry.SchemaProvider;

public record SchemaRegistrySupplierParameters(
		String schemaRegistryUrl,
		String schemaRegistryBasicAuthUserInfo) {
	
	public List<String> baseUrls() {
		return List.of(schemaRegistryUrl);
	}
	
	public int cacheCapacity() {
		return 1000;
	}
	
	public List<SchemaProvider> schemaProviders() {
		return Collections.emptyList();
	}
	
	
	public Map<String, ?> configs() {
		return Map.of();
	}

	public Map<String, String> httpHeaders() {
		var headers = new HashMap<String, String>();
		if(StringUtils.isNotBlank(schemaRegistryBasicAuthUserInfo)) {
			headers.put("Authorization", "Basic " + Base64.getEncoder().encodeToString((schemaRegistryBasicAuthUserInfo).getBytes()));
		}
		return headers;
	}
	
}
