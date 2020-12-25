#!/bin/bash
set -e

source .env

kubectl apply -f common/tb-namespace.yml
kubectl config set-context $(kubectl config current-context) --namespace=thingsboard
kubectl apply -f common/tb-node-configmap.yml
kubectl apply -f common/tb-mqtt-transport-configmap.yml
kubectl apply -f common/tb-http-transport-configmap.yml
kubectl apply -f common/tb-coap-transport-configmap.yml
kubectl apply -f common/thingsboard.yml
kubectl apply -f $DEPLOYMENT_TYPE/tb-node-cache-configmap.yml
kubectl apply -f common/tb-node.yml
