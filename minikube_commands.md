# Minikube Commands

##  Start & Stop Cluster

```bash
minikube start
minikube start --driver=docker
minikube start --cpus=4 --memory=8192

minikube stop
minikube delete
minikube pause
minikube unpause
```

---

##  Cluster Info & Status

```bash
minikube status
minikube ip
minikube dashboard
minikube dashboard --url
minikube kubectl -- get nodes
```

---

##  Configuration

```bash
minikube config set memory 4096
minikube config set cpus 2
minikube config get memory
minikube config view
minikube config unset memory
```

---

##  Addons Management

```bash
minikube addons list
minikube addons enable ingress
minikube addons disable ingress

minikube addons enable metrics-server
minikube addons enable dashboard
```

---

##  Services & Networking

```bash
minikube service <service-name>
minikube service <service-name> --url

minikube tunnel
```

---

##  File Copy (Host ↔ Cluster)

```bash
minikube cp <src> <target>
```

---

##  SSH into Minikube

```bash
minikube ssh
```

---

##  Logs & Debugging

```bash
minikube logs
minikube logs --file=logs.txt

minikube status
minikube update-context
```

---

##  Image Management

```bash
minikube image load <image-name>
minikube image build -t my-image .
minikube image ls
minikube image rm <image-name>
```

---

##  Kubernetes Access (kubectl via minikube)

```bash
minikube kubectl -- get pods
minikube kubectl -- get svc
minikube kubectl -- get all
```

---

##  Mount Local Directory

```bash
minikube mount /host/path:/container/path
```

---

##  Profiles (Multiple Clusters)

```bash
minikube profile list
minikube start -p mycluster
minikube stop -p mycluster
minikube delete -p mycluster
```

---

##  Certificates & Context

```bash
minikube update-context
minikube delete --all
```

---

##  Resource Monitoring

```bash
minikube addons enable metrics-server
kubectl top nodes
kubectl top pods
```

---

##  Advanced / Useful Flags

```bash
minikube start --kubernetes-version=v1.30.0
minikube start --container-runtime=containerd
minikube start --disk-size=20g
```

---

##  Cleanup

```bash
minikube delete
minikube delete --all --purge
```

---

##  Pro Tips (Important)

```bash
# Use minikube docker daemon
eval $(minikube docker-env)

# Or newer way
minikube image build -t my-app .
```

---

##  Help

```bash
minikube help
minikube <command> --help
```

---