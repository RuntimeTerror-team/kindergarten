#!/bin/sh
echo "Switching working directory to kindergarten-ui"
cd ../kindergarten-ui/

echo "Installing front end dependencies"
npm install

export PUBLIC_URL=http://localhost:8080/kindergarten
echo "Setting PUBLIC url path to ${PUBLIC_URL}"
echo "Building front end project"
npm run build
echo -e "\n\n\n"

echo "Switching working directory to kindergarten"
cd ../kindergarten

echo "Deleting /src/main/resources/public/"
rm -rf ./src/main/resources/public/

echo "Copying kindergarten-ui to kindergarten/src/main/resources/public"
mkdir ./src/main/resources/public/
cp -r ../kindergarten-ui/build/* ./src/main/resources/public/

mvn clean install


