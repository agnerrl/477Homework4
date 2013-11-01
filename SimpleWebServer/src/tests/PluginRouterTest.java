package tests;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.Test;

import pluginframework.PluginRouter;

/*
 * PluginRouterTest.java
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

/**
 * 
 * @author Chandan R. Rupakheti (rupakhcr@clarkson.edu)
 */
public class PluginRouterTest {
	private PluginRouter pluginTester = new PluginRouter();

	/**
	 * Test method for {@link pluginframework.PluginRouter#PluginRouter()}.
	 */
	@Test
	public void testPluginRouter() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link pluginframework.PluginRouter#routeToPlugin(protocol.HttpRequest)}.
	 */
	@Test
	public void testRouteMessage() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link pluginframework.PluginRouter#addPlugin()}.
	 */
	@Test
	public void testAddPlugin() {
		ArrayList<String> fileNames = pluginTester.addPlugin();
		System.out.println(fileNames.get(0));
		Assert.assertNotNull(fileNames);
	}

}
