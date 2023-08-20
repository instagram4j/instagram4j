package com.github.instagram4j.instagram4j;

import java.io.Serializable;
import lombok.Data;

@Data
public class IGAndroidDevice extends IGDevice implements Serializable {
    private static final long serialVersionUID = -885748975689l;
    private final String androidVersion;
    private final String androidRelease;
    private final String dpi;
    private final String displayResolution;
    private final String manufacturer;
    private final String model;
    private final String device;
    private final String cpu;
    public static final String CAPABILITIES = "3brTvw==";

    public IGAndroidDevice(String formatted) {
        super(toUserAgent(formatted), CAPABILITIES, null);
        String[] format = formatted.split("; ");
        this.androidVersion = format[0].split("/")[0];
        this.androidRelease = format[0].split("/")[1];
        this.dpi = format[1];
        this.displayResolution = format[2];
        this.manufacturer = format[3];
        this.model = format[4];
        this.device = format[5];
        this.cpu = format[6];
    }

    public static String toUserAgent(String formatted) {
        return String.format("Instagram %s Android (%s; %s)",
                IGConstants.APP_VERSION,
                formatted,
                IGConstants.LOCALE);
    }

    public static final IGAndroidDevice[] GOOD_DEVICES = {
            new IGAndroidDevice("31/12; 480dpi; 1080x2278; vivo; vivo 1939; 2004; qcom"),
            new IGAndroidDevice("26/8.0.0; 480dpi; 1080x1920; Xiaomi; MI 5s; capricorn; qcom"),
            new IGAndroidDevice("26/8.0.0; 480dpi; 1080x2076; samsung; SM-A530F; jackpotlte; samsungexynos7885"),
            /*
             * Samsung Galaxy S7 Edge SM-G935F. Released: March 2016.
             * https://www.amazon.com/Samsung-SM-G935F-Factory-Unlocked-Smartphone/dp/ B01C5OIINO
             * https://www.handsetdetection.com/properties/devices/Samsung/SM-G935F
             */
            new IGAndroidDevice(
                    "23/6.0.1; 640dpi; 1440x2560; samsung; SM-G935F; hero2lte; samsungexynos8890"),

            /*
             * OnePlus 3T. Released: November 2016.
             * https://www.amazon.com/OnePlus-A3010-64GB-Gunmetal-International/dp/ B01N4H00V8
             * https://www.handsetdetection.com/properties/devices/OnePlus/A3010
             */
            new IGAndroidDevice(
                    "24/7.0; 380dpi; 1080x1920; OnePlus; ONEPLUS A3010; OnePlus3T; qcom"),

            /*
             * LG G5. Released: April 2016.
             * https://www.amazon.com/LG-Unlocked-Phone-Titan-Warranty/dp/B01DJE22C2
             * https://www.handsetdetection.com/properties/devices/LG/RS988
             */
            new IGAndroidDevice("23/6.0.1; 640dpi; 1440x2392; LGE/lge; RS988; h1; h1"),

            /*
             * Huawei Mate 9 Pro. Released: January 2017.
             * https://www.amazon.com/Huawei-Dual-Sim-Titanium-Unlocked-International/dp/ B01N9O1L6N
             * https://www.handsetdetection.com/properties/devices/Huawei/LON-L29
             */
            new IGAndroidDevice("24/7.0; 640dpi; 1440x2560; HUAWEI; LON-L29; HWLON; hi3660"),

            /*
             * ZTE Axon 7. Released: June 2016.
             * https://www.frequencycheck.com/models/OMYDK/zte-axon-7-a2017u-dual-sim-lte-a- 64gb
             * https://www.handsetdetection.com/properties/devices/ZTE/A2017U
             */
            new IGAndroidDevice("23/6.0.1; 640dpi; 1440x2560; ZTE; ZTE A2017U; ailsa_ii; qcom"),

            /*
             * Samsung Galaxy S7 SM-G930F. Released: March 2016.
             * https://www.amazon.com/Samsung-SM-G930F-Factory-Unlocked-Smartphone/dp/ B01J6MS6BC
             * https://www.handsetdetection.com/properties/devices/Samsung/SM-G930F
             */
            new IGAndroidDevice(
                    "23/6.0.1; 640dpi; 1440x2560; samsung; SM-G930F; herolte; samsungexynos8890")};
}
