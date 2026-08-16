# AI Bug Tracker (Spring Boot + OpenAI)

## Overview
This project demonstrates how to integrate **Generative AI** into a Java Spring Boot application.  
It provides REST API endpoints that accept a bug description or error log and use OpenAI (via Spring AI) to classify the bug and suggest a fix.

## Features
- Spring Boot backend (Java 21)
- AI integration via Spring AI + OpenAI
- REST API endpoints:
   - `GET /ai/health` → health check
   - `POST /ai/bug` → analyze bug or error log
- Structured JSON responses (`BugResponse`) with:
   - `classification`
   - `suggestedFix`
- Mock mode for local development and testing (`ai.useMock=true`)
- Swagger/OpenAPI UI at `/swagger-ui.html`

## Setup
1. Clone the repo.
2. Add your OpenAI API key in `src/main/resources/application.properties`:
   ```properties
   spring.application.name=ai-bug-tracker-test
   spring.ai.openai.api-key=${OPENAI_API_KEY}
   spring.ai.openai.model=gpt-4o-mini

   # Custom flag to toggle AI mode
   ai.useMock=true

## Dependency note

This project requires **Spring Boot 3.2.x** and **Jackson 2.17.x**.  
`spring-ai` is pinned to **1.x** to avoid pulling Spring Boot 4 / Jackson 3 transitive artifacts.  
If you change `spring-ai` or other AI-related dependencies, re-run `mvn dependency:tree` and ensure no `tools.jackson` or `spring-boot-jackson` artifacts are present.

