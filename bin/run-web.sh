#!/bin/bash

if [ -f .env ]; then
	set -o allexport; source .env; set +o allexport
fi

sbt web/run
