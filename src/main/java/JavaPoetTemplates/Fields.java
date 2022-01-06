package JavaPoetTemplates;



import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import javax.lang.model.element.Modifier;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Fields {
    private String fieldName;
    private Class fieldType;
    private ArrayList<Modifier> fieldModifiers;
    private FieldSpec field;

    public Fields(Class fieldType, String fieldName, ArrayList<Modifier> fieldModifiers){
        this.fieldType = fieldType;
        this.fieldName = fieldName;
        this.fieldModifiers = fieldModifiers;
        this.field = generateField();
    }

    private FieldSpec generateField(){
        FieldSpec.Builder fieldBuild = FieldSpec
                .builder(fieldType, fieldName);
        fieldModifiers.forEach((modifier) -> fieldBuild.addModifiers(modifier));
        return fieldBuild.build();
    }

    public FieldSpec getField(){
        return field;
    }

}
