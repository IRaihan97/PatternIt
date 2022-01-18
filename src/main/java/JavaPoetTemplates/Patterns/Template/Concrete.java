package JavaPoetTemplates.Patterns.Template;

import JavaPoetTemplates.Fields;
import JavaPoetTemplates.Methods;
import com.squareup.javapoet.TypeSpec;

import java.util.ArrayList;

public class Concrete {
    private String concreteName;
    private Abstract templateClass;
    private TypeSpec concreteGen;
    private ArrayList<Methods> methodsToAdd;
    private ArrayList<Fields> fieldsToAdd;

    public Concrete(String concreteName, Abstract templateClass) {
        this.concreteName = concreteName;
        this.templateClass = templateClass;
        this.concreteGen = generateConcreteClass();
    }

    private TypeSpec generateConcreteClass() {
        TypeSpec.Builder concreteBuilder = TypeSpec
                .classBuilder(concreteName)
                .superclass(templateClass.getComponentType());
        templateClass.getMethodsToAdd().forEach((method) -> concreteBuilder.addMethod(method.getMethod().toBuilder()
                .addAnnotation(Override.class)
                .addComment("Add concrete Implementation Here")
                .build()));
        return concreteBuilder.build();
    }

    public TypeSpec getConcreteGen() {
        return concreteGen;
    }
}
