package com.loreal.sandbox.client.services;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface GoogleAnalyticsServicesAsync {
	void getAccount(AsyncCallback<Void> callback);
}
