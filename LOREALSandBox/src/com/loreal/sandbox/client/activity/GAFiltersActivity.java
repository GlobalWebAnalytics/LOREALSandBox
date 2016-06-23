package com.loreal.sandbox.client.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.dom.client.ScriptElement;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.loreal.sandbox.client.mvp.ClientFactory;
import com.loreal.sandbox.client.place.GAFiltersPlace;
import com.loreal.sandbox.client.view.GAFiltersView;
import com.loreal.sandbox.client.view.Presenter;
import com.loreal.sandbox.shared.model.DataLayer;

public class GAFiltersActivity extends AbstractActivity implements Presenter {
	private ClientFactory clientFactory;
	private DataLayer dataLayer;
	private String canonical;

	public GAFiltersActivity(GAFiltersPlace place, ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
		this.canonical = clientFactory.getCanonical(place);
	}

	/**
	 * Invoked by the ActivityManager to start a new Activity
	 */
	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
		GAFiltersView gaFiltersView = clientFactory.getGAFiltersView();
		gaFiltersView.setPresenter(this);
		Document.get().setTitle("LOREAL Sand Box - Google Analytics Create Filters");
		Document.get().getElementById("canonical").setAttribute("href", canonical);

		dataLayer = new DataLayer();
		setDataLayer();
		if (clientFactory.getFirstLoad()) {
			dataLayer.push();
		} else {
			dataLayer.pushPageView();
		}

		loadScript("https://apis.google.com/js/client.js?onload=authorize");
		loadScript("/ga/gaapi.js");

		containerWidget.setWidget(gaFiltersView.asWidget());
	}

	/**
	 * Navigate to a new Place in the browser
	 */
	@Override
	public void goTo(Place place) {
		clientFactory.getPlaceController().goTo(place);
	}

	private void setDataLayer() {
		dataLayer.setPageCategory("google analytics");
		if (clientFactory.getFirstLoad()) {
			// Do nothing
		} else {
			dataLayer.setVirtualPageUrl(canonical);
			dataLayer.setVirtualPageTitle(Document.get().getTitle());
		}
	}

	private void loadScript(String src) {
		Document doc = Document.get();
		ScriptElement script = doc.createScriptElement();
		script.setSrc(src);
		script.setType("text/javascript");
		doc.getBody().appendChild(script);

		NodeList<Element> headElement = doc.getElementsByTagName("script");
		Element firstScript = headElement.getItem(0);

		insertScriptTag(script, firstScript);
	}

	private static native void insertScriptTag(ScriptElement j, Element f) /*-{
		f.parentNode.insertBefore(j, f);
	}-*/;
}
