package cz.tul.Config;

import cz.tul.data.Measurement;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.UncategorizedMongoDbException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;

@Configuration
public class MongoConfig implements InitializingBean {
    private static final String TTL_INDEX = "TTL_INDEX";
    private static final long SECONDS_IN_14_DAYS = 60*60*24*14;

    private final MongoTemplate mongoTemplate;

    public MongoConfig(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void afterPropertiesSet() {
        try {
            mongoTemplate.indexOps(Measurement.class).ensureIndex(new Index().on(Measurement.DATE, Sort.Direction.ASC).expire(SECONDS_IN_14_DAYS).named(TTL_INDEX));
        } catch (UncategorizedMongoDbException e) {
            mongoTemplate.indexOps(Measurement.class).dropIndex(TTL_INDEX);
            mongoTemplate.indexOps(Measurement.class).ensureIndex(new Index().on(Measurement.DATE, Sort.Direction.ASC).expire(SECONDS_IN_14_DAYS).named(TTL_INDEX));
        }
    }
}