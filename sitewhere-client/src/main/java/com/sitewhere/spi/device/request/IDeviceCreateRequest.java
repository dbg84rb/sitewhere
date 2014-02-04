/*
 * IDeviceCreateRequest.java 
 * --------------------------------------------------------------------------------------
 * Copyright (c) Reveal Technologies, LLC. All rights reserved. http://www.reveal-tech.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package com.sitewhere.spi.device.request;

import com.sitewhere.spi.common.IMetadataProvider;
import com.sitewhere.spi.device.DeviceStatus;

/**
 * Interface for arguments needed to create a device.
 * 
 * @author Derek
 */
public interface IDeviceCreateRequest extends IMetadataProvider {

	/**
	 * Get the unique device hardware id.
	 * 
	 * @return
	 */
	public String getHardwareId();

	/**
	 * Get the device specification token.
	 * 
	 * @return
	 */
	public String getSpecificationToken();

	/**
	 * Get comments associated with device.
	 * 
	 * @return
	 */
	public String getComments();

	/**
	 * Get device status indicator.
	 * 
	 * @return
	 */
	public DeviceStatus getStatus();
}