package it.unimore.deduplication;

import java.io.Serializable;

import de.uni_mannheim.informatik.dws.winter.model.AbstractRecord;
import de.uni_mannheim.informatik.dws.winter.model.defaultmodel.Attribute;

public class Organization extends AbstractRecord<Attribute> implements Serializable {

	public static final Attribute LABEL = new Attribute("label");
	public static final Attribute CITY = new Attribute("city");
	public static final Attribute POSTCODE = new Attribute("postcode");
	public static final Attribute LINK = new Attribute("links");

	static String id;
	static String acronym;
	static String alias;
	static String label;
	static String creationYear;
	static String commercialLabel;
	static String address;
	static String city;
	static String citycode;
	static String country;
	static String countryCode;
	static String postcode;
	static String urbanUnit;
	static String urbanUnitCode;
	static String lat;
	static String lon;
	static String revenueRange;
	static String privateFinanceDate;
	static String employees;
	static String typeCategoryCode;
	static String typeLabel;
	static String typeKind;
	static String isPublic;
	static String leaders;
	static String links;
	static String privateOrgTypeId;
	static String privateOrgTypeLabel;
	static String activities;
	static String relations;
	static String badges;
	static String children;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAcronym() {
		return acronym;
	}

	public void setAcronym(String acronym) {
		this.acronym = acronym;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getCreationYear() {
		return creationYear;
	}

	public void setCreationYear(String creationYear) {
		this.creationYear = creationYear;
	}

	public String getCommercialLabel() {
		return commercialLabel;
	}

	public void setCommercialLabel(String commercialLabel) {
		this.commercialLabel = commercialLabel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCitycode() {
		return citycode;
	}

	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getUrbanUnit() {
		return urbanUnit;
	}

	public void setUrbanUnit(String urbanUnit) {
		this.urbanUnit = urbanUnit;
	}

	public String getUrbanUnitCode() {
		return urbanUnitCode;
	}

	public void setUrbanUnitCode(String urbanUnitCode) {
		this.urbanUnitCode = urbanUnitCode;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLon() {
		return lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}

	public String getRevenueRange() {
		return revenueRange;
	}

	public void setRevenueRange(String revenueRange) {
		this.revenueRange = revenueRange;
	}

	public String getPrivateFinanceDate() {
		return privateFinanceDate;
	}

	public void setPrivateFinanceDate(String privateFinanceDate) {
		this.privateFinanceDate = privateFinanceDate;
	}

	public String getEmployees() {
		return employees;
	}

	public void setEmployees(String employees) {
		this.employees = employees;
	}

	public String getTypeCategoryCode() {
		return typeCategoryCode;
	}

	public void setTypeCategoryCode(String typeCategoryCode) {
		this.typeCategoryCode = typeCategoryCode;
	}

	public String getTypeLabel() {
		return typeLabel;
	}

	public void setTypeLabel(String typeLabel) {
		this.typeLabel = typeLabel;
	}

	public String getTypeKind() {
		return typeKind;
	}

	public void setTypeKind(String typeKind) {
		this.typeKind = typeKind;
	}

	public String getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(String isPublic) {
		this.isPublic = isPublic;
	}

	public String getLeaders() {
		return leaders;
	}

	public void setLeaders(String leaders) {
		this.leaders = leaders;
	}

	public String getLinks() {
		return links;
	}

	public void setLinks(String links) {
		this.links = links;
	}

	public String getPrivateOrgTypeId() {
		return privateOrgTypeId;
	}

	public void setPrivateOrgTypeId(String privateOrgTypeId) {
		this.privateOrgTypeId = privateOrgTypeId;
	}

	public String getPrivateOrgTypeLabel() {
		return privateOrgTypeLabel;
	}

	public void setPrivateOrgTypeLabel(String privateOrgTypeLabel) {
		this.privateOrgTypeLabel = privateOrgTypeLabel;
	}

	public String getActivities() {
		return activities;
	}

	public void setActivities(String activities) {
		this.activities = activities;
	}

	public String getRelations() {
		return relations;
	}

	public void setRelations(String relations) {
		this.relations = relations;
	}

	public String getBadges() {
		return badges;
	}

	public void setBadges(String badges) {
		this.badges = badges;
	}

	public String getChildren() {
		return children;
	}

	public void setChildren(String children) {
		this.children = children;
	}

	@Override
	public boolean hasValue(Attribute attribute) {
		if(attribute==LABEL)
			return getLabel() != null && !getLabel().isEmpty();
		else if(attribute==CITY)
			return getCity() != null && !getCity().isEmpty();
		else if(attribute==POSTCODE)
			return getPostcode() != null;
		else if(attribute==LINK)
			return getLinks() != null;
		else
			return false;
		
	}

}
