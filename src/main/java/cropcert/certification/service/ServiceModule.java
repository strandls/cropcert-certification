package cropcert.certification.service;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;

import cropcert.certification.service.imp.AdviceServiceImpl;
import cropcert.certification.service.imp.AnimalServiceImpl;
import cropcert.certification.service.imp.FarmPlotServiceImpl;
import cropcert.certification.service.imp.InspectionServiceImpl;
import cropcert.certification.service.imp.SynchronizationServiceImpl;

public class ServiceModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(InspectionService.class).to(InspectionServiceImpl.class).in(Scopes.SINGLETON);
		bind(SynchronizationService.class).to(SynchronizationServiceImpl.class).in(Scopes.SINGLETON);
		bind(AdviceService.class).to(AdviceServiceImpl.class).in(Scopes.SINGLETON);
		bind(AnimalService.class).to(AnimalServiceImpl.class).in(Scopes.SINGLETON);
		bind(FarmPlotService.class).to(FarmPlotServiceImpl.class).in(Scopes.SINGLETON);
	}
}
