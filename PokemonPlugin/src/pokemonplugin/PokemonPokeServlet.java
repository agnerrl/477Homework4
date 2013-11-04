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
 
package pokemonplugin;

import java.io.File;
import java.io.FileWriter;

import pluginframework.AbstractServlet;
import protocol.HttpRequest;
import protocol.HttpResponse;
import protocol.HttpResponseFactory;
import protocol.Protocol;
import server.Server;

public class PokemonPokeServlet extends AbstractServlet {

	public PokemonPokeServlet(){
	}

	/* (non-Javadoc)
	 * @see pluginframework.AbstractServlet#execute(protocol.HttpRequest)
	 */
	@Override
	public HttpResponse execute(HttpRequest request, Server server) {
		String poke = "Rachel is the best Pokemon master!!!!";
		HttpResponse response = null;
		String uri = request.getUri();
		String rootDirectory = server.getRootDirectory();
		File file = new File(rootDirectory + uri);
		FileWriter writer;
		try{
			writer = new FileWriter(file, true);
			// appends body of the request to the file contents
			writer.append(request.getBody());
			//writer.append(poke);
			writer.close();
			response = HttpResponseFactory.create200OK(file, Protocol.CLOSE);
		}
		catch (Exception e) {
			e.printStackTrace();
			response = HttpResponseFactory.create400BadRequest(Protocol.CLOSE);
		}
		return response;
	}
}

