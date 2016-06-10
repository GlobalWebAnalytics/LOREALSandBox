package com.loreal.sandbox.client.services;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("mail")
public interface TaggingMailService extends RemoteService {
	void sendMail(String mail);
}
