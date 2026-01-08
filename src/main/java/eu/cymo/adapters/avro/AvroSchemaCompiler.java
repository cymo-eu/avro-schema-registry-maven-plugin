package eu.cymo.adapters.avro;

import java.io.File;
import java.io.IOException;

import org.apache.avro.compiler.specific.SpecificCompiler;

import eu.cymo.domain.schema.Schema;
import eu.cymo.domain.schema.SchemaCompilationException;
import eu.cymo.domain.schema.SchemaCompiler;

public class AvroSchemaCompiler implements SchemaCompiler {
	
	private final File outputDirectory;

	public AvroSchemaCompiler(File outputDirectory) {
		this.outputDirectory = outputDirectory;
	}

	@Override
	public void compile(Schema schema) throws SchemaCompilationException {
		var avroSchema = new org.apache.avro.Schema.Parser().parse(schema.value());
		try {
			var compiler = new SpecificCompiler(avroSchema);
			compiler.compileToDestination(null, outputDirectory);
		} catch (IOException e) {
			throw new SchemaCompilationException("Failed to compile schema '%s' to destination".formatted(avroSchema.getName()), e);
		}
	}

	public static SchemaCompiler create(File outputDirectory) {
		return new AvroSchemaCompiler(outputDirectory);
	}
	
}
