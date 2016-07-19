package com.loreal.sandbox.shared.model;

import java.io.Serializable;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.JSONBoolean;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;

@SuppressWarnings("serial")
public class DataLayer implements Serializable {
	Brand brand = null;
	Boolean adsBlocked = null;
	String country = null;
	String language = null;
	String siteTypeLevel = null;
	String uiUser = null;
	String pageCategory = null;
	String virtualPageURL = null;
	String virtualPageTitle = null;

	public DataLayer() {

	}

	private void init(JSONObject dataLayerObject) {
		if (adsBlocked != null) {
			dataLayerObject.put("adsBlocked", JSONBoolean.getInstance(adsBlocked));
		}
		if (brand != null) {
			dataLayerObject.put("brand", new JSONString(brand.code()));
		}
		if (pageCategory != null) {
			dataLayerObject.put("pageCategory", new JSONString(pageCategory));
		}
		if (virtualPageURL != null) {
			dataLayerObject.put("virtualPageURL", new JSONString(virtualPageURL));
		}
		if (virtualPageTitle != null) {
			dataLayerObject.put("virtualPageTitle", new JSONString(virtualPageTitle));
		}
		if (country != null) {
			dataLayerObject.put("country", new JSONString(country));
		}
		if (language != null) {
			dataLayerObject.put("language", new JSONString(language));
		}
		if (siteTypeLevel != null) {
			dataLayerObject.put("siteTypeLevel", new JSONString(siteTypeLevel));
		}
		if (uiUser != null) {
			dataLayerObject.put("uiUser", new JSONString(uiUser));
		}
	}

	public void push() {
		JSONObject dl = new JSONObject();
		init(dl);
		nativePush(dl.getJavaScriptObject());
	}

	public void pushPageView() {
		JSONObject dl = new JSONObject();
		init(dl);
		dl.put("event", new JSONString("updatevirtualpath"));
		nativePush(dl.getJavaScriptObject());
	}

	private native void nativePush(JavaScriptObject object) /*-{
		$wnd["dataLayer"] = $wnd["dataLayer"] || [];
		$wnd.dataLayer.push(object);
	}-*/;

	public boolean isAdsBlocked() {
		return adsBlocked;
	}

	public void setAdsBlocked(boolean adsBlocked) {
		this.adsBlocked = adsBlocked;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public String getPageCategory() {
		return pageCategory;
	}

	public void setPageCategory(String pageCategory) {
		this.pageCategory = pageCategory;
	}

	public String getvirtualPageURL() {
		return virtualPageURL;
	}

	public void setVirtualPageUrl(String virtualPageURL) {
		this.virtualPageURL = virtualPageURL;
	}

	public String getVirtualPageTitle() {
		return virtualPageTitle;
	}

	public void setVirtualPageTitle(String virtualPageTitle) {
		this.virtualPageTitle = virtualPageTitle;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getSiteTypeLevel() {
		return siteTypeLevel;
	}

	public void setSiteTypeLevel(String siteTypeLevel) {
		this.siteTypeLevel = siteTypeLevel;
	}

	public String getUiUser() {
		return uiUser;
	}

	public void setUiUser(String uiUser) {
		this.uiUser = uiUser;
	}

}
