package com.github.kelebra.security.identifier.factory;

import com.github.kelebra.security.identifier.Cusip;
import com.github.kelebra.security.identifier.Isin;
import com.github.kelebra.security.identifier.generic.SecurityIdentifier;
import com.github.kelebra.security.identifier.Sedol;

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
