package de.sanit4u.evd.example.auth.config.mongo;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "de.sanit4u.evd.example.auth.repository")
public class MongoConfig {

}
