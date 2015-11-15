package com.github.anderskolsson.regclient.rest;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import javax.ws.rs.core.MediaType;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import com.github.anderskolsson.regclient.exceptions.RestException;
import com.github.anderskolsson.regclient.rest.elements.JsonUser;
import com.github.anderskolsson.regclient.rest.elements.LogRequest;
import com.github.anderskolsson.regclient.rest.elements.LogResponse;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.ClientResponse.Status;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

public  class RestBase {
	public static final String API_VERSION = "v1";
	private String host;
	private int port;

	public RestBase(final String host, final int port) {
		this.host = host;
		this.port = port;
	}
	
	private WebResource getRequestForPath(final String path) throws RestException {
		try {
			ClientConfig cc = new DefaultClientConfig();
			cc.getClasses().add(JacksonJsonProvider.class);
			cc.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
			cc.getClasses().add(JsonUser.class);
			cc.getClasses().add(LogRequest.class);
			Client client = Client.create(cc);
			URL target = new URL("http", this.host, this.port, "/"+API_VERSION+"/"+path);
			WebResource request = client.resource(target.toURI());
			return request;
		} catch (UniformInterfaceException | MalformedURLException | URISyntaxException e) {
			throw new RestException(e);
		}
	}
	
	public String[] getLogs(final String uuid, final String password, final int numLogs) throws RestException {
		WebResource request = getRequestForPath("users/getlogins");
		JsonUser requestUser = new JsonUser(uuid, null, password);
		LogRequest logRequest = new LogRequest(requestUser, numLogs);

		ClientResponse response = request.accept(MediaType.TEXT_PLAIN_TYPE, MediaType.APPLICATION_JSON_TYPE)
				.type(MediaType.APPLICATION_JSON_TYPE).post(ClientResponse.class, logRequest);
		if(response.getStatus() == Status.OK.getStatusCode()){
			LogResponse responseData = response.getEntity(LogResponse.class);
			return responseData.logins;
		}
		else if(response.getStatus() == Status.UNAUTHORIZED.getStatusCode()
				|| response.getStatus() == Status.INTERNAL_SERVER_ERROR.getStatusCode()){
			String responseStr = response.getEntity(String.class);
			String errMsg = "";
			if (null != responseStr) {
				errMsg = responseStr;
			} else {
				errMsg = "Error response[" + response.getStatus() + "]: " + response.getStatusInfo().getReasonPhrase();
			}
			throw new RestException(errMsg);
		}
		else {
			throw new RestException("Unknown response from server: [" + response.getStatus() + "]: "
					+ response.getStatusInfo().getReasonPhrase());
		}
	}
	
	public void login(final String uuid, final String password) throws RestException {
		WebResource request = getRequestForPath("users/login");
		JsonUser requestUser = new JsonUser(uuid, null, password);

		ClientResponse response = request.accept(MediaType.TEXT_PLAIN_TYPE)
				.type(MediaType.APPLICATION_JSON_TYPE).post(ClientResponse.class, requestUser);

		if (response.getStatus() == Status.OK.getStatusCode()) {
			System.out.println("User successfully logged in");
		} else if (response.getStatus() == Status.UNAUTHORIZED.getStatusCode()
				|| response.getStatus() == Status.INTERNAL_SERVER_ERROR.getStatusCode()) {
			String responseStr = response.getEntity(String.class);
			String errMsg = "";
			if (null != responseStr) {
				errMsg = responseStr;
			} else {
				errMsg = "Error response[" + response.getStatus() + "]: " + response.getStatusInfo().getReasonPhrase();
			}
			throw new RestException(errMsg);
		} else {
			throw new RestException("Unknown response from server: [" + response.getStatus() + "]: "
					+ response.getStatusInfo().getReasonPhrase());
		}
	}
	
	public String register(final String userName, final String password) throws RestException {
		WebResource request = getRequestForPath("users");
		JsonUser requestUser = new JsonUser(null, userName, password);

		ClientResponse response = request.accept(MediaType.APPLICATION_JSON_TYPE, MediaType.TEXT_PLAIN_TYPE)
				.type(MediaType.APPLICATION_JSON_TYPE).post(ClientResponse.class, requestUser);

		if (response.getStatus() == Status.CREATED.getStatusCode()) {
			return response.getHeaders().get("location").get(0);
		} else if (response.getStatus() == Status.BAD_REQUEST.getStatusCode()
				|| response.getStatus() == Status.INTERNAL_SERVER_ERROR.getStatusCode()) {
			String responseStr = response.getEntity(String.class);
			String errMsg = "";
			if (null != responseStr) {
				errMsg = responseStr;
			} else {
				errMsg = "Error response[" + response.getStatus() + "]: " + response.getStatusInfo().getReasonPhrase();
			}
			throw new RestException(errMsg);
		} else {
			throw new RestException("Unknown response from server: [" + response.getStatus() + "]: "
					+ response.getStatusInfo().getReasonPhrase());
		}
	}
}
