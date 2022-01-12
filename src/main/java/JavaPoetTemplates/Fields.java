package JavaPoetTemplates;



import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeSpec;
import com.thaiopensource.relaxng.edit.Param;

import javax.lang.model.element.Modifier;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Fields {
    private String fieldName;
    private Class fieldType;
    private ArrayList<Modifier> fieldModifiers;
    private FieldSpec field;
    private ParameterSpec derivedParameter;

    public Fields(Class fieldType, String fieldName, ArrayList<Modifier> fieldModifiers){
        this.fieldType = fieldType;
        this.fieldName = fieldName;
        this.fieldModifiers = fieldModifiers;
        this.field = generateField();
        this.derivedParameter = generateParameterFromFields();
    }

    private FieldSpec generateField(){
        FieldSpec.Builder fieldBuild = FieldSpec
                .builder(fieldType, fieldName);
        if(fieldModifiers != null){
            fieldModifiers.forEach((modifier) -> fieldBuild.addModifiers(modifier));
        }
        return fieldBuild.build();
    }

    private ParameterSpec generateParameterFromFields() {
        ParameterSpec parameter = ParameterSpec
                .builder(fieldType, fieldName)
                .build();
        return parameter;
    }

    public String getFieldName() {
        return fieldName;
    }

    public FieldSpec getField(){
        return field;
    }

    public ParameterSpec getDerivedParameter(){return derivedParameter;}

}
