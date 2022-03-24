package JavaPoetTemplates.Patterns.Composite;

import JavaPoetTemplates.FieldGen;
import JavaPoetTemplates.MethodGen;
import com.squareup.javapoet.*;

import javax.lang.model.element.Modifier;
import java.util.ArrayList;

public class Leaf {
    private String componentName;
    private Component componentClass;
    private String leafObjName;
    private ArrayList<FieldGen> fieldGenToAdd;
    private ArrayList<MethodGen> methodsToAdd;
    private ClassName componentType;
    private TypeSpec leafGen;

    public Leaf(){};

    public Leaf(Component componentClass, String leafObjName, String packageName,
                ArrayList<FieldGen> fieldGenToAdd, ArrayList<MethodGen> methodsToAdd) {
        this.componentName = componentClass.getComponentName();
        this.componentClass = componentClass;
        this.leafObjName = leafObjName;
        this.fieldGenToAdd = fieldGenToAdd;
        this.methodsToAdd = methodsToAdd;
        this.componentType = ClassName.get(packageName, componentName);
        this.leafGen = generateLeaf();
    }

    private TypeSpec generateLeaf() {
        MethodSpec.Builder constructor = MethodSpec
                .methodBuilder(leafObjName)
                .addModifiers(Modifier.PUBLIC);
        TypeSpec.Builder leafBuilder = TypeSpec
                .classBuilder(leafObjName);

        if(fieldGenToAdd !=null) {
            for (FieldGen fieldGen : fieldGenToAdd) {
                leafBuilder.addField(fieldGen.getField());
                constructor.addParameter(fieldGen.getDerivedParameter());
                constructor.addStatement("this.$N = $N", fieldGen.getFieldName(), fieldGen.getFieldName());
            }
        }
        leafBuilder.addMethod(constructor.build())
                .addSuperinterface(componentType)
                .addMethod(componentClass.getDefaultMethod().toBuilder()
                        .addAnnotation(Override.class).build());
        methodsToAdd.forEach((method) -> leafBuilder.addMethod(method.getMethod()));
        componentClass.getCommonMethods().forEach((methods) ->
                leafBuilder.addMethod(methods.getMethod().toBuilder().addAnnotation(Override.class).build()));

        return leafBuilder.build();
    }

    public TypeSpec getLeafGen() {
        return leafGen;
    }
}
