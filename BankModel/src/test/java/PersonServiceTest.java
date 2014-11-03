import com.epam.darafeyeu.Person;
import com.epam.darafeyeu.dao.PersonDAOImpl;
import com.epam.darafeyeu.exceptions.PersonNotFoundException;
import com.epam.darafeyeu.services.PersonService;
import com.epam.darafeyeu.utils.BankModelUtils;

import org.apache.log4j.Logger;

import org.junit.*;
import static org.junit.Assert.*;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 *
 */

public class PersonServiceTest  {

    static final Logger logger = Logger.getLogger(PersonServiceTest.class);
    static final String FILENAME = "testData.dat";

    PersonService personService = null;
    ObjectOutputStream objectOutputStream = null;

    @Before
    public void setUp(){

        logger.info("setUp");

        //clear file with objects before every test in order to make tests more transparent
        try {
            objectOutputStream = BankModelUtils.getObjectOutputStream(FILENAME);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            BankModelUtils.closeObjectOutputStream(objectOutputStream);
        }

        personService = new PersonService(new PersonDAOImpl("testData.dat"));
    }


    @Test
    public void addPersonTest(){

        logger.info("addPersonTest");

        personService.addPerson( new Person(10,"TestFirstName","TestLastName") );

        Person person = null;
        try {
             person = personService.getPerson(10);
        } catch (PersonNotFoundException e) {
            logger.error(e.getMessage());
            fail();
        }

        assertEquals("FirstName should be 'TestFirstName'", person.getFirstName(), "TestFirstName");
    }

    @Test (expected =  PersonNotFoundException.class)
    public void personNotFoundExceptionTest() throws PersonNotFoundException{
        logger.info("personNotFoundExceptionTest");
        personService.getPerson(99999);
    }

    @Test
    public void getAllSortedPersonsTest(){

        logger.info("getAllPersonsTest");

        personService.addPerson( new Person(2,"TestFirstName1","TestLastName1") );
        personService.addPerson( new Person(1,"TestFirstName2","TestLastName2") );
        personService.addPerson( new Person(3,"TestFirstName3","TestLastName3") );

        List<Person> persons = personService.getAllPersons();

        assertEquals(persons.size(),3);
        assertEquals(persons.get(0).getId().intValue(),1);
        assertEquals(persons.get(1).getId().intValue(),2);
        assertEquals(persons.get(2).getId().intValue(),3);
    }

    @Test (expected =  PersonNotFoundException.class)
    public void deletePersonTest() throws PersonNotFoundException{

        logger.info("deletePersonTest");

        personService.addPerson( new Person(50,"TestFirstName1","TestLastName1") );

        personService.deletePerson(50);

        //exception should be thrown here
        personService.getPerson(50);
    }
}