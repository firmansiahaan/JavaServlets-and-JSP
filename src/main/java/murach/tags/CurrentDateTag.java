package murach.tags;

import javax.servlet.jsp.*;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.*;

public class CurrentDateTag extends TagSupport {
	
	private static final long serialVersionUID = 1L;

	@Override
	public int doStartTag() throws JspException {
		Date currentDate = new Date();
		DateFormat shortDate = DateFormat.getDateInstance(DateFormat.SHORT);
		String currentDateFormatted = shortDate.format(currentDate);
		
		try {
			JspWriter out = pageContext.getOut();
			out.print(currentDateFormatted);
		} catch(IOException ioe) {
			System.out.println(ioe);
		}
		return SKIP_BODY;
	}
}
