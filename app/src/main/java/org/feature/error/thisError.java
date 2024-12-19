package org.feature.error;

public class thisError {
    public static class IllegalArgumentSizeException extends RuntimeException{
        public IllegalArgumentSizeException(){
            System.out.println("Error name 2 > length > 1000");
        }
    }

}
