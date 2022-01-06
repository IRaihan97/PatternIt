package JavaPoetTemplates;

import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class BaseClass {
    private String className;
    private ArrayList<FieldSpec> fields;
    private ArrayList<MethodSpec> methods;
    private TypeSpec classGen;
    public BaseClass(String className, ArrayList<FieldSpec> fields, ArrayList<MethodSpec> methods){
        this.className = className;
        this.fields = fields;
        this.methods = methods;
        this.classGen = generateClass();
    }

    private TypeSpec generateClass() {
        TypeSpec.Builder classBuilder = TypeSpec
                .classBuilder(className);
        fields.forEach((field) -> classBuilder.addField(field));
        methods.forEach((methods) -> classBuilder.addMethod(methods));

        return classBuilder.build();
    }

    public TypeSpec getClassGen(){
        return this.classGen;
    }


}
