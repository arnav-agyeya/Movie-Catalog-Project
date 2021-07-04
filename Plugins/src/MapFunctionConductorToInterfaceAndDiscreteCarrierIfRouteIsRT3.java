/*
 * Copyright 2020 Mentor Graphics Corporation
 * All Rights Reserved
 *
 * THIS WORK CONTAINS TRADE SECRET AND PROPRIETARY
 * INFORMATION WHICH IS THE PROPERTY OF MENTOR
 * GRAPHICS CORPORATION OR ITS LICENSORS AND IS
 * SUBJECT TO LICENSE TERMS.
 */

import com.mentor.chs.api.IXPropertyProvider;
import com.mentor.chs.api.capture.IXFunctionConductor;
import com.mentor.chs.api.define.IXComponent;
import com.mentor.chs.api.define.IXComponentInterface;
import com.mentor.chs.plugin.constraint.IXDoDontResult;
import com.mentor.chs.plugin.constraint.IXInterfaceAndDiscreteCarrierMappingResult;
import com.mentor.chs.plugin.constraint.define.IXInterfaceAndDiscreteCarrierMappingConstraint;

import java.util.HashSet;
import java.util.Set;

/**
 * Plugin to map Platform Conductors to Discrete Carrier and Interface
 */
public class MapFunctionConductorToInterfaceAndDiscreteCarrierIfRouteIsRT3 implements IXInterfaceAndDiscreteCarrierMappingConstraint
{

	private static final String ROUTE_PROPERTY_NAME = "ROUTE";
	private static final String ROUTE_PROPERTY_VALUE = "RT3";

	//IXInterfaceAndDiscreteCarrierMappingConstraint interface method
	//Given : All the functionConductor and interfaces in the project contains the property 'ROUTE'
	//otherwise it will give NullPointerException

	public boolean match(IXFunctionConductor functionConductor, IXComponent component, IXInterfaceAndDiscreteCarrierMappingResult result)
	{
		//No match if function conductor ROUTE property is not equal to RT3
		if (!doesPropertyMatch(functionConductor)) { return false; }

		Set<IXComponentInterface> compInterfaces = new HashSet<>();

		for (IXComponentInterface compInterface : component.getInterfaces()) {
			if (doesPropertyMatch(compInterface)) {
				compInterfaces.add(compInterface);
			}
		}

		//No match if no component interface ROUTE property is equal to RT3
		if (compInterfaces.isEmpty()) { return false;}

		result.addInterfaces(compInterfaces);
		result.getDoDontResult().setValue(IXDoDontResult.DoDont.DO);
		return true;
	}

	private boolean doesPropertyMatch(IXPropertyProvider propertyProvider) {
		String property = propertyProvider.getProperty(ROUTE_PROPERTY_NAME);
		if(property == null) return false;
		return property.equals(ROUTE_PROPERTY_VALUE);
	}

	//IXPlugin interface methods
	public String getName() { return "Bash-Do map signal/message to interface and discrete carrier if '" + ROUTE_PROPERTY_NAME + "' property set to '" + ROUTE_PROPERTY_VALUE + "'"; }
	public String getDescription() { return getName(); }
	public String getVersion() { return "1.0"; }
}
