package com.example.datastate_processor;

import com.google.auto.service.AutoService;

import java.util.HashMap;
import java.util.HashSet;
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
        final Set<Element> allFields = new HashSet<>(roundEnvironment.getElementsAnnotatedWith(State.class));
        // TODO kotlin

        for (Element element : allFields) {
            if (!isValid(element)) {
                messager.printMessage(Diagnostic.Kind.ERROR, "Field is not valid!", element);
                return true;
            }
        }


        Map<Element, Set<Element>> classes = getClassElements(allFields);
        Set<Element> classElements = classes.keySet();
        for (Element classElement : classElements) {
            Element temp = getPrivateClass(classElement);
            if (temp != null) {
                messager.printMessage(Diagnostic.Kind.ERROR, "Class must not be private", temp);
                return true;
            }
        }

        for (Element classElemet : classElements) {
            final Element packageElement = getElement(classElemet, ElementKind.PACKAGE);
            String packageName = packageElement.asType().toString();
            if ("package".equals(packageName)) {
                // TOOD
            }

            final String className = getClassName(classElemet);
            final boolean isView = isAssignable(classElemet, "android.view.View");

        }


        return true;
    }


    private boolean isValid(Element element) {
        // 变量修饰范围
        Set<Modifier> set = element.getModifiers();
        log("" + element.asType());
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


    private Map<Element, Set<Element>> getClassElements(Set<Element> allFields) {
        Map<Element, Set<Element>> result = new HashMap<>();
        for (Element element : allFields) {
            Element classElement = getElement(element, ElementKind.CLASS);
            Set<Element> elements = result.get(classElement);
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

    private boolean isAssignable(Element element, String className) {
        return isAssignable(element.asType(), className);
    }

    private boolean isAssignable(TypeMirror typeMirror, String className) {
        return mTypeUtils.isAssignable(typeMirror, mUtils.getTypeElement(className).asType());
    }


    private void log(String m) {
        messager.printMessage(Diagnostic.Kind.NOTE, m);
    }
}
