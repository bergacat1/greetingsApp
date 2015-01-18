package cat.udl.eps.softarch.hello.controller;

import cat.udl.eps.softarch.hello.config.GreetingsAppTestContext;
import cat.udl.eps.softarch.hello.model.Alert;
import cat.udl.eps.softarch.hello.model.User;
import cat.udl.eps.softarch.hello.repository.AlertRepository;
import cat.udl.eps.softarch.hello.repository.UserRepository;
import com.google.common.primitives.Ints;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = GreetingsAppTestContext.class)
@WebAppConfiguration
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class UserControllerTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AlertRepository alertRepository;

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;
    private User user;
    private Alert alert;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();

        user = new User("test", "test@test.com");
        userRepository.save(user);
        alert = new Alert(user, "Sol", "Segria");
        alertRepository.save(alert);
        user.addAlert(alert);
        userRepository.save(user);
    }

    @After
    public void tearDown() throws Exception {
    }

    //TODO: Add tests for email and date greeting fields on getRegionWeather/create/update, validation errors...
/*
    @Test
    public void testList() throws Exception {

        mockMvc.perform(get("/users").accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(view().name("users"))
                .andExpect(forwardedUrl("/WEB-INF/views/users.jsp"));
    }*/

    @Test
    public void testRetrieveExisting() throws Exception {
        mockMvc.perform(get("/users/{username}", "test").accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(view().name("user"))
                .andExpect(forwardedUrl("/WEB-INF/views/user.jsp"))
                .andExpect(model().attributeExists("user"))
                .andExpect(model().attribute("user", allOf(
                        hasProperty("username", is("test")),
                        hasProperty("email", is("test@test.com")),
                        hasProperty("alerts", contains(allOf(
                                hasProperty("weather", is("Sol")),
                                hasProperty("region", is("Segria"))))))));
    }

    @Test
    public void testRetrieveNonExisting() throws Exception {
        mockMvc.perform(get("/users/{username}", "badUser").accept(MediaType.TEXT_HTML))
                .andExpect(status().isNotFound())
                .andExpect(view().name("error"))
                .andExpect(forwardedUrl("/WEB-INF/views/error.jsp"));
    }

    @Test
    public void testRetrieveExistentUser() throws Exception {
        int startSize = Ints.checkedCast(userRepository.count());

        mockMvc.perform(post("/users")
                .accept(MediaType.TEXT_HTML)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("username", "test")
                .param("email", "test@test.com"))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/users/test"))
                .andExpect(model().hasNoErrors());
        assertEquals(startSize, userRepository.count());
    }

    @Test
    public void testCreateUser() throws Exception {
        int startSize = Ints.checkedCast(userRepository.count());

        mockMvc.perform(post("/users")
                .accept(MediaType.TEXT_HTML)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("username", "newuser")
                .param("email", "newuser@test.com"))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/users/newuser"))
                .andExpect(model().hasNoErrors());

        assertEquals(startSize + 1, userRepository.count());

        mockMvc.perform(get("/users/{username}", "newuser")
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(view().name("user"))
                .andExpect(forwardedUrl("/WEB-INF/views/user.jsp"))
                .andExpect(model().attributeExists("user"))
                .andExpect(model().attribute("user", allOf(
                        hasProperty("username", is("newuser")),
                        hasProperty("email", is("newuser@test.com")),
                        hasProperty("alerts", hasSize(0)))));
    }

    @Test
    public void testCreateUserBadParams() throws Exception {
        int startSize = Ints.checkedCast(userRepository.count());

        mockMvc.perform(post("/users")
                .accept(MediaType.TEXT_HTML)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("username", "")
                .param("email", ""))
                .andExpect(status().isBadRequest());

        assertEquals(startSize, userRepository.count());
    }

    @Test
    public void testCreateAlert() throws Exception {
        int startSize = Ints.checkedCast(alertRepository.count());

        mockMvc.perform(post("/users/test")
                .accept(MediaType.TEXT_HTML)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("region", "Segria")
                .param("weather", "Sol")
                .param("addAlert", "Afegir"))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/users/test"))
                .andExpect(model().hasNoErrors());

        assertEquals(startSize + 1, alertRepository.count());
    }

    @Test
    public void testDeleteAlert() throws Exception {
        int startSize = Ints.checkedCast(alertRepository.count());

        mockMvc.perform(post("/users/test")
                .accept(MediaType.TEXT_HTML)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", String.valueOf(alert.getId()))
                .param("delete", "Eliminar"))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/users/test"))
                .andExpect(model().hasNoErrors());

        assertEquals(startSize - 1, alertRepository.count());
    }

    @Test
    public void testDisableAlert() throws Exception {
        assertTrue(alertRepository.findOne(alert.getId()).enabled);

        mockMvc.perform(post("/users/test")
                .accept(MediaType.TEXT_HTML)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", String.valueOf(alert.getId()))
                .param("enable_disable", "Activar/Desactivar"))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/users/test"))
                .andExpect(model().hasNoErrors())
        ;

        assertFalse(alertRepository.findOne(alert.getId()).enabled);

    }
}
