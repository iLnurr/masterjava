import org.junit.Test;
import ru.javaops.masterjava.xml.schema.Groups;
import ru.javaops.masterjava.xml.schema.Projects;

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
    String projectXML = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
            "<Projects groups=\"CURRENT\" xmlns=\"http://javaops.ru\">\n" +
            "    <name>nm</name>\n" +
            "    <description>dscrptn</description>\n" +
            "</Projects>";
    @Test
    public void shouldMarshallAProject() throws JAXBException{
        Projects project = new Projects();
        project.setName("nm");
        project.setDescription("dscrptn");
        project.setGroups(Groups.CURRENT);
        StringWriter stringWriter = new StringWriter();
        JAXBContext context = JAXBContext.newInstance(Projects.class);
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        m.marshal(project, stringWriter);
        System.out.println(stringWriter);

        assertEquals(projectXML, stringWriter.toString().trim());
    }

    @Test
    public void shouldUnmarshallProject() throws JAXBException{
        StringReader reader = new StringReader(projectXML);
        JAXBContext context = JAXBContext.newInstance(Projects.class);
        Unmarshaller u = context.createUnmarshaller();
        Projects project = (Projects) u.unmarshal(reader);

        assertEquals("nm", project.getName());
        assertEquals("dscrptn", project.getDescription());
        assertEquals(Groups.CURRENT, project.getGroups());

    }
}
