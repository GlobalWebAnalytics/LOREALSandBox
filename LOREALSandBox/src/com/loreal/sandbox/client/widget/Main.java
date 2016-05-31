package com.loreal.sandbox.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.polymer.paper.widget.PaperDrawerPanel;

public class Main extends Composite {

	private static MainUiBinder uiBinder = GWT.create(MainUiBinder.class);

	interface MainUiBinder extends UiBinder<Widget, Main> {
	}
	@UiField
    PaperDrawerPanel drawerPanel;
    @UiField
    HTMLPanel content;
    
	public Main() {
		initWidget(uiBinder.createAndBindUi(this));
		addContent();
	}
	
	private void addContent() {
		Content c=new Content();
	    content.add(c);
	}
}
