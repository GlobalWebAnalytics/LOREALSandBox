package com.loreal.sandbox.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class HomePlace extends Place {
	private String name;

	public HomePlace(String token) {
		this.name = token;
	}

	public String getName() {
		return name;
	}

	@Prefix("home")
	public static class Tokenizer implements PlaceTokenizer<HomePlace> {
		@Override
		public String getToken(HomePlace place) {
			return place.getName();
		}

		@Override
		public HomePlace getPlace(String token) {
			return new HomePlace(token);
		}
	}
}
