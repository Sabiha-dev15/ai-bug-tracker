package io.github.sabiha.ai_bug_tracker;

import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AiService {
    private final OpenAiChatModel chatModel;

    @Value("${ai.useMock:true}") // default true if not set
    private boolean useMock;

    public AiService(OpenAiChatModel chatModel) {
        this.chatModel = chatModel;
    }

    public String analyzeIssue(String description) {
        if (useMock) {
            if (description.toLowerCase().contains("exception") || description.toLowerCase().contains("error")) {
                return "File: UserService.java, Line: 42. Issue: NullPointerException. Suggested Fix: Initialize 'userProfile' before saving.";
            } else {
                return "Classification: UI Issue. Suggested Fix: Check button handler.";
            }
        }

        String promptText;
        if (description.toLowerCase().contains("exception") || description.contains(".java:")) {
            promptText = "Analyze the following error log. Identify the file and line number where the issue occurs, explain the cause, and suggest a fix:\n\n"
                    + description;
        } else {
            promptText = "Classify this bug or issue and suggest a fix:\n\n" + description;
        }

        Prompt prompt = new Prompt(promptText);
        ChatResponse response = chatModel.call(prompt);

        return response.getResult().getOutput().getText();
    }
}
