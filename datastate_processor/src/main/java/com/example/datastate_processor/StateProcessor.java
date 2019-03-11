package com.example.datastate_processor;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.MethodSpec;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;

@AutoService(Processor.class)
public class StateProcessor extends AbstractProcessor {


    private static final Map<String, String> TYPE_MAPPING = new HashMap<String, String>() {{
        put("boolean", "Boolean");
        put("boolean[]", "BooleanArray");
        put("java.lang.Boolean", "BoxedBoolean");
        put("byte", "Byte");
        put("byte[]", "ByteArray");
        put("java.lang.Byte", "BoxedByte");
        put("char", "Char");
        put("char[]", "CharArray");
        put("java.lang.Character", "BoxedChar");
        put("double", "Double");
        put("double[]", "DoubleArray");
        put("java.lang.Double", "BoxedDouble");
        put("float", "Float");
        put("float[]", "FloatArray");
        put("java.lang.Float", "BoxedFloat");
        put("int", "Int");
        put("int[]", "IntArray");
        put("java.lang.Integer", "BoxedInt");
        put("long", "Long");
        put("long[]", "LongArray");
        put("java.lang.Long", "BoxedLong");
        put("short", "Short");
        put("short[]", "ShortArray");
        put("java.lang.Short", "BoxedShort");
        put("java.lang.CharSequence", "CharSequence");
        put("java.lang.CharSequence[]", "CharSequenceArray");
        put("java.lang.String", "String");
        put("java.lang.String[]", "StringArray");
        put("java.util.ArrayList<java.lang.CharSequence>", "CharSequenceArrayList");
        put("java.util.ArrayList<java.lang.Integer>", "IntegerArrayList");
        put("java.util.ArrayList<java.lang.String>", "StringArrayList");
        put("android.os.Bundle", "Bundle");
        put("android.os.Parcelable[]", "ParcelableArray");
    }};


    private Types mTypeUtils;
    private Elements mUtils;
    private Filer mFiler;
    private Messager messager;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        mTypeUtils = processingEnv.getTypeUtils();
        mUtils = processingEnvironment.getElementUtils();
        mFiler = processingEnvironment.getFiler();
        messager = processingEnvironment.getMessager();
    }


    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> set = new HashSet<>();
        set.add("com.example.datastate_processor.State");
        return set;
    }


    @Override
    public SourceVersion getSupportedSourceVersion() {
        return super.getSupportedSourceVersion();
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        // 获得所有被State 注解的元素
        final Set<VariableElement> allFields = new HashSet<>();


        for (Element element : roundEnvironment.getElementsAnnotatedWith(State.class)) {
            if (!isValid(element)) {
                messager.printMessage(Diagnostic.Kind.ERROR, "Field is not valid!", element);
                return true;
            }
            allFields.add((VariableElement) element);
        }
        // TODO kotlin


        Map<TypeElement, Set<VariableElement>> classes = new HashMap<>();
        for (VariableElement element : allFields) {
            TypeElement classElement = (TypeElement) element.getEnclosingElement();

            Set<VariableElement> elements = classes.get(classElement);
            if (elements == null) {
                elements = new HashSet<>();
            }
            elements.add(element);
            classes.put(classElement, elements);
        }

        Set<TypeElement> classElements = classes.keySet();
        for (TypeElement classElement : classElements) {
            if (classElement.getModifiers().contains(Modifier.PRIVATE)) {
                messager.printMessage(Diagnostic.Kind.ERROR, "Class must not be private", classElement);
                return true;
            }
        }

        for (TypeElement classElemet : classElements) {
            final List<VariableElement> fields = sortedFileds(classes.get(classElemet));
            String packageName = mUtils.getPackageOf(classElemet).getQualifiedName().toString();
            String className = classElemet.getQualifiedName().toString().substring(
                    packageName.length() + 1).replace('.', '$');

            final boolean isView = mTypeUtils.isAssignable(classElemet.asType(), mUtils.getTypeElement("android.view.View").asType());

            MethodSpec.Builder saveMethodBuilder = MethodSpec.methodBuilder("save")
                    .addAnnotation(Override.class)
                    .addAnnotation(AnnotationSpec.builder(SuppressWarnings.class)
                            .addMember("value", "$S", "unchecked")
                            .build()).addModifiers(javax.lang.model.element.Modifier.PUBLIC);
        }


        return true;
    }


    private boolean isValid(Element element) {
        // 变量修饰范围
        Set<Modifier> set = element.getModifiers();
        if (set.contains(Modifier.STATIC)
                || set.contains(Modifier.FINAL)) {
            messager.printMessage(Diagnostic.Kind.NOTE, "Field can not be static or final", element);
            return false;
        }
        // 变量类型
        String type = element.asType().toString();
        if (TYPE_MAPPING.get(type) == null) {
            messager.printMessage(Diagnostic.Kind.NOTE, "unsupport type " + type, element);
            return false;
        }

        return true;
    }


    private Map<TypeElement, Set<VariableElement>> getClassElements(Set<VariableElement> allFields) {
        Map<TypeElement, Set<VariableElement>> result = new HashMap<>();
        for (VariableElement element : allFields) {
            TypeElement classElement = (TypeElement) element.getEnclosingElement();
            Set<VariableElement> elements = result.get(classElement);
            if (elements == null) {
                elements = new HashSet<>();
            }
            elements.add(element);
            result.put(classElement, elements);
        }
        return result;
    }

    private Element getPrivateClass(Element classElement) {
        if (classElement == null || classElement.getKind() != ElementKind.CLASS) {
            return null;
        } else if (classElement.getModifiers().contains(Modifier.PRIVATE)) {
            return classElement;
        } else {
            return getPrivateClass(classElement.getEnclosingElement());
        }
    }

    private Element getElement(Element element, ElementKind elementKind) {
        Element enclosingElement = element.getEnclosingElement();
        if (enclosingElement == null) {
            return enclosingElement;
        }
        return element.getKind() == elementKind ? element : getElement(enclosingElement, elementKind);
    }

    private String getClassName(Element classElement) {
        StringBuilder className = new StringBuilder(classElement.getSimpleName().toString());
        Element enclosingElement = classElement.getEnclosingElement();
        while (enclosingElement != null && enclosingElement.getKind() == ElementKind.CLASS) {
            className.insert(0, enclosingElement.getSimpleName() + "$");
            enclosingElement = enclosingElement.getEnclosingElement();
        }
        return className.toString();
    }

    private List<VariableElement> sortedFileds(Collection<VariableElement> fileds) {
        List<VariableElement> result = new ArrayList<>(fileds);
        Collections.sort(result, new Comparator<VariableElement>() {
            @Override
            public int compare(VariableElement variableElement, VariableElement t1) {
                return variableElement.getSimpleName().toString().compareTo(t1.getSimpleName().toString());
            }
        });
        return result;
    }

    private boolean isAssignable(Element element, String className) {
        return isAssignable(element.asType(), className);
    }

    private boolean isAssignable(TypeMirror typeMirror, String className) {
        return mTypeUtils.isAssignable(typeMirror, mUtils.getTypeElement(className).asType());
    }

}
