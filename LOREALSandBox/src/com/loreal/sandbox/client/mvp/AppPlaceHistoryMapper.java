package com.loreal.sandbox.client.mvp;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;
import com.loreal.sandbox.client.place.FacebookPlace;
import com.loreal.sandbox.client.place.GAFiltersPlace;
import com.loreal.sandbox.client.place.GAYoutubePlace;
import com.loreal.sandbox.client.place.GoogleAnalyticsPlace;
import com.loreal.sandbox.client.place.HomePlace;

//@formatter:off
@WithTokenizers({
	HomePlace.Tokenizer.class,
	GoogleAnalyticsPlace.Tokenizer.class,
	GAYoutubePlace.Tokenizer.class,
	GAFiltersPlace.Tokenizer.class,
	FacebookPlace.Tokenizer.class})
//@formatter:on
public interface AppPlaceHistoryMapper extends PlaceHistoryMapper {
}
