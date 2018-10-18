package ch18;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * @author: yuki
 * @date: 2018/10/7
 */
public class DirList {

    public static void main(String[] args) {
        File path = new File(".");
        String[] list;
//        if (args.length == 0) {
//            list = path.list();
//        } else {
//            list = path.list(getFilter(args[0]));
//        }

        list = path.list(getContentFilter("."));

        Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
        System.out.println(Arrays.toString(list));

        System.out.println("total space: " + path.getTotalSpace());
        System.out.println("usable space: " + path.getUsableSpace());
    }

    static class DirFilter implements FilenameFilter {

        private Pattern pattern;

        public DirFilter(String regex) {
            pattern = Pattern.compile(regex);
        }

        @Override
        public boolean accept(File dir, String name) {
            return pattern.matcher(name).matches();
        }
    }

    public static FilenameFilter getFilter(String regex) {
        return new FilenameFilter() {
            private Pattern pattern = Pattern.compile(regex);

            @Override
            public boolean accept(File dir, String name) {
                return pattern.matcher(name).matches();
            }
        };
    }

    public static FilenameFilter getContentFilter(String content) {
        return (dir, name) -> name.contains(content);
    }
}
