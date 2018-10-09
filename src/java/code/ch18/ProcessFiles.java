package code.ch18;

import lombok.AllArgsConstructor;

import java.io.*;

/**
 * @author yuzhe
 * @since 10/9/18
 */
@AllArgsConstructor
public class ProcessFiles {

    public interface Strategy {
        void process(File file);
    }

    private Strategy strategy;
    private String ext;

    public void start(String[] args) {
        if (args.length == 0) {
            processDictionaryTree(new File("."));
        } else {
            for (String arg : args) {
                File fileArg = new File(".");
                if (fileArg.isDirectory()) {
                    processDictionaryTree(fileArg);
                } else {
                    if (!arg.endsWith("." + ext)) {
                        arg = arg + "." + ext;
                    }
                    try {
                        strategy.process(new File(arg).getCanonicalFile());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void processDictionaryTree(File root) {
        for (File file : Dictory.walk(root.getAbsolutePath(), ".*\\." + ext)) {
            strategy.process(file);
        }
    }

    public static void main(String[] args) {
        new ProcessFiles(System.out::println, "java").start(args);
    }
}
