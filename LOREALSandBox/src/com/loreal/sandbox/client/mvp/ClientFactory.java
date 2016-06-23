package com.loreal.sandbox.client.mvp;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;
import com.loreal.sandbox.client.view.GAFiltersView;
import com.loreal.sandbox.client.view.GAYoutubeView;
import com.loreal.sandbox.client.view.GoogleAnalyticsView;
import com.loreal.sandbox.client.view.HomeView;
import com.loreal.sandbox.shared.model.DataLayer;

public interface ClientFactory {
	EventBus getEventBus();

	PlaceController getPlaceController();

	HomeView getHomeView();

	GoogleAnalyticsView getGoogleAnalyticsView();

	GAYoutubeView getGAYoutubeView();

	GAFiltersView getGAFiltersView();

	boolean getFirstLoad();

	void loaded();

	DataLayer getDataLayer();

	void setHistoryMapper(AppPlaceHistoryMapper historyMapper);

	String getCanonical(Place place);
}
