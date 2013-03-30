package com.redhat.errai.math.client.local;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

/**
 * 
 * @author Andrew Block
 *
 */
public interface MathResources extends ClientBundle {

	// Browser Images
	@Source("com/redhat/errai/math/client/resources/chrome16x16.png")
	ImageResource chrome();

	@Source("com/redhat/errai/math/client/resources/firefox16x16.png")
	ImageResource firefox();

	@Source("com/redhat/errai/math/client/resources/ie16x16.png")
	ImageResource ie8700();

	@Source("com/redhat/errai/math/client/resources/opera16x16.png")
	ImageResource opera();
	
	@Source("com/redhat/errai/math/client/resources/SafariLogo16x16.png")
	ImageResource safari();
	
	// Operating System Images
	@Source("com/redhat/errai/math/client/resources/Android-logo16x16.png")
	ImageResource android();
	
	@Source("com/redhat/errai/math/client/resources/logo-bb16x16.png")
	ImageResource blackberry();
	
	@Source("com/redhat/errai/math/client/resources/Windows_logo16x16.png")
	ImageResource windows();
	
	@Source("com/redhat/errai/math/client/resources/mac16x16.png")
	ImageResource mac();
	
	@Source("com/redhat/errai/math/client/resources/linux16x14.png")
	ImageResource linux();
	
	// Operating System Images

}
