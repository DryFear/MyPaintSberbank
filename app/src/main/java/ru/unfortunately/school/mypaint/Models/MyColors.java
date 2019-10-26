package ru.unfortunately.school.mypaint.Models;

import android.graphics.Color;

import androidx.annotation.StringRes;
import ru.unfortunately.school.mypaint.R;

public enum MyColors {

    BLACK(Color.BLACK, R.string.color_black),
    BLUE(Color.BLUE, R.string.color_blue),
    GREEN(Color.GREEN, R.string.color_green),
    YELLOW(Color.YELLOW, R.string.color_yellow),
    RED(Color.RED, R.string.color_red),
    ORANGE(Color.GRAY, R.string.color_gray),
    MAGENTA(Color.MAGENTA, R.string.color_magenta);




    public final int mColor;
    public final int mColorNameRes;

    MyColors(int color, @StringRes int colorName){
        mColor = color;
        mColorNameRes = colorName;
    }
}
