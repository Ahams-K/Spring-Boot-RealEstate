plugins {
    id("java")
    id("application")
    id("org.springframework.boot") version "3.1.0" // Add Spring Boot Plugin
    id("io.spring.dependency-management") version "1.1.0" // Add Spring Dependency Management Plugin
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

application{
    mainClass = "be.kdg.prog3.RealEstateServiceSystem.Main"
}

dependencies {
    implementation("org.webjars:bootstrap:5.3.3")
    implementation("org.webjars:webjars-locator-core:0.48")
    implementation("org.springframework.boot:spring-boot-starter") // Basic Spring Boot starter
    implementation("org.springframework.boot:spring-boot-starter-web") // For building web apps
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.projectlombok:lombok:1.18.34")
    implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    annotationProcessor("org.projectlombok:lombok:1.18.34")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    runtimeOnly("org.postgresql:postgresql")
    runtimeOnly("com.h2database:h2")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("com.google.code.gson:gson:2.11.0")
}

tasks.test {
    useJUnitPlatform()
}

tasks.getByName("run", JavaExec::class) {
    standardInput = System.`in`
}

tasks.getByName("bootRun", JavaExec::class){
    standardInput = System.`in`
}

tasks.getByName("bootTestRun", JavaExec::class){
    standardInput = System.`in`
}

