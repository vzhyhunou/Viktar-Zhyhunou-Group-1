import org.shop.api.impl.UserServiceImpl;
import org.shop.data.User;
import org.shop.repository.factory.UserRepositoryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import org.junit.runner.RunWith;
import org.junit.*;
import static org.junit.Assert.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by xdar on 12.12.14.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-context.xml"})
public class UserServiceTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void userServiceTest(){

        UserServiceImpl userServiceImpl = (UserServiceImpl) applicationContext.getBean("userServiceImpl");

        assertNotNull("error getting userMapRepository bean!", userServiceImpl);

        User user = new User();
        user.setUsername("Alex Dorofeev");

        userServiceImpl.registerUser(user);
        User user2 = userServiceImpl.getUserById(1l);

        assertNotNull( "User is Null!", user2 );
        assertEquals( "Wrong user name!","Alex Dorofeev", user2.getUsername() );

    }
}
