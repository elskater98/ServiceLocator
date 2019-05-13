package servicelocator;

public class CachedServiceLocator implements ServiceLocator {
    public void setService(String name, Factory factory) throws LocatorError {

    }

    public void setConstant(String name, Object value) throws LocatorError {

    }

    public Object getObject(String name) throws LocatorError {
        return null;
    }
}
