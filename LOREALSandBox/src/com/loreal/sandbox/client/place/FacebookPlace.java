package com.loreal.sandbox.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class FacebookPlace extends Place {
	private String name;

	public FacebookPlace(String token) {
		this.name = token;
	}

	public String getName() {
		return name;
	}

	@Prefix("media/facebook")
	public static class Tokenizer implements PlaceTokenizer<FacebookPlace> {
		@Override
		public String getToken(FacebookPlace place) {
			return place.getName();
		}

		@Override
		public FacebookPlace getPlace(String token) {
			return new FacebookPlace(token);
		}
	}
}
