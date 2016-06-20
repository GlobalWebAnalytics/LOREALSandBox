package com.loreal.sandbox.client.history;

import com.google.gwt.place.shared.PlaceHistoryHandler.Historian;

/**
 * A replacement for {@link Historian} that uses HTML5 History API, {@code pushState} and {@code onpopstate}.
 *
 * @see https://developer.mozilla.org/en/DOM/Manipulating_the_browser_history
 * @see https://github.com/balupton/history.js/wiki/The-State-of-the-HTML5- History-API
 * 
 * @see https://groups.google.com/forum/?fromgroups#!topic/google-web-toolkit/ xsf_GCnH36Q
 * @see https://gist.github.com/1883821
 * @see http://groups.google.com/group/google-web-toolkit/browse_thread/thread/
 *      919a7847e0d5370c/d647f781023e41b2?lnk=gst&amp;q=pushstate&pli=1
 * 
 * @author Thomas Broyer
 * @author carlos.aguayo : https://github.com/carlos-aguayo/html5gwthistory/
 */
public class Html5Historian extends CustomHistorian {

	@Override
	public String getPath(String path, String hash) {
		if (!isBlank(hash)) {
			// replace the state so we can get rid of the hashbang and get the
			// real url
			replaceState(hash);
			// TODO : clean
			consoleLog("1 - Html5Historian getPath return hash : " + hash);
			return hash;
		} else {
			// TODO : clean
			consoleLog("1 - Html5Historian getPath return path : " + path);
			return path;
		}
	}

	@Override
	public String getUrlSeparator() {
		return "/";
	}

	@Override
	protected void goTo(String url) {
		// TODO : clean
		consoleLog("Html5Historian pushState(url) : " + url);
		pushState(url);
	}

	private native void pushState(String url) /*-{
		url += $wnd.location.search;
		$wnd.history.pushState(null, $doc.title, url);
	}-*/;

	private native void replaceState(String url) /*-{
		url += $wnd.location.search;
		$wnd.history.replaceState(null, $doc.title, url);
	}-*/;

	@Override
	protected native void addHistoryEventHandler() /*-{
		var that = this;
		var oldHandler = $wnd.onpopstate;
		$wnd.onpopstate = $entry(function(e) {
			that.@com.loreal.sandbox.client.history.CustomHistorian::onHistoryChange()();
			if (oldHandler) {
				oldHandler();
			}
		});
	}-*/;

	private native void consoleLog(String s) /*-{
		$wnd.console.log(s);
	}-*/;
}
