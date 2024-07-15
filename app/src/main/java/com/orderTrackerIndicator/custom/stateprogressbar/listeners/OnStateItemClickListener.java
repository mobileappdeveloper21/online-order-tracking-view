package com.orderTrackerIndicator.custom.stateprogressbar.listeners;


import com.orderTrackerIndicator.custom.stateprogressbar.StateProgressBar;
import com.orderTrackerIndicator.custom.stateprogressbar.components.StateItem;

/**
 * Created by Kofi Gyan on 2/20/2018.
 */

public interface OnStateItemClickListener {

    void onStateItemClick(StateProgressBar stateProgressBar, StateItem stateItem, int stateNumber, boolean isCurrentState);

}
