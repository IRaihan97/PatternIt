package JavaPoetTemplates.Patterns.Template;

import JavaPoetTemplates.FieldGen;
import JavaPoetTemplates.Methods;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import javax.lang.model.element.Modifier;
import java.util.ArrayList;

public class Abstract {
    private String absClassName;
    private ArrayList<FieldGen> fieldGenToAdd;
    private ArrayList<Methods> methodsToAdd;
    private ClassName componentType;
    private TypeSpec absClassGen;

    public Abstract(String absClassName, String packageName, ArrayList<FieldGen> fieldGenToAdd, ArrayList<Methods> methodsToAdd) {
        this.absClassName = absClassName;
        this.fieldGenToAdd = fieldGenToAdd;
        this.methodsToAdd = methodsToAdd;
        this.componentType = ClassName.get(packageName, absClassName);
        this.absClassGen = generateAbsClass();
    }

    private TypeSpec generateAbsClass() {
        TypeSpec.Builder absClassBuilder = TypeSpec
                .classBuilder(absClassName)
                .addModifiers(Modifier.PUBLIC, Modifier.ABSTRACT);
        if(fieldGenToAdd !=null){
            fieldGenToAdd.forEach((field) -> absClassBuilder.addField(field.getField()));
        }
        if(methodsToAdd!=null){

            methodsToAdd.forEach((method) -> absClassBuilder.addMethod(method.getMethod()));
        }
        MethodSpec.Builder finalMethod = MethodSpec
                .methodBuilder("protectedMethodExample")
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addComment("This is a generic definition for the final method")
                .addComment("You can apply your own method definition here");
        methodsToAdd.forEach((method) -> finalMethod.addStatement("$L()", method.getMethodName()));
        absClassBuilder.addMethod(finalMethod.build());

        return absClassBuilder.build();

    }

    public ClassName getComponentType() {
        return componentType;
    }

    public ArrayList<FieldGen> getFieldsToAdd() {
        return fieldGenToAdd;
    }

    public ArrayList<Methods> getMethodsToAdd() {
        return methodsToAdd;
    }

    public TypeSpec getAbsClassGen() {
        return absClassGen;
    }
}
