/*
 * Copyright (c) SiteWhere, LLC. All rights reserved. http://www.sitewhere.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package com.sitewhere.web.configuration;

import com.sitewhere.spring.handler.OutboundProcessingChainParser;
import com.sitewhere.spring.handler.TenantConfigurationParser;
import com.sitewhere.web.configuration.model.AttributeNode;
import com.sitewhere.web.configuration.model.AttributeType;
import com.sitewhere.web.configuration.model.ConfigurationModel;
import com.sitewhere.web.configuration.model.ElementNode;
import com.sitewhere.web.configuration.model.ElementRole;

/**
 * Configuration model for outbound processing chain elements.
 * 
 * @author Derek
 */
public class OutboundProcessingChainModel extends ConfigurationModel {

	public OutboundProcessingChainModel() {
		addElement(createOutboundProcessingChain());
		addElement(createCommandDeliveryEventProcessorElement());
		addElement(createHazelcastEventProcessorElement());

		// Outbound processor filters.
		addElement(createFilterCriteriaElement());
		addElement(createSiteFilterElement());
	}

	/**
	 * Create the container for outbound processing chain configuration.
	 * 
	 * @return
	 */
	protected ElementNode createOutboundProcessingChain() {
		ElementNode.Builder builder =
				new ElementNode.Builder("Outbound Processors",
						TenantConfigurationParser.Elements.OutboundProcessingChain.getLocalName(),
						"sign-out", ElementRole.OutboundProcessingChain);
		builder.description("Configure a chain of processing steps that are applied to outbound data.");
		return builder.build();
	}

	/**
	 * Create a command delivery event processor.
	 * 
	 * @return
	 */
	protected ElementNode createCommandDeliveryEventProcessorElement() {
		ElementNode.Builder builder =
				new ElementNode.Builder("Command Delivery Processor",
						OutboundProcessingChainParser.Elements.CommandDeliveryEventProcessor.getLocalName(),
						"sign-out", ElementRole.OutboundProcessingChain_FilteredEventProcessor);
		builder.description("Hands off outbound device command events to the device communication subsystem. "
				+ "If this event processor is not configured, no commands will be sent to devices.");
		builder.warnOnDelete("Deleting this component will prevent commands from being sent!");
		builder.attribute((new AttributeNode.Builder("Number of processing threads", "numThreads",
				AttributeType.Integer).description(
				"Sets the number of threads used to process provisioning commands. Increase for situations "
						+ "where the load of device commands is high.").defaultValue("5").build()));
		return builder.build();
	}

	/**
	 * Create a Hazelcast event processor.
	 * 
	 * @return
	 */
	protected ElementNode createHazelcastEventProcessorElement() {
		ElementNode.Builder builder =
				new ElementNode.Builder("Hazelcast Processor",
						OutboundProcessingChainParser.Elements.HazelcastEventProcessor.getLocalName(),
						"sign-out", ElementRole.OutboundProcessingChain_FilteredEventProcessor);
		builder.description("Sends outbound events to Hazelcast topics for processing by external consumers.");
		return builder.build();
	}

	/**
	 * Create filter criteria element.
	 * 
	 * @return
	 */
	protected ElementNode createFilterCriteriaElement() {
		ElementNode.Builder builder =
				new ElementNode.Builder("Filter Criteria", "filters", "filter",
						ElementRole.OutboundProcessingChain_Filters);
		builder.description("Adds filter criteria to control which events are sent to processor. "
				+ "If any of the filters match, the event is not forwarded for processing.");
		return builder.build();
	}

	/**
	 * Create outbound processor site filter.
	 * 
	 * @return
	 */
	protected ElementNode createSiteFilterElement() {
		ElementNode.Builder builder =
				new ElementNode.Builder("Site Filter",
						OutboundProcessingChainParser.Filters.SiteFilter.getLocalName(), "filter",
						ElementRole.OutboundProcessingChain_OutboundFilters);
		builder.description("Allows events from a given site to be included or excluded for an outbound processor.");
		builder.attribute((new AttributeNode.Builder("Site", "site", AttributeType.SiteReference).description("Site filter applies to.").build()));
		builder.attribute((new AttributeNode.Builder("Include/Exclude", "operation", AttributeType.String).description(
				"Indicates whether events from the site should be included or excluded from processing.").choice(
				"include").choice("exclude").defaultValue("include").build()));
		return builder.build();
	}
}