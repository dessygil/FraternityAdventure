package adventure;

import org.junit.Test;
import org.junit.Before;

public class CheckValidDirectionTest {

    private JsonTesting jsonTesting;

    @Before
    public void setup() {
        jsonTesting = new JsonTesting();
    }

    @Test(expected = InvalidJSONFileInput.class)
    public void checkNull() throws InvalidJSONFileInput {
        jsonTesting.checkValidDirection(null);
    }

    @Test(expected = InvalidJSONFileInput.class)
    public void checkEmpty() throws InvalidJSONFileInput {
        jsonTesting.checkValidDirection("");
    }

    @Test(expected = InvalidJSONFileInput.class)
    public void checkHalfValid() throws InvalidJSONFileInput {
        jsonTesting.checkValidDirection("NS");
    }

    @Test(expected = InvalidJSONFileInput.class)
    public void checkInvalid() throws InvalidJSONFileInput {
        jsonTesting.checkValidDirection("invalid");
    }

    @Test
    public void checkValid() throws InvalidJSONFileInput {
        jsonTesting.checkValidDirection("S");
    }

}