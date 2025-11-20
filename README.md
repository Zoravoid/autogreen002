Commands for Application Usage

# backend

To run the backend select the MyLinksSpringBootApplication.java and run from your IDE interface.

Once its up and running you can access swagger UI to test APIs at:
- http://localhost:8080/swagger-ui.html

To compile the backend into a Jar file run:
- mvn clean install -DskipTests

# database/ dockercompose

to create a docker run the services in the docker compose (only works if the image is built or isnt a local image)

Running it directly from docker compose only works for the mysql and timescale services. Frontend and backend are custom services and have to be built first 
To built a custom docker image of the frontend and backend follow these steps

## Frontend

make sure you are in the frontend/autogreen directory with
- cd frontend/autogreen

and just to make sure a dockerfile exists either check with the UI or run:
- ls
here a dockerfile should be listed.

after we have confirmed its existence we run: 
- docker build -t zoravoid/autogreen-frontend:01 .

after that you can proceed to the dockercompose and run the frontend service and it will automatically build and configure the frontend container. 

## Backend

make sure you are in the backend/mylinks-spring-boot-main directory with
- cd backend/mylinks-spring-boot-main

and just to make sure a dockerfile exists either check with the UI or run:
- ls
here a dockerfile should be listed.

after we have confirmed its existence we run: 
- docker build -t zoravoid/autogreen-backend:01 .

after that you can proceed to the dockercompose and run the frontend service and it will automatically build and configure the backend container. 

## commands for the mysql docker:

To interact with the database while the docker is running use:
- docker exec -it test_mysql bash
Then to log in use:
- mysql -u root -p
Add the password
- autogreen1234
And now you are inside the database, able to edit it. 

To display existing databases or tables run:
- SHOW DATABASES;

To edit a database first switch to it with:
- USE database_name;
Then you can display existing tables with:
- SHOW TABLES;

To add test data to the database ive prepared a file where you can copy and paste the contents into the database as is.
The file is at /database/dummy_data.txt
Currently the dummy data is only configured for the moisture table so if you want to configure it to another table edit the 
- table_name 
- table_name_val 
values to configure it for other tables

# frontend

To start the Application make sure you are in the frontend/autogreen directory with:
- cd frontend/autogreen

And then start the Next.js frontend with
- npm run dev

# kubernetes

To create a Minikube (a test cluster) or to restart an existing one run:
- minikube start

To apply a deployment as specified in the yaml files run:
- kubectl apply -f file_name.yaml

IMPORTANT when deploying components do it one by one for each yaml starting with the config and secret files, then the database and then the frontend

You can check the status of the cluster by running:
- minikube status

Check the nodes
- kubectl get nodes

Check pods (the -A flag is to check all existing pods but the command also works without)
- kubectl get pods -A

Get information on components with:
- kubectl describe type_of_component name_of_component

Get the logs of the components with (add -f flag at the end to stream the logs):
- kubectl logs name_of_pod

Get service with:
- kubectl get svc

Get cluster IP with:
- minikube ip

Access the web application in the browser with (NodePort is defined in the frontend.yaml):
- minikube_ip:NodePort

Stop the cluster safely with:
- mimikube stop