package ch20.unitTest;

import lombok.AllArgsConstructor;

import java.io.File;
import java.io.IOException;

/**
 * @author yuzhe
 * @since 10/9/18
 */
@AllArgsConstructor
public class ProcessFiles {

    //纯粹为了使用接口从ch18复制过来，lombok插件和编译期注解蜜汁冲突
    public interface Strategy {
        void process(File file);
    }

    private Strategy strategy;
    private String ext;

}
