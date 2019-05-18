package servicelocator;

import java.util.HashMap;

public class CachedServiceLocator implements ServiceLocator {
    private HashMap<String, Object> cache;
    private HashMap<String, Factory> services;

    public CachedServiceLocator(){
        services = new HashMap<>();
        cache = new HashMap<>();
    }

    public void setService(String name, Factory factory) throws LocatorError {
        if (!services.containsKey(name))
            services.put(name, factory);
        else
            throw new LocatorError(new ClassCastException());
    }

    public void setConstant(String name, Object value) throws LocatorError {
        /*Per l'informe*/ /*Com que es guardaran objectes tan si son constants com els objectes que crein les factories
                            no cal un tercer hashmap per fer de cache d'objectes creats per factories*/
        if (!cache.containsKey(name))
            cache.put(name, value);
        else
            throw new LocatorError(new ClassCastException());
    }

    public Object getObject(String name) throws LocatorError {
        if (cache.containsKey(name))
            return cache.get(name);
        else if(services.containsKey(name)) {
            cache.put(name, services.get(name).create(this));
            return cache.get(name);
        } else throw new LocatorError(new ClassCastException());
    }
}
