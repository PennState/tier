package edu.psu.swe.eduperson.rest;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import edu.psu.swagger.util.SwaggerHelper;
import edu.psu.swagger.util.SwaggerJaxrsConfig;
import edu.psu.swe.scim.server.rest.ScimResourceHelper;

@ApplicationPath("v2")
public class MyApplication extends Application {

  @Override
  public Set<Class<?>> getClasses() {
    Set<Class<?>> clazzes = new HashSet<Class<?>>();

    clazzes.addAll(ScimResourceHelper.getScimClassesToLoad());
    clazzes.add(EduPersonRestResource.class);
    clazzes.add(io.swagger.jaxrs.listing.ApiListingResource.class);
    clazzes.add(io.swagger.jaxrs.listing.SwaggerSerializers.class);
    
    clazzes.addAll(SwaggerHelper.getSwaggerClassesToLoad());
    SwaggerJaxrsConfig.beanConfig.setTitle("EduPerson SCIM");

    return clazzes;
  }

  // @Override
  // public Set<Object> getSingletons()
  // {
  // Set<Object> singletons = new HashSet<>();
  // singletons.add(new VersionResource(this.getClass()));
  // return singletons;
  // }
}
