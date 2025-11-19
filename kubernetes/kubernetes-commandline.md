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