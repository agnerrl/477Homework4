/*
 * PluginRouter.java
 * Oct 31, 2013
 *
 * Simple Web Server (SWS) for CSSE 477:  Software Architecture
 * 
 * Copyright (C) 2013 John MacAslan, Alex Memering, Rachel Agner, Caleb Drake
 * Rose-HUlman Institute of Technology
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
 * 
 */

package pluginframework;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Timer;
import java.util.TimerTask;

import protocol.HttpRequest;
import protocol.HttpResponse;
import server.Server;

public class PluginRouter {

	private HashMap<String, AbstractPlugin> pluginMapping;
	private File pluginDirectory = new File("..\\plugins");
	private Timer pluginMonitor = new Timer();
	private ClassLoader pluginLoader = PluginRouter.class.getClassLoader();
	private String pluginJarName, pluginClassName;
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

	public HttpResponse routeToPlugin(HttpRequest request, Server server) {
		StringTokenizer tokenizer = new StringTokenizer(request.getUri(), "/");
		AbstractPlugin plugin = pluginMapping.get(tokenizer.nextToken());
		if (null == plugin) {
			return null;
		} else {
			return plugin.routeToServlet(request, server);
		}
	}

	@SuppressWarnings("unchecked")
	private void addPlugin() {
		File[] files = pluginDirectory.listFiles();
		if (null != files) {
			for (File file : files) {
				if (file.isDirectory()) {
					config = new File(file.getPath() + "/.config");
					if (config.exists()) {
						try {
							scanner = new Scanner(config);
							String pluginJarDirectory = file.getPath() + "\\";
							pluginJarName = scanner.nextLine();
							pluginClassName = scanner.nextLine();
							scanner.close();
							// /
							String pluginTitle = pluginJarName.substring(0,
									pluginJarName.length() - 4);

							pluginTitle = pluginTitle.replace('/', '.');

							/*
							 * TODO: PLUGIN FILE LOADING
							 */
							// /////////////////////////////////////////
							// This is where file loading gets hairy //
							// /////////////////////////////////////////
							/*
							 * 
							 *  ATTEMPT 1/2
							 *  Loads Jar file as a File object
							 */
							File jarFile = new File(pluginJarDirectory
									+ pluginJarName);

							URL url = jarFile.toURI().toURL();
							URL[] urls = { url };

							/*
							 * 
							 * HERE is where the the hairy loading gets all tangled-up
							 * 
							 * The loader should have an array of class names
							 * (viewable via debugger -> Variables view)
							 * However, the array is either empty or just
							 * displays classes not in the JAR file
							 * I think I acquired the JAR's classes once; yet,
							 * they would not instantiate
							 */
							ClassLoader loader = new URLClassLoader(urls);

							pluginClass = (Class<AbstractPlugin>) loader
									.loadClass("concreteplugin.ConcretePlugin");
							System.out.println("Executing...");
							Object tester = pluginClass.newInstance();

							/*
							 *  ATTEMPT 2/2
							 *  Loads Jar file as a JarFile object and enumerate over Jar Entries
							 *  This is the basic method for looking-through archive files (i.e. JAR, ZIP, RAR, ...)
							 *  
							 *  General processes are the same as above.
							 */
							// JarFile jarFile = new JarFile(pluginJarDirectory
							// + pluginJarName);
							// Enumeration<JarEntry> e = jarFile.entries();

							// URL url = new URL("jar:file:"
							// + "concreteplugin!/");
							// JarClassLoader cl = new JarClassLoader(url);
							// JarEntry je = null;
							// while (e.hasMoreElements()) {
							// je = (JarEntry) e.nextElement();
							//
							// if (je.getName().equals(pluginClassName)) {
							// pluginClass = (Class<AbstractPlugin>) cl
							// .loadClass(pluginClassName);
							// pluginMapping.put(pluginTitle,
							// (AbstractPlugin) tester);
							// break;
							// }
							// }

						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
	}
}
