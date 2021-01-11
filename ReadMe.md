 ## Getting Started

 ### Details
    - Application Name: PackageService
    - Jar file: packageservice-1.0-SNAPSHOT.jar
    - Tools: Java 8, Spring 5 Framework, SpringBoot2, Spring HATEOAS, Lombok, MySQL Spring Data Provider, SLF4j, Docker

### Prerequisites
 - Docker Desktop
 - docker-compose
 - Intellij or Eclipse (Optional)
 - If the docker is not present then MySQL needs to be installed locally or a remote MySQL's address
 needs to be provided in the application.properties.
    
 ### Constraints
 - System takes only package name as a mandatory field.
 - System will return bad request in the following scenarios.
    - A duplicate package name is already present while adding a new package.
    - Package name is null or empty.
    - Duration is less than zero.
    - Price is less than zero.
    - User can't update creation date or last modified date.
 
 ### APIs
 - http://localhost:8080/api/v1/packageservice/package
    - GET: will return all the packages in the system or http status 204 if there were no packages present.
    - POST: Adds a new package. Will return 400 bad in cases as described in the constraints section.
 - http://localhost:8080/api/v1/packageservice/package/{id}
    - GET: Gets a package with the given id. Will return 404 if the package is not present.
    - DELETE: Deletes a package with the given id.
    - Update: Updates a package with the given id.
 
 ### Payloads
 - POST: 
    {
        "name": "test",
        "price": 100,
        "active": "true",
        "description": "Test",
        "duration": 5
    }
 - PUT
    {
        seqId": 1,
        "name": "irfan",
        "price": 1,
        "active": false,
        "description": null,
        "duration": 1,
        "creationDate": "2020-11-30T00:53:25.752+00:00",
        "lastModifiedDate": null,
        "active": false
    }

### Response
- The responses are HATEOAS enabled.
{
    "seqId": 1,
    "name": "irfan",
    "price": 0,
    "active": false,
    "description": null,
    "duration": 0,
    "creationDate": "2020-11-30T00:58:27.345+00:00",
    "lastModifiedDate": null,
    "_links": {
        "packages": {
            "href": "http://localhost:8080/api/v1/packageservice/package"
        },
        "self": {
            "href": "http://localhost:8080/api/v1/packageservice/package/1"
        }
    }
}

### API Security
- The API IP white list map is present under /src/main/resources/whitelist.json
- The service can check a specific IP or a subnet defined like "172.x.x.x"
- The whitelist needs to be updated before running the service if it is not being called from localhost.
- Most probably the service won't work out of the box because the caller's IP or subnet won't be present in the whitelist. 

### Compilation
mvn clean package
or ./mvnw clean package

### Running
docker-compose up

### Known Issues
- Sometimes the service generates an exception when the database does not start before the service.
- This can be fixed by writing a script which can synchronize the service and database starts.
- Or database and service run via a different docker-compose.yml
