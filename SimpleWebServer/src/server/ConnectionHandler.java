/*
 * ConnectionHandler.java
 * Oct 7, 2012
 *
 * Simple Web Server (SWS) for CSSE 477
 * 
 * Copyright (C) 2012 Chandan Raj Rupakheti
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
 */
 
package server;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import protocol.HttpRequest;
import protocol.HttpResponse;
import protocol.HttpResponseFactory;
import protocol.Protocol;

/**
 * This class is responsible for handling a incoming request
 * by creating a {@link HttpRequest} object and sending the appropriate
 * response be creating a {@link HttpResponse} object. It implements
 * {@link Runnable} to be used in multi-threaded environment.
 * 
 * @author Chandan R. Rupakheti (rupakhet@rose-hulman.edu)
 */
public class ConnectionHandler implements Runnable {
	private Server server;
	private Socket socket;
	
	public ConnectionHandler(Server server, Socket socket) {
		this.server = server;
		this.socket = socket;
	}
	
	/**
	 * @return the socket
	 */
	public Socket getSocket() {
		return socket;
	}


	/**
	 * The entry point for connection handler. It first parses
	 * incoming request and creates a {@link HttpRequest} object,
	 * then either passes the request to a plugin or handles the
	 * request statically based on the URI of the request. 
	 */
	public void run() {
		long start = System.currentTimeMillis();
		
		InputStream inStream = null;
		OutputStream outStream = null;
		
		try {
			inStream = this.socket.getInputStream();
			outStream = this.socket.getOutputStream();
		}
		catch(Exception e) {
			// Cannot do anything if we have exception reading input or output stream
			e.printStackTrace();
			server.incrementConnections(1);
			long end = System.currentTimeMillis();
			this.server.incrementServiceTime(end-start);
			return;
		}
		
		HttpRequest request = null;
		HttpResponse response = null;
		try {
			request = HttpRequest.read(inStream);
		}
		catch(Exception e) {
			e.printStackTrace();
			response = HttpResponseFactory.create400BadRequest(Protocol.CLOSE);
		}
		
		if(response != null) {
			try {
				response.write(outStream);
			}
			catch(Exception e){
				e.printStackTrace();
			}

			server.incrementConnections(1);
			long end = System.currentTimeMillis();
			this.server.incrementServiceTime(end-start);
			return;
		}
		
		// attempt to have a plugin handle the request
		response = server.getPluginRouter().routeToPlugin(request, server);
		
		// handle the request statically if there was no plugin for it
		if(response == null) {
			response = staticFileHandling(request);
		}
		
		try {
			response.write(outStream);
			socket.close();
		}
		catch (Exception e){
			e.printStackTrace();
		} 
		
		server.incrementConnections(1);
		long end = System.currentTimeMillis();
		this.server.incrementServiceTime(end-start);
	}

	private HttpResponse staticFileHandling(HttpRequest request) {
		HttpResponse response = null;
		try {
			// only handle requests that match the protocol version
			if(request.getVersion().equalsIgnoreCase(Protocol.VERSION)) {
				response = Server.getDefaultServletMapping().get(request.getMethod()).execute(request, server);
			}
			if(null == response) {
				response = HttpResponseFactory.create400BadRequest(Protocol.CLOSE);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			response = HttpResponseFactory.create400BadRequest(Protocol.CLOSE);
		}
		return response;
	}
}
