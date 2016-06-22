package com.loreal.sandbox.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class GAFiltersPlace extends Place {
	private String name;

	public GAFiltersPlace(String token) {
		this.name = token;
	}

	public String getName() {
		return name;
	}

	@Prefix("googleanalytics/createfilters")
	public static class Tokenizer implements PlaceTokenizer<GAFiltersPlace> {
		@Override
		public String getToken(GAFiltersPlace place) {
			return place.getName();
		}

		@Override
		public GAFiltersPlace getPlace(String token) {
			return new GAFiltersPlace(token);
		}
	}
}
