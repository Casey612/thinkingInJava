package ch18;

import java.io.File;
import java.io.FilenameFilter;
import java.util.*;
import java.util.regex.Pattern;

/**
 * @author yuzhe
 * @since 10/8/18
 */
public final class Dictory {

    public static File[] local(File dir, final String regex) {
        return dir.listFiles(new FilenameFilter() {
            private Pattern pattern = Pattern.compile(regex);

            @Override
            public boolean accept(File dir, String name) {
                return pattern.matcher(name).matches();
            }
        });
    }


    public static File[] local(String path, final String regex) {
        return local(new File(path), regex);
    }


    public static class TreeInfo implements Iterable<File> {
        public List<File> files = new ArrayList<File>();
        public List<File> dirs = new ArrayList<File>();

        @Override
        public Iterator<File> iterator() {
            return files.iterator();
        }

        void addAll(TreeInfo other) {
            files.addAll(other.files);
            dirs.addAll(other.dirs);
        }

        @Override
        public String toString() {
            return "dirs: " + Arrays.toString(files.toArray()) + "\n"
                    + "files: " + Arrays.toString(dirs.toArray());
        }


    }

    public static TreeInfo walk(String start, String regex){
        return recurseDirs(new File(start), regex);
    }

    public static TreeInfo walk(File start){
        return recurseDirs(start, ".*");
    }

    public static TreeInfo walk(String start){
        return recurseDirs(new File(start), ".*");
    }

    private static TreeInfo recurseDirs(File file, String regex) {
        TreeInfo result = new TreeInfo();
        for(File item : Objects.requireNonNull(file.listFiles())){
            if(item.isDirectory()){
                result.dirs.add(item);
                result.addAll(recurseDirs(item, regex));
            } else {
                if(item.getName().matches(regex)){
                    result.files.add(item);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(walk("."));
    }

}
