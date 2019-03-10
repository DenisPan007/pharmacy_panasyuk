package by.panasyuk.util;


import javax.naming.Context;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class CookieTag extends TagSupport {
    private String var;
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public void setVar(String var) {
        this.var = var;
    }

    @Override
    public int doStartTag() throws JspException {
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        Cookie[] cookies = request.getCookies();
        String cookie = CookieFinder.getValueByName(name, cookies).orElse(null);
        try {
            if (cookie != null) {
                String cookieDecoding = URLDecoder.decode(cookie, "UTF-8");
                request.setAttribute(var, cookieDecoding);
            } else {
                request.setAttribute(var, null);
            }
            return SKIP_BODY;
        } catch (UnsupportedEncodingException e) {
            throw new JspException(e);
        }
    }
}
