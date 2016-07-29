/*
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
*/

package net.yoik.cordova.plugins.screenorientation;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import android.content.res.Configuration;
import org.json.JSONArray;
import org.json.JSONException;
import android.view.Surface;
import android.graphics.drawable.GradientDrawable;
import android.view.OrientationEventListener;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.util.Log;

public class YoikScreenOrientation extends CordovaPlugin {

    private static final String TAG = "YoikScreenOrientation";

    /**
     * Screen Orientation Constants
     */
        private Integer orientationMask = 0;

//    private static final String UNLOCKED = "unlocked";
//    private static final String PORTRAIT_PRIMARY = "portrait-primary";
//    private static final String PORTRAIT_SECONDARY = "portrait-secondary";
//    private static final String LANDSCAPE_PRIMARY = "landscape-primary";
//    private static final String LANDSCAPE_SECONDARY = "landscape-secondary";
//    private static final String PORTRAIT = "portrait";
//    private static final String LANDSCAPE = "landscape";

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) {

        Log.d(TAG, "execute action: " + action);
        orientationMask = Integer.parseInt(args.optString(1));
        // Route the Action
        if (action.equals("setAllowedOrientations")) {
            return routeScreenOrientation(args, callbackContext);
        }

        // Action not found
        callbackContext.error("action not recognized");
        return false;
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
          super.onConfigurationChanged(newConfig);


            Log.d(TAG, "Requested ScreenOrientation: " + newConfig.orientation);
        int rotation = cordova.getActivity().getWindowManager().getDefaultDisplay()
                .getRotation();


        Log.d(TAG, "Requested rotation: " + rotation);

        if (orientationMask == 3) {
            if(rotation == 0) {
                cordova.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
            }else if(rotation == 2){
                cordova.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
            }else if(rotation == 1) {
                cordova.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            }else {
                cordova.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT);
            }
   }
// else if (orientationMask == 12) {
//            if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
//                cordova.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
//            } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
//
//                if (rotation == 0) {
//                    cordova.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE);
//                } else if (rotation == 2) {
//                    cordova.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//                }
//            }
//        } else if (orientationMask == 10) {
//            if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
//                if (rotation == 3) {
//                    cordova.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//                } else {
//                    cordova.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
//                }
//            } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
//
//                if (rotation == 0) {
//                    cordova.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
//                } else {
//                    cordova.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//                }
//            }
//        }



    }


    private boolean routeScreenOrientation(JSONArray args, CallbackContext callbackContext) {

        String action = args.optString(0);

        if (action.equals("set")) {

          //  orientationMask = Integer.parseInt(args.optString(1));

            Log.d(TAG, "Requested ScreenOrientation: " + orientationMask);

            //Activity activity = cordova.getActivity();

            if (orientationMask == 1) {

                cordova.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT);
            }
            else if(orientationMask == 2){
                cordova.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            }
            else if (orientationMask == 4){
                cordova.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE);
            }
            else if(orientationMask ==8){
                cordova.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            }

            else if(orientationMask ==12){
                cordova.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
            }
            else if(orientationMask == 3){
                cordova.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
            }
            else if(orientationMask == 10){
                cordova.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
            }
            else if(orientationMask == 15){
                cordova.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
            }
//            if ((orientationMask & 2) > 0) {
//
//                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT);
//
//            }
//            if ((orientationMask & 4) > 0) {
//
//                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//
//            }
//            if ((orientationMask & 8) > 0) {
//
//                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE);
//
//            }
//            else if (orientation.equals(LANDSCAPE_SECONDARY)) {
//                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
//                
//                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
//                
//            } else if (orientation.equals(PORTRAIT_SECONDARY)) {
//                
//            }

            callbackContext.success();
            return true;

        } else {
            callbackContext.error("ScreenOrientation not recognised");
            return false;
        }
    }
}