package by.panasyuk.util;

import by.panasyuk.domain.Item;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.util.List;

public class TotalPriceTag extends TagSupport {
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
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        int price = itemList.stream()
                .mapToInt((item) -> item.getDrug().getPrice() * item.getAmount())
                .sum();
        request.setAttribute(var, price);
        return SKIP_BODY;

    }
}