package ru.javaops.masterjava.xml.schema;

import javax.xml.bind.annotation.*;

/**
 * Created by ilnur on 29.09.16.
 */
@XmlRootElement
public class Projects {
    @XmlElement
    private String name;
    private String description;
}
