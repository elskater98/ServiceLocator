package servicelocator;

public class LocatorError extends Exception {
    public LocatorError(ClassCastException locatorErrorException){
        super(locatorErrorException.getMessage());
    }
}
