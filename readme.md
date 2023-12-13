Build the project:
mvn clean package -DskipTests=true

docker build -t backend:latest . 

docker-compose up