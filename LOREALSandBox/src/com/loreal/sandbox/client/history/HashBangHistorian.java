package com.loreal.sandbox.client.history;

import com.google.gwt.place.shared.PlaceHistoryHandler.Historian;

/**
 * A concrete replacement for {@link Historian} that shows the GWT Place Token
 * in a "hashbang" format (ˆ la Twitter).
 * 
 * @author carlos.aguayo : https://github.com/carlos-aguayo/html5gwthistory/
 */
public class HashBangHistorian extends CustomHistorian {

	private String currentUrl = "";

	@Override
	public String getPath(String path, String hash) {
		return hash;
	}

	@Override
	public String getUrlSeparator() {
		return "!/";
	}

	@Override
	protected void goTo(String url) {
		currentUrl = url;
		updateHash(url);
	}

	private native void updateHash(String url) /*-{
		$wnd.location.hash = url;
	}-*/;

	@Override
	protected native void addHistoryEventHandler() /*-{
		var that = this;
		var oldHandler = $wnd.onhashchange;
		$wnd.onhashchange = $entry(function(e) {
			var hash = $wnd.location.hash.substring(1);
			var currentUrl = that.@com.loreal.sandbox.client.history.HashBangHistorian::currentUrl;
			// We use this event to detect when the user is going back using the back button as it will trigger
			// a hash change and we can go back to the previous state.
			// However, this event also gets triggered whenever we are going forward in history, in that case
			// we don't want to invoke this method, so that's why we compare it with the current url.
			if (hash !== currentUrl) {
				that.@com.loreal.sandbox.client.history.CustomHistorian::onHistoryChange()();
				if (oldHandler) {
					oldHandler();
				}
			}
		});
	}-*/;

}