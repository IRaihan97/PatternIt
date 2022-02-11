package JavaPoetTemplates;

import com.squareup.javapoet.MethodSpec;

import lombok.Data;

import javax.lang.model.element.Modifier;
import java.util.ArrayList;

@Data
public class MethodGen {

    private String methodName;
    private ArrayList<Modifier> methodModifiers;
    private Class methodType;
    private ArrayList<ParameterGen> parameters;
    private ArrayList<String> statements;
    private Modifier singleModifier;
    private MethodSpec method;
    private boolean isAbstract;
    private String targetClass;

    public MethodGen(String methodName, ArrayList<Modifier> methodModifiers,
                     Class methodType, ArrayList<ParameterGen> parameters, boolean isAbstract, String targetClass)
    {
        this.methodName = methodName;
        this.methodModifiers = methodModifiers;
        this.methodType = methodType;
        this.parameters = parameters;
        this.targetClass = targetClass;
        this.isAbstract = isAbstract;
        this.method = generateMethod();
    }

    public MethodGen(String methodName, ArrayList<Modifier> methodModifiers,
                     Class methodType, ArrayList<ParameterGen> parameters, ArrayList<String> statements)
    {
        this.methodName = methodName;
        this.methodModifiers = methodModifiers;
        this.methodType = methodType;
        this.parameters = parameters;
        this.statements = statements;
        this.method = generateMethod();
    }

    public MethodGen(String methodName, Class methodType, Modifier singleModifier) {
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
        if(parameters != null){
            for(int i = 0; i < parameters.size(); i++)
            {
                methodBuilder.addParameter(parameters.get(i).getParameterGen());
            }
        }

        if(isAbstract){
            return methodBuilder.build();
        }


        methodBuilder.addComment("TODO - your Method Implementation Here");
        if(statements!=null){
            statements.forEach((statements) -> methodBuilder.addStatement(statements));
        }

//        if(methodType== int.class
//                || methodType == double.class
//                || methodType == long.class
//                || methodType == short.class
//                || methodType == float.class
//        ){
//            methodBuilder.addStatement("return 0");
//        }
//        else{
//            methodBuilder.addStatement("return null");
//        }

        return methodBuilder.build();
    }
}
