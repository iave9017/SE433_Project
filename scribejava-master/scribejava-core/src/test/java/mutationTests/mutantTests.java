package mutationTests;

import static org.junit.Assert.*;

import java.util.Objects;

import org.junit.Assert;
import org.junit.Test;

import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.*;

public class mutantTests {
	
	//.core.model.OAuth1AccessToken tests 
	//5/6 mutants killed
	
    @Test //KILLS MUTANT.. Assures hash doesn't == 0, which it shouldn't (would make hash very guessable)
    public void line45ReplacedIntegerReturnWithTernary() {
    		OAuth1AccessToken oToken = new OAuth1AccessToken("Random", "String");
    		int actual = oToken.hashCode();
    		assertNotEquals(0,actual);
    }
    
    @Test //DOES NOT KILL MUTANT
    public void line50NegatedConditional() {
    		OAuth1AccessToken oToken = new OAuth1AccessToken("Random", "String");
    		boolean actual = oToken.equals(oToken);
    		assertTrue(actual);
    } 
    
    @Test //KILLS 4 MUTANTS.. assures hash is calculated properly. If hash equation changes, test will show it 
    public void line43ReplacedIntegerAdditionWithSubtraction() {
    		OAuth1AccessToken oToken = new OAuth1AccessToken("Random", "String");
    		int expected = 3;
        expected = 73 * expected + Objects.hashCode(oToken.getToken());
        expected = 73 * expected + Objects.hashCode(oToken.getTokenSecret());
		int actual = oToken.hashCode();
		assertEquals(expected,actual);
    }
    
    //.core.builder.ServiceBuilder tests
    //3/3 mutants killed
    
    @Test //KILLS 1 MUTANT.. assures exception is called when apikey() is passed empty string
    public void line42RemovedCall() {
    		Exception ex = null;
    		ServiceBuilder sBuilder = new ServiceBuilder("APIKEY");
    		try {
    			ServiceBuilder sBuilderReturned = sBuilder.apiKey("");
    		}catch (Exception e) {
    			ex = e;
    		}
    		if (ex.equals(null)) {
    			Assert.fail();
    		}
    }
    
    @Test //KILLS 1 MUTANT.. assures sBuilder returned is not null when apiKey parameter is not empty
    public void line44MutatedReturnToConditional() {
    		Exception ex = null;
    		ServiceBuilder sBuilderReturned = null;
    		ServiceBuilder sBuilder = new ServiceBuilder("APIKEY");
    		try {
    			sBuilderReturned = sBuilder.apiKey("APIKEY_NEW");
    		}catch (Exception e) {
    			ex = e;
    		}
    		assertNotEquals(sBuilderReturned,null);
    }
    
    @Test //KILLS 1 MUTANT.. assures exception is called when apiSecret() is passed empty string
    public void line49RemovedCall() {
 		Exception ex = null;
 		ServiceBuilder sBuilder = new ServiceBuilder("APIKEY");
 		try {
 			ServiceBuilder sBuilderReturned = sBuilder.apiSecret("");
 		}catch (Exception e) {
 			ex = e;
 		}
 		if (ex.equals(null)) {
 			Assert.fail();
 		}
    }
    
}
