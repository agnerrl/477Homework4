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

import pluginframework.IServlet;
import pluginframework.Plugin;
import protocol.HttpRequest;
import protocol.HttpResponse;

/**
 * 
 * @author Chandan R. Rupakheti (rupakhcr@clarkson.edu)
 */
<<<<<<< HEAD:SimpleWebServer/src/pluginframework/ServletRegistry.java
public class ServletRegistry {
	
	private ServletRegistry registry;
	private HashMap<String, AbstractServlet> servletMapping;
	
	private ServletRegistry() {
		
=======
public class ConcretePlugin extends Plugin{
	IServlet servlet;

	/**
	 * @param directory
	 */
	public ConcretePlugin(String directory) {
		super(directory);
		this.servlet = new ConcreteServlet(directory);
>>>>>>> e8279497cc9f24a78f6e7dfe63ac66442de28a7e:SimpleWebServer/src/concretePlugin/ConcretePlugin.java
	}
	
	public HttpResponse routeRequest(HttpRequest request){
		return this.servlet.execute(request);
	}

}
