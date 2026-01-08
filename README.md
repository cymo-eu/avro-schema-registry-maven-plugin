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

## License Summary

### About the License

avro-schema-registry-maven-plugin is source-available software.

That means:

- the source code is public and transparent
- you can use and modify it internally
- you can evaluate it freely

But it is *not open source* in the OSI sense.

### What you ARE allowed to do

You may:

- use avro-schema-registry-maven-plugin inside your own organization
- run it in development, test, or production for internal use
- modify the code for your own needs
- evaluate it before deciding on a commercial license

### What you are NOT allowed to do (without a commercial license)

You may not:

- offer avro-schema-registry-maven-plugin as a SaaS or managed service
- resell it or repackage it commercially
- embed it in a commercial product
- provide it to customers as part of a paid service

If your use case involves any of the above, you need a commercial license from Cymo.

### Why this license?

We want to:

- be transparent about how the plugin works
- allow engineers to learn from and evaluate the software
- protect the plugin from being resold or offered as a competing service

This licensing model allows us to keep investing in the project while remaining open and honest with the community.

### Commercial licensing

If you want to:

- use avro-schema-registry-maven-plugin in a SaaS or managed offering
- resell or embed it
- become a partner

Please contact us at:

:e-mail: info@cymo.eu