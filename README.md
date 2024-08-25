# Task Management Backend

## API Endpoints

### Task Management

#### Create Task
- **URL:** `POST /api/tasks`
- **Description:** Creates a new task
- **Request Body:** JSON object with task details (e.g., title, description, due date)
- **Response:** Success or error message

#### Retrieve All Tasks
- **URL:** `GET /api/tasks`
- **Description:** Retrieves a list of all tasks
- **Response:** JSON array of tasks

#### Retrieve Specific Task
- **URL:** `GET /api/tasks/{id}`
- **Description:** Retrieves details of a specific task by its ID
- **Response:** JSON object of the task

#### Update Task
- **URL:** `PUT /api/tasks/{id}`
- **Description:** Updates details of an existing task
- **Request Body:** JSON object with updated task details
- **Response:** Success or error message

#### Delete Task
- **URL:** `DELETE /api/tasks/{id}`
- **Description:** Deletes a specific task by its ID
- **Response:** Success or error message

### User Authentication

#### User Login
- **URL:** `POST /api/auth/login`
- **Description:** Authenticates a user and returns a token
- **Request Body:** JSON object with username and password
- **Response:** JSON object with authentication token

#### User Registration
- **URL:** `POST /api/auth/register`
- **Description:** Registers a new user
- **Request Body:** JSON object with user details (e.g., username, password)
- **Response:** Success or error message

