**AI Bug Tracker (Spring Boot + OpenAI)**
📖 **Overview**
AI Bug Tracker is a Spring Boot 3.2 application that integrates Generative AI (via Spring AI + OpenAI) to assist developers in analyzing bug reports and error logs.
It exposes REST APIs that classify bugs, suggest fixes, and provide structured responses for easy integration into issue management workflows.

🚀** Features**
Spring Boot backend (Java 21) with clean layered architecture.

AI integration using Spring AI + OpenAI GPT models.

**REST API endpoints:**

GET /ai/health → health check.

POST /ai/bug → analyze bug description or error log.

Structured JSON responses (BugResponse) including:

classification (e.g., UI bug, DB error, API issue).

suggestedFix (AI‑generated remediation steps).

Mock mode for local development/testing (ai.useMock=true).

Swagger/OpenAPI UI available at /swagger-ui.html.

Error handling with descriptive messages and consistent response format.

Configurable AI model (default: gpt-4o-mini).

🛠️ **Tech Stack**

Backend: Spring Boot 3.2.x, Java 21

AI: Spring AI 1.x, OpenAI GPT models

JSON serialization: Jackson 2.17.x

Testing: JUnit 5, MockMvc (optional for API tests)

Documentation: Swagger/OpenAPI

⚙️ **Setup & Configuration**

Clone the repository:

bash
git clone https://github.com/Sabiha-dev15/ai-bug-tracker.git
cd ai-bug-tracker
Add your OpenAI API key in src/main/resources/application.properties:

properties
spring.application.name=ai-bug-tracker-test
spring.ai.openai.api-key=${OPENAI_API_KEY}
spring.ai.openai.model=gpt-4o-mini

# Custom flag to toggle AI mode

ai.useMock=true
Run the application:

bash
./mvnw spring-boot:run
Access Swagger UI at:

Code
http://localhost:8080/swagger-ui.html
📂 Example Request
http
POST /ai/bug
Content-Type: application/json

{
  "description": "NullPointerException when saving user profile"
}
Sample Response

json
{
  "classification": "Backend Error",
  "suggestedFix": "Check for null values in user profile fields before saving. Add validation in the service layer."
}
🔒 Dependency Notes
Project requires Spring Boot 3.2.x and Jackson 2.17.x.

spring-ai is pinned to 1.x to avoid pulling Spring Boot 4 / Jackson 3 transitive artifacts.

If dependencies are updated, run:

bash
mvn dependency:tree
and ensure no tools.jackson or spring-boot-jackson artifacts are present.

📌 Future Enhancements
Add role‑based access control for API endpoints.
Extend AI responses with severity scoring and priority suggestions.
Integrate with external issue trackers (e.g., Jira, GitHub Issues).
Add caching layer for repeated bug descriptions.

👤 Author  
Sabiha — Aspiring Software Engineer focused on Java backend development, Spring Boot, and building production-ready systems with strong testing practices.
