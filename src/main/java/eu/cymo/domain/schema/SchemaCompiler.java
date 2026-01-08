package eu.cymo.domain.schema;

public interface SchemaCompiler {

	void compile(Schema schema) throws SchemaCompilationException;
	
}
