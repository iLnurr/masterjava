import org.junit.Test;
import ru.javaops.masterjava.xml.schema.*;
import ru.javaops.masterjava.xml.schema.Group;
import ru.javaops.masterjava.xml.schema.Project;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

import static org.junit.Assert.assertEquals;

/**
 * Created by ilnur on 29.09.16.
 */
public class ProjectXMLTest {
    String payloadXML = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
            "<Payload xmlns=\"http://javaops.ru\">\n" +
            "    <Cities>\n" +
            "        <City id=\"spb\">Санкт-Петербург</City>\n" +
            "        <City id=\"msk\">Москва</City>\n" +
            "    </Cities>\n" +
            "    <Users>\n" +
            "        <User flag=\"active\" city=\"spb\">\n" +
            "            <email>a@mail.ru</email>\n" +
            "            <fullName>ivanov i.i.</fullName>\n" +
            "            <Group groupType=\"CURRENT\">\n" +
            "                <name>topjava 05.2016</name>\n" +
            "            </Group>\n" +
            "        </User>\n" +
            "    </Users>\n" +
            "    <Projects>\n" +
            "        <Project>\n" +
            "            <name>topjava</name>\n" +
            "            <description>JavaWeb</description>\n" +
            "            <Groups>\n" +
            "                <Group groupType=\"CURRENT\">\n" +
            "                    <name>topjava 05.2016</name>\n" +
            "                </Group>\n" +
            "                <Group groupType=\"CURRENT\">\n" +
            "                    <name>topjava 09.2016</name>\n" +
            "                </Group>\n" +
            "            </Groups>\n" +
            "        </Project>\n" +
            "    </Projects>\n" +
            "</Payload>";
    @Test
    public void shouldMarshallAProject() throws JAXBException{
        Payload payload = new Payload();

        Payload.Cities cities = new Payload.Cities();
        Payload.Users users = new Payload.Users();
        Payload.Projects projects = new Payload.Projects();
        Project.Groups groups = new Project.Groups();

        Group group = new Group();
        group.setName("topjava 05.2016");
        group.setGroupType(GroupType.CURRENT);
        Group groupNext = new Group();
        groupNext.setName("topjava 09.2016");
        groupNext.setGroupType(GroupType.CURRENT);

        CityType spb = new CityType();
        spb.setId("spb");
        spb.setValue("Санкт-Петербург");
        CityType msk = new CityType();
        msk.setId("msk");
        msk.setValue("Москва");

        User user = new User();
        user.setEmail("a@mail.ru");
        user.setFullName("ivanov i.i.");
        user.setCity(spb);
        user.setFlag(FlagType.ACTIVE);
        user.setGroup(group);

        Project project = new Project();
        project.setName("topjava");
        project.setDescription("JavaWeb");

        groups.getGroup().add(group);
        groups.getGroup().add(groupNext);
        cities.getCity().add(spb);
        cities.getCity().add(msk);
        users.getUser().add(user);
        projects.getProject().add(project);

        project.setGroups(groups);
        payload.setCities(cities);
        payload.setUsers(users);
        payload.setProjects(projects);

        StringWriter stringWriter = new StringWriter();
        JAXBContext context = JAXBContext.newInstance(Payload.class);
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        m.marshal(payload, stringWriter);
        System.out.println(stringWriter);

        assertEquals(payloadXML, stringWriter.toString().trim());
    }

    @Test
    public void shouldUnmarshallProject() throws JAXBException{
        StringReader reader = new StringReader(payloadXML);
        JAXBContext context = JAXBContext.newInstance(Payload.class);
        Unmarshaller u = context.createUnmarshaller();
        Payload payload = (Payload) u.unmarshal(reader);
        Payload.Projects projects = new Payload.Projects();

//        assertEquals("nm", payload.getCities());
//        assertEquals("dscrptn", payload.getUsers());
//        assertEquals(projects, payload.getProjects());

    }
}
