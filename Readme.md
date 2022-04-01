# What's PatternIt?
PatternIt! is a plug-in tool for IntelliJIDE implemented using the Javapoet Library for generating Java code.
The code is generated around design patterns and user inputs provided by a G.U.I.

## How to Install
To install the plug-in on your IDE, download the zip file inside the "Compiled" folder of the repo. <br>
Then from IntelliJ go on File > Settings > Plugins > Click the gear Icon > Select "Install From Disk" > Locate Downloaded Zip File

## How to use
After installing the plug-in, you can use the by right-clicking on any folder and accessing the "PatternIt!" Option at the top of the pop up<br>
You can then select any of the (currently 3) of the patterns you want to generate. <br>
A relevant U.I. will then be opened up and you will then be able to define your class names, fields and methods for the code generation

## Join in the Development
The PatternIt! project is actively looking for new contributors to join on the development of the project. <br>
The more patterns there in the project The better!<br>
If you fancy contributing to the project, below you can find a guide on how the project works so that you can add your own Design Patterns to the project.

## Adding your own Design Pattern (Simple Hard Coded Example)
## Step 1 Registering a Plug-In Action
The first step is to add an action in the Plugin.xml file <br>
Locate the Plugin.xml file in the Resource folder and find the "actions" tag to add your own action
```xml
<actions>
        <group id="genClassProject" text="PatternIt" popup="true">
            <add-to-group group-id="ProjectViewPopupMenu" anchor="first"/>
            <group id="singleTon" text="Singleton" popup="true">
                <add-to-group group-id="CommanderPopupMenu" anchor="first"/>
                <action id="eagerGenerator"
                        class="Actions.ClassGenAction"
                        text="Eager"
                        description="UI for generating class.">
                </action>
                <action id="lazyGenerator"
                        class="Actions.ClassGenAction"
                        text="Lazy"
                        description="UI for generating class.">
                </action>
                <action id="threadSafeGenerator"
                        class="Actions.ClassGenAction"
                        text="ThreadSafe"
                        description="UI for generating class.">

                </action>
                <action id="enumGenerator"
                        class="Actions.ClassGenAction"
                        text="Enum"
                        description="UI for generating class.">

                </action>
                <separator></separator>
                <action id="singletonTutorial"
                        class="Actions.ClassGenAction"
                        text="What's a Singleton?"
                        description="UI for generating class.">

                </action>
            </group>
            <separator></separator>
            <action id="compositeGenerator"
                    class="Actions.ClassGenAction"
                    text="Composite"
                    description="UI for generating class.">
            </action>
            <separator></separator>
```
You can add your own action right after the Composite action. Below is an example of how an action can be added
```xml
            <action id="modelAction"
                    class="Actions.ClassGenAction"
                    text="ModelDistunguishedName"
                    description="UI for generating class.">
            </action>
```
Make sure that the action has a unique text <br>
The class attribute is also very important, it determines which java class will be executed when the action is clicked

## Step 2 Start defining your pattern Components
### Step 2.1
Define components that may be required for your patterns, eg Abstract Classes, Interface, Classes etc. <br>
### Step 2.2
Create a new Package under JavaPoetTemplate.Patterns with a relevant Pattern Name, such as "FactoryMethod" or "MVC"
###Step 2.3
Start defining a class in the newly defined package
Below is an extract of the possible constructor that you might require
```java
public class Model {
    private String className;
    private String packageName;
    private ArrayList<FieldGen> fields;
    private ArrayList<MethodGen> methods;
    private TypeSpec classGen;

    public Model(String className,
                     String singletonType, String packageName,
                     ArrayList<FieldGen> fields, ArrayList<MethodGen> methods) {
        this.className = ClassInputs.capitalize(className);
        this.packageName = packageName;
        this.fields = fields;
        this.methods = methods;
        classType = ClassName.get(packageName, className.substring(0,1).toUpperCase() + className.substring(1));
        classGen = generateModel();


    }
```
### Step 2.4
Define a method within the class responsible for building the a class file around the given design pattern.
Use a Spec.Builder Object to achieve this.
Example of how this can be implemented can be seen below
```java
    private TypeSpec generateLeaf() {
        MethodSpec.Builder constructor = MethodSpec
                .methodBuilder(objName)
                .addModifiers(Modifier.PUBLIC);
        TypeSpec.Builder leafBuilder = TypeSpec
                .classBuilder(objName);

        if(fieldGenToAdd !=null) {
            for (FieldGen fieldGen : fieldGenToAdd) {
                leafBuilder.addField(fieldGen.getField());
                constructor.addParameter(fieldGen.getDerivedParameter());
                constructor.addStatement("this.$N = $N", fieldGen.getFieldName(), fieldGen.getFieldName());
            }
        }
        methodsToAdd.forEach((method) -> leafBuilder.addMethod(method.getMethod()));
        return leafBuilder.build();
    }
```
Make sure you have a getter for the classGen attribute<br>
Now a simple code generation should be ready

## Step 3 Instantiate the object through an Action 
### Step 3.1
Add the Instantiation on the ClassGenAction inside the Actions package
```java
    if(action.equals(ModelDistunguishedName)){
            Model singletonClass = new Model("aName", "packageName", fields, methods)
            file = JavaFile.builder(Model.getPackageName,Model.getClassGen()).build())
            try {
                file.writeTo(outPut);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
```
### Step 3.2
Run the Project and click the relevant action. The class should be Generated

## A more detailed guide is in work in progress.
###Information about adding a G.U.I will be added in the future...