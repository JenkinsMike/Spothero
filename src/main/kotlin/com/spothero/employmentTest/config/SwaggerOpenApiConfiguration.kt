package com.spothero.employmentTest.config

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


/**
 * Extra Credit
 */
@OpenAPIDefinition
@Configuration
class SwaggerOpenApiConfiguration {
    @Bean
    fun customOpenAPI(
        @Value("\${application.version.complete}") appVersion: String?
    ): OpenAPI? {
        return OpenAPI()
            .info(
                Info()
                    .title("M. Jenkins | Employment Assignment")
                    .version(appVersion)
                    .description(
                        "The Application's main function is to allow for a user to enter a date " +
                                "time range and get back the price at which they would be charged to park for that time " +
                                "span.  \n\n" +
                                "The user can also modify rates and view rate info."
                    )
                    .contact(
                        Contact()
                            .name("Mike Jenkins")
                            .email("jenkins.mike@gmail.com?Subject=You're Hired!")
                    )
            )
    }
}