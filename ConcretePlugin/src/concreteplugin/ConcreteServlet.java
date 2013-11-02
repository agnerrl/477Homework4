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
 
package concreteplugin;

import pluginframework.AbstractServlet;
import protocol.HttpRequest;
import protocol.HttpResponse;

public class ConcreteServlet extends AbstractServlet {

	public ConcreteServlet(){
	}

//	/**
//	 * @return
//	 */
//	private HttpResponse processRequest(String message) {
//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("header", "value");
//		map.put("header2", "value2");
//		File file = new File(this.directory + "/index.html");
//		HttpResponse out = new HttpResponse("HTTP/1.1", 200, message, map, file);
//		return out;
//	}

	/* (non-Javadoc)
	 * @see pluginframework.AbstractServlet#execute(protocol.HttpRequest)
	 */
	@Override
	public HttpResponse execute(HttpRequest request) {
		// TODO Auto-generated method stub
		return null;
	}
}

