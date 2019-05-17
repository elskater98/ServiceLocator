package servicelocator2;

import java.util.HashMap;

public class CachedServiceLocator implements ServiceLocator {
    private HashMap<Class, Object> cache;
    private HashMap<Class, FactoryT> services;

    public CachedServiceLocator(){
        cache = new HashMap<>();
        services = new HashMap<>();
    }

    public <T> void setService(Class<T> klass, FactoryT<T> factoryT) throws LocatorError {
        if (!services.containsKey(klass))
            services.put(klass, factoryT);
        else
            throw new LocatorError(new ClassCastException());
    }

    public <T> void setConstant(Class<T> klass, T value) throws LocatorError {
        if (!cache.containsKey(klass))
            cache.put(klass, value);
        else
            throw new LocatorError(new ClassCastException());
    }

    @SuppressWarnings("unchecked")
    public <T> T getObject(Class<T> klass) throws LocatorError {
        if (cache.containsKey(klass))
            return (T) cache.get(klass);
        else if(services.containsKey(klass)) {
            cache.put(klass, services.get(klass).create(this));
            return (T) cache.get(klass);
        }else throw new LocatorError(new ClassCastException());
    }
}
