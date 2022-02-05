package JavaPoetTemplates;

import com.squareup.javapoet.ParameterSpec;
import lombok.Data;

@Data
public class ParameterGen {
    private String parameterName;
    private Class parameterType;
    private String targetMethod;
    private ParameterSpec parameterGen;


    public ParameterGen(String parameterName, Class parameterType) {
        this.parameterName = parameterName;
        this.parameterType = parameterType;
        this.parameterGen = generateParameter();
    }

    public ParameterGen(String parameterName, Class parameterType, String targetMethod) {
        this.parameterName = parameterName;
        this.parameterType = parameterType;
        this.targetMethod = targetMethod;
        this.parameterGen = generateParameter();
    }

    private ParameterSpec generateParameter() {
        ParameterSpec parameter = ParameterSpec
                .builder(parameterType, parameterName)
                .build();
        return parameter;
    }
}
