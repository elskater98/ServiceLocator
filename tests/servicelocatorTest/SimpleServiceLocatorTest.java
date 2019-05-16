package servicelocatorTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import servicelocator.LocatorError;
import servicelocator.*;
import testServices.*;
import static org.junit.jupiter.api.Assertions.*;


public class SimpleServiceLocatorTest {

    private SimpleServiceLocator simpleServiceLocator;


    @BeforeEach
    void setUp(){
        simpleServiceLocator = new SimpleServiceLocator();
    }


    @Test
    void setServiceTestException(){
        FactoryA1 factoryA1 = new FactoryA1();
        FactoryA1 factoryA11 = new FactoryA1();
        assertThrows(LocatorError.class, ()-> {
            simpleServiceLocator.setService("s", factoryA1);
            simpleServiceLocator.setService("s",factoryA11);
        });

    }


    @Test
    void setConstantTestException(){
        Object object1 = new Object();
        Object object11 = new Object();
        assertThrows(LocatorError.class, ()-> {
            simpleServiceLocator.setConstant("s", object1);
            simpleServiceLocator.setConstant("s",object11);
        });
    }

    @Test
    void setServiceTestA1() throws LocatorError{
        Factory factoryA1 = new FactoryA1();
        InterfaceD interfaceD = new ImplementationD1(0);
        InterfaceB interfaceB = new ImplementationB1(interfaceD);
        InterfaceC interfaceC = new ImplementationC1("Hola");

        simpleServiceLocator.setConstant("B",interfaceB);
        simpleServiceLocator.setConstant("C", interfaceC);
        simpleServiceLocator.setService("A", factoryA1);
        Object object = simpleServiceLocator.getObject("A");

        ImplementationA1 implementationA1 = (ImplementationA1) object;
        assertEquals(interfaceB, implementationA1.getB());
        assertEquals(interfaceC, implementationA1.getC());
    }

    @Test
    void setServiceTestB1() throws LocatorError{
        Factory factoryB1 = new FactoryB1();
        InterfaceD interfaceD = new ImplementationD1(0);

        simpleServiceLocator.setConstant("D", interfaceD);
        simpleServiceLocator.setService("B", factoryB1);
        Object object = simpleServiceLocator.getObject("B");

        ImplementationB1 implementationB1 = (ImplementationB1) object;
        assertEquals(interfaceD,implementationB1.getD());
    }

    @Test
    void setServiceTestC1() throws LocatorError{
        Factory factoryC1 = new FactoryC1();
        simpleServiceLocator.setConstant("S", "Hola");
        simpleServiceLocator.setService("C", factoryC1);
        Object object = simpleServiceLocator.getObject("C");
        ImplementationC1 implementationC1 = (ImplementationC1) object;

        assertEquals("Hola",implementationC1.getS());

    }

}
