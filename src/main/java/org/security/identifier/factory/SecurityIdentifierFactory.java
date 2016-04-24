package org.security.identifier.factory;

import org.security.identifier.exceptions.UnknownSecurityIdentifierType;
import org.security.identifier.generic.SecurityIdentifier;

public class SecurityIdentifierFactory {

    public static SecurityIdentifierType getType(String securityIdentifier) throws UnknownSecurityIdentifierType {
        for (SecurityIdentifierType type : SecurityIdentifierType.values()) {
            try {
                if (type.getInstance(securityIdentifier) != null)
                    return type;
            } catch (Exception ignored) {
            }
        }
        throw new UnknownSecurityIdentifierType(securityIdentifier);
    }

    public static SecurityIdentifier from(String securityIdentifier) throws UnknownSecurityIdentifierType {
        return getType(securityIdentifier).getInstance(securityIdentifier);
    }
}
