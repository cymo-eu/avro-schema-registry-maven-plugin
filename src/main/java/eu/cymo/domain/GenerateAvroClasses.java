package eu.cymo.domain;

import java.io.File;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.project.MavenProject;

import eu.cymo.domain.schema.Schema;
import eu.cymo.domain.schema.SchemaCompilationException;
import eu.cymo.domain.schema.SchemaCompiler;
import eu.cymo.domain.schema.SchemaFetchException;
import eu.cymo.domain.schema.SubjectSchemaFetcher;

public class GenerateAvroClasses {

	private final MavenProject project;
	private final Log log;
	private final SubjectSchemaFetcher subjectSchemaFetcher;
	private final SchemaCompiler schemaCompiler;
	
	public GenerateAvroClasses(
			MavenProject project,
			Log log,
			SubjectSchemaFetcher subjectSchemaFetcher,
			SchemaCompiler schemaCompiler) {
		this.project = project;
		this.log = log;
		this.subjectSchemaFetcher = subjectSchemaFetcher;
		this.schemaCompiler = schemaCompiler;
	}
	
	public void execute(GenerateAvroClassesParameters parameters) throws MojoExecutionException, MojoFailureException {
		log.info("Generating avro objects");
		
		var subjects = parameters.subjects();
		var outputDirectory = parameters.outputDirectory();
		
		outputDirectory.mkdirs();
		
		for(var subject : subjects) {
			processSubject(outputDirectory, subject);
		}
		
		project.addCompileSourceRoot(outputDirectory.getAbsolutePath());
	}
	
	void processSubject(File outputDirectory, String subject) throws MojoExecutionException, MojoFailureException {
		log.info("Processing subject '%s'".formatted(subject));
		compile(schema(subject));
	}
	
	Schema schema(String subject) throws MojoExecutionException, MojoFailureException {
		try {
			return subjectSchemaFetcher.get(subject);
		}
		catch (SchemaFetchException e) {
			throw new MojoFailureException("Failed to fetch schema metadata for subject '%s'".formatted(subject), e);
		}
		
	}
	
	void compile(Schema schema) throws MojoExecutionException, MojoFailureException {
		try {
			schemaCompiler.compile(schema);
		} catch (SchemaCompilationException e) {
			throw new MojoExecutionException("Failed to compile subject '%s'".formatted(schema.subject()), e);
		}
	}
}
