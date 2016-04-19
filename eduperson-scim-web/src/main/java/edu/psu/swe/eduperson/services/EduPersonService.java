package edu.psu.swe.eduperson.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import edu.psu.swe.eduperson.model.EduPersonResource;
import edu.psu.swe.scim.server.exception.UnableToRetrieveExtensionsException;
import edu.psu.swe.scim.server.exception.UnableToRetrieveResourceException;
import edu.psu.swe.scim.server.exception.UnableToUpdateResourceException;
import edu.psu.swe.scim.server.provider.Provider;
import edu.psu.swe.scim.spec.protocol.data.SearchRequest;
import edu.psu.swe.scim.spec.resources.ScimExtension;
import edu.psu.swe.scim.spec.schema.Meta;

public class EduPersonService implements Provider<EduPersonResource> {
  
  static Map<String, EduPersonResource> resourceMap = new HashMap<>();
  
  public EduPersonService() {
    //Seed a simple resource
    EduPersonResource epr = new EduPersonResource();
    epr.setAudio("Hamster103FM");
    epr.setCn("Harry The Hamster");
    epr.setDescription("Hamsters are rodents belonging to the subfamily Cricetinae. The subfamily contains about 25 species, classified in six or seven genera. --Wikipedia");
    epr.setDisplayName("Harold the High Flying Hamster");
    epr.setEduPersonAffiliation(Arrays.asList("student", "faculty", "staff"));
    epr.setEduPersonNickname(Arrays.asList("Eddie"));
    epr.setEduPersonPrimaryAffiliation("faculty");
    epr.setEduPersonPrincipalName("hth10101@psu.edu");
    epr.setId("1234567890");
    epr.setExternalId("0987654321");
    epr.setGivenName(Arrays.asList("Harry", "The"));
    epr.setLocality(Arrays.asList("US"));
    epr.setFacsimileTelephoneNumber(Arrays.asList("+44 01423 17698"));
    epr.setHomePhone(Arrays.asList("+44 01765 987263"));
    epr.setHomePostalAddress(Arrays.asList("7714 Sassafrass Way", "Severn", "Maryland"));
    epr.setInitials(Arrays.asList("H", "T", "H"));
    epr.setMail(Arrays.asList("hth10101@psu.edu"));
    epr.setManager(Arrays.asList("Billy The Badger"));
    epr.setMobile(Arrays.asList("+44 01423 8374954"));
    epr.setOrganization(Arrays.asList("tree"));
    epr.setOrganizationalUnitName(Arrays.asList("branch 2"));
    epr.setPostalCode(Arrays.asList("77144"));
    epr.setPreferredLanguage("Latin");
    epr.setState(Arrays.asList("North Yorkshire"));
    epr.setStreet(Arrays.asList("7714 Sassafrass Way"));
    epr.setSurname(Arrays.asList("Hamster"));
    epr.setTelephoneNumber(Arrays.asList("+44 01765 829374"));
    epr.setTitle(Arrays.asList("Walnut Warrior"));

    epr.setMeta(new Meta());
    
    resourceMap.put(epr.getId(), epr);
  }
  
  @Override
  public EduPersonResource create(EduPersonResource resource) {
    
    UUID uuid = UUID.randomUUID();
    resource.setId(uuid.toString());
    resourceMap.put(resource.getId(), resource);
    return resource;
  }

  @Override
  public EduPersonResource update(EduPersonResource resource) throws UnableToUpdateResourceException {
    if (resourceMap.containsKey(resource.getId())) {
      throw new UnableToUpdateResourceException("CONFLICT");
    }
    
    resourceMap.put(resource.getId(), resource);
    return resource;
  }

  @Override
  public EduPersonResource get(String id) {
    
    return resourceMap.get(id);
  }

  @Override
  public void delete(String id) {
    resourceMap.remove(id);
  }

  @Override
  public List<Class<? extends ScimExtension>> getExtensionList() throws UnableToRetrieveExtensionsException {
    
    return new ArrayList<Class<? extends ScimExtension>>();
  }

  @Override
  public List<EduPersonResource> find(SearchRequest request) throws UnableToRetrieveResourceException {
    // TODO Auto-generated method stub
    return null;
  }
}