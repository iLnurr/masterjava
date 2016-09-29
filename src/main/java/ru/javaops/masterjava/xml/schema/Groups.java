package ru.javaops.masterjava.xml.schema;

import javax.xml.bind.annotation.*;

/**
 * Created by ilnur on 29.09.16.
 */
@XmlRootElement
public class Groups {
    @XmlElement
    private String name;

    @XmlType
    @XmlEnum(String.class)
    public enum type{
        @XmlEnumValue("REGISTERING") REGISTERING,
        @XmlEnumValue("CURRENT") CURRENT,
        @XmlEnumValue("FINISHED") FINISHED
    }
}
