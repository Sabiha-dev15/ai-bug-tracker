# AI Bug Tracker (Spring Boot + OpenAI)

## Overview
This project demonstrates how to integrate **Generative AI** into a Java Spring Boot application.  
It provides a REST API endpoint that accepts a bug description and uses OpenAI to classify the bug and suggest a fix.

## Features
- Spring Boot backend
- AI integration via Spring AI + OpenAI
- REST API endpoint `/ai/bug`
- Example: classify bug reports like "UI issue", "Database error", "Performance bug"

## Setup
1. Clone the repo.
2. Add your OpenAI API key in `src/main/resources/application.properties`:
   ```properties
   spring.ai.openai.api-key=YOUR_API_KEY
   ai.useMock=true
