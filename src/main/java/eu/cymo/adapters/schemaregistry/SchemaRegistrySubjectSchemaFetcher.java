package eu.cymo.adapters.schemaregistry;

import java.io.IOException;

import eu.cymo.domain.schema.Schema;
import eu.cymo.domain.schema.SchemaFetchException;
import eu.cymo.domain.schema.SubjectSchemaFetcher;
import io.confluent.kafka.schemaregistry.client.SchemaRegistryClient;
import io.confluent.kafka.schemaregistry.client.rest.exceptions.RestClientException;

public class SchemaRegistrySubjectSchemaFetcher implements SubjectSchemaFetcher {
	
	private final SchemaRegistryClient schemaRegistryClient;

	public SchemaRegistrySubjectSchemaFetcher(SchemaRegistryClient schemaRegistryClient) {
		this.schemaRegistryClient = schemaRegistryClient;
	}

	@Override
	public Schema get(String subject) throws SchemaFetchException {
		try {
			var schemaMetdatadate = schemaRegistryClient.getLatestSchemaMetadata(subject);
			return new Schema(
					subject,
					schemaMetdatadate.getSchema());
		} catch (IOException | RestClientException e) {
			throw new SchemaFetchException("Failed to fetch schema metadata for subject '%s'".formatted(subject), e);
		}
	}
	
	public static SubjectSchemaFetcher create(SchemaRegistrySupplierParameters parameters) {
		return new SchemaRegistrySubjectSchemaFetcher(
				new SchemaRegistrySupplier().get(parameters));
	}

}
