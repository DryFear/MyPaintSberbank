package ru.unfortunately.school.mypaint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import androidx.annotation.Nullable;
import ru.unfortunately.school.mypaint.Models.DrawTypes;
import ru.unfortunately.school.mypaint.Models.PointsTypeColor;

public class DrawView extends View {

    private static final String TAG = "DrawView";

    private Paint mDrawPaint = new Paint();
    private Paint mBackgroundPaint = new Paint();
    private Matrix mTranslatePathsMatrix = new Matrix();
    private GestureDetector mDetector;

    private List<PointsTypeColor> mDrawableObjects = new ArrayList<>();

    private boolean mScrollMode = false;

    private DrawTypes mCurrentDrawType;
    private int mCurrentColor = Color.BLACK;

    public DrawView(Context context) {
        this(context, null);
    }

    public DrawView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setUpPaint();
        initDetector(context);
    }

    private void initDetector(Context context) {
        mDetector = new GestureDetector(context, new OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                return true;
            }

            @Override
            public void onShowPress(MotionEvent e) {

            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return false;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                for(PointsTypeColor item: mDrawableObjects){
                    for(PointF point: item.getPoints()){
                        point.x -= distanceX;
                        point.y -= distanceY;
                    }
                    mTranslatePathsMatrix.reset();
                    mTranslatePathsMatrix.setTranslate(-distanceX, -distanceY);
                    item.getPath().transform(mTranslatePathsMatrix);
                }
                invalidate();
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {

            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                return false;
            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (PointsTypeColor item: mDrawableObjects) {
            List<PointF> points = item.getPoints();
            mDrawPaint.setColor(item.getColor());
            switch (item.getDrawType()){
                case LINE:
                    canvas.drawLine(points.get(0).x, points.get(0).y, points.get(1).x, points.get(1).y, mDrawPaint);
                    break;
                case RECT:
                    canvas.drawRect(points.get(0).x, points.get(0).y, points.get(1).x, points.get(1).y, mDrawPaint);
                    break;
                case POINT:
                    canvas.drawPoint(points.get(0).x, points.get(0).y, mDrawPaint);
                case MULTITOUCH:
                    if(points.size() == 1)
                        canvas.drawPoint(points.get(0).x, points.get(0).y, mDrawPaint);
                    else
                        for (int i = 0; i < points.size(); i++) {
                            float x = points.get(i).x;
                            float y = points.get(i).y;
                            float nextX = (i+1 == points.size())? points.get(0).x : points.get(i+1).x;
                            float nextY = (i+1 == points.size())? points.get(0).y : points.get(i+1).y;
                            canvas.drawLine(x, y, nextX, nextY, mDrawPaint);
                        }
                case PATH:
                    canvas.drawPath(item.getPath(), mDrawPaint);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(mScrollMode){
            return mDetector.onTouchEvent(event);
        }

        switch (event.getActionMasked()){
            case MotionEvent.ACTION_DOWN:
                actionDown(event);
                break;
            case MotionEvent.ACTION_MOVE:
                actionMove(event);
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                if(mCurrentDrawType == DrawTypes.MULTITOUCH)
                    actionPointerDown(event);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
            default:
                return super.onTouchEvent(event);
        }
        invalidate();
        return true;
    }



    private void actionDown(MotionEvent event){
        float x = event.getX();
        float y = event.getY();
        List<PointF> points = null;
        switch (mCurrentDrawType){
            case RECT:
            case LINE:
                points = new ArrayList<>(Arrays.asList(new PointF(x, y), new PointF(x, y)));
                break;
            case PATH:
            case MULTITOUCH:
            case POINT:
                points = new ArrayList<>(Collections.singletonList(new PointF(x, y)));
                break;
        }
        mDrawableObjects.add(new PointsTypeColor(points, mCurrentDrawType, mCurrentColor));
    }

    private void actionPointerDown(MotionEvent event) {
        int pointerId = event.getPointerId(event.getActionIndex());
        List<PointF> points = mDrawableObjects.get(mDrawableObjects.size()-1).getPoints();
        if(points.size() == pointerId){
            points.add(new PointF());
        }
        PointF point = points.get(pointerId);
        point.x = event.getX(event.getActionIndex());
        point.y = event.getY(event.getActionIndex());
    }

    private void actionMove(MotionEvent event){
        float x = event.getX();
        float y = event.getY();
        List<PointF> points = mDrawableObjects.get(mDrawableObjects.size()-1).getPoints();
        switch (mCurrentDrawType){
            case LINE:
            case RECT:
                points.get(1).x = x;
                points.get(1).y = y;
            case POINT:
                break;
            case PATH:
                mDrawableObjects.get(mDrawableObjects.size()-1).getPath().lineTo(x, y);
                break;
            case MULTITOUCH:
                for (int i = 0; i < event.getPointerCount(); i++) {
                    int id = event.getPointerId(i);
                    PointF point = points.get(id);
                    point.x = event.getX(i);
                    point.y = event.getY(i);
                }
                break;
        }
    }

    public void clear(){
        mDrawableObjects.clear();
        invalidate();
    }

    private void setUpPaint(){
        mBackgroundPaint.setColor(mCurrentColor);
        mDrawPaint.setColor(Color.BLACK);
        mDrawPaint.setAntiAlias(true);
        mDrawPaint.setStrokeWidth(10f);
        mDrawPaint.setStyle(Style.STROKE);
    }

    public void setDrawType(DrawTypes drawType){
        mCurrentDrawType = drawType;
    }

    public void setColor(int color){
        mCurrentColor = color;
    }

    public void switchScrollMode(){
        mScrollMode = !mScrollMode;
    }
}
