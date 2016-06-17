package com.loreal.sandbox.client.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.dom.client.Document;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.loreal.sandbox.client.mvp.ClientFactory;
import com.loreal.sandbox.client.place.HomePlace;
import com.loreal.sandbox.client.view.HomeView;
import com.loreal.sandbox.client.view.Presenter;
import com.loreal.sandbox.shared.model.DataLayer;

public class HomeActivity extends AbstractActivity implements Presenter {
	private ClientFactory clientFactory;
	private DataLayer dataLayer;

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
		Document.get().setTitle("LOREAL Sand Box");

		dataLayer = new DataLayer();
		setDataLayer();
		if (clientFactory.getFirstLoad()) {
			dataLayer.push();
		} else {
			dataLayer.pushPageView();
		}

		containerWidget.setWidget(homeView.asWidget());
	}

	/**
	 * Navigate to a new Place in the browser
	 */
	@Override
	public void goTo(Place place) {
		clientFactory.getPlaceController().goTo(place);
	}

	private void setDataLayer() {
		dataLayer.setPageCategory("home");
		if (clientFactory.getFirstLoad()) {
			// Do nothing
		} else {
			dataLayer.setVirtualPageUrl("/home/");
			dataLayer.setVirtualPageTitle(Document.get().getTitle());
		}
	}
}
