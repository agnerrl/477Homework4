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
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Timer;
import java.util.TimerTask;

import protocol.HttpRequest;
import protocol.HttpResponse;
import protocol.HttpResponseFactory;
import protocol.Protocol;

public class PluginRouter {

	private HashMap<String, AbstractPlugin> pluginMapping;
	private File pluginDirectory = new File("plugins");
	private Timer pluginMonitor = new Timer();
	private ClassLoader pluginLoader = PluginRouter.class.getClassLoader();
	private String pluginName;
	private File config;
	private Scanner scanner;
	private Class<AbstractPlugin> pluginClass;

	public PluginRouter() {
		pluginMapping = new HashMap<String, AbstractPlugin>();
		pluginMonitor.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				addPlugin();
			}
		}, 0, 2500);
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
		File[] files = pluginDirectory.listFiles();
		if (null != files) {
			for (File file : files) {
				if (file.isDirectory()) {
					config = new File(file.getPath() + "/.config");
					if (config.exists()) {
						try {
							scanner = new Scanner(config);
							pluginName = scanner.nextLine();
							scanner.close();
							pluginClass = (Class<AbstractPlugin>) pluginLoader
									.loadClass(pluginName);
							pluginMapping.put(pluginName,
									pluginClass.newInstance());
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
	}
}
