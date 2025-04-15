package com.padmakar.jetpackcomposeintroduction.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

const val OTP_VIEW_TYPE_UNDERLINE = 1
const val OTP_VIEW_TYPE_BOX = 2

@Composable
fun OtpView(
    modifier: Modifier = Modifier,
    otpText: String,
    charColor: Color = Color.Black,
    charBackground: Color = Color.Transparent,
    charSize: TextUnit = 16.sp,
    strokeColor: Color = Color.Black,
    containerSize: Dp = charSize.value.dp * 2,
    otpCount: Int = 4,
    type: Int = OTP_VIEW_TYPE_UNDERLINE,
    enabled: Boolean = true,
    password: Boolean = false,
    passwordChar: String = "",
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
    onOtpTextChange: (String) -> Unit
) {
    BasicTextField(
        modifier = modifier,
        value = otpText,
        onValueChange = {
            if (it.length <= otpCount) {
                onOtpTextChange(it)
            }
        },
        enabled = enabled,
        keyboardOptions = keyboardOptions,
        decorationBox = {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                repeat(otpCount) { index ->
                    CharView(
                        index = index,
                        text = otpText,
                        charColor = charColor,
                        strokeColor = strokeColor,
                        charSize = charSize,
                        containerSize = containerSize,
                        type = type,
                        charBackground = charBackground,
                        password = password,
                        passwordChar = passwordChar,
                        isActive = index == otpText.length && otpText.length < otpCount // highlight current
                    )
                    if (index != otpCount - 1) {
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                }
            }
        }
    )
}

@Composable
private fun CharView(
    index: Int,
    text: String,
    charColor: Color,
    strokeColor: Color,
    charSize: TextUnit,
    containerSize: Dp,
    type: Int = OTP_VIEW_TYPE_UNDERLINE,
    charBackground: Color = Color.Transparent,
    password: Boolean = false,
    passwordChar: String = "",
    isActive: Boolean = false
) {
    val borderColor = if (isActive) strokeColor else strokeColor.copy(alpha = 0.3f)
    val cornerShape = androidx.compose.foundation.shape.RoundedCornerShape(4.dp)

    val modifier = Modifier
        .size(width = containerSize * 1.2f, height = containerSize * 1.4f)
        .let {
            if (type == OTP_VIEW_TYPE_BOX) {
                it
                    .border(1.dp, borderColor, shape = cornerShape)
                    .background(color = charBackground, shape = cornerShape)
            } else {
                it.background(charBackground)
            }
        }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.width(containerSize * 1.2f)
    ) {
        val char = when {
            index >= text.length -> ""
            password -> passwordChar
            else -> text[index].toString()
        }
        Text(
            text = char,
            color = charColor,
            modifier = modifier.wrapContentHeight(),
            fontSize = charSize,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleSmall
        )

        if (type == OTP_VIEW_TYPE_UNDERLINE) {
            Spacer(modifier = Modifier.height(2.dp))
            Box(
                modifier = Modifier
                    .background(borderColor)
                    .height(1.dp)
                    .width(containerSize * 1.2f)
            )
        }
    }
}

