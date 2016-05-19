package edu.psu.swe.eduperson.rest;

import io.swagger.annotations.Api;

import javax.inject.Inject;
import javax.ws.rs.Path;

import edu.psu.swe.eduperson.model.EduPersonResource;
import edu.psu.swe.scim.server.provider.Provider;
import edu.psu.swe.scim.server.provider.ProviderRegistry;
import edu.psu.swe.scim.server.rest.BaseResourceTypeResourceImpl;

@Path("EduPeople")
//@Api("ResourceType")
public class EduPersonRestResource extends BaseResourceTypeResourceImpl<EduPersonResource> {
  
  @Inject
  ProviderRegistry providerRegistry;

  @Override
  public Provider<EduPersonResource> getProvider() {
    return providerRegistry.getProvider(EduPersonResource.class);
  }

}
