package com.infophone.auth.presentation.viewModel

import androidx.lifecycle.ViewModel
import com.infophone.auth.R
import com.infophone.auth.domain.model.OnBoardingItem

class OnboardViewModel : ViewModel() {

    val onBoardingList = listOf(
        OnBoardingItem(
            image = R.drawable.call_chat_img,
            description = R.string.onboarding1_description,
            title1 = R.string.onboarding1_from,
            title2 = R.string.onboarding1_texts,
            title3 = R.string.onboarding1_to,
            title4 = R.string.onboarding1_calls_dot
        ),

        OnBoardingItem(
            image = R.drawable.privacy,
            description = R.string.onboarding2_description,
            title1 = R.string.onboarding2_your,
            title2 = R.string.onboarding2_world,
            title3 = R.string.onboarding2_comma_your,
            title4 = R.string.onboarding2_privacy_dot
        ),

        OnBoardingItem(
            image = R.drawable.share,
            description = R.string.onboarding3_description,
            title1 = R.string.onboarding3_share,
            title2 = R.string.onboarding3_photos,
            title3 = R.string.onboarding3_dot_send,
            title4 = R.string.onboarding3_videos_dot
        )
    )
}