package com.androidplot.ui.widget;

import android.graphics.*;
import com.androidplot.exception.PlotRenderException;
import com.androidplot.ui.layout.*;
import com.androidplot.util.Dimension;
import com.androidplot.util.Point;

public abstract class Widget implements BoxModelable {

    private Paint borderPaint;
    private Paint backgroundPaint;
    private boolean drawBorderEnabled = false;
    private boolean drawBackgroundEnabled = false;
    private boolean clippingEnabled = true;
    /*private float marginTop = 0;
    private float marginBottom = 0;
    private float marginLeft = 0;
    private float marginRight = 0;*/
    private BoxModel boxModel = new BoxModel();
    private SizeMetrics sizeMetrics;

    {
        borderPaint = new Paint();
        borderPaint.setColor(Color.BLACK);
        borderPaint.setStyle(Paint.Style.STROKE);

        backgroundPaint = new Paint();
        backgroundPaint.setColor(Color.DKGRAY);
        backgroundPaint.setStyle(Paint.Style.FILL);
    }

    public Widget(SizeMetric heightMetric, SizeMetric widthMetric) {
        sizeMetrics = new SizeMetrics(heightMetric, widthMetric);
    }

    public Widget(SizeMetrics sizeMetrics) {
        this.sizeMetrics = sizeMetrics;
    }

    public void setSize(SizeMetrics sizeMetrics) {
        this.sizeMetrics = sizeMetrics;
    }


    public void setWidth(float width) {
        sizeMetrics.getWidthMetric().setValue(width);
    }

    public void setWidth(float width, SizeLayoutType layoutType) {
        sizeMetrics.getWidthMetric().set(width, layoutType);
    }

    public void setHeight(float height) {
        sizeMetrics.getHeightMetric().setValue(height);
    }

    public void setHeight(float height, SizeLayoutType layoutType) {
       sizeMetrics.getHeightMetric().set(height, layoutType);
    }
    public SizeMetric getWidthMetric() {
        return sizeMetrics.getWidthMetric();
    }
    public SizeMetric getHeightMetric() {
        return sizeMetrics.getHeightMetric();
    }

    public float getWidthPix(float size) {
        return sizeMetrics.getWidthMetric().getPixelValue(size);
    }

    public float getHeightPix(float size) {
        return sizeMetrics.getHeightMetric().getPixelValue(size);
    }

    public RectF getMarginatedRect(RectF widgetRect) {
        return boxModel.getMarginatedRect(widgetRect);
    }

    public RectF getPaddedRect(RectF widgetMarginRect) {
        return boxModel.getPaddedRect(widgetMarginRect);
    }

    public void setMarginRight(float marginRight) {
        boxModel.setMarginRight(marginRight);
    }

    public void setMargins(float left, float top, float right, float bottom) {
        boxModel.setMargins(left, top, right, bottom);
    }

    public void setPadding(float left, float top, float right, float bottom) {
        boxModel.setPadding(left, top, right, bottom);
    }

    public float getMarginTop() {
        return boxModel.getMarginTop();
    }

    public void setMarginTop(float marginTop) {
        boxModel.setMarginTop(marginTop);
    }

    public float getMarginBottom() {
        return boxModel.getMarginBottom();
    }

    @Override
    public float getPaddingLeft() {
        return boxModel.getPaddingLeft();
    }

    @Override
    public void setPaddingLeft(float paddingLeft) {
        boxModel.setPaddingLeft(paddingLeft);
    }

    @Override
    public float getPaddingTop() {
        return boxModel.getPaddingTop();
    }

    @Override
    public void setPaddingTop(float paddingTop) {
        boxModel.setPaddingTop(paddingTop);
    }

    @Override
    public float getPaddingRight() {
        return boxModel.getPaddingRight();
    }

    @Override
    public void setPaddingRight(float paddingRight) {
        boxModel.setPaddingRight(paddingRight);
    }

    @Override
    public float getPaddingBottom() {
        return boxModel.getPaddingBottom();
    }

    @Override
    public void setPaddingBottom(float paddingBottom) {
        boxModel.setPaddingBottom(paddingBottom);
    }

    public void setMarginBottom(float marginBottom) {
        boxModel.setMarginBottom(marginBottom);
    }

    public float getMarginLeft() {
        return boxModel.getMarginLeft();
    }

    public void setMarginLeft(float marginLeft) {
        boxModel.setMarginLeft(marginLeft);
    }

    public float getMarginRight() {
        return boxModel.getMarginRight();
    }

     public void draw(Canvas canvas, RectF widgetRect) throws PlotRenderException {
        if(drawBackgroundEnabled) {
            drawBackground(canvas, widgetRect);
        }

       /* RectF marginatedRect = new RectF(widgetRect.left + marginLeft,
                widgetRect.top + marginTop,
                widgetRect.right - marginRight,
                widgetRect.bottom - marginBottom);*/

        RectF marginatedRect = boxModel.getMarginatedRect(widgetRect);
        RectF paddedRect = boxModel.getPaddedRect(marginatedRect);
        doOnDraw(canvas, paddedRect);

        if(drawBorderEnabled) {
            drawBorder(canvas, paddedRect);
        }
    }

    protected void drawBorder(Canvas canvas, RectF paddedRect) {
        canvas.drawRect(paddedRect, borderPaint);
    }

    protected void drawBackground(Canvas canvas, RectF widgetRect) {
        canvas.drawRect(widgetRect, backgroundPaint);
    }

    /**
     *
     * @param canvas The Canvas to draw onto
     * @param widgetRect the size and coordinates of this widget
     */
    //protected abstract void doBeforeDraw(Canvas canvas, Dimension viewSize, Dimension size, Point coords) throws PlotRenderException;
    protected abstract void doOnDraw(Canvas canvas, RectF widgetRect) throws PlotRenderException;

    public Paint getBorderPaint() {
        return borderPaint;
    }

    public void setBorderPaint(Paint borderPaint) {
        this.borderPaint = borderPaint;
    }

    public Paint getBackgroundPaint() {
        return backgroundPaint;
    }

    public void setBackgroundPaint(Paint backgroundPaint) {
        this.backgroundPaint = backgroundPaint;
    }

    public boolean isDrawBorderEnabled() {
        return drawBorderEnabled;
    }

    public void setDrawBorderEnabled(boolean drawBorderEnabled) {
        this.drawBorderEnabled = drawBorderEnabled;
    }

    public boolean isDrawBackgroundEnabled() {
        return drawBackgroundEnabled;
    }

    public void setDrawBackgroundEnabled(boolean drawBackgroundEnabled) {
        this.drawBackgroundEnabled = drawBackgroundEnabled;
    }

    public boolean isClippingEnabled() {
        return clippingEnabled;
    }

    public void setClippingEnabled(boolean clippingEnabled) {
        this.clippingEnabled = clippingEnabled;
    }
}
