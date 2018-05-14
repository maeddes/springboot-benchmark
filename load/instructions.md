# plain

mvn clean install
java -jar target/load-0.0.1-SNAPSHOT.jar

- will result in 300-400mb
- will load up to 200mb without a big change

java -jar -Xmx128m target/load-0.0.1-SNAPSHOT.jar
- will result in ~200mb
- breaks at allocation of 100mb

java -jar -Xmx256m target/load-0.0.1-SNAPSHOT.jar
- will result in ~300mb
- does 100mb well and goes up to ~400mb
- does 150mb well and goes up to ~450mb
- breaks at allocation of 200mb

# container
