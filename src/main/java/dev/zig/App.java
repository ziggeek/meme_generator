package dev.zig;


import java.util.Objects;

import static dev.zig.MemeGenerator.generate;
import static dev.zig.MemeGenerator.printHelp;
import static dev.zig.MemeGeneratorProperties.fromArgs;

public class App {
    public static void main(String[] args) {
        var command = args[0].toLowerCase();
        if (Objects.equals(command, "help")) {
            printHelp();
        } else if (Objects.equals(command, "mem")) {
            generate(fromArgs(args));
        } else {
            System.out.printf("Unknown command: %s", command);
            printHelp();
        }
    }
}
