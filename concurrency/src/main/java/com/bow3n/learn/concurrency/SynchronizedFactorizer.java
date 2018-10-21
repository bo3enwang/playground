package com.bow3n.learn.concurrency;

import javax.servlet.*;
import java.io.IOException;
import java.math.BigInteger;

public class SynchronizedFactorizer implements Servlet {

    private BigInteger lastNumber;
    private BigInteger[] lastFactors;


    @Override
    public void init(ServletConfig config) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        BigInteger i = extractFromRequest(req);
        if(i.equals(lastNumber)){
            res.flushBuffer();
        }else {
            BigInteger[] factors = factor(i);
            lastNumber = i;
            lastFactors = factors;
            res.flushBuffer();
        }
    }

    private BigInteger[] factor(BigInteger i) {
        return new BigInteger[0];
    }


    private BigInteger extractFromRequest(ServletRequest req) {
        return (BigInteger) req.getAttribute("i");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
