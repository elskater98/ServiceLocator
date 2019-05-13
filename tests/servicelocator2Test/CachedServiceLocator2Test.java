package servicelocator2Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import servicelocator2.FactoryT;
import servicelocator2.LocatorError;
import servicelocator2.ServiceLocator;
import servicelocator2.CachedServiceLocator;
import testServices.ImplementationA1;
import testServices.InterfaceA;
import testServices.InterfaceB;
import testServices.InterfaceC;

public class CachedServiceLocator2Test {

    private CachedServiceLocator csl;


    @BeforeEach
    void setUp(){
        csl = new CachedServiceLocator();
    }


    private static class FactoryA1 implements FactoryT{

        @Override
        public InterfaceA create(ServiceLocator sl) throws LocatorError {
            InterfaceB b = sl.getObject(InterfaceB.class);
            InterfaceC c = sl.getObject(InterfaceC.class);
            return new ImplementationA1(b, c);
        }
    }


    @Test
    void setServiceTest(){

    }


    @Test
    void setConstantTest(){

    }


    @Test
    void getObjectTest(){

    }

}