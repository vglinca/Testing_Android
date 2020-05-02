package com.example.testinglab6;

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

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;


@RunWith(AndroidJUnit4.class)
public class AppTests {

  private UiDevice mUiDevice;

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

    UiObject translate = mUiDevice.findObject(new UiSelector().textContains("Translate"));
    while (!translate.exists()){
      appViews.scrollForward();
    }
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
    //closeAll();
  }

  @Test
  public void GoogleKeep() throws UiObjectNotFoundException{
    mUiDevice.pressHome();
    UiObject allApps = mUiDevice.findObject(new UiSelector().resourceId("launcher.mi.launcher:id/all_apps_handle"));
    allApps.clickAndWaitForNewWindow();

    UiScrollable appViews = new UiScrollable(new UiSelector().scrollable(true));

    UiObject keep = mUiDevice.findObject(new UiSelector().textContains("Keep notes"));
    while (!keep.exists()){
      appViews.scrollForward();
    }
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
    //closeAll();
  }

  @Test
  public void RecordVideo() throws UiObjectNotFoundException, InterruptedException {
    mUiDevice.pressHome();
    UiObject allApps = mUiDevice.findObject(new UiSelector().resourceId("launcher.mi.launcher:id/all_apps_handle"));
    allApps.clickAndWaitForNewWindow();

    UiScrollable appViews = new UiScrollable(new UiSelector().scrollable(true));

    UiObject camera = mUiDevice.findObject(new UiSelector().textContains("Camera"));
    while (!camera.exists()){
      appViews.scrollForward();
    }
    camera.clickAndWaitForNewWindow(3000);

    UiObject video = mUiDevice.findObject(new UiSelector().textContains("Video"));
    video.waitForExists(5000);

    video.click();

    UiObject recordBtn = mUiDevice.findObject(new UiSelector().resourceId("com.android.camera:id/v9_shutter_button_internal"));
    recordBtn.waitForExists(5000);
    recordBtn.click();

    TimeUnit.SECONDS.sleep(5);

    recordBtn.click();

    mUiDevice.pressHome();
    //closeAll();
  }

  @Test
  public void OpenYouTube() throws UiObjectNotFoundException, InterruptedException {
    mUiDevice.pressHome();
    UiObject allApps = mUiDevice.findObject(new UiSelector().resourceId("launcher.mi.launcher:id/all_apps_handle"));
    allApps.clickAndWaitForNewWindow();

    UiScrollable appViews = new UiScrollable(new UiSelector().scrollable(true));

    UiObject youtube = mUiDevice.findObject(new UiSelector().textContains("YouTube"));
    while (!youtube.exists()){
      appViews.scrollForward();
    }
    youtube.clickAndWaitForNewWindow(3000);

    UiObject searchClear = mUiDevice.findObject(new UiSelector().resourceId("com.google.android.youtube:id/search_clear"));
    if(searchClear.exists()){
      searchClear.click();
      UiObject videoSearchEditTxt = mUiDevice.findObject(new UiSelector().resourceId("com.google.android.youtube:id/search_edit_text"));
      videoSearchEditTxt.waitForExists(5000);
      videoSearchEditTxt.setText("mats valk 5.55");
      mUiDevice.pressEnter();

      findAndPlayVideo();

    }else{
      UiObject searchVideo = mUiDevice.findObject(new UiSelector().descriptionContains("Search"));
      searchVideo.waitForExists(5000);
      searchVideo.click();
      UiObject videoSearchEditTxt = mUiDevice.findObject(new UiSelector().resourceId("com.google.android.youtube:id/search_edit_text"));
      videoSearchEditTxt.waitForExists(5000);
      videoSearchEditTxt.setText("mats valk 5.55");
      mUiDevice.pressEnter();

      findAndPlayVideo();
    }

    mUiDevice.pressHome();
    //closeAll();
  }

  private void findAndPlayVideo() throws UiObjectNotFoundException, InterruptedException {
    UiScrollable scroll = new UiScrollable(new UiSelector().scrollable(true));
    scroll.scrollIntoView(new UiSelector().descriptionContains("Mats Valk (former) Official"));

    UiObject video = mUiDevice.findObject(new UiSelector().descriptionContains("Mats Valk (former) Official"));
    video.clickAndWaitForNewWindow();
    TimeUnit.SECONDS.sleep(27);

    UiObject likeBtn = mUiDevice.findObject(new UiSelector().resourceId("com.google.android.youtube:id/like_button"));
    likeBtn.click();
    mUiDevice.pressHome();

  }

  @Test
  public void PlaySomeMusic() throws UiObjectNotFoundException, InterruptedException {
    mUiDevice.pressHome();
    UiObject allApps = mUiDevice.findObject(new UiSelector().resourceId("launcher.mi.launcher:id/all_apps_handle"));
    allApps.clickAndWaitForNewWindow();

    UiScrollable appViews = new UiScrollable(new UiSelector().scrollable(true));
    UiObject player = mUiDevice.findObject(new UiSelector().textContains("Pi Music Player"));
    while (!player.exists()){
      appViews.scrollForward();
    }

    player.waitForExists(5000);
    player.clickAndWaitForNewWindow(3000);

    UiObject playingContainer = mUiDevice.findObject(new UiSelector().resourceId("com.Project100Pi.themusicplayer:id/front_now_playing_container"));
    playingContainer.waitForExists(5000);
    playingContainer.clickAndWaitForNewWindow();

    UiObject playPauseBtn = mUiDevice.findObject(new UiSelector().resourceId("com.Project100Pi.themusicplayer:id/playPauseView"));
    playPauseBtn.waitForExists(5000);
    playPauseBtn.click();
    TimeUnit.SECONDS.sleep(5);

    UiObject nextSongBtn = mUiDevice.findObject(new UiSelector().resourceId("com.Project100Pi.themusicplayer:id/nextImage"));
    nextSongBtn.waitForExists(5000);
    for (int i = 0; i < 3; i++){
      nextSongBtn.click();
      TimeUnit.SECONDS.sleep(5);
    }

    playPauseBtn.click();
    mUiDevice.pressHome();
    //closeAll();

  }


  @Test
  public void NavigateToUniversity() throws UiObjectNotFoundException, InterruptedException {
    mUiDevice.pressHome();
    UiObject allApps = mUiDevice.findObject(new UiSelector().resourceId("launcher.mi.launcher:id/all_apps_handle"));
    allApps.clickAndWaitForNewWindow();

    UiScrollable appViews = new UiScrollable(new UiSelector().scrollable(true));
    UiObject maps = mUiDevice.findObject(new UiSelector().textContains("Maps"));

    while (!maps.exists()){
      appViews.scrollForward();
    }

    maps.clickAndWaitForNewWindow(3000);

    UiObject searchField = mUiDevice.findObject(new UiSelector().resourceId("com.google.android.apps.maps:id/search_omnibox_text_box"));
    searchField.waitForExists(3000);
    searchField.click();

    UiObject searchEditTxt = mUiDevice.findObject(new UiSelector().resourceId("com.google.android.apps.maps:id/search_omnibox_edit_text"));
    searchEditTxt.waitForExists(3000);
    searchEditTxt.setText("utm blocul 3");
    mUiDevice.pressEnter();
    TimeUnit.SECONDS.sleep(5);
    mUiDevice.pressHome();
    //closeAll();
  }

  @Test
  public void DownloadApp() throws UiObjectNotFoundException{
    mUiDevice.pressHome();
    UiObject allApps = mUiDevice.findObject(new UiSelector().resourceId("launcher.mi.launcher:id/all_apps_handle"));
    allApps.clickAndWaitForNewWindow();

    UiScrollable appViews = new UiScrollable(new UiSelector().scrollable(true));

    UiObject app = mUiDevice.findObject(new UiSelector().textContains("Play Store"));
    while (!app.exists()){
      appViews.scrollForward();
    }
    app.clickAndWaitForNewWindow(3000);

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
    //closeAll();
  }

  @Test
  public void NavToChrome() throws UiObjectNotFoundException, InterruptedException {
    mUiDevice.pressHome();
    UiObject allApps = mUiDevice.findObject(new UiSelector().resourceId("launcher.mi.launcher:id/all_apps_handle"));
    allApps.clickAndWaitForNewWindow();

    UiScrollable appViews = new UiScrollable(new UiSelector().scrollable(true));

    UiObject chrome = mUiDevice.findObject(new UiSelector().textContains("Chrome"));
    while (!chrome.exists()){
      appViews.scrollForward();
    }
    chrome.clickAndWaitForNewWindow(3000);

    UiObject urlBar = mUiDevice.findObject(new UiSelector().resourceId("com.android.chrome:id/url_bar"));
    urlBar.waitForExists(3000);
    urlBar.click();

    urlBar.setText("https://google.com");
    mUiDevice.pressEnter();
    TimeUnit.SECONDS.sleep(3);
    mUiDevice.pressHome();
    //closeAll();
  }

  @Test
  public void SendEmailViaGmail() throws UiObjectNotFoundException, InterruptedException {
    mUiDevice.pressHome();
    UiObject allApps = mUiDevice.findObject(new UiSelector().resourceId("launcher.mi.launcher:id/all_apps_handle"));
    allApps.clickAndWaitForNewWindow();

    UiScrollable appViews = new UiScrollable(new UiSelector().scrollable(true));

    UiObject gmail = mUiDevice.findObject(new UiSelector().textContains("Gmail"));
    while (!gmail.exists()){
      appViews.scrollForward();
    }
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
    //closeAll();
  }

  @Test
  public void TinderSearch() throws UiObjectNotFoundException, InterruptedException {
    mUiDevice.pressHome();
    UiObject allApps = mUiDevice.findObject(new UiSelector().resourceId("launcher.mi.launcher:id/all_apps_handle"));
    allApps.clickAndWaitForNewWindow();

    UiScrollable appViews = new UiScrollable(new UiSelector().scrollable(true));

    UiObject tinder = mUiDevice.findObject(new UiSelector().textContains("Tinder"));
    while (!tinder.exists()){
      appViews.scrollForward();
    }
    tinder.clickAndWaitForNewWindow();

    UiObject like = mUiDevice.findObject(new UiSelector().resourceId("com.tinder:id/gamepad_like"));
    UiObject dislike = mUiDevice.findObject(new UiSelector().resourceId("com.tinder:id/gamepad_pass"));


    Random rnd = new Random();
    ArrayList<Integer> numbers = new ArrayList<>();
    for (int i = 0; i < 10; i++){
      numbers.add(rnd.nextInt(10));
    }
    for(int n: numbers){
      if(n < 5){
        like.click();
      }else{
        dislike.click();
      }
      TimeUnit.SECONDS.sleep(3);
    }
    mUiDevice.pressHome();
    //closeAll();
  }

  @Test
  public void AddActivityGoogleFit() throws UiObjectNotFoundException{
    mUiDevice.pressHome();
    UiObject allApps = mUiDevice.findObject(new UiSelector().resourceId("launcher.mi.launcher:id/all_apps_handle"));
    allApps.clickAndWaitForNewWindow();

    UiScrollable appViews = new UiScrollable(new UiSelector().scrollable(true));

    UiObject fit = mUiDevice.findObject(new UiSelector().textContains("Fit"));
    while (!fit.exists()){
      appViews.scrollForward();
    }
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
    //closeAll();
  }



  private void closeAll() throws UiObjectNotFoundException {
    mUiDevice.pressMenu();
    UiObject closeAll = mUiDevice.findObject(new UiSelector().resourceId("com.android.systemui:id/clearAnimView"));
    closeAll.click();
  }
}
