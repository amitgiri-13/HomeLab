# Kubectl Commands

---

##  Cluster Info

```bash
kubectl cluster-info
kubectl version
kubectl config view
kubectl config current-context
kubectl config get-contexts
kubectl config use-context <context>
```

---

##  Get Resources

```bash
kubectl get pods
kubectl get services
kubectl get deployments
kubectl get nodes
kubectl get namespaces
kubectl get all

kubectl get pods -o wide
kubectl get pods -o yaml
kubectl get pods -o json

kubectl get pods -n <namespace>
kubectl get pods --all-namespaces
```

---

##  Describe (Detailed Info)

```bash
kubectl describe pod <pod-name>
kubectl describe node <node-name>
kubectl describe deployment <deployment-name>
kubectl describe service <service-name>
```

---

##  Create Resources

```bash
kubectl create -f file.yaml
kubectl apply -f file.yaml

kubectl create deployment myapp --image=nginx
kubectl create namespace mynamespace
kubectl create service clusterip mysvc --tcp=80:80
```

---

##  Edit Resources

```bash
kubectl edit pod <pod-name>
kubectl edit deployment <deployment-name>
kubectl edit svc <service-name>
```

---

##  Delete Resources

```bash
kubectl delete pod <pod-name>
kubectl delete deployment <deployment-name>
kubectl delete service <service-name>
kubectl delete namespace <namespace>

kubectl delete -f file.yaml
kubectl delete all --all
```

---

##  Update & Scale

```bash
kubectl scale deployment myapp --replicas=3
kubectl rollout status deployment myapp
kubectl rollout history deployment myapp

kubectl rollout undo deployment myapp
kubectl rollout restart deployment myapp
```

---

##  Logs & Debugging

```bash
kubectl logs <pod-name>
kubectl logs -f <pod-name>

kubectl logs <pod-name> -c <container-name>

kubectl events
kubectl get events
```

---

##  Exec into Pod

```bash
kubectl exec -it <pod-name> -- /bin/bash
kubectl exec -it <pod-name> -- /bin/sh
```

---

##  Copy Files

```bash
kubectl cp <pod-name>:/path/file /local/path
kubectl cp /local/file <pod-name>:/path
```

---

##  Services & Networking

```bash
kubectl expose deployment myapp --type=NodePort --port=80

kubectl port-forward pod/<pod-name> 8080:80
kubectl port-forward service/<service-name> 8080:80
```

---

##  Resource Usage

```bash
kubectl top nodes
kubectl top pods
```

---

##  Namespaces

```bash
kubectl create namespace dev
kubectl get namespaces
kubectl config set-context --current --namespace=dev
```

---

##  ConfigMaps & Secrets

```bash
kubectl create configmap myconfig --from-literal=key=value
kubectl get configmaps

kubectl create secret generic mysecret --from-literal=password=1234
kubectl get secrets
```

---

##  Apply & Diff

```bash
kubectl apply -f file.yaml
kubectl apply -k ./kustomize/

kubectl diff -f file.yaml
```

---

##  Labels & Selectors

```bash
kubectl label pod <pod-name> env=prod
kubectl get pods -l env=prod
```

---

##  Taints & Tolerations (Nodes)

```bash
kubectl taint nodes <node-name> key=value:NoSchedule
kubectl describe node <node-name>
```

---

##  API Resources

```bash
kubectl api-resources
kubectl api-versions
kubectl explain pod
kubectl explain deployment.spec
```

---

##  Dry Run & Generate YAML

```bash
kubectl create deployment myapp --image=nginx --dry-run=client -o yaml
```

---

## Watch Changes

```bash
kubectl get pods -w
```

---

##  Patch Resources

```bash
kubectl patch deployment myapp -p '{"spec":{"replicas":5}}'
```

---

##  Context Shortcuts (Power Users)

```bash
alias k=kubectl
k get pods
k apply -f app.yaml
```

---

##  Cleanup

```bash
kubectl delete all --all
kubectl delete namespace <namespace>
```

---

##  Pro Tips

```bash
# Auto-completion (bash)
source <(kubectl completion bash)

# Short names
kubectl get po     # pods
kubectl get svc    # services
kubectl get deploy # deployments
```

---

##  Help

```bash
kubectl --help
kubectl get --help
kubectl explain <resource>
```

---

##  Most Important Real-World Combo

```bash
kubectl apply -f app.yaml
kubectl get pods
kubectl describe pod <pod>
kubectl logs <pod>
kubectl exec -it <pod> -- sh
```

---

