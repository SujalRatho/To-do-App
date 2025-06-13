<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add New Todo</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="container">
        <header>
            <h1>Add New Todo</h1>
            <a href="todos" class="btn btn-secondary">Back to List</a>
        </header>

        <main>
            <form action="todos" method="post" class="todo-form">
                <input type="hidden" name="action" value="insert">

                <div class="form-group">
                    <label for="title">Title *</label>
                    <input type="text" id="title" name="title" required
                           placeholder="Enter todo title">
                </div>

                <div class="form-group">
                    <label for="description">Description</label>
                    <textarea id="description" name="description" rows="4"
                              placeholder="Enter todo description (optional)"></textarea>
                </div>

                <div class="form-actions">
                    <button type="submit" class="btn btn-primary">Add Todo</button>
                    <a href="todos" class="btn btn-secondary">Cancel</a>
                </div>
            </form>
        </main>
    </div>
</body>
</html>