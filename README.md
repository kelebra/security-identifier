# security-identifier
The main intention for creation of this library is to have standart domain model across financial institutions for representing security identifiers and their validations.

Currently supported security identifiers:
- [ISIN](https://en.wikipedia.org/wiki/International_Securities_Identification_Number)
- [CUSIP](https://en.wikipedia.org/wiki/CUSIP)
- [SEDOL](https://en.wikipedia.org/wiki/SEDOL)

## Quick start
```xml
<dependency>
  <groupId>com.github.kelebra</groupId>
  <artifactId>security-identifier</artifactId>
  <version>0.2</version>
</dependency>
```
## Classes representing security identifiers
| Security identifier | Class                                                                                                                                                                    |
|:-------------------:|:------------------------------------------------------------------------------------------------------------------------------------------------------------------------:|
| ISIN                |[com.github.kelebra.security.identifier.Isin](https://github.com/kelebra/security-identifier/blob/master/src/main/java/com/github/kelebra/security/identifier/Isin.java)  |
| CUSIP               |[com.github.kelebra.security.identifier.Cusip](https://github.com/kelebra/security-identifier/blob/master/src/main/java/com/github/kelebra/security/identifier/Cusip.java)|
| SEDOL               |[com.github.kelebra.security.identifier.Sedol](https://github.com/kelebra/security-identifier/blob/master/src/main/java/com/github/kelebra/security/identifier/Sedol.java)|

All of them are successors of [SecurityIdentifier class](https://github.com/kelebra/security-identifier/blob/master/src/main/java/com/github/kelebra/security/identifier/generic/SecurityIdentifier.java) which is intendent to be used in case when you want to have generic representation and do not worry about actual underlying type.

## Create particular security identifier:
```java
Isin isin = Isin.from("US0378331005");
Sedol sedol = Sedol.from("0263494");
Cusip cusip = Cusip.from("037833100");
```
## Parse any security identifier:
```java
SecurityIdentifierFactory.from("US0378331005"); // creates Isin instance
SecurityIdentifierFactory.from("0263494"); // creates Sedol instance
SecurityIdentifierFactory.from("037833100"); // creates Cusip instance
```
## Defining security identifier type:
```java
SecurityIdentifierFactory.getType("US0378331005"); // returns SecurityIdentifierType.ISIN
SecurityIdentifierFactory.getType("0263494"); // returns SecurityIdentifierType.SEDOL
SecurityIdentifierFactory.getType("037833100"); // returns SecurityIdentifierType.CUSIP
```
## Validations applied when creating an instance of security identifier:
| Security identifier | Must have country code | Is alpha numeric | Required length | Check digit validation algorithm | Additional requirements           |
|:-------------------:|:----------------------:|:----------------:|:---------------:|:--------------------------------:|:---------------------------------:|
|ISIN                 | +                      | +                | 12              | Luhn's algorithm                 |                                   |
|CUSIP                | -                      | +                | 9               | Modulo 10 hashing algorithm      |                                   |
|SEDOL                | -                      | +                | 7               | Modulo 10 hashing algorithm      |GB country code for ISIN conversion|
## Create security identifier without check digit check:
```java
new IsinBuilder().withoutCheckOfCheckDigit().build("US0378331006"); // creates Isin instance
new SedolBuilder().withoutCheckOfCheckDigit().build("0263497"); // creates Sedol instance
new CusipBuilder().withoutCheckOfCheckDigit().build("037833101"); // creates Cusip instance
```
