package com.github.ksoichiro.android.observablescrollview.test;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;

import com.github.ksoichiro.android.observablescrollview.ObservableWebView;

public class TouchInterceptionWebViewActivityTest extends ActivityInstrumentationTestCase2<TouchInterceptionWebViewActivity> {

    private Activity activity;
    private ObservableWebView scrollable;

    public TouchInterceptionWebViewActivityTest() {
        super(TouchInterceptionWebViewActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        setActivityInitialTouchMode(true);
        activity = getActivity();
        scrollable = (ObservableWebView) activity.findViewById(R.id.scrollable);
    }

    public void testScroll() throws Throwable {
        TouchUtils.touchAndCancelView(this, scrollable);
        getInstrumentation().waitForIdleSync();

        UiTestUtils.swipeVertically(this, scrollable, UiTestUtils.Direction.UP);
        getInstrumentation().waitForIdleSync();

        UiTestUtils.swipeVertically(this, scrollable, UiTestUtils.Direction.DOWN);
        getInstrumentation().waitForIdleSync();

        UiTestUtils.swipeVertically(this, scrollable, UiTestUtils.Direction.DOWN);
        getInstrumentation().waitForIdleSync();
    }

    public void testSaveAndRestoreInstanceState() throws Throwable {
        UiTestUtils.saveAndRestoreInstanceState(this, activity);
        testScroll();
    }
}
