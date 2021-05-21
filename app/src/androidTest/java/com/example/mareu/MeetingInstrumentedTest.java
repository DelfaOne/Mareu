package com.example.mareu;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.mareu.utils.DeleteViewAction;
import com.google.android.material.internal.CheckableImageButton;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.pressBack;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.example.mareu.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.allOf;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class MeetingInstrumentedTest {

    private static int TOTAL_MEETING = 6;

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule = new ActivityScenarioRule<>(MainActivity.class);

    /**
     * We ensure that our recyclerview is displaying at least on item
     */
    @Test
    public void meetingListShouldNotBeEmpty() {
        onView(allOf(withId(R.id.meeting_recyclerview), isDisplayed()))
                .check(matches(hasMinimumChildCount(1)));
    }

    /**
     * Verify AddMeetingFragment is launched when add meeting button clicked
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
    public void shouldVerifyAddButtonIsEnableWhenAllFieldsAreFills() {
        // Given
        onView(withId(R.id.add_meeting))
                .perform(click());
        //Subject
        onView(withId(R.id.subject_edit))
                .perform(replaceText("Test"));
        //Location
        onView(withId(R.id.location_menu))
                .perform(replaceText("Peach"));
        //Date
        onView(withId(R.id.date_edit)).perform(click());
        onView(withClassName(is(CheckableImageButton.class.getName())))
                .perform(click());
        onView(withHint("Date"))
                .perform(replaceText("11/04/2021"));
        onView(withText("OK"))
                .perform(click());
        //Time
        onView(withId(R.id.time_edit)).perform(click());
        onView(withText("12"))
                .perform(click());
        onView(withText("30"))
                .perform(click());
        onView(withId(com.google.android.material.R.id.material_timepicker_ok_button))
                .perform(click());
        //Mail
        onView(withId(R.id.mail_edit))
                .perform(replaceText("Testparticipant"));

       /* // When
        onView(withId(R.id.addBtn))
                .perform(click());*/

        //Then
        onView(withId(R.id.addBtn))
                .check(matches(isEnabled()));

       /* onView(Matchers.allOf(withId(R.id.meeting_recyclerview), isDisplayed()))
                .check(withItemCount(TOTAL_MEETING + 1));

        onView(Matchers.allOf(withId(R.id.meeting_title),  isDisplayed()))
                .check(matches(withText("Test Subject")));*/
    }

    /**
     * Verify button is not enabled when all fields are not filled
     */
    @Test
    public void checkAddButtonIsDisabledWhenAllFieldsAreNotCompleted() {
        // Given
        onView(withId(R.id.add_meeting))
                .perform(click());
        //WHEN
        onView(withId(R.id.subject_edit))
                .perform(replaceText("SubjectCompleted"));

        //THEN
        onView(withId(R.id.addBtn))
                .check(matches(not(isEnabled())));
    }

    /**
     * When we delete an item, the item is no more shown
     */
    @Test
    public void myNeighboursList_deleteAction_shouldRemoveItem() throws InterruptedException {
        // Given
        onView(allOf(withId(R.id.meeting_recyclerview), isDisplayed()))
                .check(withItemCount(TOTAL_MEETING));

        // When
        onView(allOf(withId(R.id.meeting_recyclerview), isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));

        // Then
        onView(allOf(withId(R.id.meeting_recyclerview), isDisplayed()))
                .check(withItemCount(TOTAL_MEETING - 1));
        reset();
    }

    /**
     * Verify display correct numbers of meetings when meetings are filter by date
     */
    @Test
    public void myNeighboursList_filterByDate_shouldDisplayCorrectMeetings() throws InterruptedException {
        //Given
        onView(allOf(withId(R.id.menu_overflow_button_create_meeting)))
                .perform(click());

        //When
        onView(withText("Filtrer par date"))
                .perform(click());
        onView(withClassName(is(CheckableImageButton.class.getName())))
                .perform(click());
        onView(withHint("Date de début"))
                .perform(replaceText("02/04/2021"));
        onView(withHint("Date de fin"))
                .perform(replaceText("05/04/2021"));
        onView(withText("ENREGISTRER"))
                .perform(click());

        //Then
        Thread.sleep(1000);
        onView(allOf(withId(R.id.meeting_recyclerview), isDisplayed()))
                .check(withItemCount(4));
        reset();

    }

    /**
     * Verify display correct numbers of meetings when meetings are filter by room
     */
    @Test
    public void myNeighboursList_filterByRoom_shouldDisplayCorrectMeetings() throws InterruptedException {
        //WHEN
        onView(allOf(withId(R.id.menu_overflow_button_create_meeting)))
                .perform(click());
        onView(withText("Filtrer par pièces"))
                .perform(click());
        onView(withText("Mario"))
                .perform(click()).check(matches(ViewMatchers.isChecked()));
        onView(withText("Wario"))
                .perform(click()).check(matches(ViewMatchers.isChecked()))
                .perform(pressBack());

        //THEN
        onView(allOf(withId(R.id.meeting_recyclerview), isDisplayed()))
                .check(withItemCount(2));
        reset();
    }

    private void reset() throws InterruptedException {
        activityScenarioRule.getScenario().close();
        Thread.sleep(3000);
    }
}