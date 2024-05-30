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
    //implementation(project(":ejemplo01_cdi"))
// https://mvnrepository.com/artifact/org.hibernate/hibernate-core
    implementation("org.hibernate:hibernate-core:6.5.2.Final")
// https://mvnrepository.com/artifact/com.h2database/h2
    implementation("com.h2database:h2:2.2.224")

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