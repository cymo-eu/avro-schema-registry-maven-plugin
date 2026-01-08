# Avro Schema Registry Maven Plugin

Generate Avro dtos straight from the Schema Registry. Instead of importing avdl/avsc files, you can configure this maven plugin to fetch configured subjects from the Schema Registry and generate the java classes for you.

## Usage

```
	<build>
		<plugins>
			<plugin>
				<groupId>eu.cymo</groupId>
				<artifactId>avro-schema-registry-maven-plugin</artifactId>
				<version>${avro-schema-registry-maven-plugin.version}</version>
				<executions>
					<execution>
						<goals>
							<goal>generate</goal>
						</goals>
						<phase>generate-sources</phase>
					</execution>
				</executions>
				<configuration>
					<schemaRegistryUrl>http://localhost:8081</schemaRegistryUrl>
					<schemaRegistryBasicAuthUserInfo>key:secret</schemaRegistryBasicAuthUserInfo>
					<subjects>
						<subject>subject-name-1</subject>
						<subject>subject-name-2</subject>
					</subjects>
				</configuration>
			</plugin>
		</plugins>
	</build>
```

### Configuration Properties

| Name                            | Required | Value                                                       |
|---------------------------------|----------|-------------------------------------------------------------|
| schemaRegistryUrl               | true     | Base url to the schema registry                             |
| schemaRegistryBasicAuthUserInfo | false    | Plain text value of the basic authentication key:secret     |
| subjects                        | true     | List of subjects for which a java class should be generated |