#!/bin/sh

mongod --dbpath ~/work/databases/ideologic/mongopoc/dev --port 27017 --nojournal --fork --logpath /tmp/ideologic-mongopoc-dev.log

