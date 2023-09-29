package dev.zig;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemeGeneratorProperties {

    private Position position = Position.BOTTOM;
    private String text = "";
    private String fontName = "Arial";
    private Color textColor = Color.WHITE;
    private int fontSize = 50;
    private String InputImage = "";
    private String outputImage = "";

    public static MemeGeneratorProperties fromArgs(String[] args) {
        MemeGeneratorProperties properties = new MemeGeneratorProperties();
        List<String> commands = new ArrayList<>(Arrays.asList(args));

        commands.forEach(i -> {
            if (commands.indexOf(i) < commands.size() - 1) {
                String value = commands.get(commands.indexOf(i) + 1);
                switch (i) {
                    case "mem":
                        properties.setInputImage(commands.get(commands.indexOf(i) + 1));
                        properties.setOutputImage(commands.get(commands.indexOf(i) + 3));
                        properties.setText(commands.get(commands.indexOf(i) + 2));
                        break;
                    case "--position":
                        properties.setPosition(Position.valueOf(value.toUpperCase()));
                        break;
                    case "--font":
                        properties.setFontName(value);
                        break;
                    case "--size":
                        properties.setFontSize(Integer.parseInt(value));
                        break;
                    case "--color":
                        properties.setTextColor(Color.decode(value));
                        break;
                }
            }
        });

        return properties;
    }
}
