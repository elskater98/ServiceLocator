package servicelocatorTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import servicelocator.LocatorError;
import servicelocator.CachedServiceLocator;
import servicelocator.Factory;
import testServices.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class CachedServiceLocatorTest {

    private CachedServiceLocator cachedServiceLocator;


    @BeforeEach
    void setUp(){
        cachedServiceLocator = new CachedServiceLocator();
    }


    @Test
    void setServiceTestException(){
        Factory factoryA1 = new FactoryA1();
        Factory factoryA11 = new FactoryA1();
        assertThrows(LocatorError.class, ()-> {
            cachedServiceLocator.setService("s", factoryA1);
            cachedServiceLocator.setService("s",factoryA11);
        });

    }

    @Test
    void setConstantTestException(){
        Object object1 = new Object();
        Object object11 = new Object();
        assertThrows(LocatorError.class, ()-> {
            cachedServiceLocator.setConstant("s", object1);
            cachedServiceLocator.setConstant("s",object11);
        });

    }

    @Test
    void setServiceTestA1() throws LocatorError{
        Factory factoryA1 = new FactoryA1();
        InterfaceD interfaceD = new ImplementationD1(0);
        InterfaceB interfaceB = new ImplementationB1(interfaceD);
        InterfaceC interfaceC = new ImplementationC1("Hola");

        //Test with one factory same object reference
        cachedServiceLocator.setConstant("B",interfaceB);
        cachedServiceLocator.setConstant("C", interfaceC);
        cachedServiceLocator.setService("A", factoryA1);
        Object object = cachedServiceLocator.getObject("A");
        Object object1 = cachedServiceLocator.getObject("A");
        assertSame(object, object1);

        //Test setting the factory correctly
        ImplementationA1 implementationA1 = (ImplementationA1) object;
        assertEquals(interfaceB, implementationA1.getB());
        assertEquals(interfaceC, implementationA1.getC());

        //Test with two factory
        Factory factoryB1 = new FactoryB1();
        InterfaceD interfaceD1 = new ImplementationD1(0);

        cachedServiceLocator.setConstant("D", interfaceD1);
        cachedServiceLocator.setService("B1", factoryB1);
        Object object2 = cachedServiceLocator.getObject("B1");
        Object object3 = cachedServiceLocator.getObject("B1");
        assertSame(object2, object3);

    }

    @Test
    void setServiceTestB1() throws LocatorError{
        Factory factoryB1 = new FactoryB1();
        InterfaceD interfaceD = new ImplementationD1(0);

        cachedServiceLocator.setConstant("D", interfaceD);
        cachedServiceLocator.setService("B", factoryB1);
        Object object = cachedServiceLocator.getObject("B");
        Object object1 = cachedServiceLocator.getObject("B");
        assertSame(object, object1);

        ImplementationB1 implementationB1 = (ImplementationB1) object;
        assertEquals(interfaceD,implementationB1.getD());


        Factory factoryC1 = new FactoryC1();
        String constant = "Hola";

        cachedServiceLocator.setConstant("S", constant);
        cachedServiceLocator.setService("C", factoryC1);
        Object object2 = cachedServiceLocator.getObject("C");
        Object object3 = cachedServiceLocator.getObject("C");
        assertSame(object2, object3);

    }

    @Test
    void setServiceTestC1() throws LocatorError{
        Factory factoryC1 = new FactoryC1();
        String constant = "Hola";

        cachedServiceLocator.setConstant("S", constant);
        cachedServiceLocator.setService("C", factoryC1);
        Object object = cachedServiceLocator.getObject("C");
        Object object1 = cachedServiceLocator.getObject("C");
        assertSame(object, object1);

        ImplementationC1 implementationC1 = (ImplementationC1) object;
        assertEquals(constant,implementationC1.getS());


        Factory factoryD1 = new FactoryD1();
        int constant1 = 0;
        cachedServiceLocator.setConstant("I", constant1);
        cachedServiceLocator.setService("D", factoryD1);

        Object object2 = cachedServiceLocator.getObject("D");
        Object object3 = cachedServiceLocator.getObject("D");
        assertSame(object2, object3);

    }

    @Test
    void setServiceTestD1() throws LocatorError{
        Factory factoryD1 = new FactoryD1();
        int constant = 0;

        cachedServiceLocator.setConstant("I", constant);
        cachedServiceLocator.setService("D", factoryD1);
        Object object = cachedServiceLocator.getObject("D");
        Object object1 = cachedServiceLocator.getObject("D");
        assertSame(object, object1);

        ImplementationD1 implementationD1 = (ImplementationD1) object;
        assertEquals(constant, implementationD1.getI());


        Factory factoryA1 = new FactoryA1();
        InterfaceD interfaceD = new ImplementationD1(0);
        InterfaceB interfaceB = new ImplementationB1(interfaceD);
        InterfaceC interfaceC = new ImplementationC1("Hola");

        cachedServiceLocator.setConstant("B",interfaceB);
        cachedServiceLocator.setConstant("C", interfaceC);
        cachedServiceLocator.setService("A", factoryA1);
        Object object2 = cachedServiceLocator.getObject("A");
        Object object3 = cachedServiceLocator.getObject("A");
        assertSame(object2, object3);

    }

}
