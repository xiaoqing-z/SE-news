package com.women.JOLI.module.photo.ui;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.women.JOLI.R;
import com.women.JOLI.annotation.ActivityFragmentInject;
import com.women.JOLI.base.BaseActivity;
import com.women.JOLI.base.BaseFragment;
import com.women.JOLI.base.BaseFragmentAdapter;
import com.women.JOLI.http.Api;
import com.women.JOLI.module.photo.presenter.IPhotoPresenter;
import com.women.JOLI.module.photo.presenter.IPhotoPresenterImpl;
import com.women.JOLI.module.photo.view.IPhotoView;
import com.women.JOLI.utils.ViewUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ActivityFragmentInject(contentViewId = R.layout.activity_photo,
        menuId = R.menu.menu_photo,
        hasNavigationView = true,
        toolbarTitle = R.string.photo,
        toolbarIndicator = R.drawable.ic_list_white,
        menuDefaultCheckedItem = R.id.action_photo
        )
public class PhotoActivity extends BaseActivity<IPhotoPresenter> implements IPhotoView {

    @Override
    protected void initView() {

        mPresenter = new IPhotoPresenterImpl(this);

    }

    @Override
    public void initViewPager() {
        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

        List<BaseFragment> fragments = new ArrayList<>();
        final List<String> title = Arrays.asList("精选", "趣图", "美图", "故事");

        fragments.add(PhotoListFragment.newInstance(Api.SINA_PHOTO_CHOICE_ID, 0));
        fragments.add(PhotoListFragment.newInstance(Api.SINAD_PHOTO_FUN_ID, 1));
        fragments.add(PhotoListFragment.newInstance(Api.SINAD_PHOTO_PRETTY_ID, 2));
        fragments.add(PhotoListFragment.newInstance(Api.SINA_PHOTO_STORY_ID, 3));

        BaseFragmentAdapter adapter = new BaseFragmentAdapter(getSupportFragmentManager(), fragments,
                title);
        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);
        ViewUtil.dynamicSetTabLayoutMode(tabLayout);

        setOnTabSelectEvent(viewPager, tabLayout);

    }

}
