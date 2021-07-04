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
import com.mentor.chs.api.define.IXCarrier;
import com.mentor.chs.api.define.IXComponent;
import com.mentor.chs.api.define.IXComponentInterface;
import com.mentor.chs.api.define.IXPlatformArchitectureDesign;
import com.mentor.chs.plugin.IXApplicationContext;
import com.mentor.chs.plugin.IXApplicationContextListener;
import com.mentor.chs.plugin.constraint.IXDoDontResult;
import com.mentor.chs.plugin.constraint.IXInterfaceAndNonDiscreteCarrierMappingResult;
import com.mentor.chs.plugin.constraint.define.IXInterfaceAndNonDiscreteCarrierMappingConstraint;

import java.util.HashSet;
import java.util.Set;

/**
 * Plugin to map Platform Conductors to Carrier and Interface
 */
public class MapFunctionConductorToInterfaceAndCarrierIfRouteIsRT3 implements IXInterfaceAndNonDiscreteCarrierMappingConstraint,
		IXApplicationContextListener
{

	private IXApplicationContext ac;
	private static final String ROUTE_PROPERTY_NAME = "ROUTE";
	private static final String ROUTE_PROPERTY_VALUE = "RT3";

	//IXInterfaceAndNonDiscreteCarrierMappingConstraint interface method
	//Given : All the functionConductor, carriers and interfaces in the project contains the property 'ROUTE'
	//otherwise it will give NullPointerException

	public boolean match(IXFunctionConductor functionConductor, IXComponent component,IXInterfaceAndNonDiscreteCarrierMappingResult result)
	{
		//No match if function conductor ROUTE property is not equal to RT3
		if (!doesPropertyMatch(functionConductor)) { return false; }

		Set<IXCarrier> carriers = new HashSet<>();
		for(IXCarrier carrier : ((IXPlatformArchitectureDesign)ac.getCurrentDesign()).getCarriers()){
			if(doesPropertyMatch(carrier)){
				carriers.add(carrier);
			}
		}

		//No match if no carrier ROUTE property set to RT3
		if(carriers.isEmpty()){ return false;}

		Set<IXComponentInterface> componentInterfaces = new HashSet<>();
		for(IXComponentInterface componentInterface : component.getInterfaces()){
			if(doesPropertyMatch(componentInterface)){
				componentInterfaces.add(componentInterface);
			}
		}

		//No match if no interface ROUTE property set to RT3
		if(componentInterfaces.isEmpty()){ return false;}

		for (IXComponentInterface interFace : componentInterfaces) {
			for (IXCarrier carrier : carriers) {
				result.addComponentInterfaceAndCarrier(interFace, carrier);
			}
		}
		result.getDoDontResult().setValue(IXDoDontResult.DoDont.DO);
		return true;
	}

	private boolean doesPropertyMatch(IXPropertyProvider propertyProvider) {
		String property = propertyProvider.getProperty(ROUTE_PROPERTY_NAME);
		if(property == null) return false;
		return property.equals(ROUTE_PROPERTY_VALUE);
	}

	//IXPlugin interface methods
	public String getName()	{ return "Bash-Do map signal/message to interface and carrier if '"+ ROUTE_PROPERTY_NAME+ "' property set to '"+ROUTE_PROPERTY_VALUE+"'."; }
	public String getDescription() { return getName(); }
	public String getVersion() { return "1.0"; }

	//IXApplicationContextListener interface method
	public void setApplicationContext(IXApplicationContext applicationContext)
	{
		ac = applicationContext;
	}
}

