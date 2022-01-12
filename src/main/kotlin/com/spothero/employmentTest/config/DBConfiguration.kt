package com.spothero.employmentTest.config

import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement


/**
 * I modeled this after the API I work with.  We explicitly define a lot of Beans in it.
 * I do not need to do this here.
 *
 * For this, I just wanted to identify the base config package and the repo.
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    "com.spothero.employmentTest.repository",
    basePackages = ["com.spothero.employmentTest.config"]
)
class DBConfiguration