package servicelocatorTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import servicelocator.LocatorError;
import servicelocator.SimpleServiceLocator;
import testServices.*;
import static org.junit.jupiter.api.Assertions.*;


public class SimpleServiceLocatorTest {

    private SimpleServiceLocator ssl;


    @BeforeEach
    void setUp(){
        ssl = new SimpleServiceLocator();
    }


    @Test
    void setServiceTestException(){
        FactoryA1 factoryA1 = new FactoryA1();
        FactoryA1 factoryA11 = new FactoryA1();
        assertThrows(LocatorError.class, ()-> {
            ssl.setService("s", factoryA1);
            ssl.setService("s",factoryA11);
        });

    }


    @Test
    void setConstantTestException(){
        Object object1 = new Object();
        Object object11 = new Object();
        assertThrows(LocatorError.class, ()-> {
            ssl.setConstant("s", object1);
            ssl.setConstant("s",object11);
        });
    }


    @Test
    void getObjectTest() throws LocatorError {
        FactoryA1 factoryA1 = new FactoryA1();
        ssl.setService("s", factoryA1);
        ssl.setConstant("B", new Object());
        ssl.setConstant("C", new Object());
        /*
        InterfaceD d = new ImplementationD1(0);
        InterfaceB b = new ImplementationB1(d);
        InterfaceC c = new ImplementationC1("C");
        ImplementationA1 a1 = new ImplementationA1(b, c);
        */


        //assertEquals(factoryA1.create(ssl), ssl.getObject("s"));


        /*
        Object object1 = new Object();
        ssl.setConstant("t",object1);
        assertEquals(object1, ssl.getObject("t"));
        */

    }

}
