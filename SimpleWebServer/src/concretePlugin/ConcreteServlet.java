/*
 * ConcreteServlet.java
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

import java.io.*;
import java.util.HashMap;

import pluginframework.IServlet;
import protocol.HttpRequest;
import protocol.HttpResponse;

public class ConcreteServlet extends IServlet {
	private String directory;

	public ConcreteServlet(String directory){
		this.directory = directory;
	}
	
	/**
	 * @param request
	 * @return
	 */
	private HttpResponse doDelete(HttpRequest request) {
		return processRequest("DELETE was processed");
	}

	/**
	 * @return
	 */
	private HttpResponse processRequest(String message) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("header", "value");
		map.put("header2", "value2");
		File file = new File(this.directory + "/index.html");
		HttpResponse out = new HttpResponse("HTTP/1.1", 200, message, map, file);
		return out;
	}

	/**
	 * @param request
	 * @return
	 */
	private HttpResponse doPut(HttpRequest request) {
		return processRequest("PUT was processed");
	}

	/**
	 * @param request
	 * @return
	 */
	private HttpResponse doPost(HttpRequest request) {
		return processRequest("POST was processed");
	}

	/**
	 * @param request
	 * @return
	 */
	private HttpResponse doGet(HttpRequest request) {
		return processRequest("GET was processed");
	}
}

