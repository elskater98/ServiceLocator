package servicelocator2;

public class LocatorError extends Exception {
    public LocatorError(ClassCastException locatorErrorMessage){
        super(locatorErrorMessage);
    }
}
