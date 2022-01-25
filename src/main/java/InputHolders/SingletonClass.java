package InputHolders;

import JavaPoetTemplates.Fields;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.util.ArrayList;
import java.util.Locale;

public class SingletonClass {
    public static final SingletonClass INSTANCE = new SingletonClass();
    private String className;
    private String singletonType;

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

    public static String lower(String str){
        return str.substring(0,1).toLowerCase(Locale.ROOT) + str.substring(1);
    }
}
