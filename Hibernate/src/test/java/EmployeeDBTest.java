import com.epam.darafeyeu.dao.*;
import com.epam.darafeyeu.domain.EmployeeStatus;
import com.epam.darafeyeu.domain.Project;
import com.epam.darafeyeu.domain.Unit;
import com.epam.darafeyeu.persistence.HibernateUtil;
import org.hibernate.Session;
import org.junit.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by xdar on 8.12.14.
 */
public class EmployeeDBTest {

    static EmployeeDAO employeeDAO;
    static UnitDAO unitDAO;
    static ProjectDAO projectDAO;

    @BeforeClass
    public static void setUpClass(){
         employeeDAO = new EmployeeDAOImpl();
         unitDAO = new UnitDAOImpl();
         projectDAO = new ProjectDAOImpl();
    }

    @Test
    public void createEmployeeTest(){

        Integer employeeId = employeeDAO.createEmployee("Alex Dorofeev", "Brest",
                "Morozova", EmployeeStatus.MIDDLE);


        assertNotNull("No employee after insert!", employeeDAO.findEmployeeById(employeeId));

        employeeDAO.updateEmployeeById(employeeId, "Mike", "Brest",
                "Urogajnaya", EmployeeStatus.SENIOR);

        assertEquals("No changes after update!", "Mike", employeeDAO.findEmployeeById(employeeId).getName());


        Integer unitId = unitDAO.createUnit("UnitName 1");
        employeeDAO.addEmployeeToUnit(employeeId, unitId);

        Unit unit = employeeDAO.findEmployeeById(employeeId).getUnit();

        assertNotNull(unit);
        assertEquals("No changes after update!", "UnitName 1", unit.getName());

        Integer projectId1 = projectDAO.createProject("IHG");
        Integer projectId2 = projectDAO.createProject("Rights Link");

        employeeDAO.assignEmployeeForProject(employeeId, projectId1);
        employeeDAO.assignEmployeeForProject(employeeId, projectId2);


        List<Project> projects = new ArrayList<Project>(employeeDAO.findEmployeeById(employeeId).getProjects());

        assertEquals("Number of project is wrong!", 2, projects.size());
        assertEquals("Wrong project name!", "IHG", projects.get(0).getName());
        assertEquals("Wrong project name!", "Rights Link", projects.get(1).getName());

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.close();
        HibernateUtil.shutdown();

    }

}
