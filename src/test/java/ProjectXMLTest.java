import org.junit.Test;
import ru.javaops.masterjava.xml.schema.Groups;
import ru.javaops.masterjava.xml.schema.Projects;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;

/**
 * Created by ilnur on 29.09.16.
 */
public class ProjectXMLTest {
    @Test
    public void shouldMarshallAProject() throws JAXBException{
        Groups generalGroup = new Groups("generalGroup", Groups.type.CURRENT);
        Projects projects = new Projects("nameOfPrjcts", "Dscrptn", generalGroup);
        StringWriter stringWriter = new StringWriter();
        JAXBContext context = JAXBContext.newInstance(Projects.class);
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        m.marshal(projects, stringWriter);
        System.out.println(stringWriter);
    }
}
