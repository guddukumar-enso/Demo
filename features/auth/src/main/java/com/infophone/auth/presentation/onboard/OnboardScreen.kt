package com.infophone.auth.presentation.onboard

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.infophone.auth.R
import com.infophone.auth.presentation.onboard.ui.DotsIndicator
import com.infophone.auth.presentation.onboard.ui.OnBoardingImage
import com.infophone.auth.presentation.onboard.ui.OnBoardingTitleDis
import com.infophone.auth.presentation.viewModel.OnboardViewModel
import com.infophone.ui.common.CustomButton
import com.infophone.ui.common.SafeArea
import com.infophone.ui.common.StatusBarTheme
import com.infophone.ui.theme.Indigo900
import com.infophone.ui.theme.White
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardScreen(
    modifier: Modifier,
    onFinish: () -> Unit,
    viewModel: OnboardViewModel = OnboardViewModel()

){


    val pagerState = rememberPagerState(pageCount = { viewModel.onBoardingList.size })
    val coroutineScope = rememberCoroutineScope()

    StatusBarTheme(true)

    SafeArea(
        modifier = Modifier.fillMaxWidth().background(color = White).systemBarsPadding(),
        bottomBar = {
           Column(
               horizontalAlignment = Alignment.CenterHorizontally,
               modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),
               verticalArrangement = Arrangement.Center
           ) {
               Box(
               modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
               ){
                   DotsIndicator(totalDots = viewModel.onBoardingList.size, selectedIndex = pagerState.currentPage)
               }
               Spacer(Modifier.heightIn(24.dp))

               CustomButton(

                   onClick = {
                       val nextPage = pagerState.currentPage + 1
                       if (nextPage < pagerState.pageCount) {
                           coroutineScope.launch {
                               pagerState.animateScrollToPage(nextPage) // Scrolls to page index 5 with animation
                           }
                       } else {

                           onFinish()
                       }

                   },
                   textResId = R.string.next,
                   contentPadding = 4.dp,
                   modifier = Modifier
                       .width(width = 120.dp)
                       .height(47.dp).
                       clip(RoundedCornerShape(50.dp)),
                   colors = ButtonDefaults.buttonColors(
                       containerColor =  Indigo900,
                       contentColor =   White,

                       )
               )

           }
        }
    ){
        Column  (
            modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {


            HorizontalPager(state = pagerState) {
                    page ->
                val item = viewModel.onBoardingList[page]

                Column(
                    Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {

                    OnBoardingTitleDis(
                        title1 = item.title1,
                        title2 = item.title2,
                        title3 = item.title3,
                        title4 = item.title4,
                        description = item.description
                    )

                    Spacer(Modifier.height(height = 54.dp))
                    OnBoardingImage(img = item.image)
                }
            }


        }
    }



}

@Preview
@Composable
fun OnBoardingScreenPreview(){
    OnboardScreen(modifier= Modifier, onFinish ={})
}