package edu.psu.swe.eduperson.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.ws.rs.core.Response.Status;

import edu.psu.swe.eduperson.model.EduPersonExtension;
import edu.psu.swe.scim.server.exception.UnableToCreateResourceException;
import edu.psu.swe.scim.server.exception.UnableToDeleteResourceException;
import edu.psu.swe.scim.server.exception.UnableToRetrieveExtensionsException;
import edu.psu.swe.scim.server.exception.UnableToRetrieveResourceException;
import edu.psu.swe.scim.server.exception.UnableToUpdateResourceException;
import edu.psu.swe.scim.server.provider.Provider;
import edu.psu.swe.scim.spec.exception.InvalidExtensionException;
import edu.psu.swe.scim.spec.protocol.search.Filter;
import edu.psu.swe.scim.spec.protocol.search.PageRequest;
import edu.psu.swe.scim.spec.protocol.search.SortRequest;
import edu.psu.swe.scim.spec.resources.Address;
import edu.psu.swe.scim.spec.resources.Email;
import edu.psu.swe.scim.spec.resources.Name;
import edu.psu.swe.scim.spec.resources.PhoneNumber;
import edu.psu.swe.scim.spec.resources.ScimExtension;
import edu.psu.swe.scim.spec.resources.ScimUser;
import edu.psu.swe.scim.spec.schema.Meta;
import edu.psu.swe.scim.spec.schema.ResourceReference;
import edu.psu.swe.scim.spec.schema.ResourceReference.ReferenceType;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ScimUserWithExtensionService implements Provider<ScimUser> {

  static Map<String, ScimUser> resourceMap = new HashMap<>();
  
  public ScimUserWithExtensionService() throws InvalidExtensionException {
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

    scimUser.addExtension(epr);
    
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
    
    //Seed a simple resource
    
    ScimUser scimUser2 = new ScimUser();
    
    EduPersonExtension epr2 = new EduPersonExtension();
    epr2.setAudio("RockinRodent1250");
    epr2.setCn("Ricky the Rat");
    epr2.setDescription("Rats are various medium-sized, long-tailed rodents of the superfamily Muroidea. \"True rats\" are members of the genus Rattus, the most important of which to humans are the black rat, Rattus rattus, and the brown rat, Rattus norvegicus. --Wikipedia");
    epr2.setDisplayName("Ricky the Rockin' Rat");
    epr2.setEduPersonAffiliation(Arrays.asList("student", "faculty"));
    epr2.setEduPersonNickname(Arrays.asList("Rat-a-Tat"));
    epr2.setEduPersonPrimaryAffiliation("faculty");
    epr2.setEduPersonPrincipalName("rtr10101@psu.edu");
    epr2.setGivenName(Arrays.asList("Ricky", "The"));
    epr2.setLocality(Arrays.asList("US"));
    epr2.setFacsimileTelephoneNumber(Arrays.asList("+44 01423 17698"));
    epr2.setHomePhone(Arrays.asList("+44 01765 987263"));
    epr2.setHomePostalAddress(Arrays.asList("7718 Sassafrass Way", "Severn", "Maryland"));
    epr2.setInitials(Arrays.asList("R", "T", "R"));
    epr2.setMail(Arrays.asList("hth10101@psu.edu"));
    epr2.setManager(Arrays.asList("Dougie The Dingo"));
    epr2.setMobile(Arrays.asList("+44 01423 8374954"));
    epr2.setOrganization(Arrays.asList("whole"));
    epr2.setOrganizationalUnitName(Arrays.asList("pit 2"));
    epr2.setPostalCode(Arrays.asList("56144"));
    epr2.setPreferredLanguage("French");
    epr2.setState(Arrays.asList("East Lothian"));
    epr2.setStreet(Arrays.asList("7718 Sassafrass Way"));
    epr2.setSurname(Arrays.asList("Rat"));
    epr2.setTelephoneNumber(Arrays.asList("+44 01765 829374"));
    epr2.setTitle(Arrays.asList("Head Cheese"));

    scimUser2.addExtension(epr2);
    
    scimUser2.setActive(true);
    scimUser2.setDisplayName(epr.getDisplayName());
    Address address2 = new Address();
    
    address2.setCountry("US");
    address2.setDisplay("7718 Sassafrass Way, Severn Md.  22144");
    address2.setLocality("Centre");
    address2.setRegion("PA");
    address2.setPrimary(true);
    address2.setStreetAddress("7718 Sassafrass Way");
    address2.setType("home");
    address2.setPostalCode("55144");
    
    scimUser2.setAddresses(Arrays.asList(address));
    
    Email email2 = new Email();
    email2.setDisplay(epr.getEduPersonPrincipalName());
    email2.setPrimary(true);
    email2.setType("work");
    email2.setValue(epr.getMail().get(0));
    
    scimUser2.setEmails(Arrays.asList(email2));
    
    scimUser2.setExternalId("1234567890");
    scimUser2.setId("0987654321");
    scimUser2.setLocale("USA");
    
    Name name2 = new Name();
    name2.setFamilyName("Rat");
    name2.setFormatted(epr.getDisplayName());
    name2.setGivenName("Ricky");
    name2.setHonorificPrefix("Dr");
    name2.setMiddleName("The");
    name2.setHonorificSuffix("IX");
    
    scimUser2.setName(name);
    scimUser2.setNickName(epr.getEduPersonNickname().get(0));
    
    PhoneNumber pn2 = new PhoneNumber();
    pn2.setDisplay("+44 01423 82739");
    pn2.setPrimary(true);
    pn2.setType("home");
    pn2.setValue("+44 01423 82739");
    
    scimUser2.setPhoneNumbers(Arrays.asList(pn));
    
    scimUser2.setPreferredLanguage(epr.getPreferredLanguage());
    scimUser2.setTimezone("EST");
    scimUser2.setTitle(epr.getTitle().get(0));
    scimUser2.setUserName("hth1010");
    scimUser2.setUserType("Varmint");
    
    scimUser2.setMeta(new Meta());
    
    ResourceReference rr = new ResourceReference();
    rr.setDisplay("Team Unicorn");
    rr.setRef("../Groups/123-ABC-456-DEF-780-GHI");
    rr.setType(ReferenceType.DIRECT);
    rr.setValue("123-ABC-456-DEF-780-GHI");
    scimUser2.setGroups(Arrays.asList(rr));
    
    resourceMap.put(scimUser2.getId(), scimUser2);
  }
  
  @Override
  public ScimUser create(ScimUser resource) throws UnableToCreateResourceException {
    
    UUID uuid = UUID.randomUUID();
    resource.setId(uuid.toString());
    
    Meta meta = resource.getMeta();
    
    if (meta == null) {
      meta = new Meta();
    }
    
    LocalDateTime now = LocalDateTime.now();
    meta.setCreated(now);
    meta.setLastModified(now);
    meta.setResourceType("User");
    meta.setLocation("https://scim.psu.edu/tier/v2/Users/" + uuid.toString());
    resource.setMeta(meta);
    
    log.info("Adding " + uuid.toString() + " to the backing store");
    resourceMap.put (uuid.toString(), resource);
    log.info("There are now " + resourceMap.size() + " resources available");

    return resource;
  }

  @Override
  public ScimUser update(ScimUser resource) throws UnableToUpdateResourceException {
    
    if (!resourceMap.containsKey(resource.getId())) {
      throw new UnableToUpdateResourceException(Status.NOT_FOUND, "No resource with id " + resource.getId() + " could be found");
    }
    
    Meta meta = resource.getMeta();
    meta.setLastModified(LocalDateTime.now());
    
    resourceMap.put(resource.getId(), resource);
    return resource;
  }

  @Override
  public List<ScimUser> find(Filter filter, PageRequest pageRequest, SortRequest sortRequest) throws UnableToRetrieveResourceException {
    return new ArrayList<ScimUser>(resourceMap.values());
  }
  
  @Override
  public ScimUser get(String id) throws UnableToRetrieveResourceException {
    
    log.info("Attempting to get user " + id + " from the resources(" + resourceMap.size() + ") exists? " + resourceMap.containsKey(id));
    
    ScimUser user = resourceMap.get(id);
    
    if (user != null) {
      log.warn("The user has " + user.getExtensions().size() + " extensions");
    }
    
    return user;
  }

  @Override
  public void delete(String id) throws UnableToDeleteResourceException {
    resourceMap.remove(id);
  }

  @Override
  public List<Class<? extends ScimExtension>> getExtensionList() throws UnableToRetrieveExtensionsException {
    return Arrays.asList(EduPersonExtension.class);
  }

}
