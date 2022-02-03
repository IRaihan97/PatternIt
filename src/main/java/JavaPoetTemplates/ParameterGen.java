package JavaPoetTemplates;

import com.squareup.javapoet.ParameterSpec;
import lombok.Data;

@Data
public class ParameterGen {
    private String parameterName;
    private Class parameterType;
    private String methodName;
    private ParameterSpec parameterGen;


    public ParameterGen(String parameterName, Class parameterType) {
        this.parameterName = parameterName;
        this.parameterType = parameterType;
        this.parameterGen = generateParameter();
    }

    public ParameterGen(String parameterName, Class parameterType, String methodName) {
        this.parameterName = parameterName;
        this.parameterType = parameterType;
        this.methodName = methodName;
        this.parameterGen = generateParameter();
    }

    private ParameterSpec generateParameter() {
        ParameterSpec parameter = ParameterSpec
                .builder(parameterType, parameterName)
                .build();
        return parameter;
    }
}
