#!/bin/bash
set -e

source .env

kubectl config set-context $(kubectl config current-context) --namespace=thingsboard

kubectl delete -f common/thingsboard.yml
kubectl delete -f common/tb-node.yml
