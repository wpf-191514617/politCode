package com.zodiac.polit.ui.fragment.signup;

import com.zodiac.polit.base.BaseFragment;
import com.zodiac.polit.ui.activity.SignupActivity;

/**
 * Created by john on 2018/10/5.
 */

public abstract class BaseSignupFragment extends BaseFragment {

    protected SignupActivity mParent;

    public void setParent(SignupActivity parent){
        mParent = parent;
    }

}
