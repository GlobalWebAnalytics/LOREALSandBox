package com.loreal.sandbox.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.polymer.paper.widget.PaperIconItem;

public class GAFiltersViewImpl extends Composite implements GAFiltersView {

	private static GoogleAnalyticsFiltersViewImplUiBinder uiBinder = GWT
			.create(GoogleAnalyticsFiltersViewImplUiBinder.class);

	private Presenter presenter;

	@UiField
	PaperIconItem authentification;

	interface GoogleAnalyticsFiltersViewImplUiBinder extends UiBinder<Widget, GAFiltersViewImpl> {
	}

	public GAFiltersViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));

		authentification.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				authorize();
			}
		});
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;

	}

	public void authentificationHidden(String s) {
		authentification.setTitle("Google Authentification done");
	}

	private static native void authorize() /*-{
		$wnd.authorize();
	}-*/;
}
