package ru.admiralnsk.admiralbd.exceptions;

public class ExcelNotStructuredException extends Exception{

    public ExcelNotStructuredException() {
        super();
    }


    public ExcelNotStructuredException(String message) {
        super(message);
    }


    public ExcelNotStructuredException(String message, Throwable cause) {
        super(message, cause);
    }


    public ExcelNotStructuredException(Throwable cause) {
        super(cause);
    }
}
