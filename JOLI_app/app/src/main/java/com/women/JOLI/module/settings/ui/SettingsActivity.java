package com.women.JOLI.module.settings.ui;

import android.content.res.ColorStateList;
import android.os.Build;
import android.text.Html;
import android.view.View;
import android.widget.CheckedTextView;

import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.women.JOLI.R;
import com.women.JOLI.annotation.ActivityFragmentInject;
import com.women.JOLI.base.BaseActivity;
import com.women.JOLI.module.settings.presenter.ISettingsPresenter;
import com.women.JOLI.module.settings.presenter.ISettingsPresenterImpl;
import com.women.JOLI.module.settings.view.ISettingsView;
import com.women.JOLI.utils.ClickUtils;
import com.women.JOLI.utils.RxBus;
import com.women.JOLI.utils.SpUtil;
import com.women.JOLI.utils.ThemeUtil;
import com.women.JOLI.utils.ViewUtil;
import com.zhy.changeskin.SkinManager;

@ActivityFragmentInject(contentViewId = R.layout.activity_settings,
        menuId = R.menu.menu_settings,
        hasNavigationView = true,
        toolbarTitle = R.string.settings,
        toolbarIndicator = R.drawable.ic_list_white,
        menuDefaultCheckedItem = R.id.action_settings)
public class SettingsActivity extends BaseActivity<ISettingsPresenter> implements ISettingsView {

    private CheckedTextView mNightModeCheckedTextView;
    private CheckedTextView mSlideModeCheckedTextView;

    @Override
    protected void initView() {

        mNightModeCheckedTextView = (CheckedTextView) findViewById(R.id.ctv_night_mode);
        mSlideModeCheckedTextView = (CheckedTextView) findViewById(R.id.ctv_slide_mode);

        mNightModeCheckedTextView.setOnClickListener(this);
        mSlideModeCheckedTextView.setOnClickListener(this);

        findViewById(R.id.tv_about).setOnClickListener(this);

        mPresenter = new ISettingsPresenterImpl(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ctv_night_mode:

                if (ClickUtils.isFastDoubleClick()) {
                    return;
                }

                boolean nightModeCheck = !((CheckedTextView) v).isChecked();

                ((CheckedTextView) v).setChecked(nightModeCheck);
                SkinManager.getInstance().changeSkin(nightModeCheck ? "night" : "");
                SpUtil.writeBoolean("enableNightMode", nightModeCheck);

                // 这里设置主题不起作用，但是我们弹窗时候的主题和着色时的颜色状态列表属性是需要主题支持的
                setTheme(nightModeCheck ? R.style.BaseAppThemeNight_AppTheme : R.style.BaseAppTheme_AppTheme);

                applyTint(mNightModeCheckedTextView);
                applyTint(mSlideModeCheckedTextView);

                mNightModeCheckedTextView.setText(nightModeCheck ? "Turn off the night mode" : "Turn on the night mode");

                // 主题更改了，发送消息通知其他导航Activity销毁掉
                RxBus.get().post("finish", true);

                break;
            case R.id.ctv_slide_mode:

                final boolean slideModeCheck = !((CheckedTextView) v).isChecked();

                mSlideModeCheckedTextView.setText(slideModeCheck ? "Close the slip back" : "Open the slip back");

                ((CheckedTextView) v).setChecked(slideModeCheck);

                SpUtil.writeBoolean("disableSlide", !slideModeCheck);

                if (slideModeCheck) {
                    String currentSlideMode = SpUtil.readBoolean("enableSlideEdge") ? "Edge slip" : "Full page slip";
                    new MaterialDialog.Builder(this).title("Select the skid mode (current is" + currentSlideMode + ")").items(R.array.slide_mode_items)
                            .itemsCallbackSingleChoice(SpUtil.readBoolean("enableSlideEdge") ? 0 : 1, new MaterialDialog.ListCallbackSingleChoice() {
                                @Override
                                public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                    SpUtil.writeBoolean("enableSlideEdge", which == 0);
                                    return true;
                                }
                            }).positiveText("select").show();
                }
                break;
            case R.id.tv_about:
                final MaterialDialog dialog = new MaterialDialog.Builder(this).title("About us").titleGravity(GravityEnum.CENTER).content("").show();
                dialog.getContentView().setText(Html.fromHtml("This project API comes from: Netease News Client "+"<a href='https://github.com/tigerguixh/QuickNews'>QuickNews</a><br>"
                        +"Used the following excellent open source projects:<br>"
                        + "<a href='https://github.com/square/retrofit'>Retrofit2.0</a>,"
                        +"<a href='https://github.com/ReactiveX/RxJava'>RxJava</a>,"
                        +"<a href='https://github.com/greenrobot/greenDAO'>GreenDAO</a>,"
                        +"<a href='https://github.com/bumptech/glide'>Glide</a>,"
                        +"<a href='https://github.com/hongyangAndroid/AndroidChangeSkin'>AndroidChangeSkin</a>,"
                        +"<a href='https://github.com/Bilibili/ijkplayer'>Ijkplayer</a>"

                ));
                break;
        }
    }

    @Override
    public void initItemState() {

        applyTint(mNightModeCheckedTextView);
        applyTint(mSlideModeCheckedTextView);

        mNightModeCheckedTextView.setChecked(SpUtil.readBoolean("enableNightMode"));
        mNightModeCheckedTextView.setText(SpUtil.readBoolean("enableNightMode") ? "Turn off the night mode" : "Turn on the night mode");

        mSlideModeCheckedTextView.setChecked(!SpUtil.readBoolean("disableSlide"));
        mSlideModeCheckedTextView.setText(!SpUtil.readBoolean("disableSlide") ? "Close the slip back" : "Open the slip back");

    }

    // 因为这里是通过鸿洋大神的换肤做的，而CheckedTextView着色不兼容5.0以下，
    // 所以切换皮肤的时候动态加载当前主题自定义的ColorStateList，对CheckMarkDrawable进行着色
    private void applyTint(CheckedTextView checkedTextView) {
        ColorStateList indicator = ThemeUtil.getColorStateList(this, R.attr.checkTextViewColorStateList);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            checkedTextView.setCheckMarkTintList(indicator);
        } else {
            ViewUtil.tintDrawable(checkedTextView.getCheckMarkDrawable(), indicator);
        }
    }

}
