#!/bin/bash

echo "================================================================================"
echo "2.1 List resource types"
echo "================================================================================"
echo
curl -X GET -H 'Accept:application/scim+json' https://scim.psu.edu/tier/v2/ResourceTypes | jsonlint -f
echo

echo "================================================================================"
echo "2.2 Retrieve a single resource type"
echo "================================================================================"
echo
curl -X GET -H 'Accept:application/scim+json' https://scim.psu.edu/tier/v2/ResourceTypes/User | jsonlint -f
echo
