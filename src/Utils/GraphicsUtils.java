package Utils;

import Game.SlickGame;
import Graphics.Position;

import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;

import java.util.Arrays;

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

    private static int[] getCoordsToDraw(int width, int height, Position position, int margin) {
        int[] coords = new int[0];
        switch (position) {
            case TopLeft:
                coords = new int[] {margin, margin};
                break;
            case TopRight:
                coords = new int[] {SlickGame.WIDTH-width-margin, margin};
                break;
            case BottomLeft:
                coords = new int[] {margin, SlickGame.HEIGHT-height-margin};
                break;
            case BottomRight:
                coords = new int[] {SlickGame.WIDTH-width-margin, SlickGame.HEIGHT-height-margin};
                break;
        }
        return coords;
    }

    public static void drawText(String text, Graphics graphics, Position position, int margin) {
        int width = graphics.getFont().getWidth(text);
        int height = graphics.getFont().getHeight(text);
        int[] coords = GraphicsUtils.getCoordsToDraw(width, height, position, margin);
        graphics.drawString(text, coords[0], coords[1]);
    }

    public static void drawTexts(String[] texts, Graphics graphics, Position position, int margin) {
        int maxWidth = Arrays.stream(texts).map(text -> graphics.getFont().getWidth(text)).mapToInt(Integer::intValue).max().getAsInt();
        //int height = Arrays.stream(texts).map(text -> graphics.getFont().getHeight(text)).mapToInt(Integer::intValue).sum();
        int height = graphics.getFont().getLineHeight() * texts.length;
        /*int[] coords = new int[0];
        switch (position) {
            case TopLeft:
                coords = new int[] {margin, margin};
                break;
            case TopRight:
                coords = new int[] {SlickGame.WIDTH-maxWidth-margin, margin};
                break;
            case BottomLeft:
                coords = new int[] {margin, SlickGame.HEIGHT-height-margin};
                break;
            case BottomRight:
                coords = new int[] {SlickGame.WIDTH-maxWidth-margin, SlickGame.HEIGHT-height-margin};
                break;
        }*/
        int[] coords = GraphicsUtils.getCoordsToDraw(maxWidth, height, position, margin);

        for (int i = 0; i < texts.length; i++) {
            graphics.drawString(texts[i], coords[0], coords[1]+i*graphics.getFont().getLineHeight());
        }
    }

}
