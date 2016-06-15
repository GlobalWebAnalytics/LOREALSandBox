package com.loreal.sandbox.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class GoogleAnalyticsViewImpl extends Composite implements GoogleAnalyticsView {

	private static GoogleAnalyticsViewImplUiBinder uiBinder = GWT.create(GoogleAnalyticsViewImplUiBinder.class);

	@SuppressWarnings("unused")
	private Presenter presenter;

	interface GoogleAnalyticsViewImplUiBinder extends UiBinder<Widget, GoogleAnalyticsViewImpl> {
	}

	public GoogleAnalyticsViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}
}
