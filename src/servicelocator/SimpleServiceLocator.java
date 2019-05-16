package servicelocator;

import java.util.HashMap;

public class SimpleServiceLocator implements ServiceLocator {
    private HashMap<String, Object> factories;
    private HashMap<String, Object> constants;

    public SimpleServiceLocator(){
        factories = new HashMap<>();
        constants = new HashMap<>();
    }

    public void setService(String name, Factory factory) throws LocatorError {
        if (!factories.containsKey(name))
            factories.put(name, factory.create(this));
        else
            throw new LocatorError();
    }

    public void setConstant(String name, Object value) throws LocatorError {
            if (!constants.containsKey(name))
                constants.put(name, value);
            else
                throw new LocatorError();
    }

    public Object getObject(String name) throws LocatorError {
        if (constants.containsKey(name))
            return constants.get(name);
        else if (factories.containsKey(name))
            return factories.get(name);
        else
            throw new LocatorError();
    }
}
