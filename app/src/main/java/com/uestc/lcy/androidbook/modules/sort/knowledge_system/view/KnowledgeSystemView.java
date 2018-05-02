package com.uestc.lcy.androidbook.modules.sort.knowledge_system.view;

import com.uestc.lcy.androidbook.base.BaseView;
import com.uestc.lcy.androidbook.model.KnowledgeSystemBean;

/**
 * Created by lcy on 2018\4\30 0030.
 */

public interface KnowledgeSystemView extends BaseView{
    void onLoadKnowledgeSystemSuccess(KnowledgeSystemBean bean);
    void onLoadKnowledgeSystemError();
}
