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

Stop the cluster safely with:
- mimikube stop