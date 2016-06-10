package com.loreal.sandbox.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.loreal.sandbox.client.services.TaggingMailService;
import com.loreal.sandbox.client.services.TaggingMailServiceAsync;
import com.vaadin.polymer.iron.widget.IronCollapse;
import com.vaadin.polymer.paper.widget.PaperDrawerPanel;
import com.vaadin.polymer.paper.widget.PaperIconItem;

public class Main extends Composite {

	private static MainUiBinder uiBinder = GWT.create(MainUiBinder.class);

	private TaggingMailServiceAsync taggingMailSvc = GWT.create(TaggingMailService.class);

	interface MainUiBinder extends UiBinder<Widget, Main> {
	}

	@UiField
	PaperDrawerPanel drawerPanel;
	@UiField
	HTMLPanel content;
	@UiField
	PaperIconItem home;

	@UiField
	PaperIconItem googleTagManager;
	@UiField
	IronCollapse collapseGoogleTagManager;

	@UiField
	PaperIconItem seo;
	@UiField
	IronCollapse collapseSEO;

	@UiField
	PaperIconItem doubleClick;
	@UiField
	IronCollapse collapseDoubleClick;

	@UiField
	PaperIconItem test;
	@UiField
	IronCollapse collapseTest;

	@UiField
	PaperIconItem taggingMailTest;

	public Main() {
		initWidget(uiBinder.createAndBindUi(this));
		// addContent();

		googleTagManager.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				collapseGoogleTagManager.toggle();
			}
		});

		seo.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				collapseSEO.toggle();
			}
		});

		doubleClick.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				collapseDoubleClick.toggle();
			}
		});

		test.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				collapseTest.toggle();
			}
		});

		taggingMailTest.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				sendTaggingMailTest();
			}
		});
	}

	// private void addContent() {
	// Content c = new Content();
	// content.add(c);
	// }

	private void sendTaggingMailTest() {
		taggingMailSvc.sendMail("", new AsyncCallback<Void>() {
			@Override
			public void onFailure(Throwable caught) {
				taggingMailTest.setTitle("Fail : " + caught.getMessage());
			}

			@Override
			public void onSuccess(Void result) {
				taggingMailTest.setTitle("Sent");
			}
		});
	}
}
