package eu.cymo.adapters.schemaregistry;

import io.confluent.kafka.schemaregistry.client.SchemaRegistryClient;
import io.confluent.kafka.schemaregistry.client.SchemaRegistryClientFactory;

public class SchemaRegistrySupplier {

	public SchemaRegistryClient get(SchemaRegistrySupplierParameters parameters) {
        return SchemaRegistryClientFactory.newClient(
                parameters.baseUrls(),
                parameters.cacheCapacity(),
                parameters.schemaProviders(),
                parameters.configs(),
                parameters.httpHeaders());
	}
	
}
