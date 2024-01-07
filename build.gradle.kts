import com.ewerk.gradle.plugins.tasks.QuerydslCompile

plugins {
    java
    id("org.springframework.boot") version "2.7.8"
    id("io.spring.dependency-management") version "1.1.4"
    id("com.ewerk.gradle.plugins.querydsl") version "1.0.10"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    //spring
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")

    developmentOnly("org.springframework.boot:spring-boot-devtools")

    //swagger
    implementation("org.springdoc:springdoc-openapi-ui:1.7.0")

    //h2
    runtimeOnly("com.h2database:h2")
    runtimeOnly("com.mysql:mysql-connector-j")

    //lombok
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    //problem
    implementation("org.zalando:problem-spring-web-starter:0.27.0")

    //test
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    //querydsl
    implementation("com.querydsl:querydsl-jpa")
    implementation ("com.querydsl:querydsl-core")
    implementation ("com.querydsl:querydsl-collections")
    annotationProcessor ("com.querydsl:querydsl-apt:5.0.0")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

val querydslDir = layout.buildDirectory.dir("generated/querydsl").get().asFile

tasks.withType<JavaCompile> {
    options.generatedSourceOutputDirectory.set(file(querydslDir))
}

querydsl {
    jpa = true
    querydslSourcesDir = querydslDir.name
}

sourceSets.getByName("main") {
    java.srcDir(querydslDir)
}

configurations {
    named("querydsl") {
        extendsFrom(configurations.compileClasspath.get())
    }
}

tasks.withType<QuerydslCompile> {
    options.annotationProcessorPath = configurations.querydsl.get()
}

// clean 이후에 querydsl 폴더를 지움
tasks.named("clean") {
    doLast {
        file(querydslDir).deleteRecursively()
    }
}
