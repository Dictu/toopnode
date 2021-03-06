package eu.toop.node.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import eu.toop.node.model.Address;
import eu.toop.node.model.ChamberOfCommerceDataSet;
import eu.toop.node.util.RestClient;

@Service
public class CountrySpecificProviderService extends RestClient {
	
	public CountrySpecificProviderService() {
		super();
	}
	
	public ChamberOfCommerceDataSet getDataSet(String url, String companyCode) throws IOException {
		ChamberOfCommerceDataSet set = new ChamberOfCommerceDataSet();
		String json = super.get(url + companyCode);
		JsonNode root = new ObjectMapper().readTree(json.getBytes(StandardCharsets.UTF_8));
		set.setCompanyCode(root.get("companyCode").asText());
		set.setCompanyName(root.get("companyName").asText());
		set.setCompanyType(root.get("companyType").asText());
		set.setLegalStatus(root.get("legalStatus").asText());
		set.setLegalStatusEffectiveDate(root.get("legalStatusEffectiveDate").asText());
		set.setRegistrationAuthority(root.get("registrationAuthority").asText());
		set.setRegistrationDate(root.get("registrationDate").asText());
		set.setRegistrationNumber(root.get("registrationNumber").asText());
		set.setActivityDeclaration(root.get("activityDeclaration").asText());
		Address address = new Address();
		address.setStreetName(root.get("headOfficeAddres").get("streetName").asText());
		address.setPostalCode(root.get("headOfficeAddres").get("postalCode").asText());
		address.setCity(root.get("headOfficeAddres").get("city").asText());
		address.setCountry(root.get("headOfficeAddres").get("country").asText());
		set.setHeadOfficeAddres(address);
		return set;
	}
}
