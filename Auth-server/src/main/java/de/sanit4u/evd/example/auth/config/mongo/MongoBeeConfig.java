package de.sanit4u.evd.example.auth.config.mongo;

import com.github.mongobee.Mongobee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
@DependsOn("mongoTemplate")
public class MongoBeeConfig {

	private static final String MONGODB_URL_FORMAT = "mongodb://%s:%s@%s:%d/%s?authMode=%s";
	private static final String MONGODB_CHANGELOGS_PACKAGE = "de.sanit4u.evd.example.auth.config.mongo.changelogs";

	@Autowired
	private MongoProperties mongoProperties;

	@Autowired
	private MongoTemplate mongoTemplate;

	@Bean
	public Mongobee mongobee() {
		// @formatter:off
        Mongobee runner = new Mongobee(String.format(MONGODB_URL_FORMAT,
                mongoProperties.getUsername(),
                mongoProperties.getPassword(),
                mongoProperties.getHost(),
                mongoProperties.getPort(),
                mongoProperties.getDatabase(), "scram-sha1"));
        // @formatter:on
		runner.setMongoTemplate(mongoTemplate);
		runner.setDbName(mongoProperties.getDatabase());
		runner.setChangeLogsScanPackage(MONGODB_CHANGELOGS_PACKAGE);
		return runner;
	}

}
