package io.github.sabiha.ai_bug_tracker;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ai")
@Tag(name = "AI Bug Tracker", description = "Endpoints for analyzing bugs and errors using AI")
public class BugController {
    private final AiService aiService;

    public BugController(AiService aiService) {
        this.aiService = aiService;
    }

    // DTO for request body
    public static class BugRequest {
        private String description;

        public String getDescription() {
            return description;
        }
        public void setDescription(String description) {
            this.description = description;
        }
    }

    @PostMapping("/bug")
    @Operation(
            summary = "Analyze bug or error",
            description = "Send a bug description or error log. The AI will classify the issue or identify file/line from stack trace and suggest a fix."
    )
    public String analyze(@RequestBody BugRequest request) {
        return aiService.analyzeIssue(request.getDescription());
    }

    @GetMapping("/health")
    public String health() {
        return "OK";
    }
}
