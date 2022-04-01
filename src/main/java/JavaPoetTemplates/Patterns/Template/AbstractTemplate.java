package JavaPoetTemplates.Patterns.Template;

import JavaPoetTemplates.FieldGen;
import JavaPoetTemplates.MethodGen;
import JavaPoetTemplates.ParameterGen;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import javax.lang.model.element.Modifier;
import java.util.ArrayList;

public class AbstractTemplate {
    private String absClassName;
    private ArrayList<FieldGen> fieldGenToAdd;
    private ArrayList<MethodGen> methodGenToAdd;
    private ClassName componentType;
    private TypeSpec absClassGen;


    public AbstractTemplate(String absClassName, String packageName, ArrayList<FieldGen> fieldGenToAdd, ArrayList<MethodGen> methodGenToAdd) {
        this.absClassName = absClassName;
        this.fieldGenToAdd = fieldGenToAdd;
        this.methodGenToAdd = methodGenToAdd;
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
        if(methodGenToAdd !=null){

            methodGenToAdd.forEach(
                    (method) -> absClassBuilder.addMethod(method.getMethod()
                            .toBuilder()
                            .addModifiers(Modifier.ABSTRACT).build()));
        }
        MethodSpec.Builder finalMethod = MethodSpec
                .methodBuilder("templateMethodExample")
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addComment("This is a generic definition for the final method")
                .addComment("You can apply your own method definition here");
        methodGenToAdd.forEach((method) -> finalMethod.addStatement("$L()", method.getMethodName()));
        absClassBuilder.addMethod(finalMethod.build());

        return absClassBuilder.build();

    }

    public ClassName getComponentType() {
        return componentType;
    }

//    public ArrayList<FieldGen> getFieldsToAdd() {
//        return fieldGenToAdd;
//    }

    public ArrayList<MethodGen> getMethodsToAdd() {
        return methodGenToAdd;
    }

    public TypeSpec getAbsClassGen() {
        return absClassGen;
    }
}
