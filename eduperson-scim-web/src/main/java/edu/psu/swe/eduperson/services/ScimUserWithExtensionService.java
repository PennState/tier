package edu.psu.swe.eduperson.services;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.ws.rs.core.Response.Status;

import edu.psu.swe.eduperson.model.EduPersonExtension;
import edu.psu.swe.eduperson.model.EduPersonResource;
import edu.psu.swe.scim.server.exception.UnableToCreateResourceException;
import edu.psu.swe.scim.server.exception.UnableToDeleteResourceException;
import edu.psu.swe.scim.server.exception.UnableToRetrieveExtensionsException;
import edu.psu.swe.scim.server.exception.UnableToRetrieveResourceException;
import edu.psu.swe.scim.server.exception.UnableToUpdateResourceException;
import edu.psu.swe.scim.server.provider.Provider;
import edu.psu.swe.scim.spec.protocol.data.SearchRequest;
import edu.psu.swe.scim.spec.resources.Address;
import edu.psu.swe.scim.spec.resources.Email;
import edu.psu.swe.scim.spec.resources.Name;
import edu.psu.swe.scim.spec.resources.PhoneNumber;
import edu.psu.swe.scim.spec.resources.ScimExtension;
import edu.psu.swe.scim.spec.resources.ScimUser;
import edu.psu.swe.scim.spec.schema.Meta;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ScimUserWithExtensionService implements Provider<ScimUser> {

  static Map<String, ScimUser> resourceMap = new HashMap<>();
  
  public ScimUserWithExtensionService() {
    ScimUser scimUser = new ScimUser();
    
    //Seed a simple resource
    EduPersonExtension epr = new EduPersonExtension();
    epr.setAudio("Hamster103FM");
    epr.setCn("Harry The Hamster");
    epr.setDescription("Hamsters are rodents belonging to the subfamily Cricetinae. The subfamily contains about 25 species, classified in six or seven genera. --Wikipedia");
    epr.setDisplayName("Harold the High Flying Hamster");
    epr.setEduPersonAffiliation(Arrays.asList("student", "faculty", "staff"));
    epr.setEduPersonNickname(Arrays.asList("Eddie"));
    epr.setEduPersonPrimaryAffiliation("faculty");
    epr.setEduPersonPrincipalName("hth10101@psu.edu");
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

    scimUser.addExtension(EduPersonResource.SCHEMA_URI, epr);
    
    scimUser.setActive(true);
    scimUser.setDisplayName(epr.getDisplayName());
    Address address = new Address();
    
    address.setCountry("US");
    address.setDisplay("7714 Sassafrass Way, Severn Md.  22144");
    address.setLocality("Anne Arundel");
    address.setRegion("MD");
    address.setPrimary(true);
    address.setStreetAddress("7714 Sassafrass Way");
    address.setType("home");
    address.setPostalCode("22144");
    
    scimUser.setAddresses(Arrays.asList(address));
    
    Email email = new Email();
    email.setDisplay(epr.getEduPersonPrincipalName());
    email.setPrimary(true);
    email.setType("work");
    email.setValue(epr.getMail().get(0));
    
    scimUser.setEmails(Arrays.asList(email));
    
    scimUser.setExternalId("0987654321");
    scimUser.setId("1234567890");
    scimUser.setLocale("USA");
    
    Name name = new Name();
    name.setFamilyName("Hamster");
    name.setFormatted(epr.getDisplayName());
    name.setGivenName("Harry");
    name.setHonorificPrefix("Sir");
    name.setMiddleName("The");
    name.setHonorificSuffix("Sr.");
    
    scimUser.setName(name);
    scimUser.setNickName(epr.getEduPersonNickname().get(0));
    
    PhoneNumber pn = new PhoneNumber();
    pn.setDisplay("+44 01423 82739");
    pn.setPrimary(true);
    pn.setType("home");
    pn.setValue("+44 01423 82739");
    
    scimUser.setPhoneNumbers(Arrays.asList(pn));
    
    scimUser.setPreferredLanguage(epr.getPreferredLanguage());
    scimUser.setTimezone("EST");
    scimUser.setTitle(epr.getTitle().get(0));
    scimUser.setUserName("hth1010");
    scimUser.setUserType("Varmint");
    
    scimUser.setMeta(new Meta());
    
    resourceMap.put(scimUser.getId(), scimUser);
  }
  
  @Override
  public ScimUser create(ScimUser resource) throws UnableToCreateResourceException {
    
    UUID uuid = UUID.randomUUID();
    resource.setId(uuid.toString());
    resourceMap.put (uuid.toString(), resource);
    return resource;
  }

  @Override
  public ScimUser update(ScimUser resource) throws UnableToUpdateResourceException {
    
    if (!resourceMap.containsKey(resource.getId())) {
      throw new UnableToUpdateResourceException(Status.NOT_FOUND, "No resource with id " + resource.getId() + " could be found");
    }
    
    resourceMap.put(resource.getId(), resource);
    return resource;
  }

  @Override
  public ScimUser get(String id) throws UnableToRetrieveResourceException {
    
    ScimUser user = resourceMap.get(id);
    
    if (user != null) {
      log.warn("The user has " + user.getExtensions().size() + " extensions");
    }
    
    return resourceMap.get(id);
  }

  @Override
  public void delete(String id) throws UnableToDeleteResourceException {
    resourceMap.remove(id);
  }

  @Override
  public List<ScimUser> find(SearchRequest request) throws UnableToRetrieveResourceException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Class<? extends ScimExtension>> getExtensionList() throws UnableToRetrieveExtensionsException {
    return Arrays.asList(EduPersonExtension.class);
  }

}
