package com.loreal.sandbox.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class GAYoutubePlace extends Place {
	private String name;

	public GAYoutubePlace(String token) {
		this.name = token;
	}

	public String getName() {
		return name;
	}

	@Prefix("googleanalytics/youtubetracking")
	public static class Tokenizer implements PlaceTokenizer<GAYoutubePlace> {
		@Override
		public String getToken(GAYoutubePlace place) {
			return place.getName();
		}

		@Override
		public GAYoutubePlace getPlace(String token) {
			return new GAYoutubePlace(token);
		}
	}
}
