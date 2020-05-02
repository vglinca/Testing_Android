package com.example.testinglab6;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiScrollable;
import androidx.test.uiautomator.UiSelector;
import androidx.test.uiautomator.Until;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class ClickMenuTest {

  private UiDevice mUiDevice;
  private static final String BASIC_SAMPLE_PACKAGE
          = "com.example.android.testing.uiautomator.BasicSample";

  @Before
  public void setup(){
    mUiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

    mUiDevice.pressHome();

    final String launcherPackage = mUiDevice.getLauncherPackageName();
    mUiDevice.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)), 5000);
  }

  @Test
  public void GoogleTranslate() throws UiObjectNotFoundException, InterruptedException {

    mUiDevice.pressHome();
    UiObject allApps = mUiDevice.findObject(new UiSelector().resourceId("launcher.mi.launcher:id/all_apps_handle"));
    allApps.clickAndWaitForNewWindow();

    UiScrollable appViews = new UiScrollable(new UiSelector().scrollable(true));
    appViews.scrollIntoView(new UiSelector().textContains("Translate"));


    UiObject translate = mUiDevice.findObject(new UiSelector().textContains("Translate"));
    translate.clickAndWaitForNewWindow();

    UiObject clearButton = mUiDevice.findObject(new UiSelector().resourceId("com.google.android.apps.translate:id/btn_clear_input"));
    if(clearButton.exists()){
      clearButton.click();
    }

    UiObject field = mUiDevice.findObject(new UiSelector().resourceId("com.google.android.apps.translate:id/touch_to_type_text"));
    field.click();

    UiObject editTxt = mUiDevice.findObject(new UiSelector().resourceId("com.google.android.apps.translate:id/edit_input"));
    editTxt.waitForExists(35000);
    editTxt.setText("I am a king");
    mUiDevice.pressEnter();
    mUiDevice.pressHome();
  }

  @Test
  public void GoogleKeep() throws UiObjectNotFoundException{
    mUiDevice.pressHome();
    UiObject allApps = mUiDevice.findObject(new UiSelector().resourceId("launcher.mi.launcher:id/all_apps_handle"));
    allApps.clickAndWaitForNewWindow();

    UiScrollable appViews = new UiScrollable(new UiSelector().scrollable(true));
    appViews.scrollIntoView(new UiSelector().textContains("Keep notes"));

    UiObject keep = mUiDevice.findObject(new UiSelector().textContains("Keep notes"));
    keep.clickAndWaitForNewWindow(3000);

    UiObject newNote = mUiDevice.findObject(new UiSelector().resourceId("com.google.android.keep:id/new_note_button"));
    newNote.clickAndWaitForNewWindow(3000);

    UiObject note = mUiDevice.findObject(new UiSelector().resourceId("com.google.android.keep:id/edit_note_text"));
    note.waitForExists(5000);
    note.setText("Много много бреда...");

    UiObject title = mUiDevice.findObject(new UiSelector().resourceId("com.google.android.keep:id/editable_title"));
    title.waitForExists(5000);
    title.setText("Задание по ТС");

    UiObject navigateUp = mUiDevice.findObject(new UiSelector().descriptionContains("Navigate up"));
    navigateUp.waitForExists(5000);
    navigateUp.clickAndWaitForNewWindow(5000);
    mUiDevice.pressHome();
  }

  @Test
  public void RecordVideo() throws UiObjectNotFoundException{
    mUiDevice.pressHome();
    UiObject allApps = mUiDevice.findObject(new UiSelector().resourceId("launcher.mi.launcher:id/all_apps_handle"));
    allApps.clickAndWaitForNewWindow();

    UiScrollable appViews = new UiScrollable(new UiSelector().scrollable(true));
    appViews.scrollIntoView(new UiSelector().textContains("Camera"));

    UiObject camera = mUiDevice.findObject(new UiSelector().textContains("Camera"));
    camera.clickAndWaitForNewWindow(3000);

    UiObject video = mUiDevice.findObject(new UiSelector().textContains("Video"));
    video.waitForExists(5000);

    video.click();

    UiObject recordBtn = mUiDevice.findObject(new UiSelector().resourceId("com.android.camera:id/v9_shutter_button_internal"));
    recordBtn.waitForExists(5000);
    recordBtn.click();

    for (int i = 0; i < 10000000; i++);

    recordBtn.click();

    mUiDevice.pressHome();
  }

  @Test
  public void OpenYouTube() throws UiObjectNotFoundException{
    mUiDevice.pressHome();
    UiObject allApps = mUiDevice.findObject(new UiSelector().resourceId("launcher.mi.launcher:id/all_apps_handle"));
    allApps.clickAndWaitForNewWindow();

    UiScrollable appViews = new UiScrollable(new UiSelector().scrollable(true));
    appViews.scrollIntoView(new UiSelector().textContains("YouTube"));

    UiObject youtube = mUiDevice.findObject(new UiSelector().textContains("YouTube"));
    youtube.waitForExists(5000);
    youtube.clickAndWaitForNewWindow(3000);

    UiObject searchClear = mUiDevice.findObject(new UiSelector().resourceId("com.google.android.youtube:id/search_clear"));
    if(searchClear.exists()){
      searchClear.click();
      UiObject videoSearchEditTxt = mUiDevice.findObject(new UiSelector().resourceId("com.google.android.youtube:id/search_edit_text"));
      videoSearchEditTxt.waitForExists(5000);
      videoSearchEditTxt.setText("mats valk 5.55");
      mUiDevice.pressEnter();
    }else{
      UiObject searchVideo = mUiDevice.findObject(new UiSelector().descriptionContains("Search"));
      searchVideo.waitForExists(5000);
      searchVideo.click();
      UiObject videoSearchEditTxt = mUiDevice.findObject(new UiSelector().resourceId("com.google.android.youtube:id/search_edit_text"));
      videoSearchEditTxt.waitForExists(5000);
      videoSearchEditTxt.setText("mats valk 5.55");
      mUiDevice.pressEnter();
    }

    mUiDevice.pressHome();

  }

  @Test
  public void PlaySomeMusic() throws UiObjectNotFoundException, InterruptedException {
    mUiDevice.pressHome();
    UiObject allApps = mUiDevice.findObject(new UiSelector().resourceId("launcher.mi.launcher:id/all_apps_handle"));
    allApps.clickAndWaitForNewWindow();

    UiScrollable appViews = new UiScrollable(new UiSelector().scrollable(true));
    appViews.scrollIntoView(new UiSelector().textContains("Pi Music Player"));

    UiObject player = mUiDevice.findObject(new UiSelector().textContains("Pi Music Player"));
    player.waitForExists(5000);
    player.clickAndWaitForNewWindow(3000);

    UiObject playingContainer = mUiDevice.findObject(new UiSelector().resourceId("com.Project100Pi.themusicplayer:id/front_now_playing_container"));
    playingContainer.waitForExists(5000);
    playingContainer.clickAndWaitForNewWindow();

    UiObject playPauseBtn = mUiDevice.findObject(new UiSelector().resourceId("com.Project100Pi.themusicplayer:id/playPauseView"));
    playPauseBtn.waitForExists(5000);
    playPauseBtn.click();

    for (int i = 0; i < 1000000; i++){
      delay();
    }

    UiObject nextSongBtn = mUiDevice.findObject(new UiSelector().resourceId("com.Project100Pi.themusicplayer:id/nextImage"));
    nextSongBtn.waitForExists(5000);
    for (int i = 0; i < 3; i++){
      nextSongBtn.click();
      TimeUnit.SECONDS.sleep(5);
      for (int j = 0; j < 1000000; j++){
        delay();
      }
    }

    playPauseBtn.click();
    mUiDevice.pressHome();

  }

  @Test
  public void NavigateToUniversity() throws UiObjectNotFoundException, InterruptedException {
    mUiDevice.pressHome();
    UiObject allApps = mUiDevice.findObject(new UiSelector().resourceId("launcher.mi.launcher:id/all_apps_handle"));
    allApps.clickAndWaitForNewWindow();

    UiScrollable appViews = new UiScrollable(new UiSelector().scrollable(true));
    appViews.scrollIntoView(new UiSelector().textContains("Maps"));

    UiObject player = mUiDevice.findObject(new UiSelector().textContains("Maps"));
    player.waitForExists(5000);
    player.clickAndWaitForNewWindow(3000);

    UiObject searchField = mUiDevice.findObject(new UiSelector().resourceId("com.google.android.apps.maps:id/search_omnibox_text_box"));
    searchField.waitForExists(3000);
    searchField.click();

    UiObject searchEditTxt = mUiDevice.findObject(new UiSelector().resourceId("com.google.android.apps.maps:id/search_omnibox_edit_text"));
    searchEditTxt.waitForExists(3000);
    searchEditTxt.setText("utm blocul 3");
    mUiDevice.pressEnter();
    TimeUnit.SECONDS.sleep(5);
    mUiDevice.pressHome();
  }

  @Test
  public void DownloadApp() throws UiObjectNotFoundException{
    mUiDevice.pressHome();
    UiObject allApps = mUiDevice.findObject(new UiSelector().resourceId("launcher.mi.launcher:id/all_apps_handle"));
    allApps.clickAndWaitForNewWindow();

    UiScrollable appViews = new UiScrollable(new UiSelector().scrollable(true));
    appViews.scrollIntoView(new UiSelector().textContains("Play Store"));

    UiObject player = mUiDevice.findObject(new UiSelector().textContains("Play Store"));
    player.waitForExists(5000);
    player.clickAndWaitForNewWindow(3000);

    UiObject searchBar = mUiDevice.findObject(new UiSelector().resourceId("com.android.vending:id/search_bar_hint"));
    searchBar.waitForExists(3000);
    searchBar.clickAndWaitForNewWindow();

    UiObject searchInput = mUiDevice.findObject(new UiSelector().resourceId("com.android.vending:id/search_bar_text_input"));
    searchInput.waitForExists(3000);
    searchInput.setText("Linkedin");
    mUiDevice.pressEnter();

    UiObject installBtn = mUiDevice.findObject(new UiSelector().resourceId("com.android.vending:id/right_button"));
    installBtn.waitForExists(3000);
    installBtn.click();
    mUiDevice.pressHome();
  }

  @Test
  public void NavToChrome() throws UiObjectNotFoundException, InterruptedException {
    mUiDevice.pressHome();
    UiObject allApps = mUiDevice.findObject(new UiSelector().resourceId("launcher.mi.launcher:id/all_apps_handle"));
    allApps.clickAndWaitForNewWindow();

    UiScrollable appViews = new UiScrollable(new UiSelector().scrollable(true));
    appViews.scrollIntoView(new UiSelector().textContains("Chrome"));

    UiObject chrome = mUiDevice.findObject(new UiSelector().textContains("Chrome"));
    chrome.waitForExists(5000);
    chrome.clickAndWaitForNewWindow(3000);

    UiObject urlBar = mUiDevice.findObject(new UiSelector().resourceId("com.android.chrome:id/url_bar"));
    urlBar.waitForExists(3000);
    urlBar.click();

    urlBar.setText("https://google.com");
    mUiDevice.pressEnter();
    TimeUnit.SECONDS.sleep(3);
    mUiDevice.pressHome();
  }

  @Test
  public void SendEmailViaGmail() throws UiObjectNotFoundException, InterruptedException {
    mUiDevice.pressHome();
    UiObject allApps = mUiDevice.findObject(new UiSelector().resourceId("launcher.mi.launcher:id/all_apps_handle"));
    allApps.clickAndWaitForNewWindow();

    UiScrollable appViews = new UiScrollable(new UiSelector().scrollable(true));
    appViews.scrollIntoView(new UiSelector().textContains("Gmail"));

    UiObject gmail = mUiDevice.findObject(new UiSelector().textContains("Gmail"));
    gmail.waitForExists(5000);
    gmail.clickAndWaitForNewWindow(3000);

    UiObject addMsg = mUiDevice.findObject(new UiSelector().resourceId("com.google.android.gm:id/compose_button"));
    addMsg.waitForExists(3000);
    addMsg.click();

    UiObject to = mUiDevice.findObject(new UiSelector().resourceId("com.google.android.gm:id/to"));
    to.waitForExists(3000);
    to.setText("vitalic1702@gmail.com");
    mUiDevice.pressBack();

    UiObject subject = mUiDevice.findObject(new UiSelector().resourceId("com.google.android.gm:id/subject"));
    subject.waitForExists(3000);
    subject.setText("Android tests");

    UiObject body = mUiDevice.findObject(new UiSelector().textContains("Compose email"));
    body.waitForExists(3000);
    body.setText("Test message from...Android test");

    UiObject sendBtn = mUiDevice.findObject(new UiSelector().resourceId("com.google.android.gm:id/send"));
    sendBtn.waitForExists(3000);
    sendBtn.clickAndWaitForNewWindow();

    mUiDevice.pressHome();
  }

  @Test
  public void AddActivityGoogleFit() throws UiObjectNotFoundException{
    mUiDevice.pressHome();
    UiObject allApps = mUiDevice.findObject(new UiSelector().resourceId("launcher.mi.launcher:id/all_apps_handle"));
    allApps.clickAndWaitForNewWindow();

    UiScrollable appViews = new UiScrollable(new UiSelector().scrollable(true));
    appViews.scrollIntoView(new UiSelector().textContains("Fit"));

    UiObject fit = mUiDevice.findObject(new UiSelector().textContains("Fit"));
    fit.waitForExists(5000);
    fit.clickAndWaitForNewWindow(3000);

    UiObject addFab = mUiDevice.findObject(new UiSelector().resourceId("com.google.android.apps.fitness:id/add_entry_fab"));
    addFab.click();

    UiObject addActivity = mUiDevice.findObject(new UiSelector().textContains("Add activity"));
    addActivity.clickAndWaitForNewWindow();

    UiObject activityTitle = mUiDevice.findObject(new UiSelector().resourceId("com.google.android.apps.fitness:id/session_name_input"));
    activityTitle.setText("My First session");

    UiObject spinner = mUiDevice.findObject(new UiSelector().resourceId("com.google.android.apps.fitness:id/activity_spinner"));
    spinner.click();

    UiObject activityItem = mUiDevice.findObject(new UiSelector().textContains("Water polo"));
    activityItem.click();

    UiObject date = mUiDevice.findObject(new UiSelector().resourceId("com.google.android.apps.fitness:id/date"));
    date.click();

    UiObject selectedDate = mUiDevice.findObject(new UiSelector().descriptionContains("1 May 2020"));
    selectedDate.click();
    UiObject okBtn = mUiDevice.findObject(new UiSelector().resourceId("android:id/button1"));
    okBtn.click();

    UiObject saveBtn = mUiDevice.findObject(new UiSelector().resourceId("com.google.android.apps.fitness:id/container_action_button"));
    saveBtn.clickAndWaitForNewWindow();

    mUiDevice.pressHome();

    mUiDevice.pressMenu();

    mUiDevice.pressHome();

  }


  private void delay(){
    for (int i = 0; i < 1000000; i++);
  }

}
