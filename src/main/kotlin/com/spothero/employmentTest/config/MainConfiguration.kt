package com.spothero.employmentTest.config

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.annotation.EnableTransactionManagement


/**
 * I modeled this after the API I work with.  We explicitly define a lot of Beans in it.
 * I do not need to do this here.
 *
 * For this, I just wanted to identify the highest package for ComponentScan.
 * .
 */
@Configuration
@EnableTransactionManagement
@ComponentScan("com.spothero.employmentTest")
class MainConfiguration