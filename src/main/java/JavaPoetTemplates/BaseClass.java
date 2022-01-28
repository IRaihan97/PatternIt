package JavaPoetTemplates;

import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import javax.lang.model.element.Modifier;
import java.util.ArrayList;

public class BaseClass {
    private String className;
    private ArrayList<FieldGen> fields;
    private ArrayList<MethodSpec> methods;
    private MethodSpec constructor;
    public BaseClass(String className, ArrayList<FieldGen> fields, ArrayList<MethodSpec> methods){
        this.className = className;
        this.fields = fields;
        this.methods = methods;
        this.constructor = generateContructor();
    }

    private MethodSpec generateContructor(){
        MethodSpec.Builder constructor = MethodSpec
                .methodBuilder(className)
                .addModifiers(Modifier.PUBLIC);
        if(fields!=null) {
            for (FieldGen fieldGen : this.fields) {
                constructor.addParameter(fieldGen.getDerivedParameter());
                constructor.addStatement("this.$N = $N", fieldGen.getFieldName(), fieldGen.getFieldName());
            }
        }
        return constructor.build();
    }

    public void addExtraFieldsandMethods(TypeSpec.Builder classGen){
        if(fields != null){
            fields.forEach((field) ->
                    classGen.addField(field.getField()));
        }
        if(methods!=null){
            methods.forEach((method) ->
                    classGen.addMethod(method));
        }
    }
    private TypeSpec generateClass() {
        TypeSpec.Builder classBuilder = TypeSpec
                .classBuilder(className);
        fields.forEach((field) -> classBuilder.addField(field.getField()));
        methods.forEach((methods) -> classBuilder.addMethod(methods));

        return classBuilder.build();
    }

    public MethodSpec getConstructor() {
        return constructor;
    }
}
