package com.loreal.sandbox.client.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.loreal.sandbox.client.mvp.ClientFactory;
import com.loreal.sandbox.client.place.HomePlace;
import com.loreal.sandbox.client.view.HomeView;
import com.loreal.sandbox.client.view.Presenter;

public class HomeActivity extends AbstractActivity implements Presenter {
	private ClientFactory clientFactory;

	public HomeActivity(HomePlace place, ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
	}

	/**
	 * Invoked by the ActivityManager to start a new Activity
	 */
	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
		HomeView homeView = clientFactory.getHomeView();
		homeView.setPresenter(this);
		pushDataLayer(clientFactory.getFirstLoad());
		containerWidget.setWidget(homeView.asWidget());
	}

	/**
	 * Navigate to a new Place in the browser
	 */
	@Override
	public void goTo(Place place) {
		clientFactory.getPlaceController().goTo(place);
	}

	private native void pushDataLayer(boolean firstLoad) /*-{
		$wnd["dataLayer"] = $wnd["dataLayer"] || [];
		if (firstLoad) {
			$wnd.dataLayer.push({
				pageCategory : "home"
			});
		} else {
			$wnd.dataLayer.push({
				event : "updatevirtualpath",
				pageCategory : "home",
				virtualPageUrl : "/home/",
				virtualPageTitle : $wnd.document.title
			});
		}
	}-*/;
}
