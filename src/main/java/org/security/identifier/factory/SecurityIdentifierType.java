package org.security.identifier.factory;

import org.security.identifier.Cusip;
import org.security.identifier.Isin;
import org.security.identifier.Sedol;
import org.security.identifier.generic.SecurityIdentifier;

public enum SecurityIdentifierType {

    ISIN {
        @Override
        public SecurityIdentifier getInstance(String securityIdentifier) {
            return Isin.from(securityIdentifier);
        }
    }, CUSIP {
        @Override
        public SecurityIdentifier getInstance(String securityIdentifier) {
            return Cusip.from(securityIdentifier);
        }
    }, SEDOL {
        @Override
        public SecurityIdentifier getInstance(String securityIdentifier) {
            return Sedol.from(securityIdentifier);
        }
    };

    public abstract SecurityIdentifier getInstance(String securityIdentifier);
}
