package org.example.UTIL;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MyIo {
    private final BufferedReader bread = new BufferedReader(new InputStreamReader(System.in));

    /////////////////////////////////////////////

    public String getUserStr(boolean isRequired,String prompt) {
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


    ///////////////////////////////////////////////////////////////////////
    public int getUsersInt(boolean isRequired, String prompt) {
        boolean bloop = true;
        int num = 0;
        while (bloop) {
            try {
                num = Integer.parseInt(getUserStr(isRequired, prompt));
                bloop = false;
            } catch (Exception ex) {
                if (num == 0 && isRequired == false) {
                    return num;

                }
                System.out.println("INVALID INPUT, please try again");
            }
        }
        return num;
    }

    public int getUsersInt(String prompt, boolean isRequired, int min, int max) {
        boolean bloop = true;
        int num2 = 0;
        while (bloop) {
            num2 = getUsersInt(isRequired, prompt);
            bloop = (num2 < min) || (num2 > min);
        }
        return num2;

    }

    public int getUserInt() {
        return getUsersInt(false, null);

    }


}
