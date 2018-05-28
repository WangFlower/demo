package com.example.lib.process;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

/**
 * Created by wang.renguang on 2018/5/2.
 */
@AutoService(Processor.class)
public class RouterProcessor extends AbstractProcessor {

    Messager messager;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        messager = processingEnvironment.getMessager();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotationTypes = new HashSet<>();
        annotationTypes.add("com.example.mrouter.compiler.MRouter");
        return annotationTypes;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return super.getSupportedSourceVersion();
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {

        for (TypeElement element:set) {



        }

        MethodSpec main = MethodSpec.methodBuilder("main")
                                    .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                                    .returns(void.class)
                                    .addParameter(String[].class, "args")
                                    .addStatement("$T.out.println($S)", System.class, "Hello, JavaPoet!")
                                    .build();

        TypeSpec helloWorld = TypeSpec.classBuilder("HelloWorld")
                                      .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                                      .addMethod(main)
                                      .build();

        JavaFile javaFile = JavaFile.builder("com.example.momo.myapplication", helloWorld)
                                    .build();

        try {
            javaFile.writeTo(processingEnv.getFiler());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }


    private void log(String m){
        messager.printMessage(Diagnostic.Kind.NOTE,m);
    }
}
