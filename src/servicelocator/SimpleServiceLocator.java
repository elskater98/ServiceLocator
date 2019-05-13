package servicelocator;

import java.util.HashMap;

public class SimpleServiceLocator implements ServiceLocator {
    private HashMap<String, Factory> factories;
    private HashMap<String, Object> constants;

    public SimpleServiceLocator(){
        factories = new HashMap<>();
        constants = new HashMap<>();
    }

    public void setService(String name, Factory factory) throws LocatorError {
        try{
            if (!factories.containsKey(name)){
                factories.put(name, factory);
            }
        } catch (ClassCastException ex){
            throw new LocatorError(ex);
        }
    }

    public void setConstant(String name, Object value) throws LocatorError {
        try{
            if (!constants.containsKey(name)){
                constants.put(name, value);
            }
        } catch (ClassCastException ex){
            throw new LocatorError(ex);
        }
    }

    public Object getObject(String name) throws LocatorError {
        ClassCastException ex = new ClassCastException(); //for locatorError exception
        if (factories.containsKey(name))
            return factories.get(name).create(this);
        else if (constants.containsKey(name))
            return constants.get(name);
        else
            throw new LocatorError(ex);
    }
}
