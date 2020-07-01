/**
 * 
 */
package cropcert.certification.controller;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import cropcert.certification.controller.impl.InspectionControllerImpl;
import cropcert.certification.controller.impl.SynchronizationControllerImpl;

/**
 * 
 * @author vilay
 *
 */
public class ControllerModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(InspectionController.class).to(InspectionControllerImpl.class).in(Scopes.SINGLETON);
		bind(SynchronizationController.class).to(SynchronizationControllerImpl.class).in(Scopes.SINGLETON);
	}
}
