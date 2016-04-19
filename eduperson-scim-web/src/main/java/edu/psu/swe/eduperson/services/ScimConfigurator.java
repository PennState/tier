package edu.psu.swe.eduperson.services;

import java.util.List;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;

import edu.psu.swe.eduperson.model.EduPersonResource;
import edu.psu.swe.scim.server.exception.InvalidProviderException;
import edu.psu.swe.scim.server.exception.UnableToRetrieveExtensionsException;
import edu.psu.swe.scim.server.provider.ProviderRegistry;
import edu.psu.swe.scim.server.schema.Registry;
import edu.psu.swe.scim.spec.resources.ScimUser;
import edu.psu.swe.scim.spec.schema.Schema;
import edu.psu.swe.scim.spec.schema.Schema.Attribute;
import lombok.extern.slf4j.Slf4j;

@WebListener
@Slf4j
public class ScimConfigurator implements ServletContextListener {

  public static final Logger LOG = LoggerFactory.getLogger(ScimConfigurator.class);

  @Inject
  private ProviderRegistry providerRegistry;
  
  @Inject
  private Registry registry;

  @Inject
  private Instance<EduPersonService> eduPersonInstance;

  @Inject
  private Instance<ScimUserWithExtensionService> withExtensionInstance;
  
  @Override
  public void contextInitialized(ServletContextEvent sce) {
    try {
      providerRegistry.registerProvider(EduPersonResource.class, eduPersonInstance);
      providerRegistry.registerProvider(ScimUser.class, withExtensionInstance);
    } catch (InvalidProviderException | JsonProcessingException | UnableToRetrieveExtensionsException e) {
      e.printStackTrace();
    }
    
    Schema schema = registry.getSchema("urn:internet2:params:scim:schemas:EduPerson");
    
    List<Attribute> attributeList = schema.getAttributes();
    
    if (attributeList == null) {
      log.error("############### No attributes found #########");
    } else {
      log.info("There are " + attributeList.size() + " attributese");
      
      for (Attribute a : attributeList) {
        log.info(a.getName() + " : " + a.getDescription());
      }
    }
  }

  @Override
  public void contextDestroyed(ServletContextEvent arg0) {
    // NOOP
  }

}