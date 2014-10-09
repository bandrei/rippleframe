Ripple
======

Ripple is built on top of Kahn Process Networks (KPN) principles and Flow-based programming, where
processes act as black boxes which can be interconnected in various ways to form
dataflow designs. Using the components of Ripple one can quickly prototype a dataflow architecture.

##Kahn Process Networks
KPNs are groups of deterministic sequential processes with unbound FIFO
channels forming a distributed computing model. Data are read and written
in an atomic fashion, the behavior of the networked processes being agnostic
to communication and computation delays. Reads from a channel are blocking
(i.e. a process will stall if there is no input on the channels) whereas writes are
non blocking (i.e. it always succeeds). Data
ow networks have been show to
be a special case of KPNs and are quite often used to implement streaming
applications, thus making it a good candidate for creating applications using the
data
ow programming paradigm. Furthermore Kahn Process Networks have
been used to model FPGA implementations and have also been proposed
as alternatives to the traditional MapReduce design pattern.

##Ripple implementation

Documentation various components of Ripple can be found in Chapter 5 of the [thesis](http://www.doc.ic.ac.uk/teaching/distinguished-projects/2014/a.bara.pdf).

Ripple satisfies the following KPN requirements:

* the building blocks of the framework allows us to construct a graph with nodes and edges.
* each node should act as an independent actor.
* the communication channels(the edges) should have unbound FIFO queues and use message passing.
* lock-step execution. A node will stall if there are no data on the inputs.

Ripple has 2 main components:

1. **Processing nodes**
2. **Communication channels**

###Processing nodes
A `Box` represents a KPN processing node and is the fundamental building block
of Ripple. 

Below is an example of how to create an implementation of the `Box` node:

```
public class ExampleNode extends Box<SubClassMode> {
    
    protected ExampleNode(BoxPin[] inputTypes, BoxPin[] outputTypes, String displayName, Class<SubClassMode> connectedModeType, boolean skipNulls) {
        super(inputTypes, outputTypes, displayName, connectedModeType, skipNulls);
    }

    @Override
    protected void startupOrSwitch(BoxMode prevBoxMode, SubClassMode prevSubMode, BoxMode newBoxMode, SubClassMode newSubMode) throws BoxError {
      
    }

    @Override
    protected void shutdown(boolean b, SubClassMode o) throws BoxError {
        
    }

    @Override
    protected Object[] execStep(Object[] objects, SubClassMode currentMode) throws BoxError, InterruptedException {
        return new Object[0];  
    }
}
```

`public class ExampleNode extends Box<SubClass>` has a sub-mode `SubClass` which tipically represents an enum for **custom** states of the `Box`.
The default modes of `Box` can be in either `BoxMode.Connected` or `BoxMode.Disconnected` to be used when a `Box` gets disconnected from the graph at runtime.

The default constructor takes in a list of `BoxPin`s as inputs, and one as ouput, toegether with the **Class** type of the submode (i.e. `SubClass.class`).

```
    @Override
    protected void startupOrSwitch(BoxMode prevMode, SubClassMode prevSubMode, BoxMode newMode, SubClassMode newSubMode) throws BoxError {
        
    }
```

The above method will be called every time there is a change in either the `BoxMode` or `SubClass` mode. If there is no change the previous and the new modes will be the same.
This method is scheduled to change in future version of the framework, together with the way mode transitioning is handled.

The `shutdown` method will be called when the `Box` is being stopped.

The `execStep` method is being called when all the inputs (`BoxPin` inputs) are synchronized and data is available on all the input channels. The method receives the values 
now at the head of the input pins as an `Object[]` list, together with the current sub mode.

The method must return a list of `Object[]` of the same length as the list of output pins. Notice the `skipNulls` flag which tells the `Box` node whether to skip
processing when **any** of the inputs is `null` or continue processing with `null` as an input (warning: one must check for `null` in order to avoid a `NullPointerException`).

###Communication channels
The second important aspect of the framework are the links between the nodes which form the communcation channels.
Thanks to the useful contribution of Rory Allford (rda10@imperial.ac.uk) we have created a construct which not only enables **lockstep execution** via the blocking FIFO queues,
but also performs automated type casting from one variable to another.

1. Output pins are the source of new data, and are connected to the input
pins of another computational node. The `BoxPin` contains information
about the `PPType` (more details in the next section) of a pin. The combination
of a `BoxPin` and the blocking queues forms the `PinSource` of
the communication channel. A `PinSource` implements the `PPBase` class
which allows for data to be casted automatically when `PinSink` connects
to it.
2. Input pins act as the sink of the `Box`. Similar to the output pins, `PinSink`
also contains a`BoxPin` field alongside with type information, but rather
than implementing `PPBase` it contains a reference to a `PinSource`. Thus,
by linking these two components we obtain a queue based message passing
channel between the nodes. (See Figure 5.2 in the thesis).

In our framework the KPN processing nodes execute in **lock step**. We implement this behavior in two stages:

1. Input synchronization: the `execStep()` function will only be called if all the input channels/streams have data, otherwise the executing thread will *block* until the empty streams are non-empty. Therefore, if all data are to be processed than every input must have received an *equal* amount of information. This approach is similar to that used by the Maxeler kernels.
2. Output synchronization: all new outputs are produced during the same cycle.


Our implementation allows for *non-monotonic* sequences of data: if for some reason an inputs do not have the same *sequence* than the inputs will be skipped so that all of them reach the highest existing sequence number. In addition, the `Box` nodes can be configured to skip `null` data, by skipping the execution step and filling all the outputs with `null` values. Should that be the case, the remaining non-null input values will be lost and will not be used in any computation.

###Input pre-processing

Thanks to Rory's contribution, the Ripple framewok has one important feature. It's able to apply functions and auto-casting at the `BoxPin` level. This typicall happens inside the `execStep` method when processing the input `objects` list with by calling `PPVariableTable.setArray(Object[])`.

Going back to the example above seting up the pre-processing components is done in the following way:

```
	protected ExampleNode(BoxPin[] inputTypes, BoxPin[] outputTypes, String displayName, Class<SubClassMode> connectedModeType, boolean skipNulls) {
        super(inputTypes, outputTypes, displayName, connectedModeType, skipNulls);
		
		/*Setting up pre-processing components */
    	PPVariableTable varTable = new PPVariableTable();
		
		/* traverse the list of input pins and acreate a mapping the variable
		* with the name of the pin and the type of the pin. The type is being used for 
		* automated casting if the input pin is connected to an output pin of a different type
		* belonging to a different Box node
		*/
        for(BoxPin pin : listInputPins()){
            varTable.create(pin.getPinName(),pin.getPinType());
        }
        /* Expressions
		* utils is not defined here yet, but we will come back to it when illustrating Spring integration
		*/
        PPType[] expressionTypes = utils.parseTypeList(config.getConfigMulti("expressionTypes"));
        PPBase[] inputValues = utils.parseTuples(config.getConfig("expression"),
        expressionTypes,varTable);
	}
```
`PPVariableTable` contains a table of variables names given to the input pins as we will see in the **Function definition** section. The names can be used to reference various `BoxPin`s belonging to a box when pre-processing their data.

Thus, in the `execStep` function we will have:

```
    @Override
    protected Object[] execStep(Object[] objects, SubClassMode currentMode) throws BoxError, InterruptedException {
	
		/**
		* We are now populating the variable table with actual values from the pins
		* and applying the pre-processor functions and type castings.
		*/
		varTable.setArray(objects);
		
		/**
		* increment the sequence number each time we execute a step to retrieve the
		* latest value if already cached. Passing a differente sequence number will 
		* re-trigger computation even if the same function has already been applied.
		* If the input is not the result of a function, then the sequenceNumber has no
		* effect.
		**/
		
		sequenceNumber++
		
		for(int i=0;i<inputValues.length;i++){
            if(inputValues[i].getValue(sequenceNumber)!=null){
                System.out.println((Integer)inputValues[i].getValue(sequenceNumber));
            }
        }
        return new Object[]{};
	}

```

We can see how the automated type casting can be useful when having more complex objects being transmitted through the channel. This way we remove the potential complexities of the logic
required by casting every individual input to a type which can be processed by this `BoxNode`. The pre-processing step allows the definition of various functions which can operate on the 
existing inputs in order to produce values which will be digested by the `Box`. A diagram detailing the relation between the different classes (e.g. `PPBase`, `PPType`) presented in the methods above can be found
on page 46 of the thesis.

You can imagine the pre-processing step as a construction site, where trucks come in through the gates loaded with various materials, and where the gate keepers ensure the materials are groupped or 
arranged in such way that the machines inside the construction site can focus on doing the heavy lifting.

###Spring integration

The Ripple Framework uses Spring as a framework for dependency injection. We have chosen Spring as there is currently a lot of community support for it and it's being used at enterprise level.
This allows Ripple Framework to be flexibile when it comes to the following:

1. **Type definition** - We can create new types
2. **Function definition** - We can create new functions

###Type definition

The Ripple framework contains several default types which support casting to other types all inherting from `PPType`:

* PPTypeInt - an *integer* type. Can be casted to:
	* PPTypeString
	* PPTypeBoolean
	* PPTypeDouble
* PPTypeBytes - an *array of bytes* type. Can be casted to:
	* PPTypeDouble (takes the last 8 bytes and converts them to a double)
	* PPTypeInt (takes the last 4 bytes and converts them to an integer)
	* PPTypeASCIIString (takes all the bytes and converts them to a string)
* PPTypeBoolean - an integer type. Can be casted to (will update).
* PPTypeGeneric - a generic type. Will be removed in future releases.
* PPTypeString - a *string* type. Can be casted to (will update).
* PPTypeDouble - a *double* type. Can be casted to (will update).

Below is an example of how to define a new type (taken from the source code of PPTypeDouble) which 
will be picked up at run-time.

```
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class PPTypeDouble extends PPType {
    @Override
    public PPBase cast(PPType newType, PPBase value) throws PPError.PPSemanticError {
        switch (newType.getClass().getSimpleName()) {
            case "PPTypeDouble":
                return value;
            case "PPTypeString":
                return new PPCast<Double, String>(value, new PPTypeString()) {
                    @Override
                    protected String cast(Double val) {
                        return val.toString();
                    }
                };
            case "PPTypeBoolean":
                return new PPCast<Double, Boolean>(value, new PPTypeBoolean()) {
                    @Override
                    protected Boolean cast(Double val) {
                        return ( ! (val == 0.0) );
                    }
                };
            default:
                throw new PPError.PPTypeError(this, newType);
        }
    }

    @Override
    public Object defaultValue() {
       return new Double(0.0);
    }
}
```

We can see that `PPTypeDouble` support casting to the following other `PPType`s:

1. PPTypeString
2. PPTypeBoolean

###Function definition

As mentioned earlier, the Ripple Framework supports input pre-processing via automated type casting and function application. 
Ripple Framework provides to helper classes `BoxConfig` and `Utils` which can be used to store and parse the configurations of a `Box` and create the input/output pins.

Below is an example of how these are used when being passed to an implementation of a `Box` using Spring beans.

```
 <bean id="predictionConfig" class="com.septacore.ripple.node.BoxConfig">
        <property name="filename" value="PredictionConfig.xml"/>
        <property name="properties">
            <map>
                <entry key="modelFile" value="src/data/testmodel.svm"/>
                <entry key="displayName" value="prediction"/>
                <entry key="expectedInstances" value="500"/>
                <entry key="inputPinNames" value="rtype;rlen;"/>
                <entry key="inputPinTypes" value="PPTypeBytes;PPTypeBytes;"/>
                <entry key="expression" value="rtype;sd(rlen);"/>
                <entry key="expressionTypes" value="PPTypeInt;PPTypeDouble;"/>
				<entry key="outputPinNames" value="compare;"/>
                <entry key="outputPinTypes" value="PPTypeBoolean;"/>
            </map>
        </property>
    </bean>

    <bean id="prediction" class="ml.Prediction">
        <constructor-arg ref="predictionConfig"/>
        <constructor-arg ref="Utils"/>
    </bean>
```
and in the Java code

```
public Prediction(BoxConfig config, Utils utils) throws PPError, IOException, BoxError.BoxConfigError {
		/**
		* utils.generatePins(BoxConfig config, boolean isOutputPins);
		*/
        super(utils.generatePins(config,false), utils.generatePins(config,true), config.getConfig("displayName"), Object.class, false);
}

```

Each `BoxPin` acting as an **input pin** can have the following information attached to it:

* a pin name - such as `rlen`
* a pin type - such as `PPTypeBytes`
* an expression - such as `sd(rlen)` which means "apply the standard deviation function to the `rlen`" input or just `rlen` meaning use the value *as is*
* an expression type - what is the type of the returned expression. This is the part which specifies either the return type of the function (i.e. `sd` in this case)
or the casting type of the pin (if a different one, otherwise it will be the same as the pin type).

Hence, when the pin type is different from expression type the automated casting will kick in and depending on whether the type casting specification it will succeed or fail.

The **output pins** only set the type of the output pin and assign a name to them.

New function can be created in a similar way to this example:

```
@Component(value = "IPRANGE")
public class IpRange implements PPAppFunction {

    private class IpRangeApp extends PPAppBase{

        private boolean checkNotNull(Object[] objects){
            for(Object o: objects){
                if(o==null) return false;
            }
            return true;
        }

        private IpRangeApp(){
            super(new PPTypeInt(), new PPType[]{new PPTypeBytes()});
        }

        @Override
        public Object applyPreprocessor(Object[] objects) {
            if(!checkNotNull(objects)) return null;
            /*
            Get the first byte and return it as an int.
             */
            ByteBuffer buff = ByteBuffer.allocate(4);
            int buffL=4;
            buff.put(--buffL,((byte[])objects[0])[0]);
            buff.order(ByteOrder.BIG_ENDIAN);
            return buff.getInt();
        }
    }

    @Override
    public PPAppBase create() {
        return new IpRangeApp();
    }
}
```

Note the component name `IPRANGE`. This will be the name of the new function, which return a type PPTypeInt and takes in one argument of PPTypeBytes. The function takes the first few bytes of the
IP address and converts it to a number.

The new function definition is being picked up by the `PPAppRegistry` at run-time and applied whenever called. The function must implement the `create` method which returns an `PPAppBase`.
A `PPAppBase` is in fact a sub-class of `PPBase` but which deals with applied functions as oppposed to constant values (`PPConstantValue`) or variables (`PPVariable`).