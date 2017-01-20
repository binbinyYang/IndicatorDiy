 
###前言
说说大概思路
下面4个空心圆是画笔画出来的,
实心圆是一个 也是画笔画的,
然后结合viewpage

 先上图。
![](http://img.blog.csdn.net/20170120154837744?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQveWFuZ2JpbjA1MTM=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/Center)

###编码
- 首先创建一个项目

- 布局，很简单
	```
 <RelativeLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/activity_main"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:paddingBottom="@dimen/activity_vertical_margin"
    android:paddingLeft="@dimen/activity_horizontal_margin"
    android:paddingRight="@dimen/activity_horizontal_margin"
    android:paddingTop="@dimen/activity_vertical_margin"
    >

    <android.support.v4.view.ViewPager
        android:id="@+id/viewpager"
        android:layout_width="match_parent"
        android:layout_height="200dp">

    </android.support.v4.view.ViewPager>

    <com.yangbin.indicatordiy.Indicator
        android:id="@+id/indicator"
        android:layout_width="160dp"
        android:layout_height="60dp"
        android:layout_alignBottom="@+id/viewpager"
        android:layout_centerHorizontal="true"/>
</RelativeLayout>
	```
 
 
 
- 自定义控件代码
	```
 
 /**
 * Created by yangbin on 12/29/2016.
 */
public class Indicator extends View {
    private int mBgColor = Color.BLUE;//背景色的圆的画笔
    private int mForeColor = Color.RED;//前景色的圆的画笔
    private int mNumber = 4;//Indicator数量
    private int mRadius = 10;//圆的半径
    private Paint mForePaint;//前景色圆的画笔
    private Paint mBgPaint;//背景色的画笔
    /**
     * 移动的偏移量
     **/
    private float mOffset;

    public Indicator(Context context) {
        super(context);
    }

    public Indicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.Indicator);
        mNumber = typedArray.getInteger(R.styleable.Indicator_setNumber, mNumber);
    }

    /***
     * 设置偏移量的方法
     * @param position
     * @param positionOffset
     */
    public void setOffset(int position, float positionOffset) {
        position %= mNumber;
        mOffset = position * 3 * mRadius + positionOffset * 3 * mRadius;
        Log.e("mOffset ", mOffset + " ");
        //重绘
        invalidate();
    }

    private void initPaint() {
        mForePaint = new Paint();
        mForePaint.setAntiAlias(true);
        mForePaint.setStyle(Paint.Style.FILL);
        mForePaint.setColor(mForeColor);
        mForePaint.setStrokeWidth(2);
        mBgPaint = new Paint();
        mBgPaint.setAntiAlias(true);
        mBgPaint.setStyle(Paint.Style.STROKE);
        mBgPaint.setColor(mBgColor);
        mBgPaint.setStrokeWidth(2);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < mNumber; i++) {
            canvas.drawCircle(60 + i * mRadius * 3, 60, mRadius, mBgPaint);
        }

        //这里大于90,是因为前景色画笔mForePaint如果到了第4个的时候在往右滑动,是会出现可以滑动情况的.直接设置为0,就是回到第一个,从此循环
        if (mOffset > 90) {
            mOffset = 0;
        }
        canvas.drawCircle(60 + mOffset, 60, mRadius, mForePaint);
    }
}
```
