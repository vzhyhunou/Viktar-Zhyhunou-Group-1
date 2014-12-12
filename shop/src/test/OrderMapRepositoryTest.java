import org.junit.*;
import org.junit.runner.RunWith;
import org.shop.data.*;

import org.shop.repository.map.OrderMapRepository;
import org.shop.repository.map.ProductMapRepository;
import org.shop.repository.map.ProposalMapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.shop.common.Products;

import static org.junit.Assert.*;


/**
 * Created by xdar on 11.12.14.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-context.xml"})
public class OrderMapRepositoryTest {

    @Autowired
    private OrderMapRepository orderMapRepository;

    @Autowired
    private ProductMapRepository productMapRepository;

    @Autowired
    private ProposalMapRepository proposalMapRepository;

    @Test
    public void orderMapRepoTest(){

        User user = new User();
        user.setUsername("Alex Dorofeev");

        Order order = new Order();
        order.setUser(user);

        orderMapRepository.createOrder(order);
        orderMapRepository.createOrder(order);

        Order order2 = orderMapRepository.getOrderById(26l);
        assertNotNull(order2);
        assertEquals( "Wrong User Name!", "Alex Dorofeev", order2.getUser().getUsername() );

        Order order3 = orderMapRepository.getOrderById(27l);
        assertNotNull(order3);

        Order order4 = orderMapRepository.getOrderById(28l);
        assertNull(order4);

        Product product = new Product();
        product.setName(Products.SAMSUNG_GALAXY_ACE);
        productMapRepository.createProduct(product);

        Proposal proposal = new Proposal();
        product.setName("proposal");
        proposalMapRepository.createProposal(proposal);

    }
}
