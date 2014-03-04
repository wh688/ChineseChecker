package org.chinesechecker.graphics;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

public interface CheckerImage extends ClientBundle {
	@Source("images/Red.gif")
	ImageResource redStone();
	@Source("images/Blue.gif")
	ImageResource blueStone();
	@Source("images/Position.gif")
	ImageResource positionStone();
	@Source("images/Invalid.gif")
	ImageResource invalidStone();
	@Source("images/Title.jpg")
	ImageResource title();
	@Source("images/Back.jpg")
	ImageResource backBoard();
}
