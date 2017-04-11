#!/usr/bin/env bash

function step {
  echo -e "\033[01;34m -- $1 --\033[00m";
}

function restoreDb {
    db=$1
    echo "DROP EVENT TRIGGER IF EXISTS log_ddl_trigger;" | sudo -u postgres psql $db
    echo "DROP DATABASE IF EXISTS $db;" | sudo -u postgres psql
    echo "CREATE DATABASE $db;" | sudo -u postgres psql
    cat /home/ubuntu/roles.dump | sudo -u postgres psql $db
    cat /home/ubuntu/test.dump | sudo -u postgres psql $db
}

step "Update OS"
apt-get update
apt-get upgrade -y

step "Getting roles test-db"
/usr/bin/pg_dumpall --host progtest.opentech.local \
    --port 5432 \
    --username "postgres" \
    --role "postgres" \
    --no-password \
    --database "test" \
    --verbose \
    --roles-only \
    --file "/home/ubuntu/roles.dump"

step "Getting structure test-db"
/usr/bin/pg_dump --host progtest.opentech.local \
    --port 5432 \
    --username "postgres" \
    --role "postgres" \
    --no-password \
    --dbname "test" \
    --verbose \
    --schema-only \
    --file "/home/ubuntu/test.dump"

restoreDb test
rm /home/ubuntu/test.dump
rm /home/ubuntu/roles.dump