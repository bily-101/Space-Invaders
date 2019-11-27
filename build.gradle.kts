buildscript {
    repositories {
        mavenCentral()
        jcenter()
    }

    dependencies {
        classpath("org.junit.platform:junit-platform-gradle-plugin:1.0.1")
    }
}

plugins {
    kotlin("jvm") version "1.3.50"
}

repositories {
    jcenter()
    maven(url="https://dl.bintray.com/anysolo/edu")
}

dependencies {
    compile(gradleApi())
    compile(kotlin("stdlib"))
    compile(kotlin("stdlib-jdk8"))
    compile(kotlin("reflect"))
    compile("com.anysolo:toyGraphics:0.8.6.4")
}
