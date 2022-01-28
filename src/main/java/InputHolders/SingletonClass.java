package InputHolders;

import JavaPoetTemplates.FieldGen;
import JavaPoetTemplates.Methods;

import java.util.ArrayList;
import java.util.Locale;

public class SingletonClass {
    public static final SingletonClass INSTANCE = new SingletonClass();
    private String className;
    private String packageName;
    private String singletonType;
    private ArrayList<FieldGen> fields = new ArrayList<>();
    private ArrayList<Methods> methods = new ArrayList<>();

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }


    public String getSingletonType() {
        return singletonType;
    }

    public void setSingletonType(String singletonType) {
        this.singletonType = singletonType;
    }

    public static String capitalize(String str){
        return str.substring(0,1).toUpperCase(Locale.ROOT) + str.substring(1);
    }

    public void addFields(FieldGen field){
        fields.add(field);
        System.out.println(field.getFieldName());
        System.out.println(field.getFieldModifiers().get(0).toString());
        System.out.println(field.getFieldModifiers().get(1).toString());
        System.out.println(field.getFieldType().toString());
    }

    public ArrayList<FieldGen> getField(){
        return this.fields;
    }

    public static String lower(String str){
        return str.substring(0,1).toLowerCase(Locale.ROOT) + str.substring(1);
    }
}
