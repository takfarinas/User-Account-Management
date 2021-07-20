package com.technical.test.controller;

import com.google.common.io.Resources;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.inject.Inject;
import java.nio.charset.StandardCharsets;

import static com.google.common.io.Resources.getResource;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest()
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@Slf4j
public class UserControllerTest {
    public static final String USER_CREATION_DIRECTORY_BASE_PATH = "user-creation";
    @Inject
    private MockMvc mockMvc;

    public static final String API_USER_BASE_PATH = "/api/user";

    @ParameterizedTest
    @DisplayName("Perform User creation with result Http status 201")
    @SneakyThrows
    @ValueSource(strings = {"nominal-user"})
    void performUserCreation(final String directory) {
        performApiCall(directory, status().isCreated(), "id");
    }

    @ParameterizedTest
    @DisplayName("Perform User creation with bad request with result Http status 400")
    @SneakyThrows
    @ValueSource(strings = {"name-missing", "countryOfResidence-missing", "birthday-missing", "user-is-not-french-adult"})
    void performUserCreationWithBadRequest(final String directory) {
        performApiCall(directory, status().isBadRequest(), "timestamp");
    }

    private void performApiCall(final String directory, final ResultMatcher status, String dynamicPart) throws Exception {
        final String testDirectoryBasePath = USER_CREATION_DIRECTORY_BASE_PATH + "/" + directory + "/";
        final String body = Resources.toString(getResource(testDirectoryBasePath + "body-content.json"), StandardCharsets.UTF_8);
        final ResultMatcher response = content().json(Resources.toString(getResource(testDirectoryBasePath + "response-content.json"), StandardCharsets.UTF_8));
        this.mockMvc.perform(post(API_USER_BASE_PATH)
                .header("Content-Type", "application/json")
                .content(body))
                .andExpect(MockMvcResultMatchers.jsonPath(dynamicPart).exists())
                .andExpect(status)
                .andExpect(response);
    }
}
