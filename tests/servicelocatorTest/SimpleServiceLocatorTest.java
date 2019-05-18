package servicelocatorTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import servicelocator.LocatorError;
import servicelocator.*;
import testInterfaces.*;
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
        Factory factoryA1 = new FactoryA1();
        Factory factoryA11 = new FactoryA1();
        
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
        Object object1 = simpleServiceLocator.getObject("A");
        assertNotSame(object, object1);

        ImplementationA1 implementationA1 = (ImplementationA1) object;
        assertSame(interfaceB, implementationA1.getB());
        assertSame(interfaceC, implementationA1.getC());
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
        assertSame(interfaceD,implementationB1.getD());
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
        assertSame(constant,implementationC1.getS());

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
        assertSame(constant, implementationD1.getI());

    }

}
