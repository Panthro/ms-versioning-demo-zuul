# MS Versioning proposal

## Running

Run the applications in sequence:

1. [EurekaServer](src/main/java/ms/versioning/test/versioning/eureka/EurekaServer.java): Boots an eureka server at http://127.0.0.1:8761/ 
2. [VersioningMSApplication](src/main/java/ms/versioning/test/versioning/ms/VersioningMSApplication.java): Boots a microservice that connects to the Eureka with a simple get route: http://localhost:8081/route1 
3. [ZuulApplication](src/main/java/ms/versioning/test/versioning/zuul/ZuulApplication.java): Boots a Zuul server/proxy that loads the services from the Eureka server and maps, you can see the available routes at: http://localhost:8080/routes

```bash
# this will call the microservice directly
curl -v http://localhost:8081/route1


# this will output the available routes on zuul  
curl -v http://localhost:8080/routes 


# this will call the microservice through the zuul mapped route
curl -v http://localhost:8080/v1/versioning/route1

```


### How it works

The Zuul server has a `PatternServiceRouteMapper` bean that uses the name of the micro services registered on Eureka to determine the routes.


### Metadata version in eureka (Optional)

For convenience the version of the microservice is registered alongside with its metadata in eureka, in the class `VersionMapperPostProcessor`.
This version is not used in this demo, but could be used with Ribbon or even to track service usages in Zuul, but its out of the scope of this demo.