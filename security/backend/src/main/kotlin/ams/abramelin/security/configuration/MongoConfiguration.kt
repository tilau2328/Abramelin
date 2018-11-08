package ams.abramelin.security.configuration

import com.mongodb.MongoClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.core.MongoTemplate

@Configuration
class MongoConfiguration {
    @Bean
    fun mongo(): MongoClient {
        return MongoClient("localhost")
    }

    @Bean
    @Throws(Exception::class)
    fun mongoTemplate(): MongoTemplate {
        return MongoTemplate(mongo(), "abramelin-auth")
    }
}
