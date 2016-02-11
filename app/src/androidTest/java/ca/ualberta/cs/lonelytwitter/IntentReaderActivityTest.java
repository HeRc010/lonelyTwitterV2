package ca.ualberta.cs.lonelytwitter;

import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ViewAsserts;
import android.widget.TextView;

import java.util.Collections;

/**
 * Created by sajediba on 2/8/16.
 */
public class IntentReaderActivityTest extends ActivityInstrumentationTestCase2{

    public IntentReaderActivityTest() {
        super(IntentReaderActivity.class);
    }

    //
    //
    public void testSendTest() {

        Intent intent = new Intent();

        String testMessage = "Test message 2";
        intent.putExtra(IntentReaderActivity.TEXT_TO_TRANSFORM_KEY, testMessage);
        setActivityIntent(intent);

        IntentReaderActivity intentReaderActivity = (IntentReaderActivity) getActivity();
        assertEquals("ERROR: IntentReaderActivity did not process text from intent", testMessage,
                intentReaderActivity.getText().toString());
    }

    public void testDisplayText() {

        Intent intent = new Intent();

        String testMessage = "Test message 3";
        intent.putExtra(IntentReaderActivity.TEXT_TO_TRANSFORM_KEY, testMessage);
        setActivityIntent(intent);

        IntentReaderActivity intentReaderActivity = (IntentReaderActivity) getActivity();
        TextView intentTextView = (TextView) intentReaderActivity.findViewById(R.id.intentText);
        assertEquals("ERROR: the displayed text is incorrect",
                testMessage, intentTextView.getText().toString());
    }

    public void testDoubleText() {

        Intent intent = new Intent();

        String testMessage = "Test Message 4";
        intent.putExtra(IntentReaderActivity.TEXT_TO_TRANSFORM_KEY, testMessage);
        intent.putExtra(IntentReaderActivity.MODE_OF_TRANSFORM_KEY, IntentReaderActivity.DOUBLE);
        setActivityIntent(intent);

        IntentReaderActivity intentReaderActivity = (IntentReaderActivity) getActivity();
        assertEquals("ERROR: The displayed text is incorrect", testMessage + testMessage,
                intentReaderActivity.getText().toString());

    }

    public void testReverseText() {

        Intent intent = new Intent();

        String testMessage = "Test Message 4";
        intent.putExtra(IntentReaderActivity.TEXT_TO_TRANSFORM_KEY, testMessage);
        intent.putExtra(IntentReaderActivity.MODE_OF_TRANSFORM_KEY, IntentReaderActivity.REVERSE);
        setActivityIntent(intent);

        IntentReaderActivity intentReaderActivity = (IntentReaderActivity) getActivity();
        assertEquals("ERROR: The displayed text is incorrect",
                new StringBuilder().append(testMessage).reverse().toString(),
                    intentReaderActivity.getText().toString());

    }

    public void testDefaultMessage() {

        Intent intent = new Intent();
        setActivityIntent(intent);

        IntentReaderActivity intentReaderActivity = (IntentReaderActivity) getActivity();
        assertEquals("ERROR: The displayed text is incorrect", "default value",
                intentReaderActivity.getText().toString());
    }

    public void testIntentTextViewVisible() {

        Intent intent = new Intent();
        setActivityIntent(intent);

        IntentReaderActivity intentReaderActivity = (IntentReaderActivity) getActivity();
        assertEquals("ERROR: The displayed text is incorrect", "default value",
                intentReaderActivity.getText().toString());

        ViewAsserts.assertOnScreen(intentReaderActivity.getWindow().getDecorView(),
                intentReaderActivity.findViewById(R.id.intentText));
    }
    //
    //
}
