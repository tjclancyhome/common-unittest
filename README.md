# CommonUnitTest

Common utilities for aiding in unit testing.

## Important

When using `common-unittest` to output results of unit tests (e.g. `writeBanner`(), `writeln()`, etc), you have to pass in to maven the property `unitTestSupport.showOutput=true` Or if you always want to see output but ensure that you switch off the show output property,  you can set it programmatically like so:

```java
import static org.tjc.common.unittest.UnitTestSupport.methodName;
import static org.tjc.common.unittest.UnitTestSupport.setShowOutput;
import static org.tjc.common.unittest.UnitTestSupport.writeBanner;

public class FooTest {

    @BeforeEach
    public void setup() {
        setShowOutput(true);
    }

    @AfterEach
    public void tearDown() {
        setShowOutput(false);
    }
  
    @Test
  	public void testFooMethod() {
      writeBanner(methodName());
      // etc.
    }
}
```



