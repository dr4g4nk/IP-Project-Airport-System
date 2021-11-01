package org.unibl.etf.rest.client;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;
import org.unibl.etf.dto.Drzava;

import com.google.gson.Gson;

public class RestClient {
	
	public RestClient() {
		// TODO Auto-generated constructor stub
	}
	
	public static List<Drzava> getDrzave(){
		ClientConfig clientConfig = new ClientConfig();
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget webTarget = client.target(UriBuilder.fromUri("http://api.countrylayer.com").build()).path("v2").path("region").path("europe").queryParam("access_key", "a95e2b95a0629d7fdd0ab2acdbee4339");
		
		Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();
		Drzava[] drzave = new Gson().fromJson(response.readEntity(String.class), Drzava[].class);
		
		return Arrays.asList(drzave);
	}

}
