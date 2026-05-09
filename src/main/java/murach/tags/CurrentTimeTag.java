package murach.tags;

import javax.servlet.jsp.*;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.*;

public class CurrentTimeTag extends TagSupport {
	
	private static final long serialVersionUID = 1L;

	@Override
	public int doStartTag() throws JspException {
		Date currentTime = new Date();
		DateFormat timeFormat = DateFormat.getTimeInstance(DateFormat.SHORT);
		String currentDateFormatted = timeFormat.format(currentTime);
		
		try {
			JspWriter out = pageContext.getOut();
			out.print(currentDateFormatted);
		} catch(IOException ioe) {
			System.out.println(ioe);
		}
		return SKIP_BODY;
	}
}
