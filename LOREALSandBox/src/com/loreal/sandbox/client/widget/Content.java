package com.loreal.sandbox.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.vaadin.polymer.iron.widget.IronCollapse;

public class Content extends Composite {

	private static ContentUiBinder uiBinder = GWT.create(ContentUiBinder.class);

	interface ContentUiBinder extends UiBinder<HTMLPanel, Content> {
	}

	@UiField
	Button heading1;
	@UiField
	IronCollapse collapse1;
	@UiField
	Button heading2;
	@UiField
	IronCollapse collapse2;
	@UiField
	Button heading3;
	@UiField
	IronCollapse collapse3;

	public Content() {
		initWidget(uiBinder.createAndBindUi(this));

		heading1.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				collapse1.toggle();
			}
		});

		heading2.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				collapse2.toggle();
			}
		});

		heading3.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				collapse3.toggle();
			}
		});
	}
}
