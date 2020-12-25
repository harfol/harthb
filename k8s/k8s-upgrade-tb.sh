#!/bin/bash
for i in "$@"
do
case $i in
    --fromVersion=*)
    FROM_VERSION="${i#*=}"
    shift
    ;;
    *)
            # unknown option
    ;;
esac
done

if [[ -z "${FROM_VERSION// }" ]]; then
    echo "--fromVersion parameter is invalid or unspecified!"
    echo "Usage: k8s-upgrade-tb.sh --fromVersion={VERSION}"
    exit 1
else
    fromVersion="${FROM_VERSION// }"
fi

kubectl apply -f common/database-setup.yml &&
kubectl wait --for=condition=Ready pod/tb-db-setup --timeout=120s &&
kubectl exec tb-db-setup -- sh -c 'export UPGRADE_TB=true; export FROM_VERSION='"$fromVersion"'; start-tb-node.sh; touch /tmp/install-finished;'

kubectl delete pod tb-db-setup
