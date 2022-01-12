package JavaPoetTemplates.Patterns.Composite;

import JavaPoetTemplates.Fields;
import com.squareup.javapoet.*;

import javax.lang.model.element.Modifier;
import java.util.ArrayList;

public class Leaf {
    private String componentName;
    private Component componentClass;
    private String leafObjName;
    private ArrayList<Fields> fieldsToAdd;
    private ArrayList<MethodSpec> methodsToAdd;
    private ClassName componentType;
    private TypeSpec leafGen;

    public Leaf(Component componentClass, String leafObjName, String packageName, ArrayList<Fields> fieldsToAdd, ArrayList<MethodSpec> methodsToAdd) {
        this.componentName = componentClass.getComponentName();
        this.componentClass = componentClass;
        this.leafObjName = leafObjName;
        this.fieldsToAdd = fieldsToAdd;
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

        if(fieldsToAdd!=null) {
            for (Fields fields : fieldsToAdd) {
                leafBuilder.addField(fields.getField());
                constructor.addParameter(fields.getDerivedParameter());
                constructor.addStatement("this.$N = $N", fields.getFieldName(), fields.getFieldName());
            }
        }
        leafBuilder.addMethod(constructor.build())
                .addSuperinterface(componentType)
                .addMethod(componentClass.getDefaultMethod().toBuilder()
                        .addAnnotation(Override.class).build());
        componentClass.getCommonMethods().forEach((methods) ->
                leafBuilder.addMethod(methods.toBuilder().addAnnotation(Override.class).build()));
        return leafBuilder.build();
    }

    public TypeSpec getLeafGen() {
        return leafGen;
    }
}
