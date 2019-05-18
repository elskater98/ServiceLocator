package servicelocatorTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import servicelocator.LocatorError;
import servicelocator.SimpleServiceLocator;
import servicelocator.Factory;
import testServices.FactoryA1;
import testServices.FactoryB1;
import testServices.FactoryC1;
import testServices.FactoryD1;
import testServices.ImplementationA1;
import testServices.ImplementationB1;
import testServices.ImplementationC1;
import testServices.ImplementationD1;
import testServices.InterfaceB;
import testServices.InterfaceC;
import testServices.InterfaceD;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

        //Test with one factory same object reference
        simpleServiceLocator.setConstant("B",interfaceB);
        simpleServiceLocator.setConstant("C", interfaceC);
        simpleServiceLocator.setService("A", factoryA1);
        Object object = simpleServiceLocator.getObject("A");
        Object object1 = simpleServiceLocator.getObject("A");
        assertNotSame(object, object1);

        //Test setting the factory correctly
        ImplementationA1 implementationA1 = (ImplementationA1) object;
        assertEquals(interfaceB, implementationA1.getB());
        assertEquals(interfaceC, implementationA1.getC());

        //Test with two factory
        Factory factoryB1 = new FactoryB1();
        InterfaceD interfaceD1 = new ImplementationD1(0);

        simpleServiceLocator.setConstant("D", interfaceD1);
        simpleServiceLocator.setService("B1", factoryB1);
        Object object2 = simpleServiceLocator.getObject("B1");
        Object object3 = simpleServiceLocator.getObject("B1");
        assertNotSame(object2, object3);

    }

    @Test
    void setServiceTestB1() throws LocatorError{
        Factory factoryB1 = new FactoryB1();
        InterfaceD interfaceD = new ImplementationD1(0);

        simpleServiceLocator.setConstant("D", interfaceD);
        simpleServiceLocator.setService("B", factoryB1);
        Object object = simpleServiceLocator.getObject("B");
        Object object1 = simpleServiceLocator.getObject("B");
        assertNotSame(object, object1);

        ImplementationB1 implementationB1 = (ImplementationB1) object;
        assertEquals(interfaceD,implementationB1.getD());


        Factory factoryC1 = new FactoryC1();
        String constant = "Hola";

        simpleServiceLocator.setConstant("S", constant);
        simpleServiceLocator.setService("C", factoryC1);
        Object object2 = simpleServiceLocator.getObject("C");
        Object object3 = simpleServiceLocator.getObject("C");
        assertNotSame(object2, object3);

    }

    @Test
    void setServiceTestC1() throws LocatorError{
        Factory factoryC1 = new FactoryC1();
        String constant = "Hola";

        simpleServiceLocator.setConstant("S", constant);
        simpleServiceLocator.setService("C", factoryC1);
        Object object = simpleServiceLocator.getObject("C");
        Object object1 = simpleServiceLocator.getObject("C");
        assertNotSame(object, object1);

        ImplementationC1 implementationC1 = (ImplementationC1) object;
        assertEquals(constant,implementationC1.getS());


        Factory factoryD1 = new FactoryD1();
        int constant1 = 0;

        simpleServiceLocator.setConstant("I", constant1);
        simpleServiceLocator.setService("D", factoryD1);
        Object object2 = simpleServiceLocator.getObject("D");
        Object object3 = simpleServiceLocator.getObject("D");
        assertNotSame(object2, object3);

    }

    @Test
    void setServiceTestD1() throws LocatorError{
        Factory factoryD1 = new FactoryD1();
        int constant = 0;

        simpleServiceLocator.setConstant("I", constant);
        simpleServiceLocator.setService("D", factoryD1);
        Object object = simpleServiceLocator.getObject("D");
        Object object1 = simpleServiceLocator.getObject("D");
        assertNotSame(object, object1);

        ImplementationD1 implementationD1 = (ImplementationD1) object;
        assertEquals(constant, implementationD1.getI());


        Factory factoryA1 = new FactoryA1();
        InterfaceD interfaceD = new ImplementationD1(0);
        InterfaceB interfaceB = new ImplementationB1(interfaceD);
        InterfaceC interfaceC = new ImplementationC1("Hola");

        simpleServiceLocator.setConstant("B",interfaceB);
        simpleServiceLocator.setConstant("C", interfaceC);
        simpleServiceLocator.setService("A", factoryA1);
        Object object2 = simpleServiceLocator.getObject("A");
        Object object3 = simpleServiceLocator.getObject("A");
        assertNotSame(object2, object3);

    }

}
