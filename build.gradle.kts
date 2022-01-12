import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("java")
	id("org.springframework.boot") version "2.5.6"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	id("org.asciidoctor.convert") version "1.5.8"
	kotlin("jvm") version "1.5.31"
	kotlin("plugin.spring") version "1.5.31"
	kotlin("plugin.jpa") version "1.5.31"
	kotlin("plugin.serialization") version "1.5.31"
	id("maven-publish")
}

group = "com.spothero"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenLocal()
	mavenCentral()
	gradlePluginPortal()
}

extra["snippetsDir"] = file("build/generated-snippets")
extra["springCloudVersion"] = "2020.0.4"

/**
 * There are probably a half-dozen dependencies that I do not need to include below.
 * I qm still learning how to edit dependencies.  I am still learning gradle.kts files.
 */
dependencies {
	implementation("org.springframework.boot:spring-boot-starter:2.5.6")
	implementation("org.springframework.boot:spring-boot-starter-web:2.5.6")
	implementation("org.springframework.boot:spring-boot-starter-actuator:2.5.6")
	//implementation("org.springframework.data:spring-data-rest-hal-browser:3.3.9.RELEASE")
	implementation("org.springframework.data:spring-data-rest-hal-explorer:3.5.4")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa:2.5.6")
	implementation("org.springframework.boot:spring-boot-starter-data-rest:2.5.6")
	implementation("org.springframework.boot:spring-boot-starter-validation:2.5.6")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.0")
	implementation("org.flywaydb:flyway-core:8.0.2")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.springframework.cloud:spring-cloud-starter-sleuth:3.0.4")
	implementation("org.springdoc:springdoc-openapi-ui:1.5.12")
	implementation("org.apache.httpcomponents:httpclient:4.5.13")
	implementation("joda-time:joda-time:2.10.13")
	implementation("org.springframework.boot:spring-boot-gradle-plugin:2.5.6")
	implementation("com.github.kittinunf.result:result:5.2.0")
	implementation("junit:junit:4.13.2")
    implementation("org.junit.jupiter:junit-jupiter:5.8.1")
    developmentOnly("org.springframework.boot:spring-boot-devtools:2.5.6")
	runtimeOnly("com.h2database:h2:1.4.200")
	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor:2.5.6")
	testImplementation("org.springframework.boot:spring-boot-starter-test:2.5.6") {
		exclude(group = "org.junit.vintage", module = "junit.vintage.engine")
	}
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc:2.0.5.RELEASE")
	testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
	testImplementation("org.junit.vintage:junit-vintage-engine:5.8.1")
	testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0")
	testImplementation("net.wuerl.kotlin:assertj-core-kotlin:0.2.1")
}

dependencyManagement {
	imports {
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
	}
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
	testLogging {
		events("passed", "skipped", "failed")
	}
}

/**
 * Doc'd out because "snippetsDir" throws an error and this is one of the things i have not learned yet...
 * asciiDoc in build.gradle.kts files
 */
//tasks.test {
//	outputs.dir(snippetsDir)
//}
//
//tasks.asciidoctor {
//	inputs.dir(snippetsDir)
//	dependsOn(test)
//}
