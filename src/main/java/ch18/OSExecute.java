package ch18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yuzhe
 * @since 10/12/18
 */
public class OSExecute {

    public static void command(String command) {
        boolean err = false;
        try {
            Process process = new ProcessBuilder(command.split(" ")).start();

            BufferedReader results = new BufferedReader(
                    new InputStreamReader(process.getInputStream())
            );
            String s;
            while ((s = results.readLine()) != null) {
                System.out.println(s);
            }

            BufferedReader errors = new BufferedReader(
                    new InputStreamReader(process.getErrorStream())
            );
            while ((s = errors.readLine()) != null) {
                System.out.println(s);
                err = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (err) {
            throw new OSExecuteException("errors executing " + command);
        }
    }

    public static void commandList(String command) {
        boolean err = false;
        try {
            Process process = new ProcessBuilder(command.split(" ")).start();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(process.getInputStream())
            );

            List<String> results = in.lines().collect(Collectors.toList());
            System.out.println(Arrays.toString(results.toArray()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
