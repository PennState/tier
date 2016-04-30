#!/bin/bash

echo "================================================================================"
echo "7.1 Retrieve a user - full user should be returned.  Verify no writeOnly attributes (eg - password) are returned"
echo "================================================================================"
echo
curl -X GET -H 'Accept:application/scim+json' https://scim.psu.edu/tier/v2/Users/1234567890 | jsonlint -f
echo

echo "================================================================================"
echo "7.2 Retrieve a user with If-None-Match etag that matches - should return 304"
echo "================================================================================"
echo
curl -X GET -H 'Accept:application/scim+json' -H 'If-None-Match:+6AtAE4IyjPejNO956IUU98ZBM7fbdWLd2Fu7Il7QnI=' https://scim.psu.edu/tier/v2/Users/1234567890 | jsonlint -f
echo

echo "================================================================================"
echo "7.3 Retrieve a user with If-None-Match etag that does not match - should return user"
echo "================================================================================"
echo
curl -X GET -H 'Accept:application/scim+json' -H 'If-None-Match:+5AtAE4IyjPejNO956IUU98ZBM7fbdWLd2Fu7Il7QnI=' https://scim.psu.edu/tier/v2/Users/1234567890 | jsonlint -f
echo

echo "================================================================================"
echo "7.4 Retrieve a user with attributes query param (both core and extended attributes) - verify only requested attributes are returned"
echo "================================================================================"
echo
curl -X GET -H 'Accept:application/scim+json' https://scim.psu.edu/tier/v2/Users/1234567890?attributes=name | jsonlint -f
echo

echo "================================================================================"
echo " Retrieve a user with excludedAttributes query param (both core and extended attributes) - verify only requested attributes are returned"
echo "================================================================================"
echo
curl -X GET -H 'Accept:application/scim+json' https://scim.psu.edu/tier/v2/Users/1234567890?ExcludedAttributes=name | jsonlint -f
echo
