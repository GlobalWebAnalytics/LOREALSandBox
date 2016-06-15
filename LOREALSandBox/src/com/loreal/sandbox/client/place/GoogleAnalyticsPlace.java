package com.loreal.sandbox.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class GoogleAnalyticsPlace extends Place {
	private String name;

	public GoogleAnalyticsPlace(String token) {
		this.name = token;
	}

	public String getName() {
		return name;
	}

	@Prefix("googleanalytics")
	public static class Tokenizer implements PlaceTokenizer<GoogleAnalyticsPlace> {
		@Override
		public String getToken(GoogleAnalyticsPlace place) {
			return place.getName();
		}

		@Override
		public GoogleAnalyticsPlace getPlace(String token) {
			return new GoogleAnalyticsPlace(token);
		}
	}
}
