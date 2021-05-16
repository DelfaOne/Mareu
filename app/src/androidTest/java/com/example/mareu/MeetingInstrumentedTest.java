package com.example.mareu;

import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.mareu.data.DummyMeetingGenerator;
import com.example.mareu.utils.DeleteViewAction;
import com.google.android.material.datepicker.MaterialDatePicker;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.example.mareu.utils.RecyclerViewItemCountAssertion.withItemCount;

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
    public void shouldAddMeetingWithCorrectText() throws InterruptedException {
        // Given
        onView(withId(R.id.add_meeting))
                .perform(click());
        onView(withId(R.id.subject_edit))
                .perform(replaceText("Test Subject"));
        onView(withId(R.id.location_menu))
                .perform(replaceText("Peach"));
        onView(withId(R.id.mail_edit))
                .perform(replaceText("Testparticipant"));
        //Date
        onView(withId(R.id.date_edit)).perform(click());
        onView(withClassName(Matchers.equalTo(MaterialDatePicker.class.getName()))).perform(PickerActions.setDate(2021, 06, 16));
        Thread.sleep(100000);
        // When
        onView(withId(R.id.addBtn))
                .perform(click());





        //Then
       /* onView(Matchers.allOf(withId(R.id.meeting_recyclerview), isDisplayed()))
                .check(withItemCount(TOTAL_MEETING + 1));

        onView(Matchers.allOf(withId(R.id.meeting_title),  isDisplayed()))
                .check(matches(withText("Test Subject")));*/
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

    @Test
    public void myNeighboursList_filterByDate_shouldDisplayCorrectMeetings() {
        onView(Matchers.allOf(withId(R.id.menu_overflow_button_create_meeting)))
                .perform(click());
        onView(withText("Filtrer par date"))
                .perform(click());
        onView(withClassName(Matchers.equalTo(MaterialDatePicker.class.getName())))
                .perform(PickerActions.setDate(2017, 6, 30));

    }

    @Test
    public void myNeighboursList_filterByRoom_shouldDisplayCorrectMeetings() {
        onView(Matchers.allOf(withId(R.id.menu_overflow_button_create_meeting)))
                .perform(click());
        onView(withText("Filtrer par pièces"))
                .perform(click());
//        onView(withId(R.id.room_selector_recycler_view))

    }

    private void pickBirthDate(Date date) {
        // Click year selector button to open year selection view
        onView(withId(R.id.month_navigation_fragment_toggle)).perform(click())

        // Scroll to year
        ViewInteraction yearGrid = onView(withRecyclerView(R.id.mtrl_calendar_year_selector_frame)).check(matches(isDisplayed()));
        yearGrid.perform(scrollTo< RecyclerView.ViewHolder>(withText(SimpleDateFormat("y", currentLocale).format(birthdate))))

        // Click the year
        onView(withText(SimpleDateFormat("y", currentLocale).format(birthdate))).perform(click())

        // Scroll to month
        val monthGrid = onView(withRecyclerView(R.id.mtrl_calendar_months))
        monthGrid.perform(scrollTo<RecyclerView.ViewHolder>(
                withChild(withText(SimpleDateFormat("MMMM, y", currentLocale).format(birthdate))))
        )

        // TODO: Simplify this (using MaterialTextViewMatcher class?)
        // Click day of month
        onView(
                allOf(
                        withParent(withClassName(equalTo("com.google.android.material.datepicker.MaterialCalendarGridView"))),
                        withClassName(equalTo("com.google.android.material.textview.MaterialTextView")),
                        // isDescendantOfA(withRecyclerView(R.id.mtrl_calendar_months)),
                        withText(SimpleDateFormat("d", currentLocale).format(birthdate)),
                        isDisplayed()
                )
        ).perform(click())

        onView(withId(R.id.confirm_button)).perform(click())
    }





}