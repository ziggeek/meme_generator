package dev.zig;

import lombok.SneakyThrows;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Map;

public class MemeGenerator {

    @SneakyThrows
    public static void generate(MemeGeneratorProperties properties) {
            File imageFile = new File(properties.getInputImage());
            BufferedImage image = ImageIO.read(imageFile);

            Graphics2D graphics = image.createGraphics();
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            graphics.setFont(new Font(properties.getFontName(), Font.PLAIN, properties.getFontSize()));
            graphics.setColor(properties.getTextColor());

            var texPosition = findTextPosition(properties.getPosition(), properties.getText(), image, graphics);

            graphics.drawString(properties.getText(), texPosition.get("x"), texPosition.get("y"));

            ImageIO.write(image, "png", new File(properties.getOutputImage()));
            System.out.println("Text added to the image and saved as " + properties.getOutputImage());

    }

    private static Map<String, Integer> findTextPosition(Position position, String text, BufferedImage image, Graphics2D graphics) {
        int x = (image.getWidth() - graphics.getFontMetrics().stringWidth(text)) / 2;

        switch (position) {
            case CENTER:
                return Map.of("x", x, "y", (image.getHeight() / 2));
            case TOP:
                return Map.of("x", x, "y", 60);
            case BOTTOM:
            default:
                return Map.of("x", x, "y", (image.getHeight() - 60));

        }
    }

    public static void printHelp() {
        System.out.println("Usage: java -jar application.jar <command> [args]");
        System.out.println("Commands:");
        System.out.println("  help - Display help");
        System.out.println("  mem <image_path> <text> <output_path> [options] - Add text to an image");
        System.out.println("Options:");
        System.out.println("  --position <position> - Position of the text (center, top, bottom). Default is bottom.");
        System.out.println("  --font <font_name> - Font name for the text. Default is Arial.");
        System.out.println("  --size <font_size> - Font size for the text. Default is 50.");
        System.out.println("  --color <color> - Text color in hexadecimal format (e.g., #FFFFFF). Default is white.");
    }
}
