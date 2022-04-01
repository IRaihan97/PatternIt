import InputHolders.ClassInputs;
import JavaPoetTemplates.FieldGen;
import JavaPoetTemplates.MethodGen;
import JavaPoetTemplates.ParameterGen;
import JavaPoetTemplates.Patterns.Composite.Component;
import JavaPoetTemplates.Patterns.Composite.Composite;
import JavaPoetTemplates.Patterns.Composite.Leaf;
import JavaPoetTemplates.Patterns.Singleton.Singleton;
import JavaPoetTemplates.Patterns.Template.AbstractTemplate;
import JavaPoetTemplates.Patterns.Template.Concrete;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;
import org.jsoup.select.Evaluator;
import org.junit.Assert;
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

    @Test
    @DisplayName("Modifier: Generate Modifier on Runtime")
    void generateModifier(){
        modifiers = new ArrayList<>();
        modifiers.add(Modifier.PUBLIC);
        modifiers.add(Modifier.STATIC);
        Assert.assertNotNull(modifiers);
        Assert.assertFalse(modifiers.isEmpty());

    }

    @Test
    @DisplayName("ParameterGen: Generate Parameters on Runtime")
    void generateParameters(){
        parametersToAdd = new ArrayList<>();
        ParameterGen parameter1 = new ParameterGen("param1", int.class);
        ParameterGen parameter2 = new ParameterGen("param2", float.class);
        parametersToAdd.add(parameter1);
        parametersToAdd.add(parameter2);
        Assert.assertNotNull(parametersToAdd);
        Assert.assertFalse(parametersToAdd.isEmpty());
    }

    @Test
    @DisplayName("ClassInputs: Generate Parameters Input")
    void generateParametersInput(){
        parametersToAdd = new ArrayList<>();
        ParameterGen parameter1 = new ParameterGen("param1", int.class);
        ParameterGen parameter2 = new ParameterGen("param2", float.class);
        parametersToAdd.add(parameter1);
        parametersToAdd.add(parameter2);
        ClassInputs.INSTANCE.setParameters(parametersToAdd);
        Assert.assertEquals(parametersToAdd, ClassInputs.INSTANCE.getParameters());
    }

    @Test
    @DisplayName("MethodGen: Generate Methods on Runtime")
    void generateMethods(){
        methodsToAdd = new ArrayList<>();
        generateModifier();
        generateParameters();
        MethodGen method1 = new MethodGen("methodName1", modifiers, void.class, parametersToAdd, false, "sample");
        methodsToAdd.add(method1);
        MethodGen method2 = new MethodGen("methodName2", modifiers, void.class, parametersToAdd, false, "sample");
        methodsToAdd.add(method2);
        MethodGen method3 = new MethodGen("methodName3", modifiers, void.class, parametersToAdd, false, "sample");
        methodsToAdd.add(method3);
        Assert.assertNotNull(methodsToAdd);
        Assert.assertFalse(methodsToAdd.isEmpty());
    }

    @Test
    @DisplayName("ClassInputs: Generate Methods Input")
    void generateMethodsInput(){
        methodsToAdd = new ArrayList<>();
        generateModifier();
        generateParameters();
        MethodGen method1 = new MethodGen("methodName1", modifiers, void.class, parametersToAdd, false, "sample");
        methodsToAdd.add(method1);
        MethodGen method2 = new MethodGen("methodName2", modifiers, void.class, parametersToAdd, false, "sample");
        methodsToAdd.add(method2);
        MethodGen method3 = new MethodGen("methodName3", modifiers, void.class, parametersToAdd, false, "sample");
        methodsToAdd.add(method3);
        ClassInputs.INSTANCE.setMethods(methodsToAdd);
        Assert.assertEquals(methodsToAdd, ClassInputs.INSTANCE.getMethods());
    }

    @Test
    @DisplayName("MethodGen: Generate Abstract Methods on Runtime")
    void generateAbsMethods(){
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
        Assert.assertNotNull(methodsToAdd);
        Assert.assertFalse(methodsToAdd.isEmpty());
    }

    @Test
    @DisplayName("ClassInput: Generate Abstract Methods Input")
    void generateAbsMethodsInput(){
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
        ClassInputs.INSTANCE.setMethods(methodsToAdd);
        Assert.assertEquals(methodsToAdd, ClassInputs.INSTANCE.getMethods());
    }

    @Test
    @DisplayName("FieldGen: Generate Fields list on Runtime")
    void generateFields(){
        fieldsToAdd = new ArrayList<>();
        FieldGen field = new FieldGen(void.class, "exampleField1", modifiers, "exampleClass");
        FieldGen field1 = new FieldGen(int.class, "exampleField2", modifiers, "exampleClass");
        FieldGen field2 = new FieldGen(String.class, "exampleField3", modifiers, "exampleClass");
        fieldsToAdd.add(field);
        fieldsToAdd.add(field1);
        fieldsToAdd.add(field2);
        Assertions.assertNotNull(fieldsToAdd);
        Assertions.assertFalse(fieldsToAdd.isEmpty());
    }

    @Test
    @DisplayName("ClassInputs: Generate Fields Input")
    void generateFieldsInput(){
        fieldsToAdd = new ArrayList<>();
        FieldGen field = new FieldGen(void.class, "exampleField1", modifiers, "exampleClass");
        FieldGen field1 = new FieldGen(int.class, "exampleField2", modifiers, "exampleClass");
        FieldGen field2 = new FieldGen(String.class, "exampleField3", modifiers, "exampleClass");
        fieldsToAdd.add(field);
        fieldsToAdd.add(field1);
        fieldsToAdd.add(field2);
        ClassInputs.INSTANCE.setFields(fieldsToAdd);
        Assert.assertEquals(fieldsToAdd, ClassInputs.INSTANCE.getFields());
    }

    @Test
    @DisplayName("MethodGen: Method Generation")
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
    @DisplayName("FieldGen: Field Generation")
    void generateField() {
        generateModifier();
        FieldGen field = new FieldGen(void.class, "exampleField", modifiers, "exampleClass");
        String generateField = field.getField().toString();
        Assertions.assertEquals("public static void exampleField;\n", generateField);
    }

    @Test
    @DisplayName("Singleton: Eager Generation")
    void generateEagerSingleton(){
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
    @DisplayName("Singleton: Lazy Generation")
    void generateLazySingleton(){
        generateParameters();
        generateFields();
        generateMethods();
        singleton = new Singleton("LazyExample", "Lazy", "SamplePacakge", fieldsToAdd, methodsToAdd);
        String generatedClass = singleton.getClassGen().toString();
        System.out.println(generatedClass);
        Assertions.assertEquals("class LazyExample {\n" +
                "  private static SamplePacakge.LazyExample lazyExample;\n" +
                "\n" +
                "  void exampleField1;\n" +
                "\n" +
                "  int exampleField2;\n" +
                "\n" +
                "  java.lang.String exampleField3;\n" +
                "\n" +
                "  private void LazyExample() {\n" +
                "  }\n" +
                "\n" +
                "  public SamplePacakge.LazyExample getInstance() {\n" +
                "    if (lazyExample == null) {\n" +
                "      lazyExample = new SamplePacakge.LazyExample();\n" +
                "    }\n" +
                "    return lazyExample;\n" +
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
    @DisplayName("Singleton: Sync Generation")
    void generateSyncSingleton(){
        generateParameters();
        generateFields();
        generateMethods();
        singleton = new Singleton("syncExample", "ThreadSafe", "SamplePacakge", fieldsToAdd, methodsToAdd);
        String generatedClass = singleton.getClassGen().toString();
        System.out.println(generatedClass);
        Assertions.assertEquals("class SyncExample {\n" +
                "  private static SamplePacakge.SyncExample syncExample;\n" +
                "\n" +
                "  void exampleField1;\n" +
                "\n" +
                "  int exampleField2;\n" +
                "\n" +
                "  java.lang.String exampleField3;\n" +
                "\n" +
                "  private void SyncExample() {\n" +
                "  }\n" +
                "\n" +
                "  public static SamplePacakge.SyncExample getInstance() {\n" +
                "    synchronized(SamplePacakge.SyncExample.class) {\n" +
                "      if (syncExample == null) {\n" +
                "        syncExample = new SamplePacakge.SyncExample();\n" +
                "      }\n" +
                "    }\n" +
                "    return syncExample;\n" +
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
    @DisplayName("Singleton: Enum Generation")
    void generateEnumSingleton(){
        generateParameters();
        generateFields();
        generateMethods();
        singleton = new Singleton("enumExample", "Enum", "SamplePacakge", fieldsToAdd, methodsToAdd);
        String generatedClass = singleton.getClassGen().toString();
        System.out.println(generatedClass);
        Assertions.assertEquals("public enum EnumExample {\n" +
                "  INSTANCE;\n" +
                "\n" +
                "  void exampleField1;\n" +
                "\n" +
                "  int exampleField2;\n" +
                "\n" +
                "  java.lang.String exampleField3;\n" +
                "\n" +
                "  public void dummyMethod() {\n" +
                "    // ADD CODE HERE\n" +
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
    @DisplayName("Composite: Component Generation")
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
    @DisplayName("Composite: Composite Generation")
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
    @DisplayName("Composite: Leaf Generation")
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
    @DisplayName("Template: AbstractTemplate Generation")
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
    @DisplayName("Template: Concrete Generation")
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

    @Test
    @DisplayName("File: Testing File Writing")
    void classGeneration(){
        generateParameters();
        generateFields();
        generateMethods();
        Singleton singleton1 = new Singleton("EagerExample", "Eager", "SamplePacakge", fieldsToAdd, methodsToAdd);;
        generateAbsMethods();
        Component comp = new Component("ComponentInterfaceExample", methodsToAdd);
        generateMethods();
        Composite compose = new Composite(comp, "compositeExample", "sample");
        Leaf leaf = new Leaf(comp, "LeafExample","sample", fieldsToAdd, methodsToAdd);
        generateAbsMethods();
        AbstractTemplate temp = new AbstractTemplate("templateExample", "sample",fieldsToAdd, methodsToAdd);
        Concrete conc = new Concrete( "concreteExample", temp);
        ArrayList<TypeSpec> listOfClasses = new ArrayList<>();
        listOfClasses.add(singleton1.getClassGen());
        listOfClasses.add(comp.getInterfaceGen());
        listOfClasses.add(compose.getCompositeGen());
        listOfClasses.add(leaf.getLeafGen());
        listOfClasses.add(temp.getAbsClassGen());
        listOfClasses.add(conc.getConcreteGen());
        for(int i = 0; i < listOfClasses.size(); i++){
            JavaFile file = JavaFile.builder("SamplePackage", listOfClasses.get(i)).build();
            try {
                file.writeTo(System.out);
            }catch(Exception e){
                Assert.assertFalse(true);
            }
        }

    }






}
