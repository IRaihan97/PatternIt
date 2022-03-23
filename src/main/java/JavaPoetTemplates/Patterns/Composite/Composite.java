package JavaPoetTemplates.Patterns.Composite;

import com.squareup.javapoet.*;

import javax.lang.model.element.Modifier;
import java.util.Locale;

public class Composite{
    private String componentInterface;
    private Component componentClass;
    private String compositeObjName;
    private ClassName componentType;
    private TypeSpec compositeGen;

    public Composite(Component component, String compositeObjName, String packageName) {
        this.componentInterface = component.getComponentName();
        this.componentClass = component;
        this.compositeObjName = compositeObjName;
        this.componentType = ClassName.get(packageName, componentInterface);
        compositeGen = generateComposite();
    }

    private TypeSpec generateComposite() {
        ClassName arrayList = ClassName.get("java.util", "ArrayList");
        TypeName compositeFieldType = ParameterizedTypeName.get(arrayList, componentType);

        //Composite Field List
        FieldSpec componentList = FieldSpec
                .builder(compositeFieldType, compositeObjName, Modifier.PRIVATE)
                .initializer("new $T()", compositeFieldType)
                .build();

        //Add Component Method
        MethodSpec addComponent = MethodSpec
                .methodBuilder("add" + componentInterface)
                .addParameter(componentType, componentInterface.toLowerCase(Locale.ROOT))
                .addStatement("$N.add($N)", compositeObjName, componentInterface.toLowerCase(Locale.ROOT))
                .returns(void.class)
                .build();

        //Remove Component Method
        MethodSpec removeComponent = MethodSpec
                .methodBuilder("remove" + componentInterface)
                .addModifiers(Modifier.PUBLIC)
                .addParameter(componentType, componentInterface.toLowerCase(Locale.ROOT))
                .addStatement("$N.remove($N)", compositeObjName, componentInterface.toLowerCase(Locale.ROOT))
                .returns(void.class)
                .build();

        TypeSpec.Builder compositeBuilder = TypeSpec
                .classBuilder(compositeObjName)
                .addModifiers(Modifier.PUBLIC)
                .addSuperinterface(componentType)
                .addField(componentList)
                .addMethod(addComponent)
                .addMethod(removeComponent)
                .addMethod(componentClass.getDefaultMethod().toBuilder()
                        .addAnnotation(Override.class).build());
        componentClass.getCommonMethods().forEach((methods) ->
                    compositeBuilder.addMethod(methods.getMethod().toBuilder().addAnnotation(Override.class).build()));


        return compositeBuilder.build();

    }


    public TypeSpec getCompositeGen() {
        return compositeGen;
    }
}
