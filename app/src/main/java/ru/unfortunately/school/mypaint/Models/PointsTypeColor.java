package ru.unfortunately.school.mypaint.Models;

import android.graphics.Path;
import android.graphics.PointF;

import java.util.ArrayList;
import java.util.List;

public class PointsTypeColor {

    private List<PointF> mPoints;
    private final int mColor;
    private final DrawTypes mType;
    private Path mPath;

    public PointsTypeColor(List<PointF> points, DrawTypes type, int color){
        mPoints = new ArrayList<>(points);
        mType = type;
        mColor = color;
        mPath = new Path();
        if(points.get(0) != null)
            mPath.moveTo(points.get(0).x, points.get(0).y);
    }

    public List<PointF> getPoints(){
        return mPoints;
    }

    public int getColor(){
        return mColor;
    }

    public DrawTypes getDrawType(){
        return mType;
    }

    public Path getPath(){
        return mPath;
    }
}
