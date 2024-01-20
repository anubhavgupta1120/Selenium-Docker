mvn clean package -DskipTests
cp -R GlobalData target/docker-resources
cp -R TestData target/docker-resources