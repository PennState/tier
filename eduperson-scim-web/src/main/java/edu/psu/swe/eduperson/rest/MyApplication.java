package edu.psu.swe.eduperson.rest;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import edu.psu.swe.scim.server.rest.ScimResourceHelper;

@ApplicationPath("v2")
public class MyApplication extends Application {
  
 @Override
 public Set<Class<?>> getClasses() {
   Set<Class<?>> clazzes = new HashSet<Class<?>>();
   
   clazzes.addAll(ScimResourceHelper.getScimClassesToLoad());
   clazzes.add(EduPersonRestResource.class);
   return clazzes;
 }

// @Override
// public Set<Object> getSingletons()
// {
//   Set<Object> singletons = new HashSet<>();
//   singletons.add(new VersionResource(this.getClass()));
//   return singletons;
// }
}
