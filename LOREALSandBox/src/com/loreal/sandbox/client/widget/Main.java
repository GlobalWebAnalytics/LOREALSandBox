package com.loreal.sandbox.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.ParagraphElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasOneWidget;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.loreal.sandbox.client.mvp.ClientFactory;
import com.loreal.sandbox.client.place.GoogleAnalyticsPlace;
import com.loreal.sandbox.client.place.HomePlace;
import com.loreal.sandbox.client.services.TaggingMailService;
import com.loreal.sandbox.client.services.TaggingMailServiceAsync;
import com.vaadin.polymer.iron.widget.IronCollapse;
import com.vaadin.polymer.paper.widget.PaperDialog;
import com.vaadin.polymer.paper.widget.PaperDrawerPanel;
import com.vaadin.polymer.paper.widget.PaperIconItem;

public class Main extends Composite implements IsWidget, HasOneWidget {

	private static MainUiBinder uiBinder = GWT.create(MainUiBinder.class);

	private ClientFactory clientFactory;
	private TaggingMailServiceAsync taggingMailSvc = GWT.create(TaggingMailService.class);
	Widget widget;

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
	PaperIconItem analytics;
	@UiField
	IronCollapse collapseGoogleAnalytics;
	@UiField
	PaperIconItem videoYoutube;

	@UiField
	PaperIconItem test;
	@UiField
	IronCollapse collapseTest;

	@UiField
	PaperIconItem taggingMailTest;

	@UiField
	PaperDialog notImplementedDialog;
	@UiField
	ParagraphElement notImplementedContent;

	public Main() {
		initWidget(uiBinder.createAndBindUi(this));

		home.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				clientFactory.getPlaceController().goTo(new HomePlace(""));
			}
		});

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

		analytics.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				collapseGoogleAnalytics.toggle();
				clientFactory.getPlaceController().goTo(new GoogleAnalyticsPlace(""));
			}
		});

		videoYoutube.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				addContent();
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
				notImplementedContent
						.setInnerText("Mail campaign for testing the tracking of mail campaign is not yet implemented. "
								+ "We have troubleshit with service in server side. "
								+ "You can use : http://mailchimp.com/ or https://aws.amazon.com/ses/ to test it.");
				notImplementedDialog.open();
				// sendTaggingMailTest();
			}
		});
	}

	private void addContent() {
		Content c = new Content();
		int length = content.getWidgetCount();
		for (int i = 0; i < length; i++) {
			content.remove(0);
		}
		content.add(c);
	}

	@SuppressWarnings("unused")
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

	@Override
	public void setWidget(IsWidget w) {
		setWidget(asWidgetOrNull(w));
	}

	@Override
	public Widget getWidget() {
		return widget;
	}

	@Override
	public void setWidget(Widget w) {
		if (w != null) {
			content.clear();
			content.add(w);
			widget = w;
		}
	}

	public void setClientFactory(ClientFactory cf) {
		clientFactory = cf;
	}
}
