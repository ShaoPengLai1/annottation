package com.example.butterknife.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.butterknife.R;
import com.example.butterknife.adapter.XRecycleViewAdapter;
import com.example.butterknife.bean.ShoppingBean;
import com.example.netlibrary.presenter.IPresenterImpl;
import com.example.netlibrary.view.IView;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.stx.xhb.xbanner.XBanner;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoaderInterface;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class HotSaleFragment extends Fragment implements IView {


    @BindView(R.id.banner_img)
    XBanner bannerImg;
    @BindView(R.id.recycle)
    XRecyclerView recycle;
    Unbinder unbinder;
    private IPresenterImpl iPresenter;
    private String urlStr="http://www.zhaoapi.cn/home/getHome?page=%d";
    private SimpleDraweeView simpleDraweeView;
    private int page;
    private XRecycleViewAdapter adapter;
    private List<ShoppingBean.DataBean.TuijianBean.ListBeanX> list;
    private List<String> imgesUrl;
    private List<String> title;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        iPresenter=new IPresenterImpl(this);
        View view = inflater.inflate(R.layout.fragment_hotsale, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        page=1;

        recycle.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        recycle.setPullRefreshEnabled(true);
        recycle.setLoadingMoreEnabled(true);
        adapter=new XRecycleViewAdapter(getActivity());
        recycle.setAdapter(adapter);
        recycle.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page=1;
                loadData();
            }

            @Override
            public void onLoadMore() {
                loadData();
            }
        });
        imgesUrl = new ArrayList<>();
        title = new ArrayList<>();
        imgesUrl.add("http://www.zhaoapi.cn/images/quarter/ad1.png");
        title.add("这是第一这个图片");
        imgesUrl.add("http://www.zhaoapi.cn/images/quarter/ad2.png");
        title.add("这是第二这个图片");
        imgesUrl.add("http://www.zhaoapi.cn/images/quarter/ad3.png");
        title.add("这是第三这个图片");
        imgesUrl.add("http://www.zhaoapi.cn/images/quarter/ad4.png");
        title.add("这是第四这个图片");
        bannerImg.setData(imgesUrl,title);
        bannerImg.setmAdapter(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, View view, int position) {
                Glide.with(getActivity()).load(imgesUrl.get(position)).into((ImageView) view);
            }
        });
        loadData();
    }

    private void loadData() {
        iPresenter.startRequestGet(String.format(urlStr,page),null,ShoppingBean.class);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void getRequest(Object data) {
        if (data instanceof ShoppingBean){
            ShoppingBean bean= (ShoppingBean) data;
            if (bean==null||!bean.isSuccess()){
                Toast.makeText(getActivity(),bean.getMsg(),Toast.LENGTH_LONG).show();
            }else {

                if (page==1){
                    adapter.setData(bean.getData().getTuijian().getList());
                }else {
                    adapter.addData(bean.getData().getTuijian().getList());
                }
                page++;
                recycle.refreshComplete();
                recycle.loadMoreComplete();
            }
        }
    }



}
