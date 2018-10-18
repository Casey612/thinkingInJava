package ch18;

import java.io.*;

/**
 * @author yuzhe
 * @since 10/9/18
 */
public class MakeDirectories {

    private static void fileData(File f){
        System.out.println("absolute path:" + f.getAbsolutePath());
        System.out.println("can read:" + f.canRead());
        System.out.println("can write:" + f.canWrite());
        System.out.println("get name:" + f.getName());
        System.out.println("length:" + f.length());
        System.out.println("last modified time:" + f.lastModified());
        System.out.println("is file:" + f.isFile());
        System.out.println("is dictionary:" + f.isDirectory());
    }

    public static void main(String[] args) {
        String path = "/home/yuzhe/IdeaProjects/thinkingInJava/src/main/resources/";

        File old = new File(path + "/ch18/text");
        File rname = new File(path + "/ch18/Text");

        old.renameTo(rname);
        fileData(old);
        fileData(rname);
    }



}
