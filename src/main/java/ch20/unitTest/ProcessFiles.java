package ch20.unitTest;

import java.io.File;

/**
 * @author yuzhe
 * @since 10/9/18
 */
public class ProcessFiles {

    //纯粹为了使用接口从ch18复制过来，lombok插件和编译期注解蜜汁冲突
    public interface Strategy {
        void process(File file);
    }

    private Strategy strategy;
    private String ext;

    public ProcessFiles(Strategy strategy, String ext) {
        this.strategy = strategy;
        this.ext = ext;
    }
}
