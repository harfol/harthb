#!/bin/bash
kubectl -n thingsboard delete svc,sts,deploy,cm,po,ing --all

kubectl -n thingsboard get pvc --no-headers=true | awk '//{print $1}' | xargs kubectl -n thingsboard delete --ignore-not-found=true pvc