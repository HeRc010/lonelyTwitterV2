package ca.ualberta.cs.lonelytwitter;

import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

/**
 * Created by sajediba on 2/8/16.
 */
public class LonelyTwitterActivityUITest extends ActivityInstrumentationTestCase2 {

    Instrumentation instrumentation;
    Activity activity;
    EditText textInput;

    public LonelyTwitterActivityUITest() {
        super(LonelyTwitterActivity.class);
    }

    protected void setUp() throws Exception {
        super.setUp();
        instrumentation = getInstrumentation();
        activity = getActivity();
        textInput = ((EditText) activity.findViewById(ca.ualberta.cs.lonelytwitter.R.id.body));
    }

    //makeTweet(text) fills in the input text field and clicks the 'save' button for the activity under test:
    private void makeTweet(String text) {
        assertNotNull(activity.findViewById(ca.ualberta.cs.lonelytwitter.R.id.save));
        textInput.setText(text);
        ((Button) activity.findViewById(ca.ualberta.cs.lonelytwitter.R.id.save)).performClick();
    }

    @UiThreadTest
    public void testMakeTweet() {

        LonelyTwitterActivity activity = (LonelyTwitterActivity) getActivity();
        ListView oldTweetsListView = (ListView) activity.findViewById(R.id.oldTweetsList);

        // Retrieve the current number of tweets in the list
        int prevCount = oldTweetsListView.getAdapter().getCount();

        String testText = "Test Test Test";
        makeTweet(testText);

        ArrayAdapter<Tweet> oldTweetsArrayAdapter = (ArrayAdapter<Tweet>) oldTweetsListView.getAdapter();

        // Ensure a no-zero number of tweets are present in the list
        assertEquals("ERROR: The number of Tweets in the old Tweet list is incorrect", prevCount + 1, oldTweetsArrayAdapter.getCount());

        // Ensure what was added to the list is a Tweet
        assertTrue("ERROR: The object is not a Tweet", oldTweetsArrayAdapter.getItem(prevCount) instanceof Tweet);

        // Ensure that the text of the most recently added Tweet is correct
        assertEquals("ERROR: The text of the Tweet is inconsistent with what was expected.", testText, oldTweetsArrayAdapter.getItem(prevCount).getMessage());
    }

    //
    //

    //
    //
}
