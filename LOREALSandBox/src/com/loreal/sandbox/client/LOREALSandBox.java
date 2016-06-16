package com.loreal.sandbox.client;

import java.util.Arrays;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.dom.client.ScriptElement;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.web.bindery.event.shared.EventBus;
import com.loreal.sandbox.client.mvp.AppActivityMapper;
import com.loreal.sandbox.client.mvp.AppPlaceHistoryMapper;
import com.loreal.sandbox.client.mvp.ClientFactory;
import com.loreal.sandbox.client.place.HomePlace;
import com.loreal.sandbox.client.widget.Main;
import com.vaadin.polymer.Polymer;
import com.vaadin.polymer.elemental.Function;

public class LOREALSandBox implements EntryPoint {
	private Place defaultPlace = new HomePlace(null);
	private Main appWidget;

	@Override
	public void onModuleLoad() {
		// We have to load icon sets before run application
		Polymer.importHref(Arrays.asList("iron-icons/iron-icons.html", "neon-animation/neon-animations.html"),
				new Function() {
					@Override
					public Object call(Object arg) {
						// The app is executed when all imports succeed.
						startApplication();
						return null;
					}
				});

	}

	private void startApplication() {
		ClientFactory clientFactory = GWT.create(ClientFactory.class);
		appWidget = new Main();
		appWidget.setClientFactory(clientFactory);
		EventBus eventBus = clientFactory.getEventBus();
		PlaceController placeController = clientFactory.getPlaceController();
		// Start ActivityManager for the main widget with our ActivityMapper
		ActivityMapper activityMapper = new AppActivityMapper(clientFactory);
		ActivityManager activityManager = new ActivityManager(activityMapper, eventBus);
		activityManager.setDisplay(appWidget);

		// Start PlaceHistoryHandler with our PlaceHistoryMapper
		AppPlaceHistoryMapper historyMapper = GWT.create(AppPlaceHistoryMapper.class);
		PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper);
		historyHandler.register(placeController, eventBus, defaultPlace);

		RootPanel.get().add(appWidget);
		// Goes to the place represented on URL else default place
		historyHandler.handleCurrentHistory();
		initGoogleTagManager("GTM-5T89DP");
		clientFactory.loaded();
	}

	private void initGoogleTagManager(String gtmId) {
		setGtmVars();

		Document doc = Document.get();
		ScriptElement script = doc.createScriptElement();
		script.setSrc("//www.googletagmanager.com/gtm.js?id=" + gtmId);
		script.setType("text/javascript");
		script.setLang("javascript");
		script.setAttribute("async", "true");
		doc.getBody().appendChild(script);

		NodeList<Element> headElement = doc.getElementsByTagName("script");
		Element firstScript = headElement.getItem(0);

		insertGtm(script, firstScript);
	}

	private static native void setGtmVars() /*-{
		$wnd["dataLayer"] = $wnd["dataLayer"] || [];
		$wnd.dataLayer.push({
			'gtm.start' : new Date().getTime(),
			event : 'gtm.js'
		});
	}-*/;

	private static native void insertGtm(ScriptElement j, Element f) /*-{
		f.parentNode.insertBefore(j, f);
	}-*/;
}
