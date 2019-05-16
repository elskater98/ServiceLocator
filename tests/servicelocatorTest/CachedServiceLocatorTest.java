package servicelocatorTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import servicelocator.Factory;
import servicelocator.CachedServiceLocator;
import servicelocator.LocatorError;
import testServices.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class CachedServiceLocatorTest {

    private CachedServiceLocator cachedServiceLocator;


    @BeforeEach
    void setUp(){
        cachedServiceLocator = new CachedServiceLocator();
    }


    @Test
    void setServiceTestException(){
        FactoryA1 factoryA1 = new FactoryA1();
        FactoryA1 factoryA11 = new FactoryA1();
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

        cachedServiceLocator.setConstant("B",interfaceB);
        cachedServiceLocator.setConstant("C", interfaceC);
        cachedServiceLocator.setService("A", factoryA1);
        Object object = cachedServiceLocator.getObject("A");

        ImplementationA1 implementationA1 = (ImplementationA1) object;
        assertEquals(interfaceB, implementationA1.getB());
        assertEquals(interfaceC, implementationA1.getC());
    }

    @Test
    void setServiceTestB1() throws LocatorError{
        Factory factoryB1 = new FactoryB1();
        InterfaceD interfaceD = new ImplementationD1(0);

        cachedServiceLocator.setConstant("D", interfaceD);
        cachedServiceLocator.setService("B", factoryB1);
        Object object = cachedServiceLocator.getObject("B");

        ImplementationB1 implementationB1 = (ImplementationB1) object;
        assertEquals(interfaceD,implementationB1.getD());
    }

    @Test
    void setServiceTestC1() throws LocatorError{
        Factory factoryC1 = new FactoryC1();
        String constant = new String("Hola");

        cachedServiceLocator.setConstant("S", constant);
        cachedServiceLocator.setService("C", factoryC1);
        Object object = cachedServiceLocator.getObject("C");

        ImplementationC1 implementationC1 = (ImplementationC1) object;
        assertEquals(constant,implementationC1.getS());

    }

    @Test
    void setServiceTestD1() throws LocatorError{
        Factory factoryD1 = new FactoryD1();
        int constant = 0;

        cachedServiceLocator.setConstant("I", constant);
        cachedServiceLocator.setService("D", factoryD1);
        Object object = cachedServiceLocator.getObject("D");

        ImplementationD1 implementationD1 = (ImplementationD1) object;
        assertEquals(constant, implementationD1.getI());

    }
}
