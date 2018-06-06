package Utils;

import Game.SlickGame;
import Graphics.Position;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;

import java.util.ArrayList;
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

    public static void drawTextWithBackground(String text, Graphics graphics, float x, float y, Color backgroundColor, int margin) {
        float[] newCoords = GraphicsUtils.getCoordinatesForTextCenteredAt(text, graphics.getFont(), x, y);
        x = newCoords[0];
        y = newCoords[1];
        Color textColor = graphics.getColor();
        graphics.setColor(backgroundColor);
        int width = graphics.getFont().getWidth(text) + margin * 2;
        int height = graphics.getFont().getHeight(text) + margin * 2;
        graphics.fillRoundRect(x-margin, y-margin, width, height, 20);
        graphics.setColor(textColor);
        graphics.drawString(text, x, y);
    }

    public static ArrayList<int[]> drawTexts(String[] texts, Graphics graphics, Position position, int margin) {
        int maxWidth = 0;
        for (String text : texts) {
            int textWidth = graphics.getFont().getWidth(text);
            if (textWidth > maxWidth) {
                maxWidth = textWidth;
            }
        }
        maxWidth += margin*2;
        int height = graphics.getFont().getLineHeight() * texts.length;
        int[] coords = GraphicsUtils.getCoordsToDraw(maxWidth, height, position, margin);

        ArrayList<int[]> textsCoords = new ArrayList<>();

        for (int i = 0; i < texts.length; i++) {
            textsCoords.add(
                new int[] {
                    coords[0],
                    coords[1]+i*graphics.getFont().getLineHeight(),
                    graphics.getFont().getWidth(texts[i]),
                    graphics.getFont().getLineHeight()
                }
            );
            graphics.drawString(texts[i], coords[0], coords[1]+i*graphics.getFont().getLineHeight());
        }

        return textsCoords;
    }

    public static ArrayList<int[]> drawTextsWithBackground(String[] texts, Graphics graphics, Position position, Color bgColor, Color fgColor, int margin) {
        int width = 0;
        for (String text : texts) {
            int textWidth = graphics.getFont().getWidth(text);
            if (textWidth > width) {
                width = textWidth;
            }
        }
        width += margin*2;
        int height = graphics.getFont().getLineHeight() * texts.length + margin * 2;
        int[] coords = GraphicsUtils.getCoordsToDraw(width, height, position, margin);

        Color oldColor = graphics.getColor();
        graphics.setColor(bgColor);
        graphics.fillRect(coords[0]-margin, coords[1], width+margin, height+margin);
        graphics.setColor(fgColor);
        ArrayList<int[]> textsCoords = GraphicsUtils.drawTexts(texts, graphics, position, margin);
        graphics.setColor(oldColor);
        return textsCoords;

    }

    private static float[] getCoordinatesForTextCenteredAt(String text, Font font, float x, float y) {
        float width = font.getWidth(text);
        float height = font.getHeight(text);
        return new float[] { x-width/2, y-height/2 };
    }

    public static boolean inRectangle(int x, int y, int width, int height, Point point) {
        return (point.getX() > x && point.getX() < x+width && point.getY() > y && point.getY() < y+height);
    }

    public static boolean inRectangle(int[] coords, Point point) {
        return GraphicsUtils.inRectangle(coords[0], coords[1], coords[2], coords[3], point);
    }

}
