package Characters;

public class ANSIColor {
    public static final String RESET = "\u001B[0m";
    public static final String RED   = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW= "\u001B[33m";
    public static final String BLUE  = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String ORANGE = "\u001B[38;5;208m";
    
}

// Usage example:
// System.out.println(ANSIColor.RED + "This text is red!" + ANSIColor.RESET);
