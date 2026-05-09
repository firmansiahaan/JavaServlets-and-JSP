package murach.http;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/downloadFile")
public class DownloadFileServlet extends HttpServlet
{   
	private static final long serialVersionUID = 1L;

	@Override
    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException
    {        
        ServletContext sc = getServletContext();
        String path = sc.getRealPath("static/");
        String name = request.getParameter("name");
        
        response.setContentType("application/zip");
        response.setHeader("content-disposition", 
            "attachment; filename=" + name);
        
        FileInputStream in = new FileInputStream(path + "/" + name);
        PrintWriter out = response.getWriter();
        
        int i = in.read();
        while (i != -1)
        {
            out.write(i);
            i = in.read();
        }
        in.close();
        out.close();
    }    
}