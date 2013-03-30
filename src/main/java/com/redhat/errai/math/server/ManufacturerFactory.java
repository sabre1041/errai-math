package com.redhat.errai.math.server;

import com.redhat.errai.math.client.shared.Manufacturer;

/**
 * 
 * @author Andrew Block
 *
 */
public class ManufacturerFactory {

	public static Manufacturer getManufacturer(
			nl.bitwalker.useragentutils.Manufacturer userAgentManufacturer) {
		switch (userAgentManufacturer) {
		case APPLE:
			return Manufacturer.APPLE;
		case BLACKBERRY:
			return Manufacturer.BLACKBERRY;
		case GOOGLE:
			return Manufacturer.GOOGLE;
		case MOZILLA:
			return Manufacturer.MOZILLA;
		case OPERA:
			return Manufacturer.OPERA;
		case MICROSOFT:
			return Manufacturer.MICROSOFT;
		default:
			return null;
		}
	}

}
