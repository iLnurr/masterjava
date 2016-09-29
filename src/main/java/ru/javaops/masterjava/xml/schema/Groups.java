
package ru.javaops.masterjava.xml.schema;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Groups.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="Groups">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="REGISTERING"/>
 *     &lt;enumeration value="CURRENT"/>
 *     &lt;enumeration value="FINISHED"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "Groups", namespace = "http://javaops.ru")
@XmlEnum
public enum Groups {

    REGISTERING,
    CURRENT,
    FINISHED;

    public String value() {
        return name();
    }

    public static Groups fromValue(String v) {
        return valueOf(v);
    }

}
