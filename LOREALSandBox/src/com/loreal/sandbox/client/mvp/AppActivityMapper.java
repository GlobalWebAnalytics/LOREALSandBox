package com.loreal.sandbox.client.mvp;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.loreal.sandbox.client.activity.FacebookActivity;
import com.loreal.sandbox.client.activity.GAFiltersActivity;
import com.loreal.sandbox.client.activity.GAYoutubeActivity;
import com.loreal.sandbox.client.activity.GoogleAnalyticsActivity;
import com.loreal.sandbox.client.activity.HomeActivity;
import com.loreal.sandbox.client.place.FacebookPlace;
import com.loreal.sandbox.client.place.GAFiltersPlace;
import com.loreal.sandbox.client.place.GAYoutubePlace;
import com.loreal.sandbox.client.place.GoogleAnalyticsPlace;
import com.loreal.sandbox.client.place.HomePlace;

public class AppActivityMapper implements ActivityMapper {

	private ClientFactory clientFactory;

	public AppActivityMapper(ClientFactory clientFactory) {
		super();
		this.clientFactory = clientFactory;
	}

	@Override
	public Activity getActivity(Place place) {
		if (place instanceof HomePlace) {
			return new HomeActivity((HomePlace) place, clientFactory);
		} else if (place instanceof GoogleAnalyticsPlace) {
			return new GoogleAnalyticsActivity((GoogleAnalyticsPlace) place, clientFactory);
		} else if (place instanceof GAYoutubePlace) {
			return new GAYoutubeActivity((GAYoutubePlace) place, clientFactory);
		} else if (place instanceof GAFiltersPlace) {
			return new GAFiltersActivity((GAFiltersPlace) place, clientFactory);
		} else if (place instanceof FacebookPlace) {
			return new FacebookActivity((FacebookPlace) place, clientFactory);
		}
		return null;
	}
}
