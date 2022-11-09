#!/bin/bash
source /etc/profile

cd $(dirname $0)

exec java -Xmx128M -Xms32M -cp conf:lib/* org.example.JdbcShellApp
