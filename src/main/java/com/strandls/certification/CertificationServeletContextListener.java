/**
 * 
 */
package com.strandls.certification;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.strandls.certification.controller.CertificaitonControllerModule;
import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

/**
 * @author Abhishek Rudra
 *
 */
public class CertificationServeletContextListener extends GuiceServletContextListener {

	@Override
	protected Injector getInjector() {

		Injector injector = Guice.createInjector(new JerseyServletModule() {
			@Override
			protected void configureServlets() {

				ObjectMapper objectMapper = new ObjectMapper();
				bind(ObjectMapper.class).toInstance(objectMapper);

				serve("/api/*").with(GuiceContainer.class);
			}
		}, new CertificaitonControllerModule());

		return injector;

	}

}
