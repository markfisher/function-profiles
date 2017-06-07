Build:

`./mvnw clean install`

Running with no profile will show null for "greeter", "uppercase", and "lowercase" (as expected):

```
java -jar runner/target/runner-0.0.1-SNAPSHOT.jar
...
greeter:   null
uppercase: null
lowercase: null
...
```

Running with local Function-scanning should find the "Greeter" Function,
but it fails with a NPE in the FunctionCatalog:

```
java -jar runner/target/runner-0.0.1-SNAPSHOT.jar --spring.cloud.function.scan.packages=fn.local
...
Caused by: java.lang.NullPointerException: null
	at org.springframework.util.ReflectionUtils.makeAccessible(ReflectionUtils.java:442) ~[spring-core-4.3.8.RELEASE.jar:4.3.8.RELEASE]
	at org.springframework.cloud.function.context.ContextFunctionCatalogAutoConfiguration$ContextFunctionPostProcessor.getField(ContextFunctionCatalogAutoConfiguration.java:493) ~[spring-cloud-function-context-1.0.0.BUILD-SNAPSHOT.jar:1.0.0.BUILD-SNAPSHOT]
...
```

(NOTE: the NPE does not occur if no scanned Functions are discovered, e.g. using a bogus scan package name)

The following show the classpath as expected (with the corresponding lowercase or uppercase JAR):

```
java -jar runner/target/runner-0.0.1-SNAPSHOT.jar --thin.classpath --thin.profile=lowercase
java -jar runner/target/runner-0.0.1-SNAPSHOT.jar --thin.classpath --thin.profile=uppercase
```

But running either without `--thin.classpath` will produce the same NPE as mentioned above.

```
java -jar runner/target/runner-0.0.1-SNAPSHOT.jar --thin.profile=uppercase
...
Caused by: java.lang.NullPointerException: null
	at org.springframework.util.ReflectionUtils.makeAccessible(ReflectionUtils.java:442) ~[spring-core-4.3.8.RELEASE.jar:4.3.8.RELEASE]
	at org.springframework.cloud.function.context.ContextFunctionCatalogAutoConfiguration$ContextFunctionPostProcessor.getField(ContextFunctionCatalogAutoConfiguration.java:493) ~[spring-cloud-function-context-1.0.0.BUILD-SNAPSHOT.jar:1.0.0.BUILD-SNAPSHOT]
...
```
