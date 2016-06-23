package com.loreal.sandbox.client.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.dom.client.Document;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.loreal.sandbox.client.mvp.ClientFactory;
import com.loreal.sandbox.client.place.GAYoutubePlace;
import com.loreal.sandbox.client.view.GAYoutubeView;
import com.loreal.sandbox.client.view.Presenter;
import com.loreal.sandbox.shared.model.DataLayer;

public class GAYoutubeActivity extends AbstractActivity implements Presenter {
	private ClientFactory clientFactory;
	private DataLayer dataLayer;
	private String canonical;

	public GAYoutubeActivity(GAYoutubePlace place, ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
		this.canonical = clientFactory.getCanonical(place);
	}

	/**
	 * Invoked by the ActivityManager to start a new Activity
	 */
	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
		GAYoutubeView gAYoutubeView = clientFactory.getGAYoutubeView();
		gAYoutubeView.setPresenter(this);
		Document.get().setTitle("LOREAL Sand Box - Google Analytics Tracking for Youtube Video");
		Document.get().getElementById("canonical").setAttribute("href", canonical);

		dataLayer = new DataLayer();
		setDataLayer();
		if (clientFactory.getFirstLoad()) {
			dataLayer.push();
		} else {
			dataLayer.pushPageView();
		}

		containerWidget.setWidget(gAYoutubeView.asWidget());
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

	private native void pushDataLayer(boolean firstLoad) /*-{
		$wnd["dataLayer"] = $wnd["dataLayer"] || [];
		if (firstLoad) {
			$wnd.dataLayer.push({
				pageCategory : "google analytics"
			});
		} else {
			$wnd.dataLayer.push({
				event : "updatevirtualpath",
				pageCategory : "google analytics",
				virtualPageUrl : canonical,
				virtualPageTitle : $wnd.document.title
			});
		}
	}-*/;

	private native void consoleLog(String s) /*-{
		$wnd.console.log(s);
	}-*/;
}
