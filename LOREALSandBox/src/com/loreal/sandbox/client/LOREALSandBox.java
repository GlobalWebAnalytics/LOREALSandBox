package com.loreal.sandbox.client;

import java.util.Arrays;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.loreal.sandbox.client.widget.Main;
import com.vaadin.polymer.Polymer;
import com.vaadin.polymer.elemental.Function;

public class LOREALSandBox implements EntryPoint {
	public void onModuleLoad() {
	    // We have to load icon sets before run application
	    Polymer.importHref(Arrays.asList("iron-icons/iron-icons.html"), new Function() {
	    	public Object call(Object arg) {
		        // The app is executed when all imports succeed.
		        startApplication();
		        return null;
	        }
	    });
	  }

	  private void startApplication() {
	    RootPanel.get().add(new Main());
	  }
}
