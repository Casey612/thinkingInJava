package ch20;


import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.Writer;
import java.util.Set;

/**
 * @author: yuki
 * @date: 2018/10/27
 */

@SupportedAnnotationTypes("ch20.ExtractInterface")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class InterfaceExtractorProcessor extends AbstractProcessor {

    /**
     * JDK8 8后不再包含apt
     * javac在执行时调用实现了该API的程序，对编译器增强。
     *
     * @param annotations 一个是在本轮中要进行处理的注解集;
     * @param roundEnv    包含了有关当前处理轮次的信息的RoundEnv 引用
     * @return
     */
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        //  这里是生成java源文件的地方，在编译前会执行这里，然后自动编译生成的java源文件

        Messager messager = processingEnv.getMessager();
        for (TypeElement annotation : annotations) {
            for (Element element : roundEnv.getElementsAnnotatedWith(annotation)) {
               //todo
            }
        }

        return true;
    }
}
