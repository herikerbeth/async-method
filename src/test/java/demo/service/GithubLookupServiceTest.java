package demo.service;

import demo.TestData;
import demo.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GithubLookupServiceTest {

    private GithubLookupService service;

    @Mock
    private RestTemplate restTemplateMock;

    @Mock
    private RestTemplateBuilder restTemplateBuilderMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        when(restTemplateBuilderMock.build()).thenReturn(restTemplateMock);

        service = new GithubLookupService(restTemplateBuilderMock);
    }

    @Test
    void testFindUser() throws Exception {
        String userName = "octocat";
        String url = String.format("https://api.github.com/users/%s", userName);
        User mockUser = TestData.createUser();

        // Setting of RestTemplate behavior
        when(restTemplateMock.getForObject(eq(url), eq(User.class))).thenReturn(mockUser);

        // Run the findUser method
        CompletableFuture<User> resultFuture = service.findUser(userName);

        // Await result
        User result = resultFuture.get();

        assertNotNull(result);
        assertEquals("The Octocat", result.getName());
        assertEquals("https://github.blog", result.getBlog());

        // Verify the RestTemplate calling
        Mockito.verify(restTemplateMock).getForObject(eq(url), eq(User.class));
    }
}
