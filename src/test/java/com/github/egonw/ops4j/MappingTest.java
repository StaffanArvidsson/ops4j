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

import org.apache.http.HttpException;
import org.apache.http.client.ClientProtocolException;
import org.junit.Assert;
import org.junit.Test;


public class MappingTest extends AbstractOPS4JTest {

	{ super.pickUpConfig(); }

	@Test
	public void mapUri() throws ClientProtocolException, IOException, HttpException {
		Mapping client = Mapping.getInstance(super.server, super.appID, super.appKey);
		Assert.assertNotNull(client);
		String turtle = client.mapUri("http://identifiers.org/ensembl/ENSG00000100030");
		Assert.assertNotNull(turtle);
		Assert.assertTrue(turtle.contains("prefix"));
		Assert.assertTrue(turtle.contains("http://identifiers.org/ensembl/ENSG00000100030"));
	}

	@Test
	public void mapUri_JSON() throws ClientProtocolException, IOException, HttpException {
		Mapping client = Mapping.getInstance(super.server, super.appID, super.appKey);
		Assert.assertNotNull(client);
		String json = client.mapUri(
			"http://identifiers.org/ensembl/ENSG00000100030",
			ResponseFormat.JSON
		);
		Assert.assertNotNull(json);
		Assert.assertTrue(json.contains("http://identifiers.org/ensembl/ENSG00000100030"));
	}
}
