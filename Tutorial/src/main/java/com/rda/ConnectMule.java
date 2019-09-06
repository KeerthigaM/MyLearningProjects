package com.rda;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class ConnectMule {
	private static final String amlBaseUrl="https://api-perf.marksandspencer.com/int-api/rda-service/api/frontend/1";
	public static void main(String[] args) throws FileNotFoundException
	{
		PrintStream o = new PrintStream(new File("D:\\Users\\886758\\Desktop\\Dummy.txt"));
		GetUPC(o,"F38","F01901","T00S0064");
		System.out.println("DONE!!!");
	}
	private static void GetUPC(PrintStream o, String category, String vendorNo,String UN) 
	{	
		final HttpHeaders headers = getHeader();		
		final HttpEntity<?> requestEntity = new HttpEntity<Object>(headers);
		final RestTemplate restTemplate = new RestTemplate();
		String specurl= "/products";
		final UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(amlBaseUrl+specurl)
				.queryParam("vendorNo", vendorNo).queryParam("categories", category);
		ResponseEntity<List<Product>> response = null;
		try {
			response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, requestEntity,
					new ParameterizedTypeReference<List<Product>>() {
					});
			if (response.getStatusCode() == HttpStatus.OK){
				System.setErr(o); 
				System.err.println(UN+"-"+vendorNo+"-"+category);
				for( Product p: response.getBody()){
					System.setErr(o); 
					System.err.println(p.getCategories()+"|"+ p.getDeptName()
								+"|"+p.getVendorNo()+"|"+p.getVendorName()
								+"|"+p.getArticleNo()+"|"+p.getArticleDescription() 
								+"|"+p.getUpc()+"|"+p.getDeliveryMethod());
					/*String classtype = getClassType(p.getDeliveryMethod());
					String siteGroup  = GetSiteGroup(classtype);
					String status = getUPT(p.getArticleNo(),siteGroup,classType,todayDate);*/
				}
			}	
		}catch(Exception e) {
			e.getMessage();
		}
	}
	private static String getClassType(String deliveryMethod) {
		switch(deliveryMethod) {		
		case "L" : return "ZFC";
		case "F" : return "ZFC";
		case "W" : return "ZFC";
		case "C" : return "ZFA,ZFB";
		case "A" : return "ZFA,ZFB";
		case "R" : return "ZFC";
		case "G" : return "ZFA,ZFB";
		case "Y" : return "ZFB";
		case "T" : return "ZFB";
		case "X" : return "ZFB";
		default  : return"";
		}
	}
	private static String GetSiteGroup(String classType) 
	{	
		String specurl= "/sitegroups";
		final MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("classType", classType);
		final UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(amlBaseUrl + specurl).queryParams(params).build().encode();
		final HttpHeaders requestHeaders = getHeader();
		final HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);
		final RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<SiteGroup>> response = null;
		String data="";
		try {
			response = restTemplate.exchange(uriComponents.toUriString(), HttpMethod.GET, requestEntity,
					new ParameterizedTypeReference<List<SiteGroup>>() {
					});
			if (response.getStatusCode() == HttpStatus.OK){
				for( SiteGroup s: response.getBody()){
				    //System.out.println(s);
				}
			}	
		}catch(Exception e) {
			e.getMessage();
		}
		return data;
	}
	private static HttpHeaders getHeader() {
		final HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "");
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		return headers;
	}
}