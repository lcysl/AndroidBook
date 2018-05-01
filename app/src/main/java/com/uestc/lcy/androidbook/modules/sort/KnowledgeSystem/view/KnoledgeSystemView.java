package com.uestc.lcy.androidbook.modules.sort.KnowledgeSystem.view;

import com.uestc.lcy.androidbook.base.BaseView;
import com.uestc.lcy.androidbook.model.KnowledgeSystemBean;

/**
 * Created by lcy on 2018\4\30 0030.
 */

public interface KnoledgeSystemView extends BaseView{
    void onLoadKnowledgeSystemSuccess(KnowledgeSystemBean bean);
    void onLoadKnowledgeSystemError();
}
