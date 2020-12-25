#!/bin/bash
set -e

source .env

kubectl apply -f common/tb-namespace.yml
kubectl config set-context $(kubectl config current-context) --namespace=thingsboard

kubectl apply -f $DEPLOYMENT_TYPE/thirdparty.yml


if [ "$DEPLOYMENT_TYPE" == "high-availability" ]; then
    echo -n "waiting for all redis pods to be ready";
    while [[ $(kubectl get pods tb-redis-5 -o 'jsonpath={..status.conditions[?(@.type=="Ready")].status}' 2>/dev/null) != "True" ]];
    do
      echo -n "." && sleep 5;
    done

    if [[ $(kubectl exec -it tb-redis-0 -- redis-cli cluster info 2>&1 | head -n 1) =~ "cluster_state:ok" ]]
    then
      echo "redis cluster is already configured"
    else
      echo "starting redis cluster"
      redisNodes=$(kubectl get pods -l app=tb-redis -o jsonpath='{range.items[*]}{.status.podIP}:6379 ')
      kubectl exec -it tb-redis-0 -- redis-cli --cluster create --cluster-replicas 1 $redisNodes
    fi

fi

