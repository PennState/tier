#!/bin/bash

echo "================================================================================"
echo "1.1 Retrieve service provider config w/out authz"
echo "================================================================================"
echo
curl -X GET -H 'Accept:application/scim+json' https://scim.psu.edu/tier/v2/ServiceProviderConfig | jsonlint -f
echo

echo "================================================================================"
echo "1.2 Retrieve service provider config w/ authz"
echo "================================================================================"
echo
echo "Not implemented"
echo
