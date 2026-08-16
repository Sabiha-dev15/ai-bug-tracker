package io.github.sabiha.ai_bug_tracker;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BugController.class)   // ✅ Only loads BugController
public class AiBugControllerTest {

	@Autowired
	private MockMvc mockMvc;

	// ✅ Mock AiService so BugController can be constructed
	@MockBean
	private AiService aiService;

	@Test
	void testBugDescription() throws Exception {
		String requestJson = "{ \"description\": \"Login button not working on homepage\" }";

		// Mock the service response
		when(aiService.analyzeIssue("Login button not working on homepage"))
				.thenReturn("Classification: UI Issue. Suggested Fix: Check button handler.");

		mockMvc.perform(post("/ai/bug")
						.contentType("application/json")
						.content(requestJson))
				.andExpect(status().isOk())
				.andExpect(content().string(org.hamcrest.Matchers.containsString("Classification")));
	}

	@Test
	void testErrorLog() throws Exception {
		String requestJson = "{ \"description\": \"Exception in thread 'main' java.lang.NullPointerException at com.example.service.UserService.save(UserService.java:42)\" }";

		// Mock the service response
		when(aiService.analyzeIssue("Exception in thread 'main' java.lang.NullPointerException at com.example.service.UserService.save(UserService.java:42)"))
				.thenReturn("File: UserService.java, Line: 42. Issue: NullPointerException. Suggested Fix: Initialize 'userProfile' before saving.");

		mockMvc.perform(post("/ai/bug")
						.contentType("application/json")
						.content(requestJson))
				.andExpect(status().isOk())
				.andExpect(content().string(org.hamcrest.Matchers.containsString("File")));
	}
}
