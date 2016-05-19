package edu.psu.swe.eduperson.rest;

import io.swagger.annotations.Api;

import javax.ws.rs.Path;

import edu.psu.swe.eduperson.model.EduPersonResource;
import edu.psu.swe.scim.spec.protocol.BaseResourceTypeResource;

@Path("EduPeople")
@Api("ResourceType")
public interface EduPersonRestResource extends BaseResourceTypeResource<EduPersonResource> {

}
