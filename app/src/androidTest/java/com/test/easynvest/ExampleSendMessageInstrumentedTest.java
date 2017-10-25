package com.test.easynvest;

import android.content.Context;
import android.os.Build;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.PerformException;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import com.test.easynvest.view.FundActivity;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import timber.log.Timber;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.TestCase.assertEquals;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class ExampleSendMessageInstrumentedTest {
    
    @Rule
    public ActivityTestRule mActivityRule = new ActivityTestRule<>(
            FundActivity.class);

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.test.easynvest", appContext.getPackageName());
    }

    @Test
    public void registerSuccessTest(){

        onView(withId(R.id.viewpager)).perform(swipeRight());

        onView(withId(R.id.editText)).perform(typeText("Daniel Redondo de Assis"),closeSoftKeyboard());
        onView(withId(R.id.checkBoxRegisterEmail)).perform(click());
        onView(withId(R.id.editPhoneText)).perform(typeText("11983518686"),closeSoftKeyboard());

        try {
            onView(withId(R.id.editEmailText)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE))).perform(typeText("dtz.assis@gmail.com"), closeSoftKeyboard());
        } catch (PerformException e){
            e.printStackTrace();
        }


        onView(withId(R.id.btnSend)).perform(click());
        try {
            wait(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.btnSendAnotherMessage)).perform(click());

    }


    @Test
    public void registerErrorTest() {

        onView(withId(R.id.viewpager)).perform(swipeRight());

        onView(withId(R.id.editText)).perform(typeText("Daniel Redondo de Assis"), closeSoftKeyboard());
        onView(withId(R.id.checkBoxRegisterEmail)).perform(click());
        onView(withId(R.id.editPhoneText)).perform(typeText("11983518686"), closeSoftKeyboard());

        try {
            onView(withId(R.id.editEmailText)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE))).perform(typeText("dtz.assis@gmail.com"), closeSoftKeyboard());
        } catch (PerformException e) {
            e.printStackTrace();
        }


        onView(withId(R.id.btnSendMessage)).perform(click());

        try {
            wait(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.btnSendAnotherMessage)).perform(click());

    }
}
