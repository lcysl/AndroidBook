package com.uestc.lcy.androidbook.modules.sort.knowledge_system.callback;

import com.uestc.lcy.androidbook.model.KnowledgeSystemBean;

/**
 * Created by lcy on 2018\4\30 0030.
 */

public interface KnowledgeSystemCallback {
    void onLoadKnowledgeSystemSuccess(KnowledgeSystemBean bean);
    void onLoadKnowledgeSystemError();
}
