package com.loreal.sandbox.client.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.loreal.sandbox.client.mvp.ClientFactory;
import com.loreal.sandbox.client.place.GoogleAnalyticsPlace;
import com.loreal.sandbox.client.view.GoogleAnalyticsView;
import com.loreal.sandbox.client.view.Presenter;

public class GoogleAnalyticsActivity extends AbstractActivity implements Presenter {
	private ClientFactory clientFactory;

	public GoogleAnalyticsActivity(GoogleAnalyticsPlace place, ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
	}

	/**
	 * Invoked by the ActivityManager to start a new Activity
	 */
	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
		GoogleAnalyticsView googleAnalyticsView = clientFactory.getGoogleAnalyticsView();
		googleAnalyticsView.setPresenter(this);
		containerWidget.setWidget(googleAnalyticsView.asWidget());
	}

	/**
	 * Navigate to a new Place in the browser
	 */
	@Override
	public void goTo(Place place) {
		clientFactory.getPlaceController().goTo(place);
	}
}
