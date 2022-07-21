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
 - Minikube for kubernetes
 - Kubectl for kubernetes
    
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

### Compilation
mvn clean package
or ./mvnw clean package

### Running
#### Running on Docker locally without K8s
Method1:
```text
bash launchservices.sh
```
Method2:
```text
cd mysqlcontainer
docker-compose up

cd packageservicecontainer
docker-compose up
```

#### Running on K8s
```text
minikube start
cd src/main/deployments
kubectl apply -f mysql-volumes.yaml
kubectl apply -f mysql.yaml
kubectl apply -f packageservice.yaml
```

### Known Issues
- Sometimes the service generates an exception when the database does not start before the service.
- This can be fixed by writing a script which can synchronize the service and database starts.
- Or database and service run via a different docker-compose.yml

### Useful commands
```text
kubectl create namespace packageservice – to create a new namespace
kubectl config set-context --current --namespace=my-namespace – switch to namespace
kubectl run curlpod --image=curlimages/curl -i --tty – sh  - Create a new pod, run, and connect to its Shell
kubectl exec -it fci-common-ui-nodejs-778d6bc594-4z7d4 sh – Connect to shell of some pod with its name
Kubectl apply –f depoloyment.yaml
Kubectl get pods
Kubectl get deployments
Kubectl delete pod <pod-name>
Kubectl delete deployment <deployment-name>
curl -H "Content-Type: application/json" -X POST http://packageservice:8080/api/v1/packageservice/package -d '{ "name": "test2", "price": 100, "active": "true", "description": "Test", "duration": 5}' 
curl http://packageservice:8080/api/v1/packageservice/package
```