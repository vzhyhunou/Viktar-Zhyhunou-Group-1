package com.epam.ear.web;

/**
 * Created by xdar on 27.1.15.
 */
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class ServletTest extends Mockito {

    @Mock
    private TopicSession topicSession;

    @Mock
    private TopicPublisher topicPublisher;

    @InjectMocks
    private MessageProducerServlet messageProducerServlet;


    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testMessageProducerServlet() throws Exception {

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);

        when( request.getRequestDispatcher( anyString() ) ).thenReturn( requestDispatcher );
        messageProducerServlet.doGet(request, response);

        verify(request, times(1) ).getParameter( "name" );
    }
}