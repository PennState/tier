package edu.psu.swe.eduperson.rest;

import javax.inject.Inject;

import edu.psu.swe.eduperson.model.EduPersonResource;
import edu.psu.swe.scim.server.provider.Provider;
import edu.psu.swe.scim.server.provider.ProviderRegistry;
import edu.psu.swe.scim.server.rest.BaseResourceTypeResourceImpl;

public class EduPersonRestResourceImpl extends BaseResourceTypeResourceImpl<EduPersonResource> implements EduPersonRestResource {
  
  @Inject
  ProviderRegistry providerRegistry;

  @Override
  public Provider<EduPersonResource> getProvider() {
    return providerRegistry.getProvider(EduPersonResource.class);
  }

}
