package com.github.robinburgess.cores;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    private static final int RED = Color.argb(255, 255, 0, 0);

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void chooseCorrectAnswer() {
        onView(withId(R.id.word)).check(matches(withText("vermelho")));
        onView(withId(R.id.colourTopLeft)).check(matches(
                withBackgroundColour(RED)));
        onView(withId(R.id.colourTopLeft)).perform(click());
        onView(withId(R.id.word)).check(matches(withText("amarelo")));
        onView(withId(R.id.colourTopRight)).perform(click());
        onView(withId(R.id.word)).check(matches(withText("vermelho")));
    }

    private static Matcher<View> withBackgroundColour(final int colour) {
        return new BoundedMatcher<View, View>(View.class) {
            @Override
            protected boolean matchesSafely(View item) {
                Drawable background = item.getBackground();
                return background instanceof ColorDrawable &&
                        ((ColorDrawable) background).getColor() == colour;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("with background colour: ");
            }
        };
    }

}
