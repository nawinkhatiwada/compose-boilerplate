package com.devfinity.composeboilerplate.ui.composables


import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.devfinity.composeboilerplate.ui.styling.theme.HorizontalSpacer2
import com.devfinity.composeboilerplate.ui.styling.theme.HorizontalSpacer24
import com.devfinity.composeboilerplate.utils.helper.NavigateBack

@Composable
fun AppToolbar(
    @DrawableRes leadingIconId: Int? = android.R.drawable.ic_menu_search,
    trailingContent: @Composable () -> Unit = {},
    title: String? = null,
    navigateBack: NavigateBack
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Red)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (leadingIconId == null) {
            HorizontalSpacer2()
        } else {
            Icon(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .clickable {
                        navigateBack()
                    },
                painter = painterResource(leadingIconId),
                contentDescription = "Back Button"
            )
        }
        HorizontalSpacer24()
        Text(text = "Dashboard")
        Spacer(modifier = Modifier.weight(1f))
        trailingContent()
    }
}