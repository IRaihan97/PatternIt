package InputHolders;

import javax.lang.model.element.Modifier;
import java.util.ArrayList;

public class FieldsClass {
    public static final FieldsClass INSTANCE = new FieldsClass();
    private String fieldName;
    private Class fieldType;
    private ArrayList<Modifier> fieldModifiers;

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Class getFieldType() {
        return fieldType;
    }

    public void setFieldType(Class fieldType) {
        this.fieldType = fieldType;
    }

    public ArrayList<Modifier> getFieldModifiers() {
        return fieldModifiers;
    }

    public void setFieldModifiers(Modifier modifier) {
        this.fieldModifiers.add(modifier);
    }
}
