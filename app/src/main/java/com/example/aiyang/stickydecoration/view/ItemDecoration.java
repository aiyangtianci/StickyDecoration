package com.example.aiyang.stickydecoration.view;

import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.aiyang.stickydecoration.bean.GoodsBean;

import java.util.ArrayList;
import java.util.List;

public class ItemDecoration extends RecyclerView.ItemDecoration {

    List<GoodsBean> goods;

    public ItemDecoration(List<GoodsBean> goods) {
        this.goods = goods;
        for (int i =0; i< goods.size();i++){
            GoodsBean bean = goods.get(i);
            if (bean.getItemViewType() ==1){
                cacheStickyViewPosition(i); //收集标题的在Adapter中的实际position
            }
        }
    }

    /**
     * 子项布局管理
     */
    private LinearLayoutManager mLayoutManager;

    /**
     * 缓存标题
     */
    private List<Integer> mStickyPositionList = new ArrayList<>();

    /**
     * 绑定数据的position
     */
    private int mBindDataPosition = -1;

    /**
     * Adapter ：托管数据集合，为每个子项创建视图
     */
    private RecyclerView.Adapter<RecyclerView.ViewHolder> mAdapter;

    /**
     * 承载子项视图的holder
     */
    private RecyclerView.ViewHolder mViewHolder;

    /**
     * 标题的视图View
     */
    private View mStickyItemView;

    /**
     * 标记屏幕可见是否有标题（默认是没有）
     * 讲解：当屏幕上没有标题时，吸附标题也会消失
     */
    private boolean mCurrentUIFindStickView;

    /**
     * 标题距离顶部距离
     */
    private int mStickyItemViewMarginTop;

    /**
     * 标题布局高度
     */
    private int mItemViewHeight;


    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        initViewHolder(parent);
        /**
         * RecyclerView和LayoutManager的getChildCount()方法返回可见的item数量,同样的getChildAt(int index)获取的也是可见的第index个位置的item。
         * RecyclerView.getAdapter().getItemCount()，这个返回的就是实际的item数量
         */
       if (mAdapter.getItemCount() <= 0) return;


        mLayoutManager = (LinearLayoutManager) parent.getLayoutManager();

        mCurrentUIFindStickView = false;

        for (int i = 0, size = mLayoutManager.getChildCount(); i < size; i++) {

            View item = mLayoutManager.getChildAt(i);
            if ((boolean) item.getTag() == true) {

                mCurrentUIFindStickView = true;

                if (item.getTop() <= 0) {//主流程：（1）标题移动到在屏幕第一行时

                    bindDataForStickyView(mLayoutManager.findFirstVisibleItemPosition(), parent.getMeasuredWidth());//主流程：（2）将其设置成吸附标题

                    mStickyItemViewMarginTop = 0;

                } else if (item.getTop() > 0 && item.getTop() <= mItemViewHeight) {//处理两个标题叠在一起的绘制效果

                    mStickyItemViewMarginTop = item.getTop() - mItemViewHeight;

                } else {
                    int currentPosition = mLayoutManager.findFirstVisibleItemPosition() + i;//被下滑的标题，在Adapter中的索引
                    int indexOfCurrentPosition = mStickyPositionList.lastIndexOf(currentPosition);//根据标题的position获得所在缓存列表中的索引
                    bindDataForStickyView(mStickyPositionList.get(indexOfCurrentPosition - 1), parent.getMeasuredWidth());
                }

                drawStickyItemView(c);// 主流程：（3)画出吸附的标题

                break;  //结束循环
            }
        }

        /**
         * 主流程：（4）当屏幕可见列表没有标题项，吸附标题依然显示
         */
        if (mCurrentUIFindStickView == false) {
            mStickyItemViewMarginTop = 0;
            drawStickyItemView(c);
        }
    }

    /**
     *  收集标题的 position
     */
    private void cacheStickyViewPosition(int position) {
        if (!mStickyPositionList.contains(position)) {//防止重复
            mStickyPositionList.add(position);
        }
    }

    /**
     * 复制一个标题view
     */
    private void initViewHolder(RecyclerView recyclerView) {
        if (mAdapter != null) return;
        mAdapter = recyclerView.getAdapter();
        mViewHolder = mAdapter.onCreateViewHolder(recyclerView, 1);//type类型1
        mStickyItemView = mViewHolder.itemView;
    }

    /**
     * 设置吸附标题
     */
    private void bindDataForStickyView(int position, int width) {
        if (mBindDataPosition == position || mViewHolder == null) return;//已经是吸附位置了 或 视图不存在
        mBindDataPosition = position;//屏幕可见第一个item在列表中的实际索引位置
        mAdapter.onBindViewHolder(mViewHolder, mBindDataPosition);//吸附标题设置显示数据
        measureLayoutStickyItemView(width);//设置布局位置
        mItemViewHeight = mStickyItemView.getBottom() - mStickyItemView.getTop();
    }

    /**
     * 设置布局悬浮位置
     */
    private void measureLayoutStickyItemView(int parentWidth) {
        if (mStickyItemView == null || !mStickyItemView.isLayoutRequested()) return;

        int widthSpec = View.MeasureSpec.makeMeasureSpec(parentWidth, View.MeasureSpec.EXACTLY);
        int heightSpec;

        ViewGroup.LayoutParams layoutParams = mStickyItemView.getLayoutParams();
        if (layoutParams != null && layoutParams.height > 0) {
            heightSpec = View.MeasureSpec.makeMeasureSpec(layoutParams.height, View.MeasureSpec.EXACTLY);
        } else {
            heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        }

        mStickyItemView.measure(widthSpec, heightSpec);//View绘制：（1）Measure
        /**
         * view.layout(l,t,r,b) ; 子布局相对于父布局的绘制的位置及大小。
         * l 和 t 是控件左边缘和上边缘相对于父类控件左边缘和上边缘的距离。r 和 b是控件右边缘和下边缘相对于父类控件左边缘和上边缘的距离。
         */
        mStickyItemView.layout(0, 0, mStickyItemView.getMeasuredWidth(), mStickyItemView.getMeasuredHeight());//View绘制：（2）Measure
    }

    /**
     * 绘制标题
     *
     * @param canvas
     */
    private void drawStickyItemView(Canvas canvas) {
        if (mStickyItemView == null) return;

        int saveCount = canvas.save();
        canvas.translate(0, mStickyItemViewMarginTop);
        mStickyItemView.draw(canvas);//View绘制：（3）Measure
        canvas.restoreToCount(saveCount);
    }
}
