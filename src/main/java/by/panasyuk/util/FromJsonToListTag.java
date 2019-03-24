package by.panasyuk.util;

import by.panasyuk.domain.Item;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.lang.reflect.Type;
import java.util.List;

public class FromJsonToListTag extends TagSupport {
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
        Type listType = new TypeToken<List<Item>>(){}.getType();
        try {
            List<Item> drugList = gson.fromJson(jsonString, listType);
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
