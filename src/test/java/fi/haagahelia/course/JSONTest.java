package fi.haagahelia.course;

import com.fasterxml.jackson.databind.ObjectMapper;
import fi.haagahelia.course.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@SpringBootTest
@RunWith(SpringRunner.class)
public class JSONTest {

    @Test
    public void testJSON(){
        String json = "{\"id\":null,\"firstName\":\"FN\",\"lastName\":\"FN\",\"email\":\"user\",\"password\":\"user\",\"role\":{\"id\":1,\"role\":\"\"}}";
        ObjectMapper mapper = new ObjectMapper();

        try {
            User user = mapper.readValue(json, User.class);
            System.out.println(user);
            System.out.println(user);
            System.out.println(user);
            System.out.println(user);
            System.out.println(user);
            System.out.println(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
