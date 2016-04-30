package edu.psu.swe.eduperson.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.ws.rs.core.Response.Status;

import edu.psu.swe.scim.server.exception.UnableToCreateResourceException;
import edu.psu.swe.scim.server.exception.UnableToDeleteResourceException;
import edu.psu.swe.scim.server.exception.UnableToRetrieveExtensionsException;
import edu.psu.swe.scim.server.exception.UnableToRetrieveResourceException;
import edu.psu.swe.scim.server.exception.UnableToUpdateResourceException;
import edu.psu.swe.scim.server.provider.Provider;
import edu.psu.swe.scim.spec.protocol.search.Filter;
import edu.psu.swe.scim.spec.protocol.search.PageRequest;
import edu.psu.swe.scim.spec.protocol.search.SortRequest;
import edu.psu.swe.scim.spec.resources.ScimExtension;
import edu.psu.swe.scim.spec.resources.ScimGroup;
import edu.psu.swe.scim.spec.schema.Meta;
import edu.psu.swe.scim.spec.schema.ResourceReference;

public class GroupService implements Provider<ScimGroup> {
  
  private Map<String, ScimGroup> groupMap = new HashMap<>();

  public GroupService() {
    ScimGroup group = new ScimGroup();
    group.setId("123-ABC-456-DEF-780-GHI");
    group.setDisplayName("Team Unicorn");
    group.setExternalId(UUID.randomUUID().toString());
    
    Meta meta = new Meta();
    LocalDateTime ldt = LocalDateTime.now().minusDays(3);
    meta.setCreated(ldt);
    meta.setLastModified(ldt.plusDays(1));
    meta.setLocation("https://scim.psu.edu/tier/v2/Groups/" + group.getId());
    meta.setResourceType("Group");
    
    List<ResourceReference> members = new ArrayList<>();
    
    ResourceReference rr = new ResourceReference();
    rr.setValue("1234567890");
    rr.setDisplay("Harry The Hamster");
    rr.setRef("https://scim.psu.edu/tier/v2/Users/" + rr.getValue());
    members.add(rr);
    
    group.setMembers(members);
    groupMap.put(group.getId(), group);
  }
  
  @Override
  public ScimGroup create(ScimGroup resource) throws UnableToCreateResourceException {
    
    Meta meta = resource.getMeta();
    
    if (meta == null) {
      meta = new Meta();
    }
    
    LocalDateTime now = LocalDateTime.now();
    meta.setCreated(now);
    meta.setLastModified(now);
    
    String id = UUID.randomUUID().toString();
    meta.setLocation("https://scim.psu.edu/tier/v2/Groups/" + id);
    meta.setResourceType("Group");
    resource.setMeta(meta);
    
    groupMap.put(id, resource);
    return resource;
  }

  @Override
  public ScimGroup update(ScimGroup resource) throws UnableToUpdateResourceException {
    ScimGroup group = groupMap.get(resource.getId());
    
    if (group == null) {
      throw new UnableToUpdateResourceException(Status.NOT_FOUND, "Resource with id " + resource.getId() + " not found");
    }
    
    Meta meta = group.getMeta();
    meta.setLastModified(LocalDateTime.now());
    groupMap.put(resource.getId(), group);
    return group;
  }

  @Override
  public ScimGroup get(String id) throws UnableToRetrieveResourceException {
    return groupMap.get(id);
  }

  @Override
  public List<ScimGroup> find(Filter filter, PageRequest pageRequest, SortRequest sortRequest) throws UnableToRetrieveResourceException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void delete(String id) throws UnableToDeleteResourceException {
    groupMap.remove(id);
    
  }

  @Override
  public List<Class<? extends ScimExtension>> getExtensionList() throws UnableToRetrieveExtensionsException {
    // TODO Auto-generated method stub
    return null;
  }

}
