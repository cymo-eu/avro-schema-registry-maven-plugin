package eu.cymo;

import java.io.File;
import java.util.List;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import eu.cymo.adapters.avro.AvroSchemaCompiler;
import eu.cymo.adapters.schemaregistry.SchemaRegistrySubjectSchemaFetcher;
import eu.cymo.adapters.schemaregistry.SchemaRegistrySupplierParameters;
import eu.cymo.domain.GenerateAvroClasses;
import eu.cymo.domain.GenerateAvroClassesParameters;

@Mojo(
		name = "generate",
		defaultPhase = LifecyclePhase.GENERATE_SOURCES)
public class GenerateMojo extends AbstractMojo {
	
	@Parameter(
			property = "schemaRegistryUrl",
			required = true)
	private String schemaRegistryUrl;

	@Parameter(
			property = "schemaRegistryBasicAuthUserInfo",
			required = false)
	private String schemaRegistryBasicAuthUserInfo;

    @Parameter(
    		defaultValue = "${project}",
    		readonly = true)
    private MavenProject project;

    @Parameter(
    		required = true)
    private List<String> subjects;
    
    @Parameter(
    		property = "outputDirectory",
            defaultValue = "${project.build.directory}/generated-sources/avro")
        private File outputDirectory;

	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		// prepare dependencies
		var subjectSchemaFetcher = SchemaRegistrySubjectSchemaFetcher.create(new SchemaRegistrySupplierParameters(
				schemaRegistryUrl,
				schemaRegistryBasicAuthUserInfo));
		var schemaCompiler = AvroSchemaCompiler.create(
				outputDirectory);
		
		// setup usecase
		var generateAvroClasses = new GenerateAvroClasses(
				project,
				getLog(),
				subjectSchemaFetcher,
				schemaCompiler);
		
		// execute
		generateAvroClasses.execute(new GenerateAvroClassesParameters(
				subjects,
				outputDirectory));
	}

}
