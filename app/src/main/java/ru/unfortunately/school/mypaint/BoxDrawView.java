//package ru.unfortunately.school.mypaint;
//
//import android.content.Context;
//import android.graphics.Canvas;
//import android.graphics.Color;
//import android.graphics.Paint;
//import android.graphics.Paint.Style;
//import android.graphics.PointF;
//import android.util.AttributeSet;
//import android.view.MotionEvent;
//import android.view.View;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import androidx.annotation.Nullable;
//import ru.unfortunately.school.mypaint.Models.PointsTypeColor;
//
//public class BoxDrawView extends View {
//
//    private Paint mBoxPaint = new Paint();
//    private List<PointsTypeColor> mPointsTypeColorList = new ArrayList<>();
//    private PointsTypeColor mCurrentPointsTypeColor;
//
//    public BoxDrawView(Context context) {
//        this(context, null);
//    }
//
//    public BoxDrawView(Context context, @Nullable AttributeSet attrs) {
//        super(context, attrs);
//        initPaint();
//    }
//
//    private void initPaint() {
//        mBoxPaint.setStyle(Style.FILL);
//        mBoxPaint.setColor(Color.BLACK);
//    }
//
//    @Override
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//        for (PointsTypeColor pointsTypeColor : mPointsTypeColorList) {
//            float left = Math.min(pointsTypeColor.getCurrent().x, pointsTypeColor.getOrigin().x);
//            float right = Math.max(pointsTypeColor.getCurrent().x, pointsTypeColor.getOrigin().x);
//            float top = Math.min(pointsTypeColor.getCurrent().y, pointsTypeColor.getOrigin().y);
//            float bottom = Math.max(pointsTypeColor.getCurrent().y, pointsTypeColor.getOrigin().y);
//            canvas.drawRect(left, top, right, bottom, mBoxPaint);
//        }
//
//    }
//
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        PointF current = new PointF(event.getX(), event.getY());
//        int actoon = event.getAction();
//        switch (actoon){
//            case MotionEvent.ACTION_DOWN:
//                mCurrentPointsTypeColor = new PointsTypeColor(current);
//                mPointsTypeColorList.add(mCurrentPointsTypeColor);
//                break;
//            case MotionEvent.ACTION_MOVE:
//                if (mCurrentPointsTypeColor != null){
//                    mCurrentPointsTypeColor.setCurrent(current);
//                    invalidate();
//                }
//                break;
//            case MotionEvent.ACTION_UP:
//            case MotionEvent.ACTION_CANCEL:
//                mCurrentPointsTypeColor = null;
//                break;
//            default:
//                return super.onTouchEvent(event);
//        }
//        return true;
//    }
//
//    public void clear(){
//        mPointsTypeColorList.clear();
//        invalidate();
//    }
//}
