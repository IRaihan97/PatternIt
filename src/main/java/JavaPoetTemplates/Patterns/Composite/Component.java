package JavaPoetTemplates.Patterns.Composite;

import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import javax.lang.model.element.Modifier;
import java.util.ArrayList;

public class Component {
    private String componentName;
    private ArrayList<MethodSpec> commonMethods;
    private MethodSpec defaultMethod;
    private TypeSpec interfaceGen;

    public Component(){};
    public Component(String componentName, ArrayList<MethodSpec> commonMethods) {
        this.componentName = componentName;
        this.commonMethods = commonMethods;
        defaultMethod = MethodSpec.
                methodBuilder("show" + componentName + "Details")
                .addModifiers(Modifier.PUBLIC)
                .returns(void.class)
                .build();
        this.interfaceGen = generateComponent();
    }
    private TypeSpec generateComponent(){
        TypeSpec.Builder componentBuilder = TypeSpec
                .interfaceBuilder(componentName)
                .addMethod(defaultMethod.toBuilder().addModifiers(Modifier.ABSTRACT).build());
        if(commonMethods != null){
            commonMethods.forEach((method) -> componentBuilder.addMethod(method.toBuilder().addModifiers(Modifier.ABSTRACT).build()));
        }


        return componentBuilder.build();

    }
    public String getComponentName(){
        return componentName;
    }
    public ArrayList<MethodSpec> getCommonMethods() {
        return commonMethods;
    }
    public MethodSpec getDefaultMethod() {
        return defaultMethod;
    }
    public TypeSpec getInterfaceGen() {
        return interfaceGen;
    }


}
