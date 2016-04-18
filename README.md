# Penn State Tier Examples
Workspace for implementing tier concepts

This example was an attempt to illustrate what an the existing EduPerson schema would look like as either a new Scim Resource 
or as an extension to ScimUser.  The code is built on top of the [Penn State Scim](https://github.com/PennState/scim) effort.  If you'd like to play yourself, the instructions for building are below.  If you'd just like to see what the ResourceTypes and Schemas would look like, they can be found in the following [gist](https://gist.github.com/ussmith/3cb4217f32a387b11474d233212fef39).

With some small work this example could be wired into an existing eduPerson implementation.

To get started (using a maven build approach), clone and build the [Penn State Scim](https://github.com/PennState/scim) develop branch.  On the command line 
you can type:

    git clone git@github.com:PennState/scim.git --branch develop
    cd scim
    mvn clean install

This will build the scim base project.  Next type:

    git clone git@github.com:PennState/tier.git --branch feature/eduperson
    cd tier
    mvn clean install -Pdocker

This will build out the example eduperson project and create a docker container named eduperson-scim-server.

To run the docker container type:

    run --rm -it  -p 8080:8080 -p 9990:9990 eduperson-scim-server

Now, to test.  You can either use your favorite REST tool to send commands (such as Postman), or curl will work just fine.

To see the schema's on the serve you can type:

    curl -X GET -H "Cache-Control: no-cache" "http://localhost:8080/eduperson-scim/v2/Schemas"

Or to get the ResourceTypes you can type:

    curl -X GET -H "Cache-Control: no-cache" "http://localhost:8080/eduperson-scim/v2/ResourceTypes"

The example was built so that you can see both examples so there's more in the out of the box responses than is practical.  If you want to see one solution
(only as a ResourceType) or the other (only as a ScimUser with an EduPerson extension) you can comment out one of the registration
lines in the ScimConfigurator.java class.  For instance, to see it solely as a ResourceType, you can reflect it in code as:

 

    @Override
     public void contextInitialized(ServletContextEvent sce) {
      try {
        providerRegistry.registerProvider(EduPersonResource.class, eduPersonInstance);
        //providerRegistry.registerProvider(ScimUser.class, withExtensionInstance);
      } catch (InvalidProviderException | JsonProcessingException | UnableToRetrieveExtensionsException e) {
        e.printStackTrace();
      }
    } 
 
