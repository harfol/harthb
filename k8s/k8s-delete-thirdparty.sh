#!/bin/bash
set -e

source .env

kubectl config set-context $(kubectl config current-context) --namespace=thingsboard
kubectl delete -f $DEPLOYMENT_TYPE/thirdparty.yml
