package com.loreal.sandbox.client.services;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface TaggingMailServiceAsync {

	void sendMail(String mail, AsyncCallback<Void> callback);

}
