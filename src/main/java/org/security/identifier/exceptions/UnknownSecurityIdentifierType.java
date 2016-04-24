package org.security.identifier.exceptions;

public class UnknownSecurityIdentifierType extends Exception {

    public UnknownSecurityIdentifierType(String securityIdentifier) {
        super("Cannot determine type of security identifier " + securityIdentifier);
    }
}
