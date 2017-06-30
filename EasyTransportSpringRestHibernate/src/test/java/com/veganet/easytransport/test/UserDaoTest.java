/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import com.veganet.easytransport.dao.impl.UserDaoImpl;
import com.veganet.easytransport.entities.User;
import javax.annotation.Resource;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 *
 * @author asus
 */
@RunWith(SpringJUnit4ClassRunner.class)

@ContextConfiguration(locations = {"classpath:**/spring-servlet.xml"})
//@WebAppConfiguration

//@TransactionConfiguration(defaultRollback = true, transactionManager = "transactionManager")
public class UserDaoTest extends AbstractJUnit4SpringContextTests {


    @Autowired
    UserDaoImpl userDao;

    public UserDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testFindByOne() {
        System.out.println("start test");
        User user = userDao.findOne(1);
        System.out.println("end test" + userDao.findOne(1).getFirstName());
        assertEquals("Nesrine", user.getFirstName());
        System.out.println("end test");

    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
