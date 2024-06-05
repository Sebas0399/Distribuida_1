plugins {
    id("java")
    id("application")
}

group = "com.distribuida"
version = "unspecified"

repositories {
    mavenCentral()

}

dependencies {
    implementation("org.jboss.weld.se:weld-se-core:5.1.2.Final")
    implementation("org.hibernate:hibernate-core:6.5.2.Final")
    implementation("com.h2database:h2:2.2.224")
    implementation("com.sparkjava:spark-core:2.9.4")
    // https://mvnrepository.com/artifact/com.google.code.gson/gson
    implementation("com.google.code.gson:gson:2.11.0")
// https://mvnrepository.com/artifact/org.projectlombok/lombok
    compileOnly("org.projectlombok:lombok:1.18.32")
    annotationProcessor("org.projectlombok:lombok:1.18.32")
    implementation("org.slf4j:slf4j-api:2.0.13")
    implementation("org.slf4j:slf4j-simple:2.0.13")


}

sourceSets {
    main {
        output.setResourcesDir( file("${buildDir}/classes/java/main"))
    }

}

tasks.jar {
    manifest {
        attributes(
                mapOf("Main-Class" to "com.distribuida.Main",
                        "Class-Path" to configurations.runtimeClasspath
                                .get()
                                .joinToString(separator = " ") { file ->
                                    "${file.name}"
                                })
        )
    }
}

tasks.test {
    useJUnitPlatform()
}