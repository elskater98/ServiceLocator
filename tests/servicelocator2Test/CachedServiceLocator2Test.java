package servicelocator2Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import servicelocator2.CachedServiceLocator;
import servicelocator2.FactoryT;
import servicelocator2.LocatorError;
import testInterfaces.*;
import testServices2.FactoryA1;
import static org.junit.jupiter.api.Assertions.assertSame;


@SuppressWarnings("unchecked")
public class CachedServiceLocator2Test {

    private CachedServiceLocator cachedServiceLocator2;


    @BeforeEach
    void setUp(){
        cachedServiceLocator2 = new CachedServiceLocator();
    }


    @Test
    <T> void setServiceTest() throws LocatorError {
        FactoryT factoryA1 = new FactoryA1();
        InterfaceD interfaceD = new ImplementationD1(0);
        T interfaceB = (T) new ImplementationB1(interfaceD);
        T interfaceC = (T) new ImplementationC1("Hola");
        Class interfaceBClass = InterfaceB.class;
        Class interfaceCClass = InterfaceC.class;
        //Class string = String.class;

        cachedServiceLocator2.setConstant(interfaceBClass, interfaceB);
        cachedServiceLocator2.setConstant(interfaceCClass, interfaceC);
        cachedServiceLocator2.setService(FactoryA1.class, factoryA1);

        T object = (T) cachedServiceLocator2.getObject(FactoryA1.class);
        T object1 = (T) cachedServiceLocator2.getObject(FactoryA1.class);
        assertSame(object, object1);
    }


    @Test
    void setConstantTest(){

    }


    @Test
    void getObjectTest(){

    }

}