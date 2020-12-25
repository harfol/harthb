#!/bin/bash
mkdir -p tb-node/log/ && sudo chown -R 799:799 tb-node/log/

mkdir -p tb-transports/coap/log && sudo chown -R 799:799 tb-transports/coap/log

mkdir -p tb-transports/http/log && sudo chown -R 799:799 tb-transports/http/log

mkdir -p tb-transports/mqtt/log && sudo chown -R 799:799 tb-transports/mqtt/log