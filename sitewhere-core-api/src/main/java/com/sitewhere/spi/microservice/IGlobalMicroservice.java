/*
 * Copyright (c) SiteWhere, LLC. All rights reserved. http://www.sitewhere.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package com.sitewhere.spi.microservice;

import com.sitewhere.spi.SiteWhereException;
import com.sitewhere.spi.microservice.configuration.IConfigurableMicroservice;

/**
 * Microservice that serves a global funcition in a SiteWhere instance.
 * 
 * @author Derek
 */
public interface IGlobalMicroservice extends IConfigurableMicroservice {

    /**
     * Get configuration data (for global microservices).
     * 
     * @return
     */
    public byte[] getConfiguration() throws SiteWhereException;
}