package com.fundamentals.phonenumberspinner;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class SpinnerSelectionTest {

    @Rule
    public final ActivityTestRule mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void iterateSpinnerItems() {
        // SOS: I get the activity through the activity rule above...
        String[] labelsArray = mActivityRule.getActivity().getResources().getStringArray(R.array.labels_array);

        // SOS: the spinner is populated w views (ie the drop-down list) at runtime. Checking for any
        // of these views immediately after the spinner is clicked may not find the view... so I have
        // to use onData to wait for my view to come into... view. is(label) checks for the presence
        // of a String matching the label in any of the views and then we click that view!
        for (String label : labelsArray) {
            onView(withId(R.id.label_spinner)).perform(click());
            onData(is(label)).perform(click());
            onView(withId(R.id.text_phonelabel)).check(matches(withText(containsString(label))));
        }
    }

    @Test
    public void useAppContext() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("com.fundamentals.phonenumberspinner", appContext.getPackageName());
    }
}
