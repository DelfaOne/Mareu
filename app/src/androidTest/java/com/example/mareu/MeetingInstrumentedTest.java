package com.example.mareu;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import com.example.mareu.utils.DeleteViewAction;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.example.mareu.utils.RecyclerViewItemCountAssertion.withItemCount;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class MeetingInstrumentedTest {

    private static int TOTAL_MEETING = 12;

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule = new ActivityScenarioRule<>(MainActivity.class);

    /**
     * We ensure that our recyclerview is displaying at least on item
     */
    @Test
    public void meetingListShouldNotBeEmpty() {
        onView(Matchers.allOf(withId(R.id.meeting_recyclerview), isDisplayed()))
                .check(matches(hasMinimumChildCount(1)));
    }

    /**
     * Verify if AddMeetingFragment is launched on add meeting button clicked
     */
    @Test
    public void onAddMeetingButtonClickedVerifyAddMeetingFragmentIsLaunched() {
        // When
        onView(withId(R.id.add_meeting))
                .perform(click());

        //Then
        onView(withId(R.id.scrollView_addMeeting))
                .check(matches(isDisplayed()));
    }

    /**
     * Verify correctly add a meeting with correct text
     */
    @Test
    public void shouldAddMeetingWithCorrectText() {
        // Given
        onView(withId(R.id.add_meeting))
                .perform(click());
        onView(withId(R.id.subject_edit))
                .perform(replaceText("Test Subject"));
        onView(withId(R.id.location_menu))
                .perform(replaceText("Peach"));
        onView(withId(R.id.date_edit))
                .perform(replaceText(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE)));
        onView(withId(R.id.time_edit))
                .perform(replaceText(LocalTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME)));
        onView(withId(R.id.mail_edit))
                .perform(replaceText("Test Participant"));

        // When
        onView(withId(R.id.addBtn))
                .perform(click());

        //Then
        onView(Matchers.allOf(withId(R.id.meeting_recyclerview), isDisplayed()))
                .check(withItemCount(TOTAL_MEETING + 1));

        onView(Matchers.allOf(withId(R.id.meeting_title),  isDisplayed()))
                .check(matches(withText("Test Subject")));
    }

    /**
     * When we delete an item, the item is no more shown
     */
    @Test
    public void myNeighboursList_deleteAction_shouldRemoveItem() {
        // Given
        onView(Matchers.allOf(withId(R.id.meeting_recyclerview), isDisplayed()))
                .check(withItemCount(TOTAL_MEETING));

        // When
        onView(Matchers.allOf(withId(R.id.meeting_recyclerview), isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));

        // Then
        onView(Matchers.allOf(withId(R.id.meeting_recyclerview), isDisplayed()))
                .check(withItemCount(TOTAL_MEETING - 1));
    }




}