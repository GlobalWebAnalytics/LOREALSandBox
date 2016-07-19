package com.loreal.sandbox.client.mvp;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.loreal.sandbox.client.view.FacebookView;
import com.loreal.sandbox.client.view.FacebookViewImpl;
import com.loreal.sandbox.client.view.GAFiltersView;
import com.loreal.sandbox.client.view.GAFiltersViewImpl;
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
	private final GAYoutubeView gaYoutubeView = new GAYoutubeViewImpl();
	private final GAFiltersView gaFiltersView = new GAFiltersViewImpl();
	private final FacebookView facebookView = new FacebookViewImpl();
	private AppPlaceHistoryMapper historyMapper;

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
		return gaYoutubeView;
	}

	@Override
	public GAFiltersView getGAFiltersView() {
		return gaFiltersView;
	}

	@Override
	public FacebookView getFacebookView() {
		return facebookView;
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

	@Override
	public void setHistoryMapper(AppPlaceHistoryMapper historyMapper) {
		this.historyMapper = historyMapper;
	}

	@Override
	public String getCanonical(Place place) {
		String canonical = "/";
		if (historyMapper != null) {
			canonical = historyMapper.getToken(place);
			if (canonical != null) {
				if (!canonical.equalsIgnoreCase("")) {
					canonical = canonical.substring(0, canonical.indexOf(":"));
					canonical = "/" + canonical + "/";
				}
			} else {
				canonical = "/";
			}
		}
		return canonical;
	}

	private native boolean nativeAdsBlocked() /*-{
		return $wnd.adsBlocked;
	}-*/;

	private native String nativeUiUser() /*-{
		return $wnd.uiUser;
	}-*/;
}
