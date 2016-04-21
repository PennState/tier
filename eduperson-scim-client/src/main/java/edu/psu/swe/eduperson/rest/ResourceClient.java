package edu.psu.swe.eduperson.rest;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResourceClient  {
  private static final Logger LOG = LoggerFactory.getLogger(ResourceClient.class);
  
  private String baseUrl;

  public ResourceClient(String url) {
    baseUrl = url;
  }
  
  public Response getDemo() {
    Client client = ClientBuilder.newClient();

    WebTarget target = client.target(baseUrl);

    Response resp = target.request().accept(MediaType.APPLICATION_JSON).get();

    return resp;
  }
  
}
