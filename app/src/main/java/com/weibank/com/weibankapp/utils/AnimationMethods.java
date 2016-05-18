package com.weibank.com.weibankapp.utils;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.transition.Fade;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;

/**
 * Created by Administrator on 2016/2/23.
 */
public class AnimationMethods{

//    public static void setupWindowSlide(Activity activity){
//
//        Slide slide = new Slide(Gravity.LEFT);
//        slide.setDuration(1500);
//        //activity.getWindow().setReturnTransition(slide);
//        activity.getWindow().setExitTransition(slide);
//
//        Fade fade = new Fade();
//        fade.setDuration(1500);
//        activity.getWindow().setEnterTransition(fade);
//
//    }

//    public static void setupWindowFade(Activity activity){
//
//
//        Slide slide = new Slide(Gravity.RIGHT);
//        slide.setDuration(1500);
//        //activity.getWindow().setReturnTransition(slide);
//        activity.getWindow().setExitTransition(slide);
//
//        Fade fade = new Fade();
//        fade.setDuration(1500);
//        activity.getWindow().setEnterTransition(fade);
//        //activity.getWindow().setExitTransition(fade);
//    }

//    public static void startToActivity(Activity activity,Class target){
//
//        Intent i = new Intent(activity, target);
//
//        Pair<View, String>[] pairs = TransitionHelper.createSafeTransitionParticipants
//                (activity, true);
//        ActivityOptionsCompat transitionActivityOptions =
//                ActivityOptionsCompat.makeSceneTransitionAnimation(activity, pairs);
//        activity.startActivity(i, transitionActivityOptions.toBundle());
//        //activity.startActivity(i, transitionActivityOptions.toBundle());
//    }
}
