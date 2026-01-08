package eu.cymo.domain.schema;

public interface SubjectSchemaFetcher {

	Schema get(String subject) throws SchemaFetchException;
	
}
