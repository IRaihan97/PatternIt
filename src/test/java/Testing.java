import JavaPoetTemplates.FieldGen;
import JavaPoetTemplates.MethodGen;
import JavaPoetTemplates.ParameterGen;
import JavaPoetTemplates.Patterns.Composite.Component;
import JavaPoetTemplates.Patterns.Composite.Composite;
import JavaPoetTemplates.Patterns.Composite.Leaf;
import JavaPoetTemplates.Patterns.Singleton.Singleton;
import JavaPoetTemplates.Patterns.Template.AbstractTemplate;
import JavaPoetTemplates.Patterns.Template.Concrete;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.lang.model.element.Modifier;
import java.util.ArrayList;

public class Testing {
    Singleton singleton;
    Component component;
    Composite composite;
    Leaf leaf;
    AbstractTemplate absTemplate;
    Concrete concrete;
    ArrayList<Modifier> modifiers;
    ArrayList<MethodGen> methodsToAdd;
    ArrayList<FieldGen> fieldsToAdd = new ArrayList<>();
    ArrayList<ParameterGen> parametersToAdd = new ArrayList<>();

    private void generateModifier(){
        modifiers = new ArrayList<>();
        modifiers.add(Modifier.PUBLIC);
        modifiers.add(Modifier.STATIC);
    }

    private void generateParameters(){
        parametersToAdd = new ArrayList<>();
        ParameterGen parameter1 = new ParameterGen("param1", int.class);
        ParameterGen parameter2 = new ParameterGen("param2", float.class);
        parametersToAdd.add(parameter1);
        parametersToAdd.add(parameter2);
    }

    private void generateMethods(){
        methodsToAdd = new ArrayList<>();
        generateModifier();
        generateParameters();
        MethodGen method1 = new MethodGen("methodName1", modifiers, void.class, parametersToAdd, false, "sample");
        methodsToAdd.add(method1);
        MethodGen method2 = new MethodGen("methodName2", modifiers, void.class, parametersToAdd, false, "sample");
        methodsToAdd.add(method2);
        MethodGen method3 = new MethodGen("methodName3", modifiers, void.class, parametersToAdd, false, "sample");
        methodsToAdd.add(method3);
    }

    private void generateAbsMethods(){
        methodsToAdd = new ArrayList<>();
        modifiers = new ArrayList<>();
        modifiers.add(Modifier.PRIVATE);
        generateParameters();
        MethodGen method1 = new MethodGen("methodName1", modifiers, void.class, parametersToAdd, true, "sample");
        methodsToAdd.add(method1);
        MethodGen method2 = new MethodGen("methodName2", modifiers, void.class, parametersToAdd, true, "sample");
        methodsToAdd.add(method2);
        MethodGen method3 = new MethodGen("methodName3", modifiers, void.class, parametersToAdd, true, "sample");
        methodsToAdd.add(method3);
    }

    private void generateFields(){
        fieldsToAdd = new ArrayList<>();
        FieldGen field = new FieldGen(void.class, "exampleField1", modifiers, "exampleClass");
        FieldGen field1 = new FieldGen(int.class, "exampleField2", modifiers, "exampleClass");
        FieldGen field2 = new FieldGen(String.class, "exampleField3", modifiers, "exampleClass");
        fieldsToAdd.add(field);
        fieldsToAdd.add(field1);
        fieldsToAdd.add(field2);

    }

    @Test
    @DisplayName("MethodGen Generation")
    void generateMethod() {
        generateModifier();
        generateParameters();
        MethodGen method1 = new MethodGen("methodName1", modifiers, void.class, parametersToAdd, false, null);
        String generateMethod = method1.getMethod().toString();
        Assertions.assertEquals("public static void methodName1(int param1, float param2) {\n" +
                "  // TODO - your Method Implementation Here\n" +
                "}\n", generateMethod);
    }

    @Test
    @DisplayName("FieldGen Generation")
    void generateField() {
        generateModifier();
        FieldGen field = new FieldGen(void.class, "exampleField", modifiers, "exampleClass");
        String generateField = field.getField().toString();
        Assertions.assertEquals("public static void exampleField;\n", generateField);
    }

    @Test
    @DisplayName("Singleton Generation")
    void generateSingleton(){
        generateParameters();
        generateFields();
        generateMethods();
        singleton = new Singleton("EagerExample", "Eager", "SamplePacakge", fieldsToAdd, methodsToAdd);
        String generatedClass = singleton.getClassGen().toString();
        Assertions.assertEquals("class EagerExample {\n" +
                "  private static SamplePacakge.EagerExample eagerExample = new SamplePacakge.EagerExample();\n" +
                "\n" +
                "  void exampleField1;\n" +
                "\n" +
                "  int exampleField2;\n" +
                "\n" +
                "  java.lang.String exampleField3;\n" +
                "\n" +
                "  private void EagerExample() {\n" +
                "  }\n" +
                "\n" +
                "  public SamplePacakge.EagerExample getInstance() {\n" +
                "    return eagerExample;\n" +
                "  }\n" +
                "\n" +
                "  public static void methodName1(int param1, float param2) {\n" +
                "    // TODO - your Method Implementation Here\n" +
                "  }\n" +
                "\n" +
                "  public static void methodName2(int param1, float param2) {\n" +
                "    // TODO - your Method Implementation Here\n" +
                "  }\n" +
                "\n" +
                "  public static void methodName3(int param1, float param2) {\n" +
                "    // TODO - your Method Implementation Here\n" +
                "  }\n" +
                "}\n", generatedClass);
    }

    @Test
    @DisplayName("Component Generation")
    void generateComponent(){
        generateAbsMethods();
        component = new Component("ComponentInterfaceExample", methodsToAdd);
        String generatedInterface = component.getInterfaceGen().toString();
        Assertions.assertEquals("interface ComponentInterfaceExample {\n" +
                "  void showComponentInterfaceExampleDetails();\n" +
                "\n" +
                "  private void methodName1(int param1, float param2);\n" +
                "\n" +
                "  private void methodName2(int param1, float param2);\n" +
                "\n" +
                "  private void methodName3(int param1, float param2);\n" +
                "}\n", generatedInterface);
    }

    @Test
    @DisplayName("Composite Generation")
    void generateComposite(){
        generateAbsMethods();
        component = new Component("ComponentInterfaceExample", methodsToAdd);
        composite = new Composite(component, "compositeExample", "sample");
        String generatedComposite = composite.getCompositeGen().toString();
        Assertions.assertEquals("public class compositeExample implements sample.ComponentInterfaceExample {\n" +
                "  private java.util.ArrayList<sample.ComponentInterfaceExample> compositeExample = new java.util.ArrayList<sample.ComponentInterfaceExample>();\n" +
                "\n" +
                "  void addComponentInterfaceExample(sample.ComponentInterfaceExample componentinterfaceexample) {\n" +
                "    compositeExample.add(componentinterfaceexample);\n" +
                "  }\n" +
                "\n" +
                "  public void removeComponentInterfaceExample(\n" +
                "      sample.ComponentInterfaceExample componentinterfaceexample) {\n" +
                "    compositeExample.remove(componentinterfaceexample);\n" +
                "  }\n" +
                "\n" +
                "  @java.lang.Override\n" +
                "  public void showComponentInterfaceExampleDetails() {\n" +
                "  }\n" +
                "\n" +
                "  @java.lang.Override\n" +
                "  private void methodName1(int param1, float param2) {\n" +
                "  }\n" +
                "\n" +
                "  @java.lang.Override\n" +
                "  private void methodName2(int param1, float param2) {\n" +
                "  }\n" +
                "\n" +
                "  @java.lang.Override\n" +
                "  private void methodName3(int param1, float param2) {\n" +
                "  }\n" +
                "}\n", generatedComposite);

    }

    @Test
    @DisplayName("Leaf Generation")
    void generateLeaf(){
        generateAbsMethods();
        component = new Component("ComponentInterfaceExample", methodsToAdd);
        generateMethods();
        generateFields();
        leaf = new Leaf(component, "LeafExample","sample", fieldsToAdd, methodsToAdd);
        String generatedLeaf = leaf.getLeafGen().toString();
        Assertions.assertEquals("class LeafExample implements sample.ComponentInterfaceExample {\n" +
                "  public static void exampleField1;\n" +
                "\n" +
                "  public static int exampleField2;\n" +
                "\n" +
                "  public static java.lang.String exampleField3;\n" +
                "\n" +
                "  public void LeafExample(void exampleField1, int exampleField2, java.lang.String exampleField3) {\n" +
                "    this.exampleField1 = exampleField1;\n" +
                "    this.exampleField2 = exampleField2;\n" +
                "    this.exampleField3 = exampleField3;\n" +
                "  }\n" +
                "\n" +
                "  @java.lang.Override\n" +
                "  public void showComponentInterfaceExampleDetails() {\n" +
                "  }\n" +
                "\n" +
                "  public static void methodName1(int param1, float param2) {\n" +
                "    // TODO - your Method Implementation Here\n" +
                "  }\n" +
                "\n" +
                "  public static void methodName2(int param1, float param2) {\n" +
                "    // TODO - your Method Implementation Here\n" +
                "  }\n" +
                "\n" +
                "  public static void methodName3(int param1, float param2) {\n" +
                "    // TODO - your Method Implementation Here\n" +
                "  }\n" +
                "\n" +
                "  @java.lang.Override\n" +
                "  private void methodName1(int param1, float param2) {\n" +
                "  }\n" +
                "\n" +
                "  @java.lang.Override\n" +
                "  private void methodName2(int param1, float param2) {\n" +
                "  }\n" +
                "\n" +
                "  @java.lang.Override\n" +
                "  private void methodName3(int param1, float param2) {\n" +
                "  }\n" +
                "}\n", generatedLeaf);
    }

    @Test
    @DisplayName("AbstractTemplate Generation")
    void generateAbstractTemplate(){
        generateAbsMethods();
        generateFields();
        absTemplate = new AbstractTemplate("templateExample", "sample",fieldsToAdd, methodsToAdd);
        generateMethods();
        String generatedTemplate = absTemplate.getAbsClassGen().toString();
        Assertions.assertEquals("public abstract class templateExample {\n" +
                "  private void exampleField1;\n" +
                "\n" +
                "  private int exampleField2;\n" +
                "\n" +
                "  private java.lang.String exampleField3;\n" +
                "\n" +
                "  private abstract void methodName1(int param1, float param2);\n" +
                "\n" +
                "  private abstract void methodName2(int param1, float param2);\n" +
                "\n" +
                "  private abstract void methodName3(int param1, float param2);\n" +
                "\n" +
                "  public final void templateMethodExample() {\n" +
                "    // This is a generic definition for the final method\n" +
                "    // You can apply your own method definition here\n" +
                "    methodName1();\n" +
                "    methodName2();\n" +
                "    methodName3();\n" +
                "  }\n" +
                "}\n", generatedTemplate);
    }

    @Test
    @DisplayName("Concrete Generation")
    void generateConcrete(){
        generateAbsMethods();
        generateFields();
        absTemplate = new AbstractTemplate("templateExample", "sample",fieldsToAdd, methodsToAdd);
        concrete = new Concrete( "concreteExample", absTemplate);
        String generatedConcrete = concrete.getConcreteGen().toString();
        Assertions.assertEquals("class concreteExample extends sample.templateExample {\n" +
                "  @java.lang.Override\n" +
                "  private void methodName1(int param1, float param2) {\n" +
                "    // Add concrete Implementation Here\n" +
                "  }\n" +
                "\n" +
                "  @java.lang.Override\n" +
                "  private void methodName2(int param1, float param2) {\n" +
                "    // Add concrete Implementation Here\n" +
                "  }\n" +
                "\n" +
                "  @java.lang.Override\n" +
                "  private void methodName3(int param1, float param2) {\n" +
                "    // Add concrete Implementation Here\n" +
                "  }\n" +
                "}\n", generatedConcrete);
    }







}
