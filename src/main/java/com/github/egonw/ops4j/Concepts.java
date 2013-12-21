/* Copyright (C) 2013  Egon Willighagen <egonw@users.sf.net>
 * 
 * Contact: cdk-devel@lists.sourceforge.net
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public License
 * as published by the Free Software Foundation; either version 2.1
 * of the License, or (at your option) any later version.
 * All we ask is that proper credit is given for our work, which includes
 * - but is not limited to - adding the above copyright notice to the beginning
 * of your source code files, and to any copyright notice that you may distribute
 * with programs based on this work.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 */
package com.github.egonw.ops4j;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpException;
import org.apache.http.client.ClientProtocolException;

public class Concepts extends AbstractOPS4JClient {

	private Concepts(String server, String appID, String appKey) throws MalformedURLException {
		this.server = server;
		if (!this.server.endsWith("/")) this.server += "/";
		new URL(this.server); // validate the server URL
		this.appID = appID;
		this.appKey = appKey;
	}

	public static Concepts getInstance(String server, String apiID, String appKey) throws MalformedURLException {
		return new Concepts(server, apiID, appKey);
	}

	public String freetext(String text, Object... objects) throws ClientProtocolException, IOException, HttpException {
		Map<String,String> params = new HashMap<String,String>();
		params.put("q", text);
		return runRequest(server + "search/freetext", params, objects);
	}

	public String freetextByTag(String text, String uuid, Object... objects)
	throws ClientProtocolException, IOException, HttpException {
		Map<String,String> params = new HashMap<String,String>();
		params.put("q", text);
		params.put("uuid", uuid);
		return runRequest(server + "search/byTag", params, objects);
	}

	public String description(String conceptWikiID, Object... objects)
	throws ClientProtocolException, IOException, HttpException {
		Map<String,String> params = new HashMap<String,String>();
		params.put("uuid", conceptWikiID);
		return runRequest(server + "getConceptDescription", params, objects);
	}
}