package com.loreal.sandbox.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class FacebookViewImpl extends Composite implements FacebookView {

	private static FacebookViewImplUiBinder uiBinder = GWT.create(FacebookViewImplUiBinder.class);

	@SuppressWarnings("unused")
	private Presenter presenter;

	interface FacebookViewImplUiBinder extends UiBinder<Widget, FacebookViewImpl> {
	}

	public FacebookViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

}
