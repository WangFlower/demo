package com.example.datastate_processor;

import com.google.auto.service.AutoService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;

@AutoService(Processor.class)
public class DataStateProcessor extends AbstractProcessor {

    private Elements mUtils;
    private Filer mFiler;
    private Messager messager;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        mUtils = processingEnvironment.getElementUtils();
        mFiler = processingEnvironment.getFiler();
        messager = processingEnvironment.getMessager();
    }


    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> set = new HashSet<>();
        set.add(State.class.getName());
        return set;
    }


    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        final Set<Element> allFields = new HashSet<>(roundEnvironment.getElementsAnnotatedWith(State.class));
        final Set<Element> validFields = new HashSet<>();
        for (Element element : allFields) {
            if (isValid(element)) {
                validFields.add(element);
            }
        }
        return true;
    }


    private boolean isValid(Element element) {
        Set<Modifier> set = element.getModifiers();
        if (set.contains(Modifier.STATIC)
                || set.contains(Modifier.FINAL)) {
            log("Field can not be static and final");
            return false;
        }
        List<? extends AnnotationMirror> annotationMirrors = element.getAnnotationMirrors();
        for (AnnotationMirror annotationMirror : annotationMirrors) {
            // 过滤掉不支持序列化的类型
        }


        return true;
    }


    private void log(String m) {
        messager.printMessage(Diagnostic.Kind.NOTE, m);
    }
}
