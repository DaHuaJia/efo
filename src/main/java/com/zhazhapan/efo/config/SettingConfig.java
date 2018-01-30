package com.zhazhapan.efo.config;

import com.zhazhapan.efo.EfoApplication;
import com.zhazhapan.efo.modules.constant.ConfigConsts;
import com.zhazhapan.efo.util.CommonUtils;
import com.zhazhapan.modules.constant.ValueConsts;
import com.zhazhapan.util.Checker;
import com.zhazhapan.util.Formatter;

import java.util.Date;

/**
 * @author pantao
 * @date 2018/1/26
 */
public class SettingConfig {

    private static final String WINDOWS = "windows";

    private static final String MAC = "mac";

    private static final String LINUX = "linux";

    private static OsName currentOS;

    static {
        if (Checker.isWindows()) {
            currentOS = OsName.WINDOWS;
        } else if (Checker.isMacOS()) {
            currentOS = OsName.MAC;
        } else {
            currentOS = OsName.LINUX;
        }
    }

    public static String getUploadStoragePath() {
        String parent = getStoragePath(ConfigConsts.UPLOAD_PATH_OF_SETTING);
        String formatWay = EfoApplication.settings.getStringUseEval(ConfigConsts.UPLOAD_FORM_OF_SETTING);
        return parent + ValueConsts.SEPARATOR + Formatter.datetimeToCustomString(new Date(), formatWay);
    }

    public static String getStoragePath(String path) {
        path += ValueConsts.DOT_SIGN;
        if (currentOS == OsName.WINDOWS) {
            path += WINDOWS;
        } else if (currentOS == OsName.MAC) {
            path += MAC;
        } else {
            path += LINUX;
        }
        return CommonUtils.checkPath(EfoApplication.settings.getStringUseEval(path));
    }

    /**
     * 当前系统名称
     */
    public enum OsName {
        /**
         * windows系统
         */
        WINDOWS,

        /**
         * MacOS系统
         */
        MAC,

        /**
         * Linux系统
         */
        LINUX
    }
}
