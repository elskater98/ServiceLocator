package servicelocator2;

public class CachedServiceLocator implements ServiceLocator {
    public <T> void setService(Class<T> klass, FactoryT<T> factoryT) throws LocatorError {
        
    }

    public <T> void setConstant(Class<T> klass, T value) throws LocatorError {

    }

    public <T> T getObject(Class<T> klass) throws LocatorError {
        return null;
    }
}
