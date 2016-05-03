package edu.psu.swe.eduperson.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import edu.psu.injection.validator.Email;
import edu.psu.swe.scim.spec.annotation.ScimAttribute;
import edu.psu.swe.scim.spec.annotation.ScimExtensionType;
import edu.psu.swe.scim.spec.resources.ScimExtension;
import edu.psu.swe.scim.spec.schema.Schema.Attribute.Returned;
import lombok.Data;
import lombok.EqualsAndHashCode;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
@ScimExtensionType(id = EduPersonExtension.SCHEMA_URI, name = EduPersonExtension.RESOURCE_NAME, required = false, description = "Eduperson extension")
@Data
@EqualsAndHashCode
public class EduPersonExtension implements ScimExtension {

  private static final long serialVersionUID = 7338701089020172760L;
  public static final String SCHEMA_URI = "urn:internet2:params:scim:schemas:EduPersonExtension";
  public static final String RESOURCE_NAME = "EduPersonExtension";

  @ScimAttribute(canonicalValueList={"faculty", "student", "staff", "alum", "member", "affiliate", "employee", "library-walk-in"}, description="Specifies the person's relationship(s) to the institution in broad categories such as student, faculty, staff, alum, etc. (See controlled vocabulary). Notes If there is a value in eduPersonPrimaryAffiliation, that value MUST be asserted here as well.")
  @XmlElement
  private List<String> eduPersonAffiliation;
 
  @ScimAttribute(description="URI (either URN or URL) that indicates a set of rights to specific resources.")
  @XmlElement
  private List<String> eduPersonEntitlement;

  @ScimAttribute(description="Person's nickname, or the informal name by which they are accustomed to be hailed.")
  @XmlElement
  private List<String> eduPersonNickname;

  @ScimAttribute(description="The distinguished name (DN) of the directory entry representing the institution with which the person is associated. LDAP example eduPersonOrgDN: o=Hogwarts, dc=hsww, dc=wiz")
  @XmlElement
  private String eduPersonOrgDN;

  @ScimAttribute(description="The distinguished name(s) (DN) of the directory entries representing the person's Organizational Unit(s). May be multivalued, as for example, in the case of a faculty member with appointments in multiple departments or a person who is a student in one department and an employee in another. LDAP Example - eduPersonOrgUnitDN: ou=Potions, o=Hogwarts, dc=hsww, dc=wiz")
  @XmlElement
  private List<String> eduPersonOrgUnitDN;

  @ScimAttribute(canonicalValueList={"faculty", "student", "staff", "alum", "member", "affiliate", "employee", "library-walk-in"}, description="Person's nickname, or the informal name by which they are accustomed to be hailed. LDAP Example - eduPersonPrimaryAffiliation: student")
  @XmlElement
  private String eduPersonPrimaryAffiliation;

  @ScimAttribute(description="The distinguished name (DN) of the directory entry representing the person's primary Organizational Unit(s).  LDAP example - eduPersonPrimaryOrgUnitDN: ou=Music Department, o=Notre Dame, dc=nd, dc=edu")
  @XmlElement
  private String eduPersonPrimaryOrgUnitDN; 

  @ScimAttribute(description="A scoped identifier for a person. It should be represented in the form \"user@scope\" where 'user' is a name-based identifier for the person and where the \"scope\" portion MUST be the administrative domain of the identity system where the identifier was created and assigned. Each value of 'scope' defines a namespace within which the assigned identifiers MUST be unique. Given this rule, if two eduPersonPrincipalName (ePPN) values are the same at a given point in time, they refer to the same person. There must be one and only one \"@\" sign in valid values of eduPersonPrincipalName. LDAP example - eduPersonPrincipalName: hputter@hsww.wiz")
  @XmlElement
  private String eduPersonPrincipalName;

  @ScimAttribute(description="Each value of this multi-valued attribute represents an ePPN (eduPersonPrincipalName) value that was previously associated with the entry. The values MUST NOT include the currently valid ePPN value. There is no implied or assumed order to the values. This attribute MUST NOT be populated if ePPN values are ever reassigned to a different entry (after, for example, a period of dormancy). That is, they MUST be unique in space and over time. LDAP example - eduPersonPrincipalNamePrior: foo@hsww.wiz")
  @XmlElement
  private List<String>eduPersonPrincipalNamePrior;

  @ScimAttribute(description="Specifies the person's affiliation within a particular security domain in broad categories such as student, faculty, staff, alum, etc. The values consist of a left and right component separated by an \"@\" sign. The left component is one of the values from the eduPersonAffiliation controlled vocabulary.This right-hand side syntax of eduPersonScopedAffiliation intentionally matches that used for the right-hand side values for eduPersonPrincipalName. The \"scope\" portion MUST be the administrative domain to which the affiliation applies. Multiple \"@\" signs are not recommended, but in any case, the first occurrence of the \"@\" sign starting from the left is to be taken as the delimiter between components. Thus, user identifier is to the left, security domain to the right of the first \"@\". This parsing rule conforms to the POSIX \"greedy\" disambiguation method in regular expression processing. LDAP exapmple - eduPersonScopedAffiliation: faculty@cs.berkeley.edu")
  @XmlElement
  private List<String>eduPersonScopedAffiliation; 

  @ScimAttribute(description="A persistent, non-reassigned, opaque identifier for a principal.")
  @XmlElement
  private List<String> eduPersonTargetedID; 

  @ScimAttribute(description="Set of URIs that assert compliance with specific standards for identity assurance. LDAP example - eduPersonAssurance: urn:mace:incommon:IAQ:sample")
  @XmlElement
  private List<String> eduPersonAssurance;

  @ScimAttribute(description=" A long-lived, non re-assignable, omnidirectional identifier suitable for use as a principal identifier by authentication providers or as a unique external key by applications. LDAP example - eduPersonUniqueId: 28c5353b8bb34984a8bd4169ba94c606@foo.edu")
  @XmlElement
  private String eduPersonUniqueId;

  @ScimAttribute(description="ORCID iDs are persistent digital identifiers for individual researchers. Their primary purpose is to unambiguously and definitively link them with their scholarly work products. ORCID iDs are assigned, managed and maintained by the ORCID organization. LDAP example - eduPersonOrcid: http://orcid.org/0000-0002-1825-0097")
  @XmlElement
  private List<String> eduPersonOrcid;

  @ScimAttribute(description="RFC1274 notes that the proprietary format they recommend is \"interim\" only.")
  @XmlElement
  private String audio;

  @ScimAttribute(description="Common name.")
  @XmlElement
  private String cn;

  @ScimAttribute(description="Open-ended; whatever the person or the directory manager puts here. According to RFC4519, \"The 'description' attribute type contains human-readable descriptive phrases about the object. Each description is one value of this multi-valued attribute.\" LDIF example - description: A jolly good felon")
  @XmlElement
  private String description;

  @ScimAttribute(description="The name(s) that should appear in white-pages-like applications for this person. LDIF example - displayName: Jack Dougherty")
  @XmlElement
  private String displayName;

  @ScimAttribute(description="According to RFC4519: \"The 'facsimileTelephoneNumber' attribute type contains telephone numbers (and, optionally, the parameters) for facsimile terminals. Each telephone number is one value of this multi-valued attribute.\" LDIF example - facsimileTelephoneNumber: +44 71 123 4567")
  @XmlElement
  private List<String> facsimileTelephoneNumber;

  @ScimAttribute(description="From RFC4519 description:\"The 'givenName' attribute type contains name strings that are the part of a person's name that is not their surname. Each string is one value of this multi-valued attribute.\" LDIF example - givenName: Stephen")
  @XmlElement
  private List<String> givenName;

  @ScimAttribute(description="From RFC1274 description: \"The [homePhone] attribute type specifies a home telephone number associated with a person.\" LDIF example - homePhone: +1 608 555 1212")
  @XmlElement
  private List<String> homePhone;

  @ScimAttribute(description="From RFC1274 description: \"The Home postal address attribute type specifies a home postal address for an object. This should be limited to up to 6 lines of 30 characters each.\" LDIF example - homePostalAddress: 1212 Como Ave.$Midton, SD 45621$USA")
  @XmlElement
  private List<String> homePostalAddress;

  @ScimAttribute(description="From RFC4519 description: \"The 'initials' attribute type contains strings of initials of some or all of an individual's names, except the surname(s). Each string is one value of this multi-valued attribute.\" LDIF example - initials: f x")
  @XmlElement
  private List<String> initials;

  @ScimAttribute(description="Follow inetOrgPerson definition of RFC2798: \"Used to store one or more images of a person using the JPEG File Interchange Format [JFIF].\"")
  @XmlElement
  private List<Byte[]> jpegPhoto;

  @ScimAttribute(description="locality name. LDIF example - l: Hudson Valley")
  @XmlElement
  private List<String> locality;

  @ScimAttribute(description="Follow inetOrgPerson definition of RFC2079: \"Uniform Resource Identifier with optional label.\"  LDIF example - labeledURI: http://www.hsww.wiz/%7Eputter Harry's home page")
  @XmlElement 
  private List<String> labeledURI;

  @ScimAttribute(description="From RFC4524: The 'mail' (rfc822mailbox) attribute type holds Internet mail addresses in Mailbox [RFC2821] form (e.g., user@example.com). LDIF example - mail: dumbledore@hsww.wiz")
  @XmlElement 
  @Email
  private List<String> mail;

  @ScimAttribute(description="From RFC4524: \"The 'manager' attribute specifies managers, by distinguished name, of the person (or entity).\" LDIF example - manager: uid=twilliams, ou=people, dc=hobart, dc=edu")
  @XmlElement 
  private List<String> manager;

  @ScimAttribute(description="From RFC4524: \"The 'mobile' (mobileTelephoneNumber) attribute specifies mobile telephone numbers (e.g., \"+1 775 555 6789\") associated with a person (or entity).\" LDIF example - mobile: +47 22 44 66 88")
  @XmlElement 
  private List<String> mobile;

  @ScimAttribute(name="o", description="Standard name of the top-level organization (institution) with which this person is associated. LDIF example - o: St. Cloud State")
  @XmlElement(name="o")
  private List<String> organization;

  @ScimAttribute(name="ou", description="Organizational unit(s). According to X.520(2000), \"The Organizational Unit Name attribute type specifies an organizational unit. When used as a component of a directory name it identifies an organizational unit with which the named object is affiliated. The designated organizational unit is understood to be part of an organization designated by an OrganizationName [o] attribute. It follows that if an Organizational Unit Name attribute is used in a directory name, it must be associated with an OrganizationName [o] attribute. An attribute value for Organizational Unit Name is a string chosen by the organization of which it is a part.\" LDIF example - ou: Faculty Senate")
  @XmlElement (name="ou")
  private List<String > organizationalUnitName;

  @ScimAttribute(description="From RFC4524: \"The 'pager' (pagerTelephoneNumber) attribute specifies pager telephone numbers (e.g., \"+1 775 555 5555\") for an object.\" LDIF example - pager: +1 202 555 4321")
  @XmlElement 
  private List<String> pager;

  @ScimAttribute(description="Campus or office address. inetOrgPerson has a homePostalAddress that complements this attribute. X.520(2000) reads: \"The Postal Address attribute type specifies the address information required for the physical postal delivery to an object.\" LDIF example - postalAddress: P.O. Box 333$Whoville, WH 99999$USA")
  @XmlElement 
  private List<String> postalAddress;

  @ScimAttribute(description="Follow X.500(2001): \"The postal code attribute type specifies the postal code of the named object. If this attribute\" LDIF example - postalCode: 54321")
  @XmlElement 
  private List<String> postalCode;

  @ScimAttribute(description="From RFC4519: \"The 'postOfficeBox' attribute type contains postal box identifiers that a Postal Service uses when a customer arranges to receive mail at a box on the premises of the Postal Service. Each postal box identifier is a single value of this multi-valued attribute.\" SCIM example - postOfficeBox: 109260")
  @XmlElement 
  private List<String> postOfficeBox;

  @ScimAttribute(description="Follow inetOrgPerson definition of RFC2798: \"preferred written or spoken language for a person.\" LDIF example - preferredLanguage: EO")
  @XmlElement 
  private String preferredLanguage;

  @ScimAttribute(description="From RFC4519: The 'seeAlso' attribute type contains the distinguished names of objects that are related to the subject object. Each related object name is one value of this multi-valued attribute.\" LDIF example - seeAlso: cn=Department Chair, ou=physics, o=University of Technology, dc=utech, dc=ac, dc=uk")
  @XmlElement 
  private List<String> seeAlso;

  @ScimAttribute(name="sn", description="Surname or family name. From RFC4519: \"The 'sn' ('surname' in X.500) attribute type contains name strings for the family names of a person. Each string is one value of this multi-valued attribute.\" LDIF example - sn: Carson-Smith")
  @XmlElement(name="sn")
  private List<String> surname;

  @ScimAttribute(name="st", description="Abbreviation for state or province name. LDIF example - st: IL")
  @XmlElement(name="st")
  private List<String> state;

  @ScimAttribute(description="From RFC4519: \"The 'street' ('streetAddress' in X.500) attribute type contains site information from a postal address (i.e., the street name, place, avenue, and the house number). Each street is one value of this multi-valued attribute.\" LDIF example - street: 303 Mulberry St")
  @XmlElement
  private List<String> street;

  @ScimAttribute(description="Office/campus phone number. Attribute values should comply with the international format specified in ITU Recommendation E.123: e.g., \"+44 71 123 4567.\" LDIF example - telephoneNumber: +1 212 555 1234")
  @XmlElement
  private List<String> telephoneNumber;

  @ScimAttribute(description="From RFC4519: \"The 'title' attribute type contains the title of a person in their organizational context. Each title is one value of this multi-valued attribute.\" LDIF example - title: Assistant Vice-Deputy for Redundancy Reduction")
  @XmlElement
  private List<String> title;

  @ScimAttribute(description="From RFC4519: \"The 'uid' ('userid' in RFC1274) attribute type contains computer system login names associated with the object. Each name is one value of this multi-valued attribute.\" LDIF example - uid: gmettes")
  @XmlElement
  private List<String> uid;

  @ScimAttribute(description="From RFC4524: \"The 'uniqueIdentifier' attribute specifies a unique identifier for an object represented in the Directory. The domain within which the identifier is unique and the exact semantics of the identifier are for local definition. For a person, this might be an institution- wide payroll number. For an organizational unit, it might be a department code.\"")
  @XmlElement
  private List<String> uniqueIdentifier;

  @ScimAttribute(description="A user's X.509 certificate.")
  @XmlElement
  private List<String> userCertificate;

  @ScimAttribute(returned = Returned.NEVER, description="This attribute identifies the entry's password and encryption method in the following format: {encryption method}encrypted password.")
  @XmlElement
  private List<String> userPassword;

  @ScimAttribute(description="An X.509 certificate specifically for use in S/MIME applications (see RFCs 2632, 2633 and 2634).")
  @XmlElement
  private List<String> userSMIMECertificate;

  @ScimAttribute(description="Defined originally in X.509(96) and included in RFC2256.")
  @XmlElement
  private List<String> x500uniqueIdentifier;

  @Override
  public String getUrn() {
    return SCHEMA_URI;
  }

}
