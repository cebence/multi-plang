# Multiple programming languages in the same Maven project (kind of)

The "multi-plang" project is a "Hello World" type multi-module Maven project with two "essential" modules
- API and application - and a few optional "extender" modules.

`multi-plang-api` is the API module hosting application abstractions:

- `Greeter` - an entity that can say something to the world,
- `Microphone` - is the way for a greeter to do its thing.

`multi-plang-app` is the executable application module hosting the "main(args)" entry point, setting up
the world stage (plugging in the microphone), enumerating all `Greeter` implementations (through Java's `ServiceLoader`) and letting them come up to the stage and have a go at the microphone.

These two modules are self-sufficient, i.e. the application works and prints out a greeting from one
(default) `Greeter` implementation (inside the application module itself).
And are also written in pure Java.

Other modules are `Greeter` implementations in other Java-like programming languages, i.e. those that
compile into a JVM-compatible binaries.

`multi-plang-groovy` module is hosting a `Greeter` implemented in, wait for it... Groovy :smile:
This module has just one `*.groovy` file and gets packaged into a JAR for deployment
into the application. Maven is configured to use the Groovy compiler, everything else is basically
just as same as in a Java project.

Similarly to the Groovy module, `multi-plang-kotlin` and `multi-plang-scala` are implemented in Kotlin and Scala, and are quite easy to setup to produce a JAR file.

- [ ] Could also add a Clojure implementation (`multi-plang-clojure`).


## Build

This project is JDK 8 compatible, but requires a JDK 17 to compile due to one of the compiler plugins, i.e.
the `JAVA_HOME` should point to JDK 17.

```
REM set JAVA_HOME=d:\java-tools\openjdk-17.0.1_12-x64
mvn clean package
```


## Run / Deployment

To run the demo type in the following in the command-line / terminal:

```
java -jar multi-plang-app-1.0-SNAPSHOT.jar
```

One special notice here - the `multi-plang-app` module is made aware of all the "extension" modules
at compile-time for a simple reason - ease of use for this demo. Otherwise, running the demo application
would involve shell scripts to copy all other JARs (the API and extension JARs, including transitive
dependencies - e.g. Kotlin stdlib) into the same folder as the application JAR (i.e. `multi-plang-app/target`)
and construct the `CLASSPATH` environment variable to include all the JARs... or make the developers do all that manually :smile:

For that purpose the `multi-plang-app/pom.xml` lists the extension plugins as dependencies even though
none of the extension code is referenced in the `multi-plang-app` code.

The `maven-dependency-plugin` copies all dependencies to the `multi-plang-app/target/libs` folder,
and the `maven-jar-plugin` lists them in the `META-INF/MANIFEST.MF`'s `Class-Path` entry.

```
Manifest-Version: 1.0
Created-By: Maven JAR Plugin 3.2.2
Build-Jdk-Spec: 17
Class-Path: libs/multi-plang-api-1.0-SNAPSHOT.jar libs/multi-plang-groov
 y-1.0-SNAPSHOT.jar libs/groovy-3.0.9-indy.jar libs/multi-plang-kotlin-1
 .0-SNAPSHOT.jar libs/kotlin-stdlib-1.6.10.jar libs/annotations-13.0.jar
  libs/kotlin-stdlib-common-1.6.10.jar libs/multi-plang-scala-1.0-SNAPSH
 OT.jar libs/scala3-library_3-3.1.1.jar libs/scala-library-2.13.6.jar
Main-Class: cebence.learning.multiplang.app.Main
```

In a test/production environment where an application is being "deployed" - e.g. a WAR file -
all these JARs would have been dynamically included in the classpath (by the container, e.g. Apache Tomcat),
so the classpath entry in the manifest would not be needed.

> Obviously, for the extension JARs to be packaged into a WAR file we have to list them as dependencies.
> For example. if this demo was a webapp, and the `multi-plang-app` was not executable but contained
> "business logic", we would have e.g. `multi-plang-webapp` module of "type" `war` that would include
> all other JARs as dependencies and that's it.


## Bonus

I would also like for you to notice the beauty of the `ServiceLoader`. Extension JARs are being
located/loaded at run-time, there is no need to list them as dependencies in the application code.
By simply deploying a JAR file with a proper `META-INF/services` configuration the application is
able to locate previously unknown code.
