package by.panasyuk.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;


@RunWith(JUnit4.class)
public class ConnectionPoolTest {
    private static final Logger LOGGER = LogManager.getLogger(ConnectionPoolTest.class);
    private static final int N_THREADS = 10 ;
    private static final int POOL_CAPACITY = 5 ;
    @Test
    public void shouldGetConnection () throws InterruptedException, SQLException,ClassNotFoundException {
        ConnectionPool connectionPool = Mockito. spy (
                ConnectionPool.getInstance ());
        ExecutorService executorService = Executors. newFixedThreadPool (N_THREADS);
        Set< Integer > hashCodes = Collections. synchronizedSet ( new HashSet<>());
        IntStream. range ( 0 , N_THREADS). forEach (i -> executorService. submit (() -> {
            LOGGER. info ( "Try to get connection" );
            try ( Connection connection = connectionPool. getConnection ()) {
                LOGGER. info ( "working with connection..." );
                Thread. sleep ( 1_00L );
                Assert. assertTrue (connection instanceof Proxy);
                int hashCode = connection. hashCode ();
                hashCodes. add (hashCode);
                LOGGER. info ( "release connection: " + hashCode);
            } catch ( SQLException | IllegalStateException e ) {
                LOGGER. error (e);
            } catch ( InterruptedException e ) {
                LOGGER. error (e);
                throw new RuntimeException (e);
            }
        }));
        executorService. awaitTermination ( 4L , TimeUnit.SECONDS);
        Assert. assertEquals (POOL_CAPACITY, hashCodes. size ());
        Mockito. verify (((ConnectionPool) connectionPool),
                Mockito. times (N_THREADS)). releaseConnection (Mockito. any ());
    }
}