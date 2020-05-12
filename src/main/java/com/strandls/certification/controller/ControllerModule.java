/**
 * 
 */
package com.strandls.certification.controller;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.strandls.certification.controller.impl.InspectionControllerImpl;

/**
 * 
 * @author vilay
 *
 */
public class ControllerModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(InspectionController.class).to(InspectionControllerImpl.class).in(Scopes.SINGLETON);
	}
}
