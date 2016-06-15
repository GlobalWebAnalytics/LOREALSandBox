package com.loreal.sandbox.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class GAYoutubeViewImpl extends Composite implements GAYoutubeView {

	private static GAYoutubeViewImplUiBinder uiBinder = GWT.create(GAYoutubeViewImplUiBinder.class);

	@SuppressWarnings("unused")
	private Presenter presenter;

	interface GAYoutubeViewImplUiBinder extends UiBinder<Widget, GAYoutubeViewImpl> {
	}

	public GAYoutubeViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

}
