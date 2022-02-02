package InputHolders;

import JavaPoetTemplates.FieldGen;
import JavaPoetTemplates.MethodGen;
import JavaPoetTemplates.Patterns.Singleton.Singleton;
import com.squareup.javapoet.TypeSpec;
import lombok.Data;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Locale;

@Data
public class SingletonClass {
    public static final SingletonClass INSTANCE = new SingletonClass();
    private String className;
    private String packageName;
    private String singletonType;
    private ArrayList<FieldGen> fields = new ArrayList<>();
    private ArrayList<MethodGen> methods = new ArrayList<>();
    private ArrayList<JComponent[]> fieldsToAddComps = new ArrayList<>();
    private ArrayList<JComponent[]> methodsToAddComps = new ArrayList<>();;
    private static Singleton singletonClass;
    private static TypeSpec classGen;


    public static void generateClass(){
        INSTANCE.singletonClass = new Singleton(INSTANCE.getClassName(), INSTANCE.getSingletonType(), INSTANCE.getPackageName(), INSTANCE.getFields(), INSTANCE.methods);
        setClassGen(INSTANCE.singletonClass.getClassGen());
    }

    public void setSingletonType(String singletonType) {
        this.singletonType = singletonType;
    }

    public static String capitalize(String str){
        return str.substring(0,1).toUpperCase(Locale.ROOT) + str.substring(1);
    }

    public void addFields(FieldGen field){
        fields.add(field);
    }

    public void addMethods(MethodGen method){
        methods.add(method);
    }

    public ArrayList<FieldGen> getField(){
        return this.fields;
    }

    public static String lower(String str){
        return str.substring(0,1).toLowerCase(Locale.ROOT) + str.substring(1);
    }

    public void setFieldsToAddComps(ArrayList<JComponent[]> fieldsToAddComps) {
        this.fieldsToAddComps = fieldsToAddComps;
    }

    public void setMethodsToAddComps(ArrayList<JComponent[]> methodsToAddComps) {
        this.methodsToAddComps = methodsToAddComps;
    }

    public static Singleton getSingletonClass() {
        return singletonClass;
    }

    public static void setSingletonClass(Singleton singletonClass) {
        SingletonClass.singletonClass = singletonClass;
    }

    public static TypeSpec getClassGen() {
        return classGen;
    }

    public static void setClassGen(TypeSpec classGen) {
        SingletonClass.classGen = classGen;
    }
}
