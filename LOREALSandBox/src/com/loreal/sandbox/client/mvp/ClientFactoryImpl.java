package com.loreal.sandbox.client.mvp;

import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.loreal.sandbox.client.view.GAYoutubeView;
import com.loreal.sandbox.client.view.GAYoutubeViewImpl;
import com.loreal.sandbox.client.view.GoogleAnalyticsView;
import com.loreal.sandbox.client.view.GoogleAnalyticsViewImpl;
import com.loreal.sandbox.client.view.HomeView;
import com.loreal.sandbox.client.view.HomeViewImpl;
import com.loreal.sandbox.shared.model.Brand;
import com.loreal.sandbox.shared.model.DataLayer;

public class ClientFactoryImpl implements ClientFactory {

	private final EventBus eventBus = new SimpleEventBus();
	private final PlaceController placeController = new PlaceController(eventBus);

	// Views
	private final HomeView homeView = new HomeViewImpl();
	private final GoogleAnalyticsView googleAnalyticsView = new GoogleAnalyticsViewImpl();
	private final GAYoutubeView gAYoutubeView = new GAYoutubeViewImpl();

	// GTM vars
	boolean firstLoad = true;
	DataLayer dataLayer = new DataLayer();

	ClientFactoryImpl() {
		dataLayer.setAdsBlocked(nativeAdsBlocked());
		dataLayer.setBrand(Brand.GWA);
		dataLayer.setCountry("world");
		dataLayer.setLanguage("en");
		dataLayer.setSiteTypeLevel("guideline");
		dataLayer.setUiUser(nativeUiUser());
		dataLayer.push();
	}

	@Override
	public EventBus getEventBus() {
		return eventBus;
	}

	@Override
	public PlaceController getPlaceController() {
		return placeController;
	}

	@Override
	public HomeView getHomeView() {
		return homeView;
	}

	@Override
	public GoogleAnalyticsView getGoogleAnalyticsView() {
		return googleAnalyticsView;
	}

	@Override
	public GAYoutubeView getGAYoutubeView() {
		return gAYoutubeView;
	}

	@Override
	public boolean getFirstLoad() {
		return firstLoad;
	}

	@Override
	public void loaded() {
		firstLoad = false;
	}

	@Override
	public DataLayer getDataLayer() {
		return dataLayer;
	}

	private native boolean nativeAdsBlocked() /*-{
		return $wnd.adsBlocked;
	}-*/;

	private native String nativeUiUser() /*-{
		return $wnd.uiUser;
	}-*/;
}
