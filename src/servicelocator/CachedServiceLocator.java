package servicelocator;

import java.util.HashMap;

public class CachedServiceLocator implements ServiceLocator {
    private HashMap<String, Object> cachedServices;

    public CachedServiceLocator(){
        cachedServices = new HashMap<>();
    }

    public void setService(String name, Factory factory) throws LocatorError {
        if (!cachedServices.containsKey(name))
            cachedServices.put(name, factory);
        else
            throw new LocatorError(new ClassCastException());
    }

    public void setConstant(String name, Object value) throws LocatorError {
        if (!cachedServices.containsKey(name))
            cachedServices.put(name, value);
        else
            throw new LocatorError(new ClassCastException());
    }

    public Object getObject(String name) throws LocatorError {
        if (cachedServices.containsKey(name))
            return cachedServices.get(name);
        else
            throw new LocatorError(new ClassCastException());
    }
}
