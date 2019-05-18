package servicelocator2Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import servicelocator2.FactoryT;
import servicelocator2.LocatorError;
import servicelocator2.SimpleServiceLocator;
import testInterfaces.*;
import testServices2.*;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotSame;

@SuppressWarnings("unchecked")
public class SimpleServiceLocator2Test {

    private SimpleServiceLocator simpleServiceLocator;


    @BeforeEach
    void setUp(){
        simpleServiceLocator = new SimpleServiceLocator();
    }


    @Test
    void setServiceTestException() {
        FactoryT factoryA1 = new FactoryA1();
        FactoryT factoryA11 = new FactoryA1();

        assertThrows(LocatorError.class, ()-> {
            simpleServiceLocator.setService(String.class, factoryA1);
            simpleServiceLocator.setService(String.class,factoryA11);
        });
    }

    @Test
    <T> void  setConstantTestException() {
        T object = (T) "Object";
        T object1 = (T) "Object1";

        assertThrows(LocatorError.class, ()-> {
            simpleServiceLocator.setConstant(Object.class, object);
            simpleServiceLocator.setConstant(Object.class, object1);
        });
    }

    @Test
    <T> void setServiceTestA1() throws LocatorError {
        FactoryT factoryA1 = new FactoryA1();
        InterfaceD interfaceD = new ImplementationD1(0);
        T interfaceB = (T) new ImplementationB1(interfaceD);
        T interfaceC = (T) new ImplementationC1("Hola");
        Class interfaceBClass = InterfaceB.class;
        Class interfaceCClass = InterfaceC.class;

        simpleServiceLocator.setConstant(interfaceBClass, interfaceB);
        simpleServiceLocator.setConstant(interfaceCClass, interfaceC);
        simpleServiceLocator.setService(FactoryA1.class, factoryA1);

        T object = (T) simpleServiceLocator.getObject(FactoryA1.class);
        T object1 = (T) simpleServiceLocator.getObject(FactoryA1.class);
        assertNotSame(object, object1);

    }

    @Test
    <T> void setServiceTestB1() throws LocatorError {
        FactoryT factoryB1 = new FactoryB1();
        T interfaceD = (T) new ImplementationD1(0);
        Class interfaceDClass = InterfaceD.class;

        simpleServiceLocator.setConstant(interfaceDClass, interfaceD);
        simpleServiceLocator.setService(FactoryB1.class, factoryB1);

        T object = (T) simpleServiceLocator.getObject(FactoryB1.class);
        T object1 = (T) simpleServiceLocator.getObject(FactoryB1.class);
        assertNotSame(object, object1);

    }

    @Test
    <T> void setServiceTestC1() throws LocatorError {
        FactoryT factoryC1 = new FactoryC1();
        T constant = (T) "Hola";
        Class constantClass = String.class;

        simpleServiceLocator.setConstant(constantClass, constant);
        simpleServiceLocator.setService(FactoryC1.class, factoryC1);

        T object = (T) simpleServiceLocator.getObject(FactoryC1.class);
        T object1 = (T) simpleServiceLocator.getObject(FactoryC1.class);
        assertNotSame(object, object1);
    }

    @Test
    <T> void setServiceTestD1() throws LocatorError {
        FactoryT factoryD1 = new FactoryD1();
        Integer integer = 0;
        T constant = (T) integer;
        Class constantClass = Integer.class;

        simpleServiceLocator.setConstant(constantClass, constant);
        simpleServiceLocator.setService(FactoryD1.class, factoryD1);

        T object = (T) simpleServiceLocator.getObject(FactoryD1.class);
        T object1 = (T) simpleServiceLocator.getObject(FactoryD1.class);
        assertNotSame(object, object1);
    }

}