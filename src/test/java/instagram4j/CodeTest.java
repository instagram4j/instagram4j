package instagram4j;

import org.junit.Assert;
import org.junit.Test;
import com.github.instagram4j.instagram4j.utils.IGUtils;

public class CodeTest {
    @Test
    public void testToCode() {
        Assert.assertEquals("CClpx7qsYm-", IGUtils.toCode(2352470131599313342L));
    }
    
    @Test
    public void testFromCode() {
        Assert.assertEquals(2352470131599313342L, IGUtils.fromCode("CClpx7qsYm-"));
    }
}
