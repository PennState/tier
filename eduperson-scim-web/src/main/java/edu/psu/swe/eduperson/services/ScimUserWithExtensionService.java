package edu.psu.swe.eduperson.services;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import lombok.extern.slf4j.Slf4j;
import edu.psu.swe.eduperson.model.EduPersonExtension;
import edu.psu.swe.scim.server.exception.UnableToCreateResourceException;
import edu.psu.swe.scim.server.exception.UnableToDeleteResourceException;
import edu.psu.swe.scim.server.exception.UnableToRetrieveExtensionsException;
import edu.psu.swe.scim.server.exception.UnableToRetrieveResourceException;
import edu.psu.swe.scim.server.exception.UnableToUpdateResourceException;
import edu.psu.swe.scim.server.provider.Provider;
import edu.psu.swe.scim.spec.extension.EnterpriseExtension;
import edu.psu.swe.scim.spec.protocol.search.Filter;
import edu.psu.swe.scim.spec.protocol.search.PageRequest;
import edu.psu.swe.scim.spec.protocol.search.SortRequest;
import edu.psu.swe.scim.spec.resources.ScimExtension;
import edu.psu.swe.scim.spec.resources.ScimUser;

@Slf4j
public class ScimUserWithExtensionService implements Provider<ScimUser> {

  @Inject
  ScimUserWithExtensionSingleton singleton;
    
  @Override
  public ScimUser create(ScimUser resource) throws UnableToCreateResourceException {
    return singleton.create(resource);
  }
  
  @Override
  public ScimUser update(String id, ScimUser resource) throws UnableToUpdateResourceException {
    return singleton.update(id, resource);
  }

  @Override
  public List<ScimUser> find(Filter filter, PageRequest pageRequest, SortRequest sortRequest) throws UnableToRetrieveResourceException {
    return singleton.find(filter, pageRequest, sortRequest);
  }
  
  @Override
  public ScimUser get(String id) throws UnableToRetrieveResourceException {
    log.info("Calling singleton get");
    return singleton.get(id);
    
  }

  @Override
  public void delete(String id) throws UnableToDeleteResourceException {
    singleton.delete(id);
  }

  @Override
  public List<Class<? extends ScimExtension>> getExtensionList() throws UnableToRetrieveExtensionsException {
    return Arrays.asList(EduPersonExtension.class, EnterpriseExtension.class);
  }

}
