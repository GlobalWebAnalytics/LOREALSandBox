package com.loreal.sandbox.client.services;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("ga")
public interface GoogleAnalyticsServices extends RemoteService {

	void getAccount();

}
