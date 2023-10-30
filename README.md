# Sample Springboot Microservice (Java)
- Pattern: Microservice
- Gradle kts version 8.0
- Service:
  * authservice: include login and register method, port 8081
  * common: include models, repositories of all services & configuration of spring security
  * gateway: config port of all services in 8080
# Setup:
Download and open Docker desktop, then run these command in terminal to create a postgre database in Docker:
  * docker pull postgres
  * docker run -d -p 5432:5432 --name my-postgres-container -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=mydatabase postgres
  
    



