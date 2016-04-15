package edu.psu.swe.eduperson.rest;

import edu.psu.swe.eduperson.services.Service;

import java.lang.Override;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResourceImpl implements Resource {
  
  private static final Logger LOG = LoggerFactory.getLogger(ResourceImpl.class);
  
  @Inject
  private Service service;
  
  @Override
  public Response getDemo() {
    return Response.ok().build();
  }
  
}
