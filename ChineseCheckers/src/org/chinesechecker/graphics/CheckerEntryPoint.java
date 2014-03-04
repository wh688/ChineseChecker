package org.chinesechecker.graphics;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.core.shared.GWT;

public class CheckerEntryPoint implements EntryPoint {
	@Override
	public void onModuleLoad() {
		Graphics graphics = new Graphics();
		RootLayoutPanel.get().add(graphics);
	}
}
