package servicelocatorTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import servicelocator.Factory;
import servicelocator.CachedServiceLocator;
import servicelocator.LocatorError;
import servicelocator.ServiceLocator;
import testServices.ImplementationA1;
import testServices.InterfaceA;
import testServices.InterfaceB;
import testServices.InterfaceC;


public class CachedServiceLocatorTest {

    private CachedServiceLocator csl;


    @BeforeEach
    void setUp(){
        csl = new CachedServiceLocator();
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
