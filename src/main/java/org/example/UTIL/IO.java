package org.example.UTIL;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class IO {
    //TODO: grab stringprompt library class for text color.
    //read line var
    public final BufferedReader bread = new BufferedReader(new InputStreamReader(System.in));

    // myIO getUserInput str, int, & float
    public String getUserStr(String prompt, boolean isRequired) {
        String strReturn = null;
        boolean bLoop = true;
        // loop until value is given if required
        while (bLoop) {
            if (strReturn == null)
                System.out.println(prompt);
            try {

                strReturn = bread.readLine();
                bLoop = (isRequired && (strReturn.length() < 1));

            } catch (Exception ex) {
                System.out.println("ERROR" + ex.getMessage());
            }
            // is this required
        }
        return strReturn;
    }
    public int getUserInt(String prompt, boolean isRequired) {
        boolean bloop = true;
        int num = 0;
        while (bloop) {
            try {
                bloop = false;
                num = Integer.parseInt(getUserStr(prompt, isRequired));
            } catch (Exception ex) {
                if (num == 0 && isRequired == false) {
                    return num;

                }
                System.out.println("{INVALID INPUT}, Please try again!");
            }
        }
        return num;
    }
    public int getUserInt(String prompt, boolean isRequired, int min, int max) {
        boolean bloop = true;
        int num2 = 0;
        while (bloop) {
            num2 = getUserInt(prompt, isRequired);
            bloop = (num2 < min) || (num2 > min);
        }
        return num2;

    }
    public int getUserInt() {
        return getUserInt(null, false);
    }
    public float getUserFloat(String prompt, boolean isRequired) {
        boolean bloop = true;
        float num = 0;
        while (bloop) {
            try {
                bloop = false;
                num = Float.parseFloat(getUserStr(prompt, isRequired));
            } catch (Exception ex) {
                if (num == 0 && isRequired == false) {
                    return num;
                }
                System.out.println("{INVALID INPUT}, Please try again!");
            }
        }
        return num;
    }
}
