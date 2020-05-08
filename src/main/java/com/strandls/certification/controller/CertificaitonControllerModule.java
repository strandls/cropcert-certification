/**
 * 
 */
package com.strandls.certification.controller;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.strandls.certification.controller.impl.CertificationControllerImpl;

/**
 * 
 * @author vilay
 *
 */
public class CertificaitonControllerModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(CertificationController.class).to(CertificationControllerImpl.class).in(Scopes.SINGLETON);
	}
}
