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

docker run -d -p 8081:8080 load:v0.1

docker stats

CONTAINER ID        NAME                CPU %               MEM USAGE / LIMIT     MEM %               NET I/O             BLOCK I/O           PIDS
fdcd36522481        suspicious_spence   0.21%               326.3MiB / 5.818GiB   5.48%               1.44kB / 0B         56.2MB / 0B         32

does not increase with increasing load

# kubernetes

same
