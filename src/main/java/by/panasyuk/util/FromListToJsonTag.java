package by.panasyuk.util;

import by.panasyuk.domain.Item;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.util.List;

public class FromListToJsonTag extends TagSupport {
    private List<Item> itemList;
    private String var;

    public void setVar(String var) {
        this.var = var;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    @Override
    public int doStartTag() throws JspException {
        Gson gson = new Gson();
        try {
            String itemListJson = gson.toJson(itemList);
            pageContext.getRequest().setAttribute(var,itemListJson);
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
