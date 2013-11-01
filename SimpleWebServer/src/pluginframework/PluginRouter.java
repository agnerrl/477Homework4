/*
 * PluginRouter.java
 * Oct 31, 2013
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

package pluginframework;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.Timer;
import java.util.TimerTask;

import protocol.HttpRequest;
import protocol.HttpResponse;
import protocol.HttpResponseFactory;
import protocol.Protocol;

/**
 * 
 * @author Chandan R. Rupakheti (rupakhcr@clarkson.edu)
 */
public class PluginRouter {

	private HashMap<String, AbstractPlugin> pluginMapping;
	private File pluginDirectory = new File("plugins");
	private Timer pluginMonitor = new Timer();
	private PluginLoader pluginLoader = new PluginLoader();

	public PluginRouter() {
		pluginMapping = new HashMap<String, AbstractPlugin>();
		pluginMonitor.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				addPlugin();
			}
		}, 0, 5000);
	}

	public HttpResponse routeToPlugin(HttpRequest request) {
		StringTokenizer tokenizer = new StringTokenizer(request.getUri(), "/");
		AbstractPlugin plugin = pluginMapping.get(tokenizer.nextToken());
		if (null == plugin) {
			return HttpResponseFactory.create404NotFound(Protocol.CLOSE);
		} else {
			return plugin.routeToServlet(request);
		}
	}

	private void addPlugin() {
		File files[] = pluginDirectory.listFiles();
		String pluginPath = "";
		File config;
		for (File file : files) {
			if (file.isDirectory()) {
				// grab name from .config file
				// load plugin class
				// add to map
			}
		}
	}
}
