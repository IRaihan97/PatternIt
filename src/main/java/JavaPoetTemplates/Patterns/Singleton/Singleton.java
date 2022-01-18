package JavaPoetTemplates.Patterns.Singleton;

import JavaPoetTemplates.Fields;
import com.squareup.javapoet.*;

import javax.lang.model.element.Modifier;
import java.util.ArrayList;

public class Singleton {
    private String className;
    private String objectName;
    private String singletonType;
    ClassName classType;
    private String packageName;
    private ArrayList<Fields> fields;
    private ArrayList<MethodSpec> methods;
    private TypeSpec classGen;

    public Singleton(String className, String objectName,
                     String singletonType, String packageName,
                     ArrayList<Fields> fields, ArrayList<MethodSpec> methods) {
        this.className = className;
        this.objectName = objectName;
        this.singletonType = singletonType;
        this.packageName = packageName;
        this.fields = fields;
        this.methods = methods;
        classType = ClassName.get(packageName, className);
        if(singletonType.matches("Enum")){
            classGen = enumSingleton();
        }
        else{
            classGen = generateSingleton();
        }


    }

    public Singleton(String className, String objectName,
                     String singletonType, String packageName)
    {
        this.className = className;
        this.objectName = objectName;
        this.singletonType = singletonType;
        this.packageName = packageName;
        classType = ClassName.get(packageName, className);
        if(singletonType.matches("Enum")){
            classGen = enumSingleton();
        }
        else{
            classGen = generateSingleton();
        }
    }

    public Singleton(){}

    private TypeSpec generateSingleton(){
        FieldSpec.Builder singletonObj = FieldSpec
                .builder(classType, objectName, Modifier.PRIVATE, Modifier.STATIC);
        if(singletonType.matches("Eager")){
            singletonObj.initializer("new $T()", classType);
        }
        MethodSpec privConstructor = MethodSpec
                .methodBuilder(className)
                .addModifiers(Modifier.PRIVATE)
                .build();
        MethodSpec.Builder getInstance = MethodSpec.methodBuilder("getInstance");
        if(singletonType.matches("Lazy")){
            lazySingleton(getInstance);
        }
        else if(singletonType.matches("Sync")){
            syncSingleton(getInstance);
        }
        else{
            eagerSingleton(getInstance);
        }
        TypeSpec.Builder classBuilder = TypeSpec
                .classBuilder(className)
                .addField(singletonObj.build())
                .addMethod(privConstructor)
                .addMethod(getInstance.build());

        addExtraFieldsandMethods(classBuilder);

        return classBuilder.build();
    }

    private void lazySingleton(MethodSpec.Builder getInstance)
    {
        getInstance
                .addModifiers(Modifier.PUBLIC)
                .returns(classType)
                .beginControlFlow("if ($N == null)", objectName)
                .addStatement("$N = new $T()", objectName, classType)
                .endControlFlow()
                .addStatement("return " + objectName);
    }

    private void eagerSingleton(MethodSpec.Builder getInstance){
        getInstance
                .addModifiers(Modifier.PUBLIC)
                .returns(classType)
                .addStatement("return " + objectName);
    }

    //ThreadSafe
    private void syncSingleton(MethodSpec.Builder getInstance){
        getInstance
                .addModifiers(Modifier.PUBLIC)
                .addModifiers(Modifier.STATIC)
                .returns(classType)
                .beginControlFlow("synchronized($T.class)", classType)
                .beginControlFlow("if ($N == null)" , objectName)
                .addStatement("$N = new $T()", objectName, classType)
                .endControlFlow()
                .endControlFlow()
                .addStatement("return " + objectName);
    }

    private TypeSpec enumSingleton()
    {
        MethodSpec method = MethodSpec
                .methodBuilder("dummyMethod")
                .addModifiers(Modifier.PUBLIC)
                .addComment("ADD CODE HERE")
                .build();
        TypeSpec.Builder enumClass = TypeSpec
                .enumBuilder(className)
                .addModifiers(Modifier.PUBLIC)
                .addEnumConstant("INSTANCE")
                .addMethod(method);
        addExtraFieldsandMethods(enumClass);
        return enumClass.build();
    }


    private void addExtraFieldsandMethods(TypeSpec.Builder classBuilder){
        if(fields != null){
            fields.forEach((field) -> classBuilder.addField(field.getField()));
        }
        if(methods != null){
            methods.forEach((method) -> classBuilder.addMethod(method));
        }
    }

    public TypeSpec getClassGen(){
        return classGen;
    }
}