#!/bin/bash

if [ -f .env ]; then
	set -o allexport; source .env; set +o allexport
fi

docker run -p "$DB_HOST":"$DB_PORT":5432 -e POSTGRES_PASSWORD="$DB_PASSWORD" -e POSTGRES_USER="$DB_USERNAME" -e POSTGRES_DB="$DB_NAME" postgres
