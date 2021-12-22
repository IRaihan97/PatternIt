package JavaPoetTemplates;

import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import javax.lang.model.element.Modifier;

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

        TypeSpec newClass = TypeSpec
                .classBuilder(className)
                .addField(generateFields(fieldName))
                .addField(generateFields(fieldName + "2"))
                .addMethod(generateConstructor())
                .addMethod(generateMethod())
                .build();

        

        return newClass;
    }
}

