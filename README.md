# JavaServlets-and-JSP

Java Servlets and JSP (JavaServer Pages) are two core technologies in the Java EE (Jakarta EE) ecosystem for building dynamic web applications. Let me break them down step by step so you can see how they fit together:

### 🌐 Java Servlets
- **Definition**: Servlets are Java classes that run on a web server and handle HTTP requests and responses.
- **Role**: They act as controllers in a web application, processing input, managing business logic, and sending output back to the client.
- **Lifecycle**:
  1. **Initialization** (`init()` method)
  2. **Request handling** (`service()` or `doGet()` / `doPost()`)
  3. **Destruction** (`destroy()`)
- **Example**: A servlet might take form data from a user, process it, and return a confirmation page.

### 📄 JSP (JavaServer Pages)
- **Definition**: JSP is a technology that allows embedding Java code directly into HTML pages.
- **Role**: It’s mainly used for the **view layer**—displaying dynamic content to users.
- **How it works**: 
  - A JSP file is compiled into a servlet by the server.
  - This means JSP is essentially a more convenient way to write servlets when the focus is on presentation.
- **Example**: A JSP page might display a list of products retrieved from a database.

### 🔗 How They Work Together
- **Servlets**: Handle the heavy lifting—business logic, database access, request routing.
- **JSP**: Handle the presentation—formatting the data into HTML for the user.
- **MVC Pattern**: Often, servlets act as controllers, JSPs as views, and JavaBeans or other classes as the model.

### ⚖️ Comparison
| Feature            | Servlets                          | JSP                              |
|--------------------|-----------------------------------|----------------------------------|
| Focus              | Logic & control                   | Presentation (UI)                |
| Syntax             | Pure Java code                    | HTML + Java code (scriptlets, EL)|
| Compilation        | Written as Java classes           | Compiled into servlets           |
| Best Use           | Request handling, routing, logic  | Dynamic web page rendering       |

If you’re starting out, a good workflow is:
1. Use **Servlets** for handling requests and preparing data.
2. Pass that data to **JSPs** for rendering.
3. Keep Java code out of JSPs as much as possible by using JSTL (JSP Standard Tag Library) or Expression Language (EL).

Perfect! Let’s walk through a **Hello World** example using both a **Servlet** and a **JSP** so you can clearly see the difference.

---

### 🛠️ Example 1: Hello World with a Servlet
```java
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class HelloServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        out.println("<html><body>");
        out.println("<h1>Hello World from Servlet!</h1>");
        out.println("</body></html>");
    }
}
```

- **What happens**: When you access this servlet via a URL (e.g., `/HelloServlet`), it generates HTML dynamically and sends it back to the browser.

---

### 📄 Example 2: Hello World with JSP
```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<html>
  <body>
    <h1>Hello World from JSP!</h1>
  </body>
</html>
```

- **What happens**: When you access this JSP file (e.g., `hello.jsp`), the server compiles it into a servlet behind the scenes. It’s simpler for presentation because you can mix HTML with Java if needed.

---

### 🔗 Putting Them Together (Servlet + JSP)
A common pattern is:
1. **Servlet** prepares data (e.g., user name).
2. **Servlet** forwards the request to a JSP.
3. **JSP** displays the data.

**Servlet Example:**
```java
public class HelloController extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String message = "Hello World from Servlet to JSP!";
        request.setAttribute("msg", message);
        
        RequestDispatcher rd = request.getRequestDispatcher("hello.jsp");
        rd.forward(request, response);
    }
}
```

**JSP Example (`hello.jsp`):**
```jsp
<html>
  <body>
    <h1>${msg}</h1>
  </body>
</html>
```

Here, the servlet sets a message, and the JSP displays it using **Expression Language (EL)**.

---

Here’s the workflow diagram you asked for—showing how a request flows from the browser, through the servlet, into the JSP, and back to the browser:

`https://copilot.microsoft.com/th/id/BCO.b0787d63-32ce-4217-858d-540763797dc8.png`

---

### 🔎 Step-by-Step Flow
1. **Browser Request** → User enters `/HelloController`.
2. **Servlet (Controller)** → Processes the request, sets a message, and forwards it to `hello.jsp`.
3. **JSP (View)** → Renders the message into HTML using `${msg}`.
4. **Browser Response** → Displays the final page: *Hello World from Servlet to JSP!*.

This is the classic **MVC pattern** in action:  
- **Model**: Data (e.g., message, database results).  
- **View**: JSP page that displays the data.  
- **Controller**: Servlet that decides what to do with the request.  

---

Excellent choice, Firman — moving from “Hello World” to a **real-world Servlet + JSP example** will help you see how data flows in a practical app. Let’s build a **mini login form** example that demonstrates user input, servlet processing, and JSP output.

---

### 🧩 Step 1: `login.jsp` — The Form (View)
```jsp
<html>
  <body>
    <h2>Login Page</h2>
    <form action="LoginServlet" method="post">
      Username: <input type="text" name="username"><br>
      Password: <input type="password" name="password"><br>
      <input type="submit" value="Login">
    </form>
  </body>
</html>
```
- This page collects user input and sends it to `LoginServlet`.

---

### ⚙️ Step 2: `LoginServlet.java` — The Controller
```java
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String user = request.getParameter("username");
        String pass = request.getParameter("password");

        if ("admin".equals(user) && "1234".equals(pass)) {
            request.setAttribute("message", "Welcome, " + user + "!");
        } else {
            request.setAttribute("message", "Invalid credentials. Try again.");
        }

        RequestDispatcher rd = request.getRequestDispatcher("result.jsp");
        rd.forward(request, response);
    }
}
```
- The servlet reads form data, checks credentials, and forwards the result to `result.jsp`.

---

### 🖥️ Step 3: `result.jsp` — The Output (View)
```jsp
<html>
  <body>
    <h2>${message}</h2>
    <a href="login.jsp">Back to Login</a>
  </body>
</html>
```
- Displays the message set by the servlet using Expression Language (`${message}`).

---

### 🔄 Workflow Summary
1. User fills out the form in `login.jsp`.
2. Form data is sent to `LoginServlet`.
3. Servlet validates input and forwards to `result.jsp`.
4. JSP displays the result dynamically.

---

This pattern scales beautifully — you can later connect it to a **database**, add **session management**, or integrate **JSTL tags** for cleaner logic.

Excellent — let’s upgrade your login example to use **JDBC** so it validates credentials from a **real database** instead of hardcoded values. This will make it feel like a true web app.

---

### 🧩 Step 1: Database Setup
Let’s assume you have a **MySQL** database named `userdb` with a table `users`:

```sql
CREATE DATABASE userdb;
USE userdb;

CREATE TABLE users (
  id INT AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(50),
  password VARCHAR(50)
);

INSERT INTO users (username, password) VALUES ('admin', '1234');
```

---

### ⚙️ Step 2: JDBC Configuration
Add the MySQL JDBC driver to your project (for example, `mysql-connector-j.jar`).

In your `web.xml`, define database connection parameters:
```xml
<context-param>
  <param-name>jdbcURL</param-name>
  <param-value>jdbc:mysql://localhost:3306/userdb</param-value>
</context-param>
<context-param>
  <param-name>jdbcUsername</param-name>
  <param-value>root</param-value>
</context-param>
<context-param>
  <param-name>jdbcPassword</param-name>
  <param-value>yourpassword</param-value>
</context-param>
```

---

### 🧠 Step 3: `LoginServlet.java`
```java
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class LoginServlet extends HttpServlet {
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;

    public void init() {
        ServletContext context = getServletContext();
        jdbcURL = context.getInitParameter("jdbcURL");
        jdbcUsername = context.getInitParameter("jdbcUsername");
        jdbcPassword = context.getInitParameter("jdbcPassword");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String user = request.getParameter("username");
        String pass = request.getParameter("password");
        String message;

        try (Connection conn = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE username=? AND password=?")) {
            
            stmt.setString(1, user);
            stmt.setString(2, pass);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                message = "Welcome, " + user + "!";
            } else {
                message = "Invalid credentials. Try again.";
            }
        } catch (SQLException e) {
            message = "Database error: " + e.getMessage();
        }

        request.setAttribute("message", message);
        RequestDispatcher rd = request.getRequestDispatcher("result.jsp");
        rd.forward(request, response);
    }
}
```

---

### 📄 Step 4: `result.jsp`
```jsp
<html>
  <body>
    <h2>${message}</h2>
    <a href="login.jsp">Back to Login</a>
  </body>
</html>
```

---

### 🔄 Flow Summary
1. User submits login form.  
2. Servlet connects to MySQL via JDBC.  
3. It checks credentials in the `users` table.  
4. JSP displays success or failure message.

---

This setup is a foundation for **secure authentication**.  
Next, you could:
- Hash passwords using **SHA-256 or bcrypt**.
- Add **session management** to keep users logged in.
- Use **connection pooling** for performance.

Great, Firman — let’s make your login system **production-ready** by adding two critical features: **password hashing** and **session management**.  

---

### 🔒 Step 1: Password Hashing (Security)
Instead of storing plain text passwords, we hash them. A common choice is **SHA-256** or **bcrypt**. Here’s a simple SHA-256 example:

**Utility Class: `PasswordUtil.java`**
```java
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordUtil {
    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }
}
```

**Database Insert Example:**
```sql
INSERT INTO users (username, password) VALUES ('admin', 'hash_of_1234');
```
(You’d generate the hash in Java before inserting.)

---

### 🧑‍💻 Step 2: LoginServlet with Hashing
```java
String user = request.getParameter("username");
String pass = request.getParameter("password");

// Hash the input password
String hashedPass = PasswordUtil.hashPassword(pass);

PreparedStatement stmt = conn.prepareStatement(
    "SELECT * FROM users WHERE username=? AND password=?");
stmt.setString(1, user);
stmt.setString(2, hashedPass);
ResultSet rs = stmt.executeQuery();
```

---

### 🗝️ Step 3: Session Management
Once a user logs in successfully, create a session to keep them authenticated.

```java
if (rs.next()) {
    HttpSession session = request.getSession();
    session.setAttribute("username", user);
    message = "Welcome, " + user + "! You are now logged in.";
} else {
    message = "Invalid credentials. Try again.";
}
```

---

### 📄 Step 4: Protecting Pages
In your JSPs or servlets, check if the session exists:

```java
HttpSession session = request.getSession(false);
if (session == null || session.getAttribute("username") == null) {
    response.sendRedirect("login.jsp");
    return;
}
```

This ensures only logged-in users can access protected pages.

---

### 🔄 Flow Summary
1. User enters credentials.  
2. Servlet hashes the password and checks against the database.  
3. If valid → create a session.  
4. JSPs/servlets check session before allowing access.  

---

Perfect, Firman — let’s extend your login system with **logout functionality** and **role-based access control** so you can manage multiple user types like *admin* and *regular users*.  

---

### 🧩 Step 1: Database Update
Add a `role` column to your `users` table:

```sql
ALTER TABLE users ADD role VARCHAR(20);

-- Example users
INSERT INTO users (username, password, role) VALUES ('admin', 'hashed_admin_pass', 'ADMIN');
INSERT INTO users (username, password, role) VALUES ('john', 'hashed_john_pass', 'USER');
```

---

### ⚙️ Step 2: LoginServlet with Roles
```java
if (rs.next()) {
    String role = rs.getString("role");

    HttpSession session = request.getSession();
    session.setAttribute("username", user);
    session.setAttribute("role", role);

    if ("ADMIN".equals(role)) {
        response.sendRedirect("admin.jsp");
    } else {
        response.sendRedirect("user.jsp");
    }
} else {
    request.setAttribute("message", "Invalid credentials. Try again.");
    RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
    rd.forward(request, response);
}
```

- After successful login, the servlet stores both `username` and `role` in the session.
- Redirects users to different pages depending on their role.

---

### 📄 Step 3: Role-Based JSP Pages
**`admin.jsp`**
```jsp
<%@ page session="true" %>
<%
    String role = (String) session.getAttribute("role");
    if (!"ADMIN".equals(role)) {
        response.sendRedirect("login.jsp");
    }
%>
<html>
  <body>
    <h2>Admin Dashboard</h2>
    <p>Welcome, ${username}. You have admin privileges.</p>
    <a href="LogoutServlet">Logout</a>
  </body>
</html>
```

**`user.jsp`**
```jsp
<%@ page session="true" %>
<html>
  <body>
    <h2>User Dashboard</h2>
    <p>Welcome, ${username}. You are logged in as a regular user.</p>
    <a href="LogoutServlet">Logout</a>
  </body>
</html>
```

---

### 🚪 Step 4: LogoutServlet
```java
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class LogoutServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); // destroy session
        }
        response.sendRedirect("login.jsp");
    }
}
```

---

### 🔄 Flow Summary
1. User logs in → Servlet checks credentials + role.  
2. Session stores `username` + `role`.  
3. Redirects to `admin.jsp` or `user.jsp`.  
4. LogoutServlet invalidates session → back to login page.  

---

This gives you a **secure login system with role-based access control**.  
