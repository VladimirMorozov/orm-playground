package me.vmorozov.orm.playground.jooq.config;

import org.simpleflatmapper.jooq.SfmRecordMapperProvider;
import org.simpleflatmapper.jooq.SfmRecordMapperProviderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JooqConfig {

    /**
     * Auto-injected into jooq's config by spring-boot-starter-jooq auto-configuration
     * Allows auto-mapping of one to one structures. Does not support collections, use SFM with ResultSets for that
     */
    @Bean
    public SfmRecordMapperProvider sfmRecordMapperProvider() {
        return SfmRecordMapperProviderFactory.newInstance().newProvider();
    }

}
