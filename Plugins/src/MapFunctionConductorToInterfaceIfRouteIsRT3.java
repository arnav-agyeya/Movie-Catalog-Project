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
import com.mentor.chs.plugin.constraint.IXInterfaceMappingResult;
import com.mentor.chs.plugin.constraint.define.IXInterfaceMappingConstraint;

import java.util.HashSet;
import java.util.Set;

/**
 * Plugin to map Platform Conductor to Interface
 */
public class MapFunctionConductorToInterfaceIfRouteIsRT3 implements IXInterfaceMappingConstraint{

	private static final String ROUTE_PROPERTY_NAME = "ROUTE";
	private static final String ROUTE_PROPERTY_VALUE = "RT3";

	//IXInterfaceMappingConstraint interface method
	//Given : All the functionConductor and interfaces in the project contains the property 'ROUTE'
	//otherwise it will give NullPointerException

	public boolean match(IXFunctionConductor functionConductor, IXComponent component, IXInterfaceMappingResult result)
	{
		//No match if function conductor ROUTE property is not equal to RT3
		if (!doesPropertyMatch(functionConductor)) { return false; }

		Set<IXComponentInterface> componentInterfaces = new HashSet<>();

		for (IXComponentInterface componentInterface : component.getInterfaces()) {
			if (doesPropertyMatch(componentInterface)) {
				componentInterfaces.add(componentInterface);
			}
		}

		//No match if no component interface ROUTE property is equal to RT3
		if (componentInterfaces.isEmpty()) { return false;}

		result.addInterfaces(componentInterfaces);
		result.getDoDontResult().setValue(IXDoDontResult.DoDont.DO);
		return true;
	}

	private boolean doesPropertyMatch(IXPropertyProvider propertyProvider) {
		String property = propertyProvider.getProperty(ROUTE_PROPERTY_NAME);
		if(property == null) return false;
		return property.equals(ROUTE_PROPERTY_VALUE);
	}

	//IXPlugin interface methods
	public String getName() { return "Bash-Do map signal/message to interface if '" + ROUTE_PROPERTY_NAME +
									 "' property set to '" + ROUTE_PROPERTY_VALUE + "'"; }
	public String getDescription() { return getName(); }
	public String getVersion() { return "2.0"; }
}
