package adventure;

import org.junit.Test;
import org.json.simple.JSONArray;
import org.junit.Before;

public class CheckDeadEndTest{
    
    private JsonTesting jsonTesting;
    private JSONArray testArrayOne;
    private JSONArray testArrayTwo;

    @Before
    public void setup() {
       testArrayOne = null;
       testArrayTwo = new JSONArray();
       jsonTesting = new JsonTesting();
    }

    @Test(expected = InvalidJSONFileInput.class)
    public void checkNull() throws InvalidJSONFileInput{
        jsonTesting.checkNotDeadEnd(testArrayOne);
    }
    
    @Test(expected = InvalidJSONFileInput.class)
    public void checkEmpty() throws InvalidJSONFileInput{
        jsonTesting.checkNotDeadEnd(testArrayTwo);
    }

}