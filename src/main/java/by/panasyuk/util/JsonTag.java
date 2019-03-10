package by.panasyuk.util;

import by.panasyuk.domain.Drug;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class JsonTag extends TagSupport {
    private String jsonString;
    private String var;

    public void setVar(String var) {
        this.var = var;
    }

    public void setJsonString(String jsonString) {
        this.jsonString = jsonString;
    }

    @Override
    public int doStartTag() throws JspException {
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Drug>>(){}.getType();
        try {
            List<Drug> drugList = gson.fromJson(jsonString, listType);
            pageContext.getRequest().setAttribute(var,drugList);
        }catch (JsonSyntaxException e){
            pageContext.getRequest().setAttribute(var,null);
        }

        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        return super.doEndTag();
    }
}
