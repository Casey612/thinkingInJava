package ch20;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
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
        for (TypeElement annotation : annotations) {
            for (Element element : roundEnv.getElementsAnnotatedWith(annotation)) {
                if(element.getKind().isClass()){
                    //明确处理注解类型
                    ExtractInterface extractInterface
                            = element.getAnnotation(ExtractInterface.class);
                    String value = extractInterface.value();

                    String fullAnnotationPath = annotation.getQualifiedName().toString();
                    String packageStr = fullAnnotationPath.substring(0, fullAnnotationPath.lastIndexOf('.'));
                    String objectType = element.getSimpleName().toString();
                    try {
                        Class<?> cl = Class.forName(packageStr + "." + objectType);

                        StringBuilder sb = new StringBuilder()
                                .append("package ").append(packageStr).append(";\n")
                                .append("public interface ").append(value).append(" {\n");

                        for(Method m : cl.getDeclaredMethods()){
                            if(Modifier.isPublic(m.getModifiers()) && !Modifier.isStatic(m.getModifiers())){
                                sb.append("\tpublic ").append(m.getReturnType()).append(" ")
                                        .append(m.getName()).append("(");

                                for(Parameter parameter : m.getParameters()){
                                    sb.append(parameter.getType()).append(" ")
                                            .append(parameter.getName()).append(", ");
                                }
                                sb.deleteCharAt(sb.length() - 1);
                                sb.deleteCharAt(sb.length() - 1);
                                sb.append(");\n");
                            }
                        }
                        sb.append("}");
                        System.out.println(sb.toString());
                        //写出源文件
                        JavaFileObject source = processingEnv.getFiler().createSourceFile(value);

                        Writer writer = source.openWriter();
                        writer.write(sb.toString());
                        writer.flush();
                        writer.close();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        }

        return true;
    }
}
