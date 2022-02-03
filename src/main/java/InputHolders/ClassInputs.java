package InputHolders;

import JavaPoetTemplates.FieldGen;
import JavaPoetTemplates.MethodGen;
import JavaPoetTemplates.ParameterGen;
import JavaPoetTemplates.Patterns.Singleton.Singleton;
import com.squareup.javapoet.TypeSpec;
import lombok.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Locale;

@Data
public class ClassInputs {
    public static final ClassInputs INSTANCE = new ClassInputs();
    private ArrayList<String> className = new ArrayList<>();
    private String packageName;
    private String patternToGenerate;
    private ArrayList<FieldGen> fields = new ArrayList<>();
    private ArrayList<MethodGen> methods = new ArrayList<>();
    private ArrayList<ParameterGen> parameters = new ArrayList<>();
    private ArrayList<JComponent[]> fieldsToAddComps = new ArrayList<>();
    private ArrayList<JComponent[]> methodsToAddComps = new ArrayList<>();

    private static ArrayList<TypeSpec> classGen = new ArrayList<>();


    public static void generateClass(){
        if(INSTANCE.patternToGenerate.equals("Composite")){
            //Generate Composite
            return;
        }
        else if(INSTANCE.patternToGenerate.equals("Template")){
            //Generate Template
            return;
        }
        else{
            Singleton singletonClass = new Singleton(INSTANCE.className.get(0), INSTANCE.patternToGenerate, INSTANCE.packageName, INSTANCE.fields, INSTANCE.methods);
            classGen.add(singletonClass.getClassGen());
            return;
        }

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

    public static String lower(String str){
        return str.substring(0,1).toLowerCase(Locale.ROOT) + str.substring(1);
    }

    public void addParameter(ParameterGen parameter){
        parameters.add(parameter);
    }

    public static ArrayList<TypeSpec> getClassGen() {
        return classGen;
    }

    public static void setClassGen(ArrayList<TypeSpec> classGen) {
        ClassInputs.classGen = classGen;
    }

    public static void addClassName(String className){
        INSTANCE.className.add(className);
    }

    public void clearAll(){
        className.clear();
        fields.clear();
        methods.clear();
        parameters.clear();
        fieldsToAddComps.clear();
        methodsToAddComps.clear();
    }

}
