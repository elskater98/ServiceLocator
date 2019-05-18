package servicelocator2Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import servicelocator2.FactoryT;
import servicelocator2.LocatorError;
import servicelocator2.SimpleServiceLocator;
import testInterfaces.*;
import testServices2.FactoryA1;

import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("unchecked")
public class SimpleServiceLocator2Test {

    private SimpleServiceLocator simpleServiceLocator;


    @BeforeEach
    void setUp(){
        simpleServiceLocator = new SimpleServiceLocator();
    }


    @Test
    void setServiceTestException(){
        FactoryT factoryA1 = new FactoryA1();
        FactoryT factoryA11 = new FactoryA1();

        assertThrows(LocatorError.class, ()-> {
            simpleServiceLocator.setService(String.class, factoryA1);
            simpleServiceLocator.setService(String.class,factoryA11);
        });
    }

    @Test
    <T> void  setConstantTestException(){
        T object = (T) "Object";
        T object1 = (T) "Object1";

        assertThrows(LocatorError.class, ()-> {
            simpleServiceLocator.setConstant(Object.class, object);
            simpleServiceLocator.setConstant(Object.class, object1);
        });
    }

    @Test
    <T> void setServiceTest() throws LocatorError{
        FactoryT factoryA1 = new FactoryA1();
        InterfaceD interfaceD = new ImplementationD1(0);
        T interfaceB = (T) new ImplementationB1(interfaceD);
        T interfaceC = (T) new ImplementationC1("Hola");
        Class interfaceBClass = InterfaceB.class;
        Class interfaceCClass = InterfaceC.class;
        Class string = String.class;

        simpleServiceLocator.setConstant(interfaceBClass, interfaceB);
        simpleServiceLocator.setConstant(interfaceCClass, interfaceC);
        simpleServiceLocator.setService(string, factoryA1);

        T object = (T) simpleServiceLocator.getObject(string);
        T object1 = (T) simpleServiceLocator.getObject(string);
        assertNotSame(object, object1);

    }


    @Test
    void setConstantTest(){

    }


    @Test
    void getObjectTest(){

    }

}