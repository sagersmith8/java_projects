//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.03.03 at 09:26:31 PM MST 
//


package org.example.heroschema;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.example.heroschema package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.example.heroschema
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Hero }
     * 
     */
    public Hero createHero() {
        return new Hero();
    }

    /**
     * Create an instance of {@link Hero.General }
     * 
     */
    public Hero.General createHeroGeneral() {
        return new Hero.General();
    }

    /**
     * Create an instance of {@link Hero.SecretIdentity }
     * 
     */
    public Hero.SecretIdentity createHeroSecretIdentity() {
        return new Hero.SecretIdentity();
    }

    /**
     * Create an instance of {@link Hero.Costume }
     * 
     */
    public Hero.Costume createHeroCostume() {
        return new Hero.Costume();
    }

}