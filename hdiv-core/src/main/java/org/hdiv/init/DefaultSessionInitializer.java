/**
 * Copyright 2005-2016 hdiv.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.hdiv.init;

import javax.servlet.http.HttpSession;

import org.hdiv.config.HDIVConfig;
import org.hdiv.idGenerator.PageIdGenerator;
import org.hdiv.util.Constants;
import org.hdiv.util.HDIVUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Default implementation of {@link SessionInitializer}.
 * <p>
 * Initializes and destroys {@link HttpSession} scoped attributes.
 * 
 * @since 2.1.10
 */
public class DefaultSessionInitializer implements SessionInitializer, ApplicationContextAware {

	public static final String HDIV_PARAMETER = "HDIVParameter";

	public static final String MODIFY_STATE_HDIV_PARAMETER = "modifyHDIVStateParameter";

	protected HDIVConfig config;

	protected ApplicationContext applicationContext;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.hdiv.init.SessionInitializer#initializeSession(javax.servlet.http.HttpSession)
	 */
	public void initializeSession(final HttpSession session) {

		initPageIdGenerator(session);
		initStateParameterNames(session);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.hdiv.init.SessionInitializer#destroySession(javax.servlet.http.HttpSession)
	 */
	public void destroySession(final HttpSession session) {

	}

	/**
	 * PageIdGenerator initialization.
	 * 
	 * @param httpSession http session
	 */
	protected void initPageIdGenerator(final HttpSession httpSession) {

		// Obtain new instance of PageIdGenerator
		PageIdGenerator pageIdGenerator = applicationContext.getBean(PageIdGenerator.class);
		httpSession.setAttribute(Constants.PAGE_ID_GENERATOR_NAME, pageIdGenerator);
	}

	/**
	 * State parameter names initialization.
	 * 
	 * @param httpSession http session
	 * @since HDIV 1.1
	 */
	protected void initStateParameterNames(final HttpSession httpSession) {

		String hdivParameterName;
		String modifyHdivStateParameterName;

		if (config.isRandomName()) {
			hdivParameterName = HDIVUtil.createRandomToken(Integer.MAX_VALUE);
			modifyHdivStateParameterName = HDIVUtil.createRandomToken(Integer.MAX_VALUE);
		}
		else {
			hdivParameterName = config.getStateParameterName();
			modifyHdivStateParameterName = config.getModifyStateParameterName();
		}

		httpSession.setAttribute(HDIV_PARAMETER, hdivParameterName);
		httpSession.setAttribute(MODIFY_STATE_HDIV_PARAMETER, modifyHdivStateParameterName);
	}

	public void setApplicationContext(final ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	/**
	 * @param config the config to set
	 */
	public void setConfig(final HDIVConfig config) {
		this.config = config;
	}
}
