package ru.unfortunately.school.mypaint.Models;

import androidx.annotation.StringRes;
import ru.unfortunately.school.mypaint.R;

public enum DrawTypes {

    RECT(R.string.draw_type_name_rect),
    LINE(R.string.draw_type_name_line),
    POINT(R.string.draw_type_name_point),
    PATH(R.string.draw_type_name_path),
    MULTITOUCH(R.string.draw_type_name_multitouch);



    public final int mTypeNameRes;

    DrawTypes(@StringRes int typeNameRes){
        mTypeNameRes = typeNameRes;
    }

}
