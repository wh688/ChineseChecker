package org.chinesechecker.graphics;

import org.chinesechecker.client.ManLogic;
import org.chinesechecker.graphics.Presenter;
import org.game_api.GameApi;
import org.game_api.GameApi.Container;
import org.game_api.GameApi.Game;
import org.game_api.GameApi.UpdateUI;
import org.game_api.GameApi.VerifyMove;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootLayoutPanel;

public class CheckerEntryPoint implements EntryPoint {
	Container container;
	Presenter presenter;	
	@Override
	public void onModuleLoad() {		
		Game game = new Game() {
			@Override
			public void sendVerifyMove(VerifyMove verifyMove) {
				container.sendVerifyMoveDone(new ManLogic(null, null,null,null).verify(verifyMove));
			}
			@Override
			public void sendUpdateUI(UpdateUI updateUI) {
				presenter.updateUI(updateUI);
			}
		};
		container = new GameApi.ContainerConnector(game);		
		Graphics graphics = new Graphics();
		presenter = new Presenter(graphics, container);
		RootLayoutPanel.get().add(graphics);
		container.sendGameReady();
	}
}