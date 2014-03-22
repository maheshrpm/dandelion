/*
 * [The "BSD licence"]
 * Copyright (c) 2013-2014 Dandelion
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 
 * 1. Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * 3. Neither the name of Dandelion nor the names of its contributors 
 * may be used to endorse or promote products derived from this software 
 * without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 * NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 * THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.github.dandelion.extras.webjar.asset.locator;

import static java.util.Collections.singletonMap;
import static org.fest.assertions.Assertions.assertThat;

import java.net.MalformedURLException;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import com.github.dandelion.core.Context;
import com.github.dandelion.core.asset.web.WebConstants;
import com.github.dandelion.core.storage.AssetStorageUnit;

public class WebjarLocatorTest {

	private WebjarLocator locator = new WebjarLocator();
	private MockHttpServletRequest request;
	
	@Before
	public void setup(){
		request = new MockHttpServletRequest();
		request.setAttribute(WebConstants.DANDELION_CONTEXT_ATTRIBUTE, new Context());
	}
	
	@Test
	public void should_return_the_internal_url(){
		AssetStorageUnit asu = new AssetStorageUnit("jquery-js", singletonMap("webjar", "jquery.js"));
		String location = locator.getLocation(asu, request);
		assertThat(location).isEqualTo("META-INF/resources/webjars/jquery/1.11.0/jquery.js");
	}
	
	@Test
	public void should_return_the_content() throws MalformedURLException{
		AssetStorageUnit asu = new AssetStorageUnit("jquery-js", singletonMap("webjar", "jquery.js"));
		String content = locator.getContent(asu, request);
		assertThat(content).contains("jQuery JavaScript Library v1.11.0");
	}
}
