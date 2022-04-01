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
    private ArrayList<String> classesToGenerate = new ArrayList<>();
    private String packageName;
    private String patternToGenerate;
    private ArrayList<FieldGen> fields = new ArrayList<>();
    private ArrayList<MethodGen> methods = new ArrayList<>();
    private ArrayList<ParameterGen> parameters = new ArrayList<>();
    private ArrayList<JComponent[]> fieldsToAddComps = new ArrayList<>();
    private ArrayList<JComponent[]> methodsToAddComps = new ArrayList<>();

    private static ArrayList<TypeSpec> classGen = new ArrayList<>();


    public static void generateClass(){


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

    public static void addClassGen(TypeSpec classToAdd){
        classGen.add(classToAdd);
    }

    public static void setClassGen(ArrayList<TypeSpec> classGen) {
        ClassInputs.classGen = classGen;
    }

    public static void addClassName(String className){
        INSTANCE.classesToGenerate.add(className);
    }

    public void clearAll(){
        if(!classesToGenerate.isEmpty()){
            classesToGenerate.clear();
        }
        if(!methods.isEmpty()){
            methods.clear();
        }
        if(!fields.isEmpty()){
            fields.clear();
        }
        if(!parameters.isEmpty()){
            parameters.clear();
        }
        if(!fieldsToAddComps.isEmpty()){
            fieldsToAddComps.clear();
        }
        if(!methodsToAddComps.isEmpty()){
            methodsToAddComps.clear();
        }
        if(!classGen.isEmpty()){
            classGen.clear();
        }

    }

}
