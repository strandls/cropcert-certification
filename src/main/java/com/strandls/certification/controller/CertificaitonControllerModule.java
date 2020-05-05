/**
 * 
 */
package com.strandls.certification.controller;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;

/**
 * @author Abhishek Rudra
 *
 */
public class CertificaitonControllerModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(CertificationController.class).in(Scopes.SINGLETON);
	}
}
