package servicelocator2Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import servicelocator2.CachedServiceLocator;
import servicelocator2.FactoryT;
import servicelocator2.LocatorError;
import testInterfaces.*;
import testServices2.*;
import static org.junit.jupiter.api.Assertions.*;


@SuppressWarnings("unchecked")
public class CachedServiceLocator2Test {

    private CachedServiceLocator cachedServiceLocator2;


    @BeforeEach
    void setUp(){
        cachedServiceLocator2 = new CachedServiceLocator();
    }


    @Test
    @DisplayName("Check Throws LocatorError when use setService")
    void setServiceTestException(){
        FactoryA1 factoryA1 = new FactoryA1();
        FactoryA1 factoryA11 = new FactoryA1();
        assertThrows(LocatorError.class, ()->{
            cachedServiceLocator2.setService(FactoryA1.class, factoryA1);
            cachedServiceLocator2.setService(FactoryA1.class, factoryA11);
        });
    }

    @Test
    @DisplayName("Check Throws LocatorError when use setConstant")
    void setConstantTestException() {
        InterfaceC interface1 = new ImplementationC1("Hola");
        InterfaceC interface2 = new ImplementationC1("Hola");
        assertThrows(LocatorError.class, ()->{
            cachedServiceLocator2.setConstant(InterfaceC.class, interface1);
            cachedServiceLocator2.setConstant(InterfaceC.class, interface2);
        });
    }

    @Test
    @DisplayName("Check setService using FactoryA1")
    <T> void setServiceTest() throws LocatorError {
        FactoryT factoryA1 = new FactoryA1();
        InterfaceD interfaceD = new ImplementationD1(0);
        InterfaceB interfaceB = new ImplementationB1(interfaceD);
        InterfaceC interfaceC = new ImplementationC1("Hola");
        Class interfaceBClass = InterfaceB.class;
        Class interfaceCClass = InterfaceC.class;
        Class interfaceDClass = InterfaceD.class;
        Class factoryA1Class = FactoryA1.class;

        cachedServiceLocator2.setConstant(interfaceBClass, interfaceB);
        cachedServiceLocator2.setConstant(interfaceCClass, interfaceC);
        cachedServiceLocator2.setService(factoryA1Class, factoryA1);

        T object = (T) cachedServiceLocator2.getObject(factoryA1Class);
        T object1 = (T) cachedServiceLocator2.getObject(factoryA1Class);
        assertSame(object, object1);

        FactoryB1 factoryB1 = new FactoryB1();
        cachedServiceLocator2.setConstant(interfaceDClass, interfaceD);
        cachedServiceLocator2.setService(FactoryB1.class, factoryB1);
        assertSame(cachedServiceLocator2.getObject(FactoryB1.class), cachedServiceLocator2.getObject(FactoryB1.class));
    }

}