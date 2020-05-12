package com.strandls.certification.service;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.strandls.certification.service.imp.AdviceServiceImpl;
import com.strandls.certification.service.imp.AnimalServiceImpl;
import com.strandls.certification.service.imp.FarmPlotServiceImpl;
import com.strandls.certification.service.imp.InspectionServiceImpl;

public class ServiceModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(InspectionService.class).to(InspectionServiceImpl.class).in(Scopes.SINGLETON);
		bind(AdviceService.class).to(AdviceServiceImpl.class).in(Scopes.SINGLETON);
		bind(AnimalService.class).to(AnimalServiceImpl.class).in(Scopes.SINGLETON);
		bind(FarmPlotService.class).to(FarmPlotServiceImpl.class).in(Scopes.SINGLETON);
	}
}
