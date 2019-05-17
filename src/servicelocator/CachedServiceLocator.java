package servicelocator;

import java.util.HashMap;

public class CachedServiceLocator implements ServiceLocator {
    private HashMap<String, Factory> factories;
    private HashMap<String, Object> constants;
    private HashMap<String, Object> cache;

    public CachedServiceLocator(){
        factories = new HashMap<>();
        constants = new HashMap<>();
        cache = new HashMap<>();
    }

    public void setService(String name, Factory factory) throws LocatorError {
        if (!factories.containsKey(name))
            factories.put(name, factory);
        else
            throw new LocatorError(new ClassCastException());
    }

    public void setConstant(String name, Object value) throws LocatorError {
        if (!constants.containsKey(name))
            constants.put(name, value);
        else
            throw new LocatorError(new ClassCastException());
    }

    public Object getObject(String name) throws LocatorError {
        /*Per l'informe*/ /*Si hi ha dos objectes amb
                           el mateix nom independentment si es factoria o constant, excepció*/
        if (factories.containsKey(name) && constants.containsKey(name))
            throw new LocatorError(new ClassCastException());

        if (constants.containsKey(name))
            return constants.get(name);
        else if (factories.containsKey(name)){
            return null;

        }else
            throw new LocatorError(new ClassCastException());
    }
}
