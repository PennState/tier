package edu.psu.swe.eduperson.rest;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import edu.psu.rest.common.VersionResource;
import edu.psu.swagger.util.SwaggerHelper;
import edu.psu.swagger.util.SwaggerJaxrsConfig;

@ApplicationPath("resources")
public class MyApplication extends Application {
  
  private  static Set<Class<?>> classes = new HashSet<>();

  static { 
   classes.add(ResourceImpl.class);

   //swagger config
   classes.addAll(SwaggerHelper.getSwaggerClassesToLoad());
   SwaggerJaxrsConfig.beanConfig.setTitle("XYZ Service");
 }

 @Override
 public Set<Class<?>> getClasses() {
   return classes;
 }

 @Override
 public Set<Object> getSingletons()
 {
   Set<Object> singletons = new HashSet<>();
   singletons.add(new VersionResource(this.getClass()));
   return singletons;
 }
}
