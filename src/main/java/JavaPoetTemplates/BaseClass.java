package JavaPoetTemplates;

import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import javax.lang.model.element.Modifier;
import java.util.ArrayList;

public class BaseClass {
    private String className;
    private ArrayList<Fields> fields;
    private ArrayList<MethodSpec> methods;
    private MethodSpec constructor;
    public BaseClass(String className, ArrayList<Fields> fields, ArrayList<MethodSpec> methods){
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
            for (Fields fields : this.fields) {
                constructor.addParameter(fields.getDerivedParameter());
                constructor.addStatement("this.$N = $N", fields.getFieldName(), fields.getFieldName());
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
