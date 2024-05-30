plugins {
    id("java")
}

group = "com.distribuida"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    // https://mvnrepository.com/artifact/org.jboss.weld.se/weld-se-core
    implementation("org.jboss.weld.se:weld-se-core:5.1.2.Final")

}
sourceSets{
    main{
        java{
            output.setResourcesDir(file("${buildDir}/classes/java/main"))
        }
    }
}
tasks.test {
    useJUnitPlatform()
}