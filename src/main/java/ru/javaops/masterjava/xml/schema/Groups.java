package ru.javaops.masterjava.xml.schema;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by ilnur on 29.09.16.
 */
public class Groups {
    private String name;
    @XmlType
    @XmlEnum(String.class)
    public enum type{
        @XmlEnumValue("REGISTERING") REGISTERING,
        @XmlEnumValue("CURRENT") CURRENT,
        @XmlEnumValue("FINISHED") FINISHED
    }
}
