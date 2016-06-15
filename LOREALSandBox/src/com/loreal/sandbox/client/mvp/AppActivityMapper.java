package com.loreal.sandbox.client.mvp;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.loreal.sandbox.client.activity.GoogleAnalyticsActivity;
import com.loreal.sandbox.client.activity.HomeActivity;
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
		}
		return null;
	}
}
