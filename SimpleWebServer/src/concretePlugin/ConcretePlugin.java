/*
 * ConcretePlugin.java
 * Nov 1, 2013
 *
 * Simple Web Server (SWS) for EE407/507 and CS455/555
 * 
 * Copyright (C) 2011 Chandan Raj Rupakheti, Clarkson University
 * 
 * This program is free software: you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public License 
 * as published by the Free Software Foundation, either 
 * version 3 of the License, or any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/lgpl.html>.
 * 
 * Contact Us:
 * Chandan Raj Rupakheti (rupakhcr@clarkson.edu)
 * Department of Electrical and Computer Engineering
 * Clarkson University
 * Potsdam
 * NY 13699-5722
 * http://clarkson.edu/~rupakhcr
 */
 
package concretePlugin;

import java.io.File;
import java.util.HashMap;

import pluginframework.Plugin;
import protocol.HttpRequest;
import protocol.HttpResponse;
import protocol.HttpResponseFactory;

/**
 * 
 * @author Chandan R. Rupakheti (rupakhcr@clarkson.edu)
 */
public class ConcretePlugin extends Plugin{

	/**
	 * @param directory
	 */
	public ConcretePlugin(String directory) {
		super(directory);
		// TODO Auto-generated constructor stub
	}
	
	public HttpResponse routeRequest(HttpRequest request){
		String method = request.getMethod();
		HttpResponse out = null;
		if(method == "GET")
			out = processGET(request);
		else if(method == "POST")
			out = processPOST(request);
		else if(method == "PUT")
			out = processPUT(request);
		else if(method == "DELETE")
			out = processDELETE(request);
		return out;
	}

	/**
	 * @param request
	 * @return
	 */
	private HttpResponse processDELETE(HttpRequest request) {
		return processRequest("DELETE was processed");
	}

	/**
	 * @return
	 */
	private HttpResponse processRequest(String message) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("header", "value");
		map.put("header2", "value2");
		File file = new File(super.directory + "/index.html");
		HttpResponse out = new HttpResponse("HTTP/1.1", 200, message, map, file);
		return out;
	}

	/**
	 * @param request
	 * @return
	 */
	private HttpResponse processPUT(HttpRequest request) {
		return processRequest("PUT was processed");
	}

	/**
	 * @param request
	 * @return
	 */
	private HttpResponse processPOST(HttpRequest request) {
		return processRequest("POST was processed");
	}

	/**
	 * @param request
	 * @return
	 */
	private HttpResponse processGET(HttpRequest request) {
		return processRequest("GET was processed");
	}

}
