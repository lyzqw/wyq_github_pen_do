package com.wyq.common.enum

import com.wyq.common.R

enum class MainTabEnum(val index: Int, val normalColor: Int, val selectedColor: Int) {

    TAB_DIARY(0, R.color.gray_C3C3C3, R.color.blue_53BCF4),
    TAB_NOTE(1, R.color.gray_C3C3C3, R.color.orange_fbbf2e),
    TAB_PENDING(2, R.color.gray_C3C3C3, R.color.color_08c263),
    TAB_SCHEDULE(3, R.color.gray_C3C3C3, R.color.red_BF3E38);

}