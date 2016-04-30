#!/bin/bash

echo "================================================================================"
echo "3.1 List schemas"
echo "================================================================================"
echo
curl -X GET -H 'Accept:application/scim+json' https://scim.psu.edu/tier/v2/Schemas | jsonlint -f
echo

echo "================================================================================"
echo "3.2 Retrieve a single schema"
echo "================================================================================"
echo
curl -X GET -H 'Accept:application/scim+json' https://scim.psu.edu/tier/v2/Schemas/urn:ietf:params:scim:schemas:core:2.0:User | jsonlint -f
echo

