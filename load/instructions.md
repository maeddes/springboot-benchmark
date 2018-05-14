mvn clean install
java -jar target/load-0.0.1-SNAPSHOT.jar

will result in 300-400mb
will load up to 200mb without a change

java -jar -Xmx128m target/load-0.0.1-SNAPSHOT.jar
will result in ~200mb
breaks at allocation of 100mb
