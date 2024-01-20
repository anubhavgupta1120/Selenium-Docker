#!/bin/bash
echo "-------------------------------------------"
echo "HUB_HOST      : ${HUB_HOST:-hub}"
echo "BROWSER       : ${BROWSER:-Chrome}"
echo "THREAD_COUNT  : ${THREAD_COUNT:-1}"
echo "TEST_SUITE    : ${TEST_SUITE}"
echo "-------------------------------------------"

# Do not start the tests immediately. Hub has to be ready with browser nodes
echo "Checking if hub is ready..!"
count=0
while [ "$( curl -s http://${HUB_HOST:-hub}:4444/status | jq -r .value.ready )" != "true" ]
do
  count=$((count+1))
  echo "Attempt: ${count}"
  if [ "$count" -ge 30 ]
  then
      echo "**** HUB IS NOT READY WITHIN 30 SECONDS ****"
      exit 1
  fi
  sleep 1
done

# At this point, selenium grid should be up!
echo "Selenium Grid is up and running. Running the test...."

# Start the java command -> The properties like setSeleniumGrid are comming from the .properties file
java -cp 'libs/*' \
     -DSetSeleniumGrid=True \
     -DSeleniumGrid.HubHost="${HUB_HOST:-hub}" \
     -DBrowser="${BROWSER:-Chrome}" \
     org.testng.TestNG \
     -threadcount "${THREAD_COUNT:-1}" \
     Test-suites/"${TEST_SUITE}"