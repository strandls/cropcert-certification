package com.strandls.certification.service;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.strandls.certification.service.imp.InspectionServiceImpl;

public class ServiceModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(InspectionService.class).to(InspectionServiceImpl.class).in(Scopes.SINGLETON);
	}
}
