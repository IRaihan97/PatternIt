package JavaPoetTemplates;

import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import javax.lang.model.element.Modifier;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class Methods {
    private String methodName;
    private ArrayList<Modifier> methodModifiers;
    private Class methodType;
    private ArrayList<Class> parameterType;
    private ArrayList<String> parameterName;
    private ArrayList<String> statements;
    private Modifier singleModifier;
    private MethodSpec method;

    public Methods(String methodName, ArrayList<Modifier> methodModifiers,
                   Class methodType, ArrayList<Class> parameterType,
                   ArrayList<String> parameterName, ArrayList<String> statements)
    {
        this.methodName = methodName;
        this.methodModifiers = methodModifiers;
        this.methodType = methodType;
        this.parameterType = parameterType;
        this.parameterName = parameterName;
        this.statements = statements;
        this.method = generateMethod();
    }

    public Methods(String methodName, Class methodType, Modifier singleModifier) {
        this.methodName = methodName;
        this.methodType = methodType;
        this.singleModifier = singleModifier;
        this.method = generateMethod();
    }

    private MethodSpec generateMethod() {
        MethodSpec.Builder methodBuilder = MethodSpec
                .methodBuilder(methodName)
                .returns(methodType);
        if(singleModifier!=null){
            methodBuilder.addModifiers(singleModifier);
        }
        if(methodModifiers!=null){
            methodModifiers.forEach((modifier) -> methodBuilder.addModifiers(modifier));
        }
        if(parameterType != null || parameterName != null){
            for(int i = 0; i < parameterType.size()-1; i++)
            {
                methodBuilder.addParameter(parameterType.get(i), parameterName.get(i));
            }
        }
        if(statements!=null){
            statements.forEach((statements) -> methodBuilder.addStatement(statements));
        }

        return methodBuilder.build();
    }

    public Class getMethodType() {
        return methodType;
    }

    public MethodSpec getMethod(){
        return method;
    }

    public String getMethodName() {
        return methodName;
    }
}
