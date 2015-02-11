package web;

import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;
import com.mysql.jdbc.jdbc2.optional.MysqlXid;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.XAConnection;
import javax.sql.XADataSource;
import javax.transaction.xa.XAException;
import javax.transaction.xa.XAResource;
import javax.transaction.xa.Xid;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by Alex on 23.01.15.
 */
@WebServlet(description = "EnterpriseDesignPatternServlet", urlPatterns = {"/EDPServlet", "/EDPServlet.do"})
public class MessageProducerServlet extends HttpServlet {

    Context ic = null;
    XAResource xaRes1;
    XAConnection xaConn1;
    Connection conn1;
    MysqlXADataSource mysqlXADataSource;

    public void init() throws ServletException {

        super.init();

        mysqlXADataSource = new MysqlXADataSource();
        mysqlXADataSource.setUrl("jdbc:mysql://localhost:3306/mashdb");
        mysqlXADataSource.setPinGlobalTxToPhysicalConnection(true);
        mysqlXADataSource.setRollbackOnPooledClose(true);
        mysqlXADataSource.setUser("root");
        mysqlXADataSource.setPassword("ttt");

        try {
            xaConn1 = mysqlXADataSource.getXAConnection();
            xaRes1 = xaConn1.getXAResource();
            conn1 = xaConn1.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //transaction identifier
        //        global transaction ID,         branch qualifier ID).
        Xid xid = new MysqlXid(new byte[]{0x01}, new byte[]{0x02}, 100);

        //prepare audit data
        String user = mysqlXADataSource.getUser();
        Date date = new Date();

        try {

            //2 phase commit example
            xaRes1.start(xid, XAResource.TMNOFLAGS);
            conn1.createStatement().executeUpdate("insert into " +
                    "mashdb.ticket values (null,'terminator',100,'" + user + '/' + date + "')");
            xaRes1.end(xid, XAResource.TMSUCCESS);

            //second transaction branch joins to first
            xaRes1.start(xid, XAResource.TMJOIN);
            conn1.createStatement().executeUpdate("insert into " +
                    "mashdb.ticket values (null,'titanic',120,'" + user + '/' + date + "')");
            xaRes1.end(xid, XAResource.TMSUCCESS);

            xaRes1.prepare(xid);
            xaRes1.commit(xid, false);
            //xaRes1.rollback(xid);

        } catch (XAException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("WEB-INF/jsps/input.jsp").forward(request, response);
    }


    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        if (xaConn1 != null) {
            xaConn1.close();
        }
        if (conn1 != null) {
           conn1.close();
        }
    }

    private static Context getContext() throws NamingException {
//        Hashtable<String, Object> p = new Hashtable<String, Object>();
//        p.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        final Context context = new InitialContext();
        return context;
    }



}
