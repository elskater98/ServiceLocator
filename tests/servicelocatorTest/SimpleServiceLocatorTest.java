package servicelocatorTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import servicelocator.Factory;
import servicelocator.LocatorError;
import servicelocator.ServiceLocator;
import servicelocator.SimpleServiceLocator;
import testServices.ImplementationA1;
import testServices.InterfaceA;
import testServices.InterfaceB;
import testServices.InterfaceC;


public class SimpleServiceLocatorTest {

    private SimpleServiceLocator ssl;


    @BeforeEach
    void setUp(){
        ssl = new SimpleServiceLocator();
    }


    private static class FactoryA1 implements Factory{

        @Override
        public InterfaceA create(ServiceLocator sl) throws LocatorError {

            try{
                InterfaceB b = (InterfaceB) sl.getObject("B");
                InterfaceC c = (InterfaceC) sl.getObject("C");
                return new ImplementationA1(b, c);
            } catch (ClassCastException ex){
                throw new LocatorError(ex);
            }
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
