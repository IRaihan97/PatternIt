package JavaPoetTemplates;

import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import javax.lang.model.element.Modifier;
import java.util.ArrayList;

public class SampleTemplate {
    private static String className;
    private static String methodName;
    private static String fieldName;
    SampleTemplate(){};

    public SampleTemplate(String className, String methodName, String fieldName){
        this.className = className;
        this.methodName = methodName;
        this.fieldName = fieldName;
    }

    private static MethodSpec generateConstructor() {
        MethodSpec constructorSpec = MethodSpec
                .methodBuilder(className)
                .addParameter(int.class, fieldName)
                .addParameter(String.class, fieldName + "2")
                .build();

        return constructorSpec;

    }

    private static FieldSpec generateFields(String fieldName) {
        FieldSpec field = FieldSpec
                .builder(String.class, fieldName)
                .addModifiers(Modifier.PRIVATE)
                .build();
        return field;
    }

    private static MethodSpec generateMethod() {
        MethodSpec method = MethodSpec
                .methodBuilder(methodName)
                .addStatement("String testingCode = \"testing\"")
                .build();
        return method;

    }

    public TypeSpec generateJavaClass() {

        //TestingList
        ArrayList<MethodSpec> methods = new ArrayList<MethodSpec>();
        methods.add(generateConstructor());
        methods.add(generateMethod());

        TypeSpec.Builder newClass = TypeSpec
                .classBuilder(className)
                .addField(generateFields(fieldName))
                .addField(generateFields(fieldName + "2"));
        methods.forEach((method) -> newClass.addMethod(method));

        TypeSpec builtClass = newClass.build();


        

        return builtClass;
    }
}

