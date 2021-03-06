package org.flyve.mdm.agent.security;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import org.flyve.mdm.agent.utils.FlyveLog;

/*
 *   Copyright © 2017 Teclib. All rights reserved.
 *
 *   This file is part of flyve-mdm-android
 *
 * flyve-mdm-android is a subproject of Flyve MDM. Flyve MDM is a mobile
 * device management software.
 *
 * Flyve MDM is free software: you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 3
 * of the License, or (at your option) any later version.
 *
 * Flyve MDM is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * ------------------------------------------------------------------------------
 * @author    Rafael Hernandez
 * @date      4/7/17
 * @copyright Copyright © 2017 Teclib. All rights reserved.
 * @license   GPLv3 https://www.gnu.org/licenses/gpl-3.0.html
 * @link      https://github.com/flyve-mdm/flyve-mdm-android
 * @link      https://flyve-mdm.com
 * ------------------------------------------------------------------------------
 */
public class FlyveDeviceAdminUtils {

    private DevicePolicyManager mDPM;
    private ComponentName mDeviceAdmin;
    private Context context;

    public FlyveDeviceAdminUtils(Context context) {
        this.context = context;
        mDPM = (DevicePolicyManager) context.getSystemService(Context.DEVICE_POLICY_SERVICE);
        mDeviceAdmin = new ComponentName(context, FlyveAdminReceiver.class);
    }

    /**
     * Erase all data of the device
     */
    public void wipe() {
        mDPM.wipeData(0);
    }

    /**
     * Lock the device now
     */
    public void lockDevice() {
        mDPM.lockNow();
    }

    /**
     * Request to user encrypt files
     */
    public void storageEncryptionDeviceRequest() {
        Intent intent = new Intent(DevicePolicyManager.ACTION_START_ENCRYPTION);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.getApplicationContext().startActivity(intent);
    }

    /**
     * Encrypt Storage from dashboard
     * @param isEncryption boolean
     */
    public void storageEncryptionDevice(boolean isEncryption) {
        int status = mDPM.getStorageEncryptionStatus();
        FlyveLog.d("status: " + status);

        if(isEncryption && status == DevicePolicyManager.ENCRYPTION_STATUS_ACTIVE) {
            // the data is already encrypted
            return;
        }

        if(isEncryption && status == DevicePolicyManager.ENCRYPTION_STATUS_ACTIVATING) {
            // the encryption is working
            return;
        }

        if (status != DevicePolicyManager.ENCRYPTION_STATUS_UNSUPPORTED) {
            // encrypt file mute
            int isEncrypt = mDPM.setStorageEncryption(mDeviceAdmin, isEncryption);
            FlyveLog.d("setStorageEncryption: " + isEncrypt);
        }
    }

    /**
     * Disable the possibility to use the camera
     * @param disable boolean true | false
     */
    public void disableCamera(boolean disable) {
        mDPM.setCameraDisabled(mDeviceAdmin, disable);
    }

    /**
     * Set password length
     * @param length int
     */
    public void setPasswordLength(int length) {
        FlyveLog.d("setPasswordLength: " + length);
        if(mDPM.getPasswordMinimumLength(mDeviceAdmin)!=length){
            FlyveLog.d("Apply setPasswordLength: " + length);
            mDPM.setPasswordMinimumLength(mDeviceAdmin, length);
        }
    }

    /**
     * Set password quality
     * @param quality String quality type
     */
    public void setPasswordQuality(String quality) {
         if("PASSWORD_QUALITY_NUMERIC".equalsIgnoreCase(quality)) {
             FlyveLog.d("switch: PASSWORD_QUALITY_NUMERIC");
             mDPM.setPasswordQuality(mDeviceAdmin, DevicePolicyManager.PASSWORD_QUALITY_NUMERIC);
         }

         if("PASSWORD_QUALITY_ALPHABETIC".equalsIgnoreCase(quality)) {
            FlyveLog.d("switch: PASSWORD_QUALITY_ALPHABETIC");
            mDPM.setPasswordQuality(mDeviceAdmin, DevicePolicyManager.PASSWORD_QUALITY_ALPHABETIC);
        }

        if("PASSWORD_QUALITY_ALPHANUMERIC".equalsIgnoreCase(quality)) {
            FlyveLog.d("switch: PASSWORD_QUALITY_ALPHANUMERIC");
            mDPM.setPasswordQuality(mDeviceAdmin, DevicePolicyManager.PASSWORD_QUALITY_ALPHANUMERIC);
        }

        if("PASSWORD_QUALITY_COMPLEX".equalsIgnoreCase(quality)) {
            FlyveLog.d("switch: PASSWORD_QUALITY_COMPLEX");
            mDPM.setPasswordQuality(mDeviceAdmin, DevicePolicyManager.PASSWORD_QUALITY_COMPLEX);
        }

        if("PASSWORD_QUALITY_SOMETHING".equalsIgnoreCase(quality)) {
            FlyveLog.d("switch: PASSWORD_QUALITY_SOMETHING");
            mDPM.setPasswordQuality(mDeviceAdmin, DevicePolicyManager.PASSWORD_QUALITY_SOMETHING);
        }

        if("PASSWORD_QUALITY_UNSPECIFIED".equalsIgnoreCase(quality)) {
            FlyveLog.d("switch: PASSWORD_QUALITY_UNSPECIFIED");
            mDPM.setPasswordQuality(mDeviceAdmin, DevicePolicyManager.PASSWORD_QUALITY_UNSPECIFIED);
        }
    }

    /**
     * Set Password minumim letters
     * @param minLetters int
     */
    public void setPasswordMinumimLetters(int minLetters) {
        if(mDPM.getPasswordMinimumLetters(mDeviceAdmin)!=minLetters) {
            FlyveLog.d("setPasswordMinumimLetters:  " + minLetters);
            mDPM.setPasswordMinimumUpperCase(mDeviceAdmin, minLetters);
        }
    }

    /**
     * set Password Minimum Lower Case
     * @param minLowerCase int
     */
    public void setPasswordMinimumLowerCase(int minLowerCase) {
        if(mDPM.getPasswordMinimumLowerCase(mDeviceAdmin)!=minLowerCase) {
            FlyveLog.d("setPasswordMinimumLowerCase:  " + minLowerCase);
            mDPM.setPasswordMinimumLowerCase(mDeviceAdmin, minLowerCase);
        }
    }

    /**
     * set Password Minimum Upper Case
     * @param minUpperCase int
     */
    public void setPasswordMinimumUpperCase(int minUpperCase) {
        if(mDPM.getPasswordMinimumUpperCase(mDeviceAdmin)!=minUpperCase) {
            FlyveLog.d("setPasswordMinimumUpperCase:  " + minUpperCase);
            mDPM.setPasswordMinimumUpperCase(mDeviceAdmin, minUpperCase);
        }
    }

    /**
     * set Password Minimum Non Letter
     * @param minNonLetter int
     */
    public void setPasswordMinimumNonLetter(int minNonLetter) {
        if(mDPM.getPasswordMinimumNonLetter(mDeviceAdmin)!=minNonLetter) {
            FlyveLog.d("setPasswordMinimumNonLetter: " + minNonLetter);
            mDPM.setPasswordMinimumNonLetter(mDeviceAdmin, minNonLetter);
        }
    }

    /**
     * set Password Minimum Numeric
     * @param minNumeric int
     */
    public void setPasswordMinimumNumeric(int minNumeric) {
        if(mDPM.getPasswordMinimumNumeric(mDeviceAdmin)!=minNumeric) {
            FlyveLog.d("setPasswordMinimumNumeric:  " + minNumeric);
            mDPM.setPasswordMinimumNumeric(mDeviceAdmin, minNumeric);
        }
    }

    /**
     * set Password Minimum Symbols
     * @param minSymbols int
     */
    public void setPasswordMinimumSymbols(int minSymbols) {
        if(mDPM.getPasswordMinimumSymbols(mDeviceAdmin)!=minSymbols) {
            FlyveLog.d("setPasswordMinimumSymbols:  " + minSymbols);
            mDPM.setPasswordMinimumSymbols(mDeviceAdmin, minSymbols);
        }
    }

    /**
     * set Maximum Failed Passwords For Wipe
     * @param maxFailed int
     */
    public void setMaximumFailedPasswordsForWipe(int maxFailed) {
        if(mDPM.getMaximumFailedPasswordsForWipe(mDeviceAdmin)!=maxFailed) {
            FlyveLog.d("setMaximumFailedPasswordsForWipe:  " + maxFailed);
            mDPM.setMaximumFailedPasswordsForWipe(mDeviceAdmin, maxFailed);
        }
    }

    /**
     * set Maximum Time To Lock
     * @param timeMs
     */
    public void setMaximumTimeToLock(long timeMs) {
        if(mDPM.getMaximumTimeToLock(mDeviceAdmin)!=timeMs) {
            FlyveLog.d("setMaximumTimeToLock:  " + timeMs);
            mDPM.setMaximumTimeToLock(mDeviceAdmin, timeMs);
        }
    }

}
