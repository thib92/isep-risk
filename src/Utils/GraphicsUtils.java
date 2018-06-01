package Utils;

import Game.SlickGame;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;

public class GraphicsUtils {

    /**
     * Draw a centered text with the Font provided
     * @param text The text to draw
     * @param font The font to draw the text with
     * @param y The y position to draw the text with
     */
    public static void drawCenteredText(String text, Font font, float y) {
        font.drawString(SlickGame.WIDTH / 2 - (font.getWidth(text) / 2), y, text);
    }

    /**
     * Draw a centered text with the Graphics object, using its pre-defined Font
     * @param text The text to draw
     * @param graphics The Graphics to draw the text with
     * @param y The y positiopn to draw the text with
     */
    public static void drawCenteredText(String text, Graphics graphics, float y) {
        graphics.drawString(text, SlickGame.WIDTH / 2 - (graphics.getFont().getWidth(text) / 2), y);
    }

}
