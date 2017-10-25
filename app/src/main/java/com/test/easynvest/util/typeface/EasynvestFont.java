package com.test.easynvest.util.typeface;

public enum EasynvestFont implements Typefaces.Font {

    DIN_ENG_STD("DINEngschriftStd.otf",1),
    DIN_MITTEL_STD("DINMittelschriftStd.otf",2),
    DIN_NEUZEIT_GROTESK_STD_BDCOND("DINNeuzeitGroteskStd-BdCond.otf",3),
    DIN_NEUZEIT_GROTESK_STD_LIGHT("DINNeuzeitGroteskStd-Light.otf",4),
    DIN_PRO_BLACK("DINPro-Black.otf",5),
    DIN_PRO_BOLD("DINPro-Bold.otf",6),
    DIN_PRO_LIGHT("DINPro-Light.otf",7),
    DIN_PRO_MEDIUM("DINPro-Medium.otf",8),
    DIN_PRO_REGULAR("DINPro-Regular.otf",9);


    private String name;
    private int code;

    EasynvestFont(String name, int code) {
        this.name = name;
        this.code = code;
    }

    @Override
    public String getName() {
            return name;
        }

    @Override
    public int getCode() {
        return code;
    }

    public static Typefaces.Font fontByCode(int code) {
        for (Typefaces.Font f : EasynvestFont.values()) {
            if (f.getCode() == code) {
                return f;
            }
        }
        return null;
    }


}
