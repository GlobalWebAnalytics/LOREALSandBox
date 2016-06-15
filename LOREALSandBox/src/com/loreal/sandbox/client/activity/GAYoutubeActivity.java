package com.loreal.sandbox.client.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.loreal.sandbox.client.mvp.ClientFactory;
import com.loreal.sandbox.client.place.GAYoutubePlace;
import com.loreal.sandbox.client.view.GAYoutubeView;
import com.loreal.sandbox.client.view.Presenter;

public class GAYoutubeActivity extends AbstractActivity implements Presenter {
	private ClientFactory clientFactory;

	public GAYoutubeActivity(GAYoutubePlace place, ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
	}

	/**
	 * Invoked by the ActivityManager to start a new Activity
	 */
	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
		GAYoutubeView gAYoutubeView = clientFactory.getGAYoutubeView();
		gAYoutubeView.setPresenter(this);
		containerWidget.setWidget(gAYoutubeView.asWidget());
	}

	/**
	 * Navigate to a new Place in the browser
	 */
	@Override
	public void goTo(Place place) {
		clientFactory.getPlaceController().goTo(place);
	}
}
