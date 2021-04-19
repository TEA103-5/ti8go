package taglib;

import java.io.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

import taglib.util.*;

/**
 * This class is a tag handler for custom action that replaces
 * HTML special characters in its body with the corresponding
 * HTML character entities.
 *
 * @author Hans Bergsten, Gefion software <hans@gefionsoftware.com>
 * @version 1.0
 */
public class EncodeHTMLTag extends BodyTagSupport {
    
    /**
     * Reads the body content, converts all HTML special characters
     * with the corresponding HTML character entities, and adds the
     * result to the response body.
     */
    public int doAfterBody() throws JspException {
        BodyContent bc = getBodyContent();
        JspWriter out = getPreviousOut();
        try {
            out.write(StringFormat.toHTMLString(bc.getString()));
        }
        catch (IOException e) {} // Ignore
        return SKIP_BODY;
    }
}