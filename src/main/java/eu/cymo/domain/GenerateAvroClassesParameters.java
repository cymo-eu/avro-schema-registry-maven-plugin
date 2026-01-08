package eu.cymo.domain;

import java.io.File;
import java.util.List;

public record GenerateAvroClassesParameters(
		List<String> subjects,
		File outputDirectory) {

}
