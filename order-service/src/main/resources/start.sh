#!/bin/sh

max_restarts=900    # 900 x 2 seconds = 1800 seconds = 30 minutes
count=0

while [ $count -lt $max_restarts ]; do
  java -jar /app/app.jar
  echo "App crashed or exited - restarting in 2 seconds..."
  sleep 2
  count=$((count + 1))
done

echo "30 minutes elapsed, stopping container."
