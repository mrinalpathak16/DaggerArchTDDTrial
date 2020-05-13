import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ${NAME} {

    // region constants ----------------------------------------------------------------------------
    
    // endregion constants -------------------------------------------------------------------------
    
    // region helper fields ------------------------------------------------------------------------
    
    // endregion helper fields ---------------------------------------------------------------------
    
    private ${CLASS_NAME} SUT;
    
    @Before
    public void setup() throws Exception{
        SUT = new ${CLASS_NAME}();
        ${BODY}
    }
    
    // region helper methods -----------------------------------------------------------------------
    
    // endregion helper methods --------------------------------------------------------------------
    
    // region helper classes -----------------------------------------------------------------------
    
    // endregion helper classes --------------------------------------------------------------------
    
}