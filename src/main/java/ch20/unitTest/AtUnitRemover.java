package ch20.unitTest;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.MethodInfo;
import javassist.bytecode.annotation.Annotation;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * @author yuzhe
 * @since 11/9/18
 */
public class AtUnitRemover implements ProcessFiles.Strategy {
    private static boolean remove = false;

    @Override
    public void process(File file) {
        boolean modified = false;
        try{
            String cname = ClassNameFinder.thisClass(BinaryFile.read(file));
            if(!cname.contains(".")){
                return;
            }
            ClassPool cPool = ClassPool.getDefault();
            CtClass ctClass = cPool.get(cname);
            for(CtMethod m : ctClass.getDeclaredMethods()){
                MethodInfo mi = m.getMethodInfo();
                AnnotationsAttribute attr = (AnnotationsAttribute) mi.getAttribute(AnnotationsAttribute.visibleTag);
                if(attr == null){
                    continue;
                }
                for(Annotation ann : attr.getAnnotations()){
                    if(ann.getTypeName().startsWith("net.mindview.atunit")){
                        System.out.println(ctClass.getName() + " Method: " + mi.getName() + " " + ann);
                        if(remove){
                            ctClass.removeMethod(m);
                            modified = true;
                        }
                    }
                }
            }

            if(modified){
                ctClass.toBytecode(new DataOutputStream(new FileOutputStream(file)));
                ctClass.detach();
            }

        }catch (Exception e){

        }
    }
}
