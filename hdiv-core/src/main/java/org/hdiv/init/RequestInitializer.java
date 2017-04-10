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

import org.hdiv.context.RequestContextHolder;
import org.hdiv.filter.RequestWrapper;
import org.hdiv.filter.ResponseWrapper;
import org.hdiv.filter.ValidatorFilter;

/**
 * Methods for request scope data and wrapper initialization. Used in {@link ValidatorFilter}.
 * 
 * @author Gotzon Illarramendi
 * @since 2.1.5
 */
public interface RequestInitializer {

	/**
	 * Initialize request scoped data
	 * 
	 * @param request request object
	 * @param response response object
	 */
	void initRequest(RequestContextHolder context);

	/**
	 * Destroy request scoped data
	 * 
	 * @param request request object
	 * @param response response object
	 */
	void endRequest(RequestContextHolder context);

	/**
	 * Create request wrapper.
	 * 
	 * @param request HTTP request
	 * @param response HTTP response
	 * @return the request wrapper
	 */
	RequestWrapper createRequestWrapper(RequestContextHolder context);

	/**
	 * Create response wrapper.
	 * 
	 * @param request HTTP request
	 * @param response HTTP response
	 * @return the response wrapper
	 */
	ResponseWrapper createResponseWrapper(RequestContextHolder context);
}
