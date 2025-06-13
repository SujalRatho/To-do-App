# Todo App - JSP/Servlet/JDBC/PostgreSQL

A simple yet complete todo application built with Java web technologies following the MVC (Model-View-Controller) architecture pattern.

## 🚀 Features

- ✅ **View all todos** - Display todos in a clean, responsive grid layout
- ➕ **Add new todos** - Create todos with title and description
- ✏️ **Edit existing todos** - Update todo details and mark as completed
- 🗑️ **Delete todos** - Remove todos with confirmation
- 📱 **Responsive design** - Works seamlessly on desktop and mobile devices
- 💾 **Persistent storage** - All data stored in PostgreSQL database

## 🛠️ Technology Stack

- **Backend:** Java Servlets, JSP, JDBC
- **Frontend:** HTML5, CSS3, JSTL
- **Database:** PostgreSQL
- **Build Tool:** Maven
- **Server:** Apache Tomcat (or any Servlet container)

## 📋 Prerequisites

Before running this application, ensure you have the following installed:

- **Java 11** or higher
- **Maven 3.6+**
- **PostgreSQL 12+**
- **Apache Tomcat 9+** (or any servlet container)

## 🔧 Installation & Setup

### 1. Clone the Repository
```bash
git clone <repository-url>
cd todo-app
```

### 2. Database Setup

1. **Install PostgreSQL** and start the service
2. **Create database and user:**
   ```sql
   -- Connect to PostgreSQL as superuser
   CREATE DATABASE todoapp;
   CREATE USER todouser WITH PASSWORD 'todopass';
   GRANT ALL PRIVILEGES ON DATABASE todoapp TO todouser;
   ```

3. **Connect to the todoapp database and create table:**
   ```sql
   \c todoapp
   
   CREATE TABLE todos (
       id SERIAL PRIMARY KEY,
       title VARCHAR(255) NOT NULL,
       description TEXT,
       completed BOOLEAN DEFAULT FALSE,
       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
   );
   
   -- Insert sample data (optional)
   INSERT INTO todos (title, description) VALUES 
   ('Learn JSP', 'Study JSP fundamentals and best practices'),
   ('Build Todo App', 'Create a complete todo application using JSP/Servlet'),
   ('Database Integration', 'Connect to PostgreSQL using JDBC');
   ```

### 3. Configure Database Connection

Update the database connection settings in `src/main/java/com/todoapp/util/DatabaseConnection.java`:

```java
private static final String URL = "jdbc:postgresql://localhost:5432/todoapp";
private static final String USERNAME = "todouser";
private static final String PASSWORD = "todopass";
```

### 4. Build the Application

```bash
mvn clean compile
mvn package
```

### 5. Deploy to Tomcat

1. Copy the generated WAR file from `target/todo-app-1.0.0.war` to your Tomcat's `webapps` directory
2. Start Tomcat server
3. Access the application at: `http://localhost:8080/todo-app-1.0.0/`

## 📁 Project Structure

```
todo-app/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/todoapp/
│       │       ├── model/
│       │       │   └── Todo.java              # Data model
│       │       ├── dao/
│       │       │   └── TodoDAO.java           # Data access layer
│       │       ├── servlet/
│       │       │   └── TodoServlet.java       # Controller
│       │       └── util/
│       │           └── DatabaseConnection.java # DB utility
│       └── webapp/
│           ├── WEB-INF/
│           │   └── web.xml                    # Web configuration
│           ├── css/
│           │   └── style.css                  # Styling
│           ├── index.jsp                      # Main page
│           ├── add-todo.jsp                   # Add todo form
│           └── edit-todo.jsp                  # Edit todo form
├── pom.xml                                    # Maven configuration
└── README.md                                  # This file
```

## 🎯 How to Use

### Adding a Todo
1. Click **"Add New Todo"** button on the main page
2. Fill in the title (required) and description (optional)
3. Click **"Add Todo"** to save

### Editing a Todo
1. Click **"Edit"** button on any todo card
2. Modify the title, description, or completion status
3. Click **"Update Todo"** to save changes

### Deleting a Todo
1. Click **"Delete"** button on any todo card
2. Confirm the deletion in the popup dialog

## 🏗️ Architecture Overview

This application follows the **MVC (Model-View-Controller)** pattern:

- **Model (`Todo.java`)**: Represents the data structure
- **View (`*.jsp files`)**: Handles the presentation layer
- **Controller (`TodoServlet.java`)**: Manages user requests and business logic
- **DAO (`TodoDAO.java`)**: Handles database operations

### Request Flow
```
Browser → TodoServlet → TodoDAO → PostgreSQL
   ↑         ↓           ↓
   └─── JSP Pages ←──────┘
```

## 🔗 API Endpoints

| Method | URL | Action | Description |
|--------|-----|--------|-------------|
| GET | `/todos` | list | Display all todos |
| GET | `/todos?action=new` | new | Show add todo form |
| GET | `/todos?action=edit&id={id}` | edit | Show edit todo form |
| GET | `/todos?action=delete&id={id}` | delete | Delete specific todo |
| POST | `/todos?action=insert` | insert | Create new todo |
| POST | `/todos?action=update` | update | Update existing todo |

## 🎨 Customization

### Styling
- Modify `webapp/css/style.css` to change the appearance
- The design is responsive and uses CSS Grid for layout

### Database
- Update table schema in the SQL setup section
- Modify `TodoDAO.java` to match any schema changes
- Update `Todo.java` model if adding new fields

### Features
- Add categories or tags by extending the `Todo` model
- Implement user authentication by adding user management
- Add due dates, priorities, or attachments

## 🐛 Troubleshooting

### Common Issues

**Database Connection Error:**
- Verify PostgreSQL is running
- Check database credentials in `DatabaseConnection.java`
- Ensure the `todoapp` database exists

**ClassNotFoundException for PostgreSQL Driver:**
- Ensure PostgreSQL JDBC driver is in the classpath
- Check Maven dependencies in `pom.xml`

**404 Error on Deployment:**
- Verify the WAR file is properly deployed
- Check Tomcat is running
- Ensure the context path is correct

**Compilation Errors:**
- Verify Java 11+ is installed
- Run `mvn clean compile` to check for build issues
- Ensure all dependencies are resolved

## 📝 Development Notes

### Adding New Features
1. Update the `Todo` model if new fields are needed
2. Modify the database schema accordingly
3. Update `TodoDAO` methods for new operations
4. Add new servlet methods and JSP pages as needed

### Performance Considerations
- Consider implementing connection pooling for production use
- Add pagination for large todo lists
- Implement caching for frequently accessed data

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👥 Authors

- Your Name - Initial work

## 🙏 Acknowledgments

- Built with Java EE technologies
- Inspired by modern web development practices
- Thanks to the open-source community for tools and libraries used

---

**Happy Coding! 🚀**

For questions or issues, please open an issue in the repository or contact the maintainers.
