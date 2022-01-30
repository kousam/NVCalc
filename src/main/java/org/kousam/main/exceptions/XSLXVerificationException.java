package org.kousam.main.exceptions;

public class XSLXVerificationException extends Exception {

    public XSLXVerificationException(){
        super("XSLXVerificationException:\nTarkista asiakirja");
    }

    public XSLXVerificationException(String msg){
        super(msg);
    }

}
